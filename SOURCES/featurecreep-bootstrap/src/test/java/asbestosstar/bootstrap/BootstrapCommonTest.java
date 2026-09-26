package asbestosstar.bootstrap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BootstrapCommonTest {

    @Test
    void classExistsDoesNotInitializeOrAttachAnything() {
        assertTrue(BootstrapCommon.classExists("java.lang.String"));
        assertFalse(BootstrapCommon.classExists("featurecreep.test.DoesNotExist"));
    }
}
