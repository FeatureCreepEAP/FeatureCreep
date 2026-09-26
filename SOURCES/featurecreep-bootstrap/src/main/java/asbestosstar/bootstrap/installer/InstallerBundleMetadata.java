package asbestosstar.bootstrap.installer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import asbestosstar.bootstrap.FeatureCreepMain;

/** Reads target metadata stamped into version-specific all-in-one FeatureCreep JARs. */
final class InstallerBundleMetadata {
    private InstallerBundleMetadata() {}

    static String fixedMinecraftVersion() {
        String override = System.getProperty("featurecreep.installer.fixedMinecraftVersion");
        if (override != null && !override.isBlank()) return override.trim();
        Path self = currentJar();
        return self == null ? null : fixedMinecraftVersion(self);
    }

    /** Returns the running version-specific Minecraft bundle when this really is one. */
    static Path currentMinecraftBundle(String expectedVersion) {
        Path self = currentJar();
        if (self == null) return null;
        String actual = fixedMinecraftVersion(self);
        return expectedVersion != null && expectedVersion.equals(actual) ? self : null;
    }

    private static Path currentJar() {
        try {
            Path self = Path.of(FeatureCreepMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return Files.isRegularFile(self) ? self.toAbsolutePath().normalize() : null;
        } catch (Exception ignored) {
            return null;
        }
    }

    static String fixedMinecraftVersion(Path codeSource) {
        if (codeSource == null || !Files.isRegularFile(codeSource)) return null;
        try (JarFile jar = new JarFile(codeSource.toFile())) {
            Manifest manifest = jar.getManifest();
            if (manifest == null) return null;
            String bundle = manifest.getMainAttributes().getValue("FeatureCreep-Bundle");
            String version = manifest.getMainAttributes().getValue("FeatureCreep-Target-Version");
            if (!"minecraft".equalsIgnoreCase(bundle) || version == null || version.isBlank()) return null;
            version = version.trim();
            return MinecraftInstallSupport.SUPPORTED_VERSIONS.contains(version) ? version : null;
        } catch (IOException ignored) {
            return null;
        }
    }
}
