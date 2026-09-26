package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ArtifactResolverTest {
    @TempDir java.nio.file.Path temp;

    @Test
    void embeddedCurrentJarArtifactWinsOverLocalMavenCopy() throws Exception {
        ArtifactCoordinate coordinate = new ArtifactCoordinate("test.group", "resolver-test", "1", ArtifactResolver.CENTRAL);
        var stale = temp.resolve(coordinate.relativePath());
        Files.createDirectories(stale.getParent());
        Files.writeString(stale, "stale-local-m2\n", StandardCharsets.UTF_8);

        var result = new ArtifactResolver(temp).resolve(coordinate);
        assertEquals("embedded-current-jar\n", Files.readString(result, StandardCharsets.UTF_8));
    }
    @Test
    void onlyActualBootstrapJarMaySelfResolveAsBootstrap() throws Exception {
        var bootstrap = temp.resolve("featurecreep-bootstrap-12.jar");
        writeJarWithArtifactId(bootstrap, "featurecreep-bootstrap");
        assertTrue(ArtifactResolver.isBootstrapArtifact(bootstrap));

        var minecraftBundle = temp.resolve("featurecreepmc-26.1.2-12-jar-with-dependencies.jar");
        writeJarWithArtifactId(minecraftBundle, "featurecreepmc-26.1.2");
        assertFalse(ArtifactResolver.isBootstrapArtifact(minecraftBundle));
    }

    private static void writeJarWithArtifactId(java.nio.file.Path jar, String artifactId) throws Exception {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().putValue("FeatureCreep-Artifact-Id", artifactId);
        try (JarOutputStream out = new JarOutputStream(Files.newOutputStream(jar), manifest)) {
            // Manifest-only test JAR.
        }
    }

}
