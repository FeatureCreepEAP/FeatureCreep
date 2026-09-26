package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class InstallerBundleMetadataTest {
    @TempDir Path temp;

    @Test
    void minecraftAllInOneJarLocksInstallerToStampedVersion() throws Exception {
        Path jar = temp.resolve("featurecreepmc-26.1.2-12-jar-with-dependencies.jar");
        Manifest mf = new Manifest();
        mf.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        mf.getMainAttributes().putValue("FeatureCreep-Bundle", "minecraft");
        mf.getMainAttributes().putValue("FeatureCreep-Target-Version", "26.1.2");
        try (OutputStream out = Files.newOutputStream(jar); JarOutputStream ignored = new JarOutputStream(out, mf)) {}
        assertEquals("26.1.2", InstallerBundleMetadata.fixedMinecraftVersion(jar));
    }

    @Test
    void genericBootstrapHasNoFixedGameVersion() throws Exception {
        Path jar = temp.resolve("featurecreep-bootstrap-12.jar");
        Manifest mf = new Manifest();
        mf.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        mf.getMainAttributes().putValue("FeatureCreep-Artifact-Id", "featurecreep-bootstrap");
        try (OutputStream out = Files.newOutputStream(jar); JarOutputStream ignored = new JarOutputStream(out, mf)) {}
        assertNull(InstallerBundleMetadata.fixedMinecraftVersion(jar));
    }
}
