package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MinecraftInstallSupportTest {
    @TempDir Path temp;

    @Test
    void installedClientUsesStartupAgentAndCompleteMixinAsmRuntime() throws Exception {
        Path m2 = temp.resolve("m2");
        for (String rel : new String[] {
                "com/asbestosstar/featurecreep-loader/12/featurecreep-loader-12.jar",
                "com/asbestosstar/featurecreep-api/12/featurecreep-api-12.jar",
                "com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar",
                "com/asbestosstar/featurecreepmc-26.1.2/12/featurecreepmc-26.1.2-12.jar",
                "org/jboss/modules/jboss-modules/2.3.0/jboss-modules-2.3.0.jar",
                "org/spongepowered/mixin/0.8.7/mixin-0.8.7.jar",
                "org/ow2/asm/asm/9.5/asm-9.5.jar",
                "org/ow2/asm/asm-analysis/9.5/asm-analysis-9.5.jar",
                "org/ow2/asm/asm-commons/9.5/asm-commons-9.5.jar",
                "org/ow2/asm/asm-tree/9.5/asm-tree-9.5.jar",
                "org/ow2/asm/asm-util/9.5/asm-util-9.5.jar" }) {
            Path p = m2.resolve(rel);
            Files.createDirectories(p.getParent());
            Files.write(p, new byte[] { 1 });
        }

        Path mc = temp.resolve("minecraft");
        var result = MinecraftInstallSupport.installClient(mc, "26.1.2", new ArtifactResolver(m2));
        String json = Files.readString(result.metadata());

        assertTrue(json.contains("-javaagent:${library_directory}/com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar"));
        assertTrue(json.contains("-Dfeaturecreep.launch.managed=true"));
        assertTrue(json.contains("org.jboss.modules:jboss-modules:2.3.0"));
        assertTrue(Files.isRegularFile(mc.resolve("libraries/org/jboss/modules/jboss-modules/2.3.0/jboss-modules-2.3.0.jar")));
        assertTrue(json.contains("org.ow2.asm:asm:9.5"));
        assertTrue(json.contains("org.ow2.asm:asm-analysis:9.5"));
        assertTrue(json.contains("org.ow2.asm:asm-commons:9.5"));
        assertTrue(json.contains("org.ow2.asm:asm-tree:9.5"));
        assertTrue(json.contains("org.ow2.asm:asm-util:9.5"));
    }

    @Test
    void serverScriptsUseStartupAgentAndManagedLaunchFlag() throws Exception {
        Path m2 = temp.resolve("m2-server");
        for (String rel : new String[] {
                "com/asbestosstar/featurecreep-loader/12/featurecreep-loader-12.jar",
                "com/asbestosstar/featurecreep-api/12/featurecreep-api-12.jar",
                "com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar",
                "org/jboss/modules/jboss-modules/2.3.0/jboss-modules-2.3.0.jar",
                "org/spongepowered/mixin/0.8.7/mixin-0.8.7.jar",
                "org/ow2/asm/asm/9.5/asm-9.5.jar",
                "org/ow2/asm/asm-analysis/9.5/asm-analysis-9.5.jar",
                "org/ow2/asm/asm-commons/9.5/asm-commons-9.5.jar",
                "org/ow2/asm/asm-tree/9.5/asm-tree-9.5.jar",
                "org/ow2/asm/asm-util/9.5/asm-util-9.5.jar" }) {
            Path artifact = m2.resolve(rel);
            Files.createDirectories(artifact.getParent());
            Files.write(artifact, new byte[] { 1 });
        }

        // Avoid network access in this focused test by creating the expected server jar.
        Path server = temp.resolve("server");
        Files.createDirectories(server);
        Files.write(server.resolve("minecraft_server.26.1.2.jar"), new byte[] { 1 });

        // Test the generated launch metadata directly; MinecraftServerResolver is
        // integration-tested separately because it consults Mojang metadata.
        java.lang.reflect.Method method = MinecraftInstallSupport.class.getDeclaredMethod(
                "serverJson", String.class, String.class, java.util.List.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        String json = (String) method.invoke(null, "26.1.2", "minecraft_server.26.1.2.jar", java.util.List.of());
        assertTrue(json.contains("-javaagent:libraries/com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar"));
        assertTrue(json.contains("-Dfeaturecreep.launch.managed=true"));
    }
}
