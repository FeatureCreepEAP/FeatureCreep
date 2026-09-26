package asbestosstar.bootstrap;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class FeatureCreepMainTest {
    @Test
    void explicitFeatureCreepRuntimeSuppressesInstaller() {
        String old = System.getProperty("featurecreep.runtime");
        try {
            System.setProperty("featurecreep.runtime", "true");
            assertTrue(FeatureCreepMain.isFeatureCreepModuleRuntime());
        } finally {
            if (old == null) System.clearProperty("featurecreep.runtime"); else System.setProperty("featurecreep.runtime", old);
        }
    }
}
