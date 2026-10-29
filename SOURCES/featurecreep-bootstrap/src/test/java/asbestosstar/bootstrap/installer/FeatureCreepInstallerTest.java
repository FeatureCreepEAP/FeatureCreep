package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class FeatureCreepInstallerTest {
    private static final Path CURRENT = Path.of("").toAbsolutePath();

    @Test
    void absentFlagDoesNotRequestServerInstall() {
        assertNull(FeatureCreepInstaller.serverInstallTarget(new String[] {}));
        assertNull(FeatureCreepInstaller.serverInstallTarget(new String[] { "--other" }));
    }

    @Test
    void bareFlagUsesCurrentDirectory() {
        assertEquals(CURRENT, FeatureCreepInstaller.serverInstallTarget(new String[] { "--install-server" }));
    }

    @Test
    void flagFollowedByOptionUsesCurrentDirectory() {
        assertEquals(CURRENT, FeatureCreepInstaller.serverInstallTarget(new String[] { "--install-server", "--other" }));
    }

    @Test
    void flagWithSeparateValueUsesThatDirectory() {
        assertEquals(Path.of("servers", "fc"), FeatureCreepInstaller.serverInstallTarget(new String[] { "--install-server", "servers/fc" }));
    }

    @Test
    void flagWithEqualsValueUsesThatDirectory() {
        assertEquals(Path.of("servers", "fc"), FeatureCreepInstaller.serverInstallTarget(new String[] { "--install-server=servers/fc" }));
    }

    @Test
    void emptyEqualsValueUsesCurrentDirectory() {
        assertEquals(CURRENT, FeatureCreepInstaller.serverInstallTarget(new String[] { "--install-server=" }));
    }
}
