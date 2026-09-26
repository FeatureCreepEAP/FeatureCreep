package asbestosstar.bootstrap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import featurecreep.attach.Attach;

class BootstrapAttachPathTest {
    @Test
    void directFeatureCreepAttachApiIsOnBootstrapClasspath() {
        assertNotNull(Attach.class);
    }
}
