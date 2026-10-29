package asbestosstar.bootstrap.sm.mixinhost;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.spongepowered.asm.service.IGlobalPropertyService;
import org.spongepowered.asm.service.IPropertyKey;

class StandaloneMixinServiceTest {
    @Test
    void serviceExposesProviders() {
        StandaloneMixinService service = new StandaloneMixinService();
        assertEquals("FeatureCreepStandalone", service.getName());
        service.prepare();
        assertNotNull(service.getClassProvider());
        assertNotNull(service.getBytecodeProvider());
        assertNotNull(service.getPrimaryContainer());
        assertEquals(1, service.getPlatformAgents().size());
    }

    @Test
    void classProviderFindsApplicationClasses() throws Exception {
        StandaloneMixinService service = new StandaloneMixinService();
        service.prepare();
        assertSame(String.class, service.getClassProvider().findClass("java.lang.String"));
        assertSame(String.class, service.getClassProvider().findClass("java.lang.String", false));
        assertSame(String.class, service.getClassProvider().findAgentClass("java.lang.String", false));
        assertThrows(ClassNotFoundException.class, () -> service.getClassProvider().findClass("no.such.Class"));
    }

    @Test
    void bytecodeProviderReadsClassNodes() throws Exception {
        StandaloneMixinService service = new StandaloneMixinService();
        service.prepare();
        // Read a class from the Mixin jar itself (major 52): ASM 9.5 cannot parse
        // Java 25 (major 69) class files, so JDK classes are off limits here even
        // though Minecraft classes (<= Java 21) are fine at runtime.
        var node = service.getBytecodeProvider().getClassNode(
                "org.spongepowered.asm.service.IClassProvider", false, 0);
        assertEquals("org/spongepowered/asm/service/IClassProvider", node.name);
        assertThrows(ClassNotFoundException.class,
                () -> service.getBytecodeProvider().getClassNode("no.such.Class", false, 0));
    }

    @Test
    void blackboardRoundTripsValues() {
        IGlobalPropertyService board = new FeatureCreepGlobalPropertyService(false);
        IPropertyKey key = board.resolveKey("test.key");
        assertNull(board.getProperty(key));
        board.setProperty(key, "value");
        assertEquals("value", board.getProperty(key));
        assertEquals("value", board.getProperty(key, "default"));
        assertEquals("fallback", board.getProperty(board.resolveKey("missing"), "fallback"));
        assertEquals("value", board.getPropertyString(key, "default"));
        assertEquals("default", board.getPropertyString(board.resolveKey("missing"), "default"));

        IPropertyKey numeric = board.resolveKey("test.numeric");
        board.setProperty(numeric, 42);
        assertEquals("42", board.getPropertyString(numeric, "0"));
    }

    @Test
    void providersDeferToHostWhenStandaloneIsInactive() {
        // No standalone runtime installed in this JVM, so the public entry points
        // must refuse to claim the environment.
        assertThrows(IllegalStateException.class, FeatureCreepGlobalPropertyService::new);
        assertFalse(new StandaloneMixinService().isValid());
    }
}
