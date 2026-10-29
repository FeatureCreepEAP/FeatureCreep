package asbestosstar.bootstrap.sm.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Locates the complete standalone Mixin runtime for vanilla/Rift/Nil launches.
 *
 * <p>This class never touches Maven. Resolution is strictly local, in order:
 * the runtime jars the FeatureCreep installer already staged into the launcher
 * library directory (derived from this jar's own location), then jars bundled
 * inside the running bootstrap jar itself. Only the installer downloads anything,
 * and even it prefers jars bundled inside the installer jar over remote Maven.
 * If neither source has the runtime, the launch fails with a clear instruction
 * to run the installer.</p>
 */
public final class StandaloneMixinResolver {
    private StandaloneMixinResolver() {}

    static final String MIXIN_VERSION = "0.17.4+mixin.0.8.7";
    static final String ASM_VERSION = "9.8";

    /** Paths below the launcher library directory, matching the installed layout. */
    private static final List<String> RUNTIME_JARS = List.of(
            "net/fabricmc/sponge-mixin/" + MIXIN_VERSION + "/sponge-mixin-" + MIXIN_VERSION + ".jar",
            "org/ow2/asm/asm/" + ASM_VERSION + "/asm-" + ASM_VERSION + ".jar",
            "org/ow2/asm/asm-analysis/" + ASM_VERSION + "/asm-analysis-" + ASM_VERSION + ".jar",
            "org/ow2/asm/asm-commons/" + ASM_VERSION + "/asm-commons-" + ASM_VERSION + ".jar",
            "org/ow2/asm/asm-tree/" + ASM_VERSION + "/asm-tree-" + ASM_VERSION + ".jar",
            "org/ow2/asm/asm-util/" + ASM_VERSION + "/asm-util-" + ASM_VERSION + ".jar");

    /** Optional: enables MixinExtras expression injection points when staged alongside the runtime. */
    static final String MIXINEXTRAS_JAR = "io/github/llamalad7/mixinextras-common/0.5.5/mixinextras-common-0.5.5.jar";

    private static Path localRepositoryDir;

    /** Overrides the library root lookup (tests). */
    static void setLocalRepositoryDir(Path dir) {
        localRepositoryDir = dir;
    }

    /**
     * Makes the standalone runtime available to {@link SpongeMixinUtils}, using only
     * local sources. Never downloads anything.
     */
    public static boolean ensureAvailable() {
        if (SpongeMixinUtils.isStandaloneRuntimeActive()) return true;

        List<Path> jars = findInstalledRuntimeJars();
        if (jars == null) jars = extractBundledRuntimeJars();
        if (jars == null && fetchMissingFromProfile()) jars = findInstalledRuntimeJars();
        if (jars == null) {
            Path root = localRepositoryDir != null ? localRepositoryDir : launcherLibraryRoot();
            System.err.println("[FeatureCreep] Standalone Mixin runtime is not installed under "
                    + root + ". Run the FeatureCreep installer so the runtime jars are staged into the launcher library directory.");
            return false;
        }

        try {
            SpongeMixinUtils.installRuntimeJars(jars);
            return true;
        } catch (Exception e) {
            System.err.println("[FeatureCreep] Failed to install standalone Mixin runtime: " + e);
            return false;
        }
    }

    /**
     * The runtime jars staged by the installer under {@code ${library_directory}}.
     * Returns null unless every required jar is present.
     */
    static List<Path> findInstalledRuntimeJars() {
        Path root = localRepositoryDir != null ? localRepositoryDir : launcherLibraryRoot();
        if (root == null) return null;
        List<Path> jars = new ArrayList<>(RUNTIME_JARS.size());
        for (String rel : RUNTIME_JARS) {
            Path jar = root.resolve(rel);
            if (!Files.isRegularFile(jar)) return null;
            jars.add(jar);
        }
        Path mixinExtras = root.resolve(MIXINEXTRAS_JAR);
        if (Files.isRegularFile(mixinExtras)) jars.add(mixinExtras);
        return jars;
    }

    /**
     * Finds {@code ${library_directory}} without any network access, in order:
     * the {@code libraryDirectory} system property launchers already pass
     * (the vanilla launcher and TLauncher both set it), the working directory
     * when it already contains the installed layout, and finally this jar's own
     * installed location. The code source is consulted last because alternative
     * launchers may load bootstrap classes from a jar that does not sit inside
     * the libraries layout at all (for example a fat game bundle in mods/).
     */
    static Path launcherLibraryRoot() {
        String property = System.getProperty("libraryDirectory");
        if (property != null && !property.isBlank()) {
            Path root = Path.of(property);
            if (isLibraryRoot(root)) return root;
        }
        Path workingDir = Path.of(System.getProperty("user.dir", "."));
        Path sibling = workingDir.resolve("libraries");
        if (isLibraryRoot(sibling)) return sibling;
        return libraryRootFromCodeSource();
    }

    private static boolean isLibraryRoot(Path root) {
        return Files.isDirectory(root) && Files.isDirectory(root.resolve("com/asbestosstar"));
    }

    private static Path libraryRootFromCodeSource() {
        Path jar = bootstrapJar();
        return jar == null ? null : libraryRootOf(jar);
    }

    /** Maven layout: libraries/com/asbestosstar/featurecreep-bootstrap/&lt;version&gt;/&lt;jar&gt; */
    private static final int BOOTSTRAP_JAR_TO_LIBRARY_ROOT_DEPTH = 5;

    static Path libraryRootOf(Path jar) {
        Path root = jar;
        for (int i = 0; i < BOOTSTRAP_JAR_TO_LIBRARY_ROOT_DEPTH && root != null; i++) {
            root = root.getParent();
        }
        return root != null && isLibraryRoot(root) ? root : null;
    }

    /** Runtime jars embedded in the running bootstrap jar, extracted to a local cache. */
    static List<Path> extractBundledRuntimeJars() {
        Path jar = bootstrapJar();
        if (jar == null) return null;
        Path dir = extractionCacheDir();
        List<Path> jars = new ArrayList<>(RUNTIME_JARS.size());
        try (JarFile jf = new JarFile(jar.toFile())) {
            for (String rel : RUNTIME_JARS) {
                String name = rel.substring(rel.lastIndexOf('/') + 1);
                JarEntry entry = jf.getJarEntry("META-INF/featurecreep/artifacts/" + name);
                if (entry == null) return null;
                Path dest = dir.resolve(name);
                try (InputStream in = jf.getInputStream(entry)) {
                    Files.createDirectories(dir);
                    Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                }
                jars.add(dest);
            }
        } catch (IOException e) {
            return null;
        }
        return jars;
    }

    /**
     * Recovers missing runtime jars using the FeatureCreep client profile written by
     * the installer: each runtime library entry carries its upstream repository URL,
     * so the jar can be fetched directly from there. This is launcher-manifest
     * resolution, not Maven: no local repository, no remote metadata, only URLs the
     * installer already recorded.
     */
    static boolean fetchMissingFromProfile() {
        Path root = localRepositoryDir != null ? localRepositoryDir : launcherLibraryRoot();
        if (root == null) return false;
        Path versions = root.resolveSibling("versions");
        if (!Files.isDirectory(versions)) return false;

        List<Path> profiles = new ArrayList<>();
        try (var stream = Files.list(versions)) {
            for (Path dir : stream.filter(Files::isDirectory).toList()) {
                try (var inner = Files.list(dir)) {
                    inner.filter(p -> p.getFileName().toString().endsWith(".json")).forEach(profiles::add);
                }
            }
        } catch (IOException e) {
            return false;
        }

        for (String rel : RUNTIME_JARS) {
            Path dest = root.resolve(rel);
            if (Files.isRegularFile(dest)) continue;
            String url = findLibraryUrl(profiles, coordinateOf(rel));
            if (url == null) continue;
            System.out.println("[FeatureCreep] Fetching missing runtime jar from profile URL: " + url);
            try {
                Files.createDirectories(dest.getParent());
                try (InputStream in = java.net.URI.create(url).toURL().openStream()) {
                    Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                System.err.println("[FeatureCreep] Failed to fetch " + rel + ": " + e);
            }
        }
        return findInstalledRuntimeJars() != null;
    }

    /** {@code net/fabricmc/sponge-mixin/v/a-v.jar} -> {@code net.fabricmc:sponge-mixin:v} */
    static String coordinateOf(String rel) {
        String[] p = rel.split("/");
        StringBuilder group = new StringBuilder();
        for (int i = 0; i < p.length - 3; i++) {
            if (i > 0) group.append('.');
            group.append(p[i]);
        }
        return group + ":" + p[p.length - 3] + ":" + p[p.length - 2];
    }

    private static String findLibraryUrl(List<Path> profiles, String coordinate) {
        String needle = "\"name\":\"" + coordinate + "\"";
        for (Path profile : profiles) {
            try {
                String text = Files.readString(profile);
                int name = text.indexOf(needle);
                if (name < 0) continue;
                int url = text.indexOf("\"url\":\"", name);
                if (url < 0 || url > name + 2000) continue;
                int end = text.indexOf('"', url + 7);
                if (end < 0) continue;
                return text.substring(url + 7, end);
            } catch (IOException ignored) {
            }
        }
        return null;
    }

    private static Path bootstrapJar() {
        URL self = SpongeMixinUtils.selfCodeSource();
        if (self == null || !"file".equals(self.getProtocol())) return null;
        try {
            Path jar = Path.of(self.toURI());
            return Files.isRegularFile(jar) ? jar : null;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static Path extractionCacheDir() {
        String home = System.getProperty("user.home", ".");
        return Path.of(home).resolve(".featurecreep").resolve("cache").resolve("jars");
    }
}
