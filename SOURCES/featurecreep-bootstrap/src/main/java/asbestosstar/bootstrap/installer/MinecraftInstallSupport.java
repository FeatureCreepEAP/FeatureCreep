package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

/** Writes launcher/TLauncher-compatible client profiles and dedicated server layouts. */
public final class MinecraftInstallSupport {
    public static final String FC_VERSION = "12.3";
    public static final String MIXIN_VERSION = "0.17.4+mixin.0.8.7";
    public static final String ASM_VERSION = "9.8";
    public static final String JBOSS_MODULES_VERSION = "2.3.0";
    public static final String MIXINEXTRAS_VERSION = "0.5.5";
    public static final List<String> SUPPORTED_VERSIONS = List.of("26.1.2", "26.2", "26.3", "26.4");

    private static final ArtifactCoordinate LOADER = new ArtifactCoordinate("com.asbestosstar", "featurecreep-loader", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate API = new ArtifactCoordinate("com.asbestosstar", "featurecreep-api", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate BOOTSTRAP = new ArtifactCoordinate("com.asbestosstar", "featurecreep-bootstrap", FC_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate MIXIN = new ArtifactCoordinate("net.fabricmc", "sponge-mixin", MIXIN_VERSION, ArtifactResolver.FABRIC);
    private static final ArtifactCoordinate JBOSS_MODULES = new ArtifactCoordinate("org.jboss.modules", "jboss-modules", JBOSS_MODULES_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate MIXINEXTRAS = new ArtifactCoordinate("io.github.llamalad7", "mixinextras-common", MIXINEXTRAS_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM = new ArtifactCoordinate("org.ow2.asm", "asm", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_ANALYSIS = new ArtifactCoordinate("org.ow2.asm", "asm-analysis", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_COMMONS = new ArtifactCoordinate("org.ow2.asm", "asm-commons", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_TREE = new ArtifactCoordinate("org.ow2.asm", "asm-tree", ASM_VERSION, ArtifactResolver.CENTRAL);
    private static final ArtifactCoordinate ASM_UTIL = new ArtifactCoordinate("org.ow2.asm", "asm-util", ASM_VERSION, ArtifactResolver.CENTRAL);

    /*
     * Minecraft version JSONs are not Maven builds: launchers do not resolve the
     * transitive dependency graph for us. The FeatureCreep bootstrap directly uses
     * JBoss Modules, and the Fabric sponge-mixin fork 0.17.4+mixin.0.8.7 is built against ASM 9.8, so every standalone/
     * vanilla install must list JBoss Modules and the ASM modules explicitly.
     */
    private static final List<ArtifactCoordinate> VANILLA_RUNTIME = List.of(
            LOADER, API, BOOTSTRAP, JBOSS_MODULES, MIXIN, MIXINEXTRAS,
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
        Path libraries = root.resolve("libraries");
        Files.createDirectories(libraries);
        List<Resolved> artifacts = new ArrayList<>(resolveInto(libraries, resolver, VANILLA_RUNTIME));
        Resolved game = installGameJar(libraries, mcVersion, resolver);
        artifacts.add(game);

        String profileId = "FeatureCreep-" + mcVersion + "-" + FC_VERSION;
        Path versionDir = root.resolve("versions").resolve(profileId);
        Files.createDirectories(versionDir);
        Path json = versionDir.resolve(profileId + ".json");
        Files.writeString(json, clientJson(profileId, mcVersion, artifacts), StandardCharsets.UTF_8);

        Path profileJar = versionDir.resolve(profileId + ".jar");
        Files.copy(game.path(), profileJar, StandardCopyOption.REPLACE_EXISTING);

        List<Path> installed = new ArrayList<>(artifacts.stream().map(Resolved::path).toList());
        installed.add(profileJar);
        return new InstallResult(profileId, json, List.copyOf(installed));
    }

    public static InstallResult installServer(Path serverDir, String mcVersion, ArtifactResolver resolver) throws Exception {
        validateVersion(mcVersion);
        Path root = serverDir.toAbsolutePath().normalize();
        Files.createDirectories(root.resolve("libraries"));
        List<Resolved> artifacts = new ArrayList<>(resolveInto(root.resolve("libraries"), resolver, VANILLA_RUNTIME));
        artifacts.add(installGameJar(root.resolve("libraries"), mcVersion, resolver));
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

    private static List<Resolved> resolveInto(Path libraries, ArtifactResolver resolver, List<ArtifactCoordinate> coordinates) throws Exception {
        List<Resolved> out = new ArrayList<>();
        for (ArtifactCoordinate c : coordinates) {
            Path source = resolver.resolve(c);
            Path target = libraries.resolve(c.relativePath());
            Files.createDirectories(target.getParent());
            if (!source.equals(target)) Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            out.add(new Resolved(c, target));
        }
        return out;
    }

    private static String clientJson(String id, String mcVersion, List<Resolved> artifacts) throws Exception {
        StringBuilder libs = new StringBuilder();
        for (int i = 0; i < artifacts.size(); i++) {
            if (i > 0) libs.append(",\n");
            libs.append(libraryJson(artifacts.get(i)));
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

    private static String libraryJson(Resolved r) throws Exception {
        ArtifactCoordinate c = r.coordinate();
        byte[] bytes = Files.readAllBytes(r.path());
        String sha1 = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-1").digest(bytes));
        return "    {\"name\":\"" + escape(c.notation()) + "\",\"downloads\":{\"artifact\":{"
                + "\"path\":\"" + escape(c.relativePath()) + "\","
                + "\"url\":\"" + escape(ArtifactResolver.urlOf(c)) + "\","
                + "\"sha1\":\"" + sha1 + "\","
                + "\"size\":" + bytes.length + "}}}";
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
                "  \"featureCreepVersion\": \"" + FC_VERSION + "\",\n" +
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
                "#!/bin/sh\ncd \"$(dirname \"$0\")\" || exit 1\nexec java '-javaagent:" + agentUnix + "' -Dfeaturecreep.minecraft.host=vanilla -Dfeaturecreep.launch.managed=true -cp '" + cpUnix + "' asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher \"$@\"\n",
                StandardCharsets.UTF_8);
        root.resolve("run-featurecreep-server.sh").toFile().setExecutable(true, false);
        Files.writeString(root.resolve("run-featurecreep-server.bat"),
                "@echo off\r\ncd /d \"%~dp0\"\r\njava \"-javaagent:" + agentWin + "\" -Dfeaturecreep.minecraft.host=vanilla -Dfeaturecreep.launch.managed=true -cp \"" + cpWin + "\" asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher %*\r\n",
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

    private static Resolved installGameJar(Path libraries, String mcVersion, ArtifactResolver resolver) throws Exception {
        ArtifactCoordinate game = gameCoordinate(mcVersion);
        Path source = resolveGameJar(mcVersion, resolver);
        Path target = libraries.resolve(game.relativePath());
        Files.createDirectories(target.getParent());
        if (!source.equals(target)) Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        return new Resolved(game, target);
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
