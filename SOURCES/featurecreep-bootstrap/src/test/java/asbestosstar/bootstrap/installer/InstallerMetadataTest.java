package asbestosstar.bootstrap.installer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import asbestosstar.bootstrap.sm.util.SpongeMixinUtils;

class InstallerMetadataTest {
    private static final String IMPLEMENTATION_MIXIN = "featurecreepimpl.mixins.json";

    @Test
    void superloadersAdvertiseImplementationMixinAndFabricPreLaunchEntrypoint() throws Exception {
        String fabric = resource("/fabric.mod.json");
        String forge = resource("/META-INF/mods.toml");
        String neo = resource("/META-INF/neoforge.mods.toml");

        assertTrue(fabric.contains("\"mixins\""));
        assertTrue(fabric.contains(IMPLEMENTATION_MIXIN));
        assertTrue(fabric.contains("\"entrypoints\""));
        assertTrue(fabric.contains("\"preLaunch\""));
        assertTrue(fabric.contains("asbestosstar.bootstrap.minecraft.FabricBootstrapEntrypoint"));

        assertTrue(forge.contains("[[mixins]]"));
        assertTrue(forge.contains(IMPLEMENTATION_MIXIN));
        assertTrue(neo.contains("[[mixins]]"));
        assertTrue(neo.contains(IMPLEMENTATION_MIXIN));

    }

    @Test
    void runtimeUsesImplementationMixinConfigDirectly() {
        assertTrue(SpongeMixinUtils.IMPLEMENTATION_CONFIG.equals(IMPLEMENTATION_MIXIN));
        assertTrue(SpongeMixinUtils.BOOTSTRAP_CONFIG.equals(IMPLEMENTATION_MIXIN));
    }

    @Test
    void spongeMetadataIsPresent() throws Exception {
        String sponge = resource("/META-INF/sponge_plugins.json");
        assertTrue(sponge.contains("\"featurecreep\""));
        assertTrue(sponge.contains("SpongeBootstrapPlugin"));
    }

    private static String resource(String name) throws Exception {
        try (var in = InstallerMetadataTest.class.getResourceAsStream(name)) {
            assertNotNull(in, name);
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
