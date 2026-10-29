package asbestosstar.bootstrap.sm.util;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StandaloneMixinResolverTest {
    private static final List<String> RUNTIME_JARS = List.of(
            "net/fabricmc/sponge-mixin/0.17.4+mixin.0.8.7/sponge-mixin-0.17.4+mixin.0.8.7.jar",
            "org/ow2/asm/asm/9.8/asm-9.8.jar",
            "org/ow2/asm/asm-analysis/9.8/asm-analysis-9.8.jar",
            "org/ow2/asm/asm-commons/9.8/asm-commons-9.8.jar",
            "org/ow2/asm/asm-tree/9.8/asm-tree-9.8.jar",
            "org/ow2/asm/asm-util/9.8/asm-util-9.8.jar");

    @TempDir Path temp;

    @AfterEach
    void reset() {
        StandaloneMixinResolver.setLocalRepositoryDir(null);
    }

    @Test
    void installedRuntimeIsAcceptedOnlyWhenComplete() throws Exception {
        // Incomplete staging must be rejected.
        for (int i = 0; i < RUNTIME_JARS.size() - 1; i++) {
            Path p = temp.resolve(RUNTIME_JARS.get(i));
            Files.createDirectories(p.getParent());
            Files.write(p, new byte[] { 1 });
        }
        StandaloneMixinResolver.setLocalRepositoryDir(temp);
        assertNull(StandaloneMixinResolver.findInstalledRuntimeJars());

        // Complete staging is accepted.
        Path last = temp.resolve(RUNTIME_JARS.get(RUNTIME_JARS.size() - 1));
        Files.createDirectories(last.getParent());
        Files.write(last, new byte[] { 1 });
        assertEquals(RUNTIME_JARS.size(), StandaloneMixinResolver.findInstalledRuntimeJars().size());
    }

    @Test
    void libraryRootIsNotDerivedFromUninstalledLayouts() {
        // Tests run from a classes directory, which must not be misread as an install.
        assertNull(StandaloneMixinResolver.launcherLibraryRoot());
    }

    @Test
    void libraryRootIsResolvedFromBootstrapJarInMavenLayout() throws Exception {
        Path libraries = temp.resolve("libraries");
        Files.createDirectories(libraries.resolve("com/asbestosstar"));
        Path jar = libraries.resolve("com/asbestosstar/featurecreep-bootstrap/12/featurecreep-bootstrap-12.jar");
        Files.createDirectories(jar.getParent());
        Files.write(jar, new byte[] { 1 });

        assertEquals(libraries, StandaloneMixinResolver.libraryRootOf(jar));
    }

    @Test
    void libraryRootOfRejectsJarOutsideLibraryLayout() throws Exception {
        Path jar = temp.resolve("mods/featurecreep-bootstrap-12.jar");
        Files.createDirectories(jar.getParent());
        Files.write(jar, new byte[] { 1 });

        assertNull(StandaloneMixinResolver.libraryRootOf(jar));
    }
}
