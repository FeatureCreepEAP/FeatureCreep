package asbestosstar.bootstrap.minecraft;

import java.io.File;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.modules.ModuleFinder;

import asbestosstar.bootstrap.BootstrapCommon;
import featurecreep.loader.ExecutionSide;
import featurecreep.loader.GameProvider;

public class MinecraftGameProvider implements GameProvider {

    private static final Pattern VERSION_ID = Pattern.compile("\"id\"\\s*:\\s*\"([^\"]+)\"");

    public static final Set<String> packages_needed = new HashSet<String>();
    private static final String[] GAME_ANCHORS = {
            "net.minecraft.client.Minecraft",
            "net.minecraft.server.MinecraftServer",
            "net.minecraft.server.Main"
    };

    static {
        // These paths are host-first: a mod must never define a second copy of the
        // running game, launcher, Mixin, or FeatureCreep API classes.
        packages_needed.add("net/minecraft");
        packages_needed.add("com/mojang");
        packages_needed.add("it/unimi/dsi/fastutil");
        packages_needed.add("featurecreep/loader");
        packages_needed.add("featurecreep/api");
        packages_needed.add("asbestosstar/bootstrap");
        packages_needed.add("org/spongepowered/asm");
        packages_needed.add("net/fabricmc");
        packages_needed.add("net/minecraftforge");
        packages_needed.add("net/neoforged");
        packages_needed.add("cpw/mods");

        // Other host libraries are intentionally not forced host-first. They remain
        // reachable through the fallback loader, allowing a mod to use a private
        // version of Gson/Guava/etc. when it bundles one.
    }

    public static boolean debugmode = Boolean.getBoolean("featurecreep.debug");
    public static Instrumentation instrumentation = BootstrapCommon.instrument;

    @Override public boolean getDebugMode() { return debugmode; }
    @Override public boolean setDebugMode(boolean val) { debugmode = val; return debugmode; }

    @Override
    public Path[] getModulePKZipLocations() {
        Path modsDir = getGameWorkingDirectory().resolve("mods").normalize();
        return new Path[] { modsDir };
    }

    @Override public Path[] getClassPathPKZipLocations() { return new Path[0]; }
    @Override public Instrumentation getInstrumentation() { return instrumentation; }
    @Override public Instrumentation setInstrumentation(Instrumentation instrument) { instrumentation = instrument; return instrumentation; }
    @Override public Set<String> getNeededPackages() { return packages_needed; }
    @Override public void addNeededPackage(String pack) { if (pack != null) packages_needed.add(pack); }

    @Override
    public List<String> getAvoidedModSuffixes() {
        List<String> set = new ArrayList<String>();
        set.add(".nil.jar");
        set.add(".nil");
        set.add(".deactivation");
        set.add(".disabled");
        return set;
    }

    @Override
    public ClassLoader getGameClassLoader() {
        // Ask actual Minecraft classes who defined them. This is more reliable than
        // ClassLoader.getSystemClassLoader() under Knot/ModLauncher/NeoForge.
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        for (String anchor : GAME_ANCHORS) {
            Class<?> clazz = findClass(anchor, context);
            if (clazz != null && clazz.getClassLoader() != null) {
                return clazz.getClassLoader();
            }
        }
        return context != null ? context : getClass().getClassLoader();
    }

    @Override
    public Path getGameLaunchPath() {
        ClassLoader gameLoader = getGameClassLoader();
        for (String anchor : GAME_ANCHORS) {
            Class<?> clazz = findClass(anchor, gameLoader);
            Path path = codeSourcePath(clazz);
            if (path != null) {
                return path;
            }
        }
        return codeSourcePath(getClass()) != null ? codeSourcePath(getClass()) : getGameWorkingDirectory();
    }

    /** Location of FeatureCreep's own bootstrap JAR/classes, useful to providers. */
    public Path getFeatureCreepLaunchPath() {
        Path path = codeSourcePath(getClass());
        return path != null ? path : getGameWorkingDirectory();
    }

    /** Stable identifier of the running game. Vanilla Minecraft reports {@code minecraft}. */
    public String getGameId() {
        return "minecraft";
    }

    /**
     * Version of the running game. The {@code featurecreep.game.version} system property wins;
     * otherwise the {@code id} from the game JAR's {@code version.json} is used.
     */
    public String getGameVersion() {
        String override = System.getProperty("featurecreep.game.version");
        if (override != null && !override.isBlank()) return override.trim();
        try {
            InputStream in = null;
            ClassLoader context = Thread.currentThread().getContextClassLoader();
            if (context != null) {
                in = context.getResourceAsStream("version.json");
            }
            if (in == null) {
                ClassLoader own = MinecraftGameProvider.class.getClassLoader();
                if (own != null) {
                    in = own.getResourceAsStream("version.json");
                }
            }
            if (in == null) return MinecraftGameIdentity.UNKNOWN;
            try (InputStream version = in) {
                return parseVersionId(new String(version.readAllBytes(), StandardCharsets.UTF_8));
            }
        } catch (Throwable ignored) {
            return MinecraftGameIdentity.UNKNOWN;
        }
    }

    /**
     * Stable identifier of the superloader hosting the game. Vanilla reports {@code vanilla};
     * superloader providers override this.
     */
    public String getLoaderId() {
        return "vanilla";
    }

    /**
     * Version of the hosting superloader. Defaults to the {@code featurecreep.loader.version}
     * system property; providers may override this with the host's own version API.
     */
    public String getLoaderVersion() {
        String override = System.getProperty("featurecreep.loader.version");
        if (override != null && !override.isBlank()) return override.trim();
        String version = GameProvider.super.getLoaderVersion();
        return version == null ? MinecraftGameIdentity.UNKNOWN : version;
    }

    /** Version of the FeatureCreep bootstrap, read from Maven metadata packaged in the bootstrap JAR. */
    public String getFeatureCreepVersion() {
        try (InputStream in = MinecraftGameProvider.class.getResourceAsStream(
                "/META-INF/maven/com.asbestosstar/featurecreep-bootstrap/pom.properties")) {
            if (in == null) return MinecraftGameIdentity.UNKNOWN;
            Properties props = new Properties();
            props.load(in);
            return props.getProperty("version", MinecraftGameIdentity.UNKNOWN);
        } catch (Throwable ignored) {
            return MinecraftGameIdentity.UNKNOWN;
        }
    }

    /** Snapshot of all identity metadata. Built from the overridable accessors above. */
    public MinecraftGameIdentity getGameIdentity() {
        return new MinecraftGameIdentity(
                getGameId(),
                getGameVersion(),
                getLoaderId(),
                getLoaderVersion(),
                getFeatureCreepVersion());
    }

    /** Extracts the top-level {@code "id"} value from a Minecraft {@code version.json} document. */
    static String parseVersionId(String json) {
        if (json == null) return MinecraftGameIdentity.UNKNOWN;
        Matcher matcher = VERSION_ID.matcher(json);
        return matcher.find() ? matcher.group(1) : MinecraftGameIdentity.UNKNOWN;
    }

    private static Class<?> findClass(String name, ClassLoader preferred) {
        if (preferred != null) {
            try {
                return Class.forName(name, false, preferred);
            } catch (ClassNotFoundException | LinkageError ignored) {
            }
        }
        try {
            return Class.forName(name, false, MinecraftGameProvider.class.getClassLoader());
        } catch (ClassNotFoundException | LinkageError ignored) {
            return null;
        }
    }

    private static Path codeSourcePath(Class<?> clazz) {
        if (clazz == null) return null;
        try {
            CodeSource source = clazz.getProtectionDomain().getCodeSource();
            if (source == null) return null;
            URL location = source.getLocation();
            if (location == null) return null;
            String raw = location.toExternalForm();
            if (raw.startsWith("union:")) raw = "file:" + raw.substring("union:".length());
            if (raw.startsWith("jar:")) raw = raw.substring(4);
            int bang = raw.indexOf("!/");
            if (bang >= 0) raw = raw.substring(0, bang);
            URI uri = URI.create(raw);
            if (!"file".equalsIgnoreCase(uri.getScheme())) return null;
            return Paths.get(uri).toAbsolutePath().normalize();
        } catch (Throwable ignored) {
            return null;
        }
    }

    @Override
    public ExecutionSide getExecutionSide() {
        ClassLoader cl = getGameClassLoader();
        boolean clientPresent = cl != null && cl.getResource("net/minecraft/client/Minecraft.class") != null;
        return clientPresent ? ExecutionSide.CLIENT : ExecutionSide.SERVER;
    }

    @Override public List<ModuleFinder> getDefaultModuleFinders() { return new ArrayList<ModuleFinder>(); }
    @Override public boolean isSuperLoaderModZip(File zip) { return isFeatureCreepPlatformJar(zip); }

    /**
     * FeatureCreep's own platform jars are supplied by the launcher. A copy left in mods/
     * must not be loaded as a module, or it would shadow the launcher's classes.
     */
    public static boolean isFeatureCreepPlatformJar(File file) {
        return file != null && file.getName().toLowerCase(Locale.ROOT).startsWith("featurecreep");
    }
    @Override public boolean isSuperLoaderModFolder(File folder) { return false; }
}
