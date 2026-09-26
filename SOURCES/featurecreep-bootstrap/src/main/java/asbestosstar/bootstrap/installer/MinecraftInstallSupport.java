package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/** Writes launcher/TLauncher-compatible client profiles and dedicated server layouts. */
public final class MinecraftInstallSupport {
    public static final String FC_VERSION = "12";
    public static final String MIXIN_VERSION = "0.8.7";
    public static final String ASM_VERSION = "9.5";
    public static final List<String> SUPPORTED_VERSIONS = List.of("26.1.2", "26.2", "26.3", "26.4");

    private static final ArtifactCoordinate LOADER = new ArtifactCoordinate("com.asbestosstar", "featurecreep-loader", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate API = new ArtifactCoordinate("com.asbestosstar", "featurecreep-api", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate BOOTSTRAP = new ArtifactCoordinate("com.asbestosstar", "featurecreep-bootstrap", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate MIXIN = new ArtifactCoordinate("org.spongepowered", "mixin", MIXIN_VERSION, ArtifactResolver.SPONGE);
    private static final ArtifactCoordinate ASM = new ArtifactCoordinate("org.ow2.asm", "asm", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_ANALYSIS = new ArtifactCoordinate("org.ow2.asm", "asm-analysis", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_COMMONS = new ArtifactCoordinate("org.ow2.asm", "asm-commons", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_TREE = new ArtifactCoordinate("org.ow2.asm", "asm-tree", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_UTIL = new ArtifactCoordinate("org.ow2.asm", "asm-util", ASM_VERSION, ArtifactResolver.CENTRAL);

    /*
     * Minecraft version JSONs are not Maven builds: launchers do not resolve the
     * transitive dependency graph for us. Mixin 0.8.7 is built against ASM 9.5,
     * so every standalone/vanilla install must list the ASM modules explicitly.
     */
    private static final List<ArtifactCoordinate> VANILLA_RUNTIME = List.of(
            LOADER, API, BOOTSTRAP, MIXIN,
            ASM, ASM_ANALYSIS, ASM_COMMONS, ASM_TREE, ASM_UTIL);

    private MinecraftInstallSupport() {}

    public static Path defaultMinecraftDirectory() {
        String home = System.getProperty("user.home");
        String os = System.getProperty("os.name", "").toLowerCase();
        if (os.contains("win")) {
            String appData = System.getenv("APPDATA");
            return Path.of(appData != null && !appData.isBlank() ? appData : home, ".minecraft");
        }
        if (os.contains("mac")) return Path.of(home, "Library", "Application Support", "minecraft");
        return Path.of(home, ".minecraft");
    }

    public static InstallResult installClient(Path minecraftDir, String mcVersion, ArtifactResolver resolver) throws Exception {
        validateVersion(mcVersion);
        Path root = minecraftDir.toAbsolutePath().normalize();
        Files.createDirectories(root.resolve("libraries"));
        List<Resolved> artifacts = resolveIntoMinecraftLibraries(root, resolver);

        String profileId = "FeatureCreep-" + mcVersion + "-12";
        Path versionDir = root.resolve("versions").resolve(profileId);
        Files.createDirectories(versionDir);
        Path json = versionDir.resolve(profileId + ".json");
        Files.writeString(json, clientJson(profileId, mcVersion, artifacts), StandardCharsets.UTF_8);

        // The child version JAR is the version-specific FeatureCreep Minecraft
        // implementation. When the installer itself is a stamped all-in-one JAR,
        // use that exact JAR and never ask the user to select another game version.
        // A generic bootstrap installer resolves the matching thin game artifact.
        Path gameJar = resolveGameJar(mcVersion, resolver);
        Path profileJar = versionDir.resolve(profileId + ".jar");
        if (!gameJar.equals(profileJar)) Files.copy(gameJar, profileJar, StandardCopyOption.REPLACE_EXISTING);

        List<Path> installed = new ArrayList<>(artifacts.stream().map(Resolved::path).toList());
        installed.add(profileJar);
        return new InstallResult(profileId, json, List.copyOf(installed));
    }

    public static InstallResult installServer(Path serverDir, String mcVersion, ArtifactResolver resolver) throws Exception {
        validateVersion(mcVersion);
        Path root = serverDir.toAbsolutePath().normalize();
        Files.createDirectories(root.resolve("libraries"));
        List<Resolved> artifacts = new ArrayList<>(resolveIntoServerLibraries(root, resolver));
        ArtifactCoordinate gameCoordinate = gameCoordinate(mcVersion);
        Path gameSource = resolveGameJar(mcVersion, resolver);
        Path gameTarget = root.resolve("libraries").resolve(gameCoordinate.relativePath());
        Files.createDirectories(gameTarget.getParent());
        if (!gameSource.equals(gameTarget)) Files.copy(gameSource, gameTarget, StandardCopyOption.REPLACE_EXISTING);
        artifacts.add(new Resolved(gameCoordinate, gameTarget));
        Path config = root.resolve("featurecreep-server.json");
        String serverJar = "minecraft_server." + mcVersion + ".jar";
        new MinecraftServerResolver().ensureServerJar(root, mcVersion, serverJar);
        Files.writeString(config, serverJson(mcVersion, serverJar, artifacts), StandardCharsets.UTF_8);
        writeServerScripts(root, serverJar, artifacts);
        Files.createDirectories(root.resolve("mods"));
        Path eula = root.resolve("eula.txt");
        if (!Files.exists(eula)) Files.writeString(eula, "# Read https://aka.ms/MinecraftEULA before changing this value\neula=false\n", StandardCharsets.UTF_8);
        return new InstallResult("FeatureCreep Server " + mcVersion, config, artifacts.stream().map(Resolved::path).toList());
    }

    private static List<Resolved> resolveIntoMinecraftLibraries(Path root, ArtifactResolver resolver) throws Exception {
        return resolveInto(root.resolve("libraries"), resolver);
    }

    private static List<Resolved> resolveIntoServerLibraries(Path root, ArtifactResolver resolver) throws Exception {
        return resolveInto(root.resolve("libraries"), resolver);
    }

    private static List<Resolved> resolveInto(Path libraries, ArtifactResolver resolver) throws Exception {
        List<Resolved> out = new ArrayList<>();
        for (ArtifactCoordinate c : VANILLA_RUNTIME) {
            Path source = resolver.resolve(c);
            Path target = libraries.resolve(c.relativePath());
            Files.createDirectories(target.getParent());
            if (!source.equals(target)) Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            out.add(new Resolved(c, target));
        }
        return out;
    }

    private static String clientJson(String id, String mcVersion, List<Resolved> artifacts) {
        StringBuilder libs = new StringBuilder();
        for (int i = 0; i < artifacts.size(); i++) {
            ArtifactCoordinate c = artifacts.get(i).coordinate();
            if (i > 0) libs.append(",\n");
            libs.append("    {\"name\":\"").append(escape(c.notation())).append("\"");
            if (c.repository() != null) libs.append(",\"url\":\"").append(escape(c.repository())).append("\"");
            libs.append("}");
        }
        String agent = "-javaagent:${library_directory}/" + BOOTSTRAP.relativePath().replace('\\', '/');
        return "{\n" +
                "  \"id\": \"" + escape(id) + "\",\n" +
                "  \"inheritsFrom\": \"" + escape(mcVersion) + "\",\n" +
                "  \"type\": \"release\",\n" +
                "  \"time\": \"" + Instant.now() + "\",\n" +
                "  \"releaseTime\": \"" + Instant.now() + "\",\n" +
                "  \"mainClass\": \"asbestosstar.bootstrap.minecraft.VanillaMinecraftLauncher\",\n" +
                "  \"arguments\": {\"jvm\":[\"-Dfeaturecreep.minecraft.host=vanilla\",\"-Dfeaturecreep.launch.managed=true\",\"" + escape(agent) + "\"],\"game\":[]},\n" +
                "  \"libraries\": [\n" + libs + "\n  ]\n" +
                "}\n";
    }

    private static String serverJson(String mcVersion, String serverJar, List<Resolved> artifacts) {
        StringBuilder cp = new StringBuilder();
        for (Resolved a : artifacts) {
            if (!cp.isEmpty()) cp.append(System.getProperty("path.separator"));
            cp.append("libraries/").append(a.coordinate().relativePath().replace('\\', '/'));
        }
        String agent = "-javaagent:libraries/" + BOOTSTRAP.relativePath().replace('\\', '/');
        return "{\n" +
                "  \"format\": 2,\n" +
                "  \"minecraftVersion\": \"" + escape(mcVersion) + "\",\n" +
                "  \"minecraftServerJar\": \"" + escape(serverJar) + "\",\n" +
                "  \"featureCreepVersion\": \"12\",\n" +
                "  \"mainClass\": \"asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher\",\n" +
                "  \"jvmArgs\": [\"-Dfeaturecreep.minecraft.host=vanilla\",\"-Dfeaturecreep.launch.managed=true\",\"" + escape(agent) + "\"],\n" +
                "  \"libraryClasspath\": \"" + escape(cp.toString()) + "\"\n" +
                "}\n";
    }

    private static void writeServerScripts(Path root, String serverJar, List<Resolved> artifacts) throws IOException {
        String sepUnix = ":";
        String sepWin = ";";
        String cpUnix = classpath(artifacts, sepUnix) + sepUnix + serverJar;
        String cpWin = classpath(artifacts, sepWin) + sepWin + serverJar;
        String agentUnix = "libraries/" + BOOTSTRAP.relativePath().replace('\\', '/');
        String agentWin = agentUnix.replace('/', '\\');
        Files.writeString(root.resolve("run-featurecreep-server.sh"),
                "#!/bin/sh\nexec java '-javaagent:" + agentUnix + "' -Dfeaturecreep.minecraft.host=vanilla -Dfeaturecreep.launch.managed=true -cp '" + cpUnix + "' asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher \"$@\"\n",
                StandardCharsets.UTF_8);
        root.resolve("run-featurecreep-server.sh").toFile().setExecutable(true, false);
        Files.writeString(root.resolve("run-featurecreep-server.bat"),
                "@echo off\r\njava \"-javaagent:" + agentWin + "\" -Dfeaturecreep.minecraft.host=vanilla -Dfeaturecreep.launch.managed=true -cp \"" + cpWin + "\" asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher %*\r\n",
                StandardCharsets.UTF_8);
    }

    private static String classpath(List<Resolved> artifacts, String sep) {
        StringBuilder cp = new StringBuilder();
        for (Resolved a : artifacts) {
            if (!cp.isEmpty()) cp.append(sep);
            cp.append("libraries/").append(a.coordinate().relativePath().replace('\\', '/'));
        }
        return cp.toString();
    }

    private static ArtifactCoordinate gameCoordinate(String mcVersion) {
        return new ArtifactCoordinate("com.asbestosstar", "featurecreepmc-" + mcVersion, FC_VERSION, ArtifactResolver.CENTRAL);
    }

    private static Path resolveGameJar(String mcVersion, ArtifactResolver resolver) throws Exception {
        Path currentBundle = InstallerBundleMetadata.currentMinecraftBundle(mcVersion);
        if (currentBundle != null) return currentBundle;
        return resolver.resolve(gameCoordinate(mcVersion));
    }

    private static void validateVersion(String version) {
        if (!SUPPORTED_VERSIONS.contains(version)) throw new IllegalArgumentException("Unsupported Minecraft version: " + version);
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public record Resolved(ArtifactCoordinate coordinate, Path path) {}
    public record InstallResult(String name, Path metadata, List<Path> artifacts) {}
}
