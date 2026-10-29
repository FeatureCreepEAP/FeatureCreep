package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MinecraftInstallSupportTest {
    private static final List<String> RUNTIME_ARTIFACTS = List.of(
            "com/asbestosstar/featurecreep-loader/12/featurecreep-loader-12.jar",
            "com/asbestosstar/featurecreep-api/12/featurecreep-api-12.jar",
            "com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar",
            "org/jboss/modules/jboss-modules/2.3.0/jboss-modules-2.3.0.jar",
            "net/fabricmc/sponge-mixin/0.17.4+mixin.0.8.7/sponge-mixin-0.17.4+mixin.0.8.7.jar",
            "io/github/llamalad7/mixinextras-common/0.5.5/mixinextras-common-0.5.5.jar",
            "org/ow2/asm/asm/9.8/asm-9.8.jar",
            "org/ow2/asm/asm-analysis/9.8/asm-analysis-9.8.jar",
            "org/ow2/asm/asm-commons/9.8/asm-commons-9.8.jar",
            "org/ow2/asm/asm-tree/9.8/asm-tree-9.8.jar",
            "org/ow2/asm/asm-util/9.8/asm-util-9.8.jar");
    private static final String GAME_ARTIFACT = "com/asbestosstar/featurecreepmc-26.1.2/12/featurecreepmc-26.1.2-12.jar";

    @TempDir Path temp;

    @Test
    void installedClientUsesStartupAgentAndCompleteMixinAsmRuntime() throws Exception {
        Path m2 = temp.resolve("m2");
        for (String rel : RUNTIME_ARTIFACTS) write(m2.resolve(rel));
        write(m2.resolve(GAME_ARTIFACT));

        Path mc = temp.resolve("minecraft");
        var result = MinecraftInstallSupport.installClient(mc, "26.1.2", new ArtifactResolver(m2));
        String json = Files.readString(result.metadata());

        assertTrue(json.contains("-javaagent:${library_directory}/com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar"));
        assertTrue(json.contains("-Dfeaturecreep.launch.managed=true"));
        assertTrue(json.contains("org.jboss.modules:jboss-modules:2.3.0"));
        assertTrue(Files.isRegularFile(mc.resolve("libraries/org/jboss/modules/jboss-modules/2.3.0/jboss-modules-2.3.0.jar")));
        assertTrue(json.contains("org.ow2.asm:asm:9.8"));
        assertTrue(json.contains("org.ow2.asm:asm-analysis:9.8"));
        assertTrue(json.contains("org.ow2.asm:asm-commons:9.8"));
        assertTrue(json.contains("org.ow2.asm:asm-tree:9.8"));
        assertTrue(json.contains("org.ow2.asm:asm-util:9.8"));
    }

    @Test
    void installedServerIsLaunchableFromItsOwnDirectory() throws Exception {
        Path m2 = temp.resolve("m2-server");
        for (String rel : RUNTIME_ARTIFACTS) write(m2.resolve(rel));
        write(m2.resolve(GAME_ARTIFACT));

        // A pre-staged server jar makes MinecraftServerResolver return without touching Mojang.
        Path server = temp.resolve("server");
        write(server.resolve("minecraft_server.26.1.2.jar"));

        var result = MinecraftInstallSupport.installServer(server, "26.1.2", new ArtifactResolver(m2));

        String sh = Files.readString(server.resolve("run-featurecreep-server.sh"));
        String bat = Files.readString(server.resolve("run-featurecreep-server.bat"));
        assertTrue(sh.contains("cd \"$(dirname \"$0\")\" || exit 1"));
        assertTrue(bat.contains("cd /d \"%~dp0\""));
        assertTrue(sh.contains("-javaagent:libraries/com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar"));
        assertTrue(bat.contains("-javaagent:libraries\\com\\asbestosstar\\featurecreep-bootstrap\\12\\featurecreep-bootstrap-12.jar"));
        for (String script : List.of(sh, bat)) {
            assertTrue(script.contains("-Dfeaturecreep.launch.managed=true"));
            assertTrue(script.contains("asbestosstar.bootstrap.minecraft.VanillaMinecraftServerLauncher"));
            assertTrue(script.contains("minecraft_server.26.1.2.jar"));
        }
        assertTrue(sh.contains("libraries/" + GAME_ARTIFACT));
        assertTrue(bat.contains("libraries/" + GAME_ARTIFACT));
        assertTrue(Files.isRegularFile(server.resolve("libraries/" + GAME_ARTIFACT)));
        for (String rel : RUNTIME_ARTIFACTS) {
            assertTrue(Files.isRegularFile(server.resolve("libraries/" + rel)), rel);
        }
        String config = Files.readString(result.metadata());
        assertTrue(config.contains("\"minecraftServerJar\": \"minecraft_server.26.1.2.jar\""));
        assertTrue(config.contains("-Dfeaturecreep.launch.managed=true"));
        assertEquals(server.resolve("featurecreep-server.json"), result.metadata());
        assertTrue(Files.readString(server.resolve("eula.txt")).contains("eula=false"));
        assertTrue(Files.isDirectory(server.resolve("mods")));
    }

    private static void write(Path p) throws Exception {
        Files.createDirectories(p.getParent());
        Files.write(p, new byte[] { 1 });
    }
}
