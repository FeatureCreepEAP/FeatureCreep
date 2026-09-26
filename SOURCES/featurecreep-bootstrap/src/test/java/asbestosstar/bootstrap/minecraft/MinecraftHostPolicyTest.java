package asbestosstar.bootstrap.minecraft;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MinecraftHostPolicyTest {
    @Test
    void establishedSuperloadersOwnTheirMixinRuntime() {
        assertTrue(MinecraftHostKind.FABRIC.hostOwnsMixin());
        assertTrue(MinecraftHostKind.FORGE.hostOwnsMixin());
        assertTrue(MinecraftHostKind.NEOFORGE.hostOwnsMixin());
        assertTrue(MinecraftHostKind.SPONGE.hostOwnsMixin());
    }

    @Test
    void riftNilAndVanillaDoNotClaimHostOwnedMixin() {
        assertFalse(MinecraftHostKind.RIFT.hostOwnsMixin());
        assertFalse(MinecraftHostKind.NIL.hostOwnsMixin());
        assertFalse(MinecraftHostKind.VANILLA.hostOwnsMixin());
        assertTrue(MinecraftHostKind.RIFT.hostHasTransformerPipeline());
        assertTrue(MinecraftHostKind.NIL.hostHasTransformerPipeline());
        assertFalse(MinecraftHostKind.VANILLA.hostHasTransformerPipeline());
    }
}
