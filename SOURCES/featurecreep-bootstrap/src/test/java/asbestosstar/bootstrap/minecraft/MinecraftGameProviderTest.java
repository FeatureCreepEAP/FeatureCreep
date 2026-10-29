package asbestosstar.bootstrap.minecraft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MinecraftGameProviderTest {

    @AfterEach
    void clearOverrides() {
        System.clearProperty("featurecreep.game.version");
        System.clearProperty("featurecreep.loader.version");
    }

    @Test
    void vanillaIdentityDefaultsToMinecraftOnVanillaLoader() {
        MinecraftGameProvider provider = new MinecraftGameProvider();
        assertEquals("minecraft", provider.getGameId());
        assertEquals("vanilla", provider.getLoaderId());
        MinecraftGameIdentity identity = provider.getGameIdentity();
        assertEquals("minecraft", identity.gameId());
        assertEquals("vanilla", identity.loaderId());
        assertNotNull(identity.featureCreepVersion());
        assertTrue(!identity.featureCreepVersion().isBlank());
    }

    @Test
    void superloaderProvidersReportTheirLoaderId() {
        assertEquals("fabricloader", new FabricGameProvider().getLoaderId());
        assertEquals("forge", new MCForgeGameProvider().getLoaderId());
        assertEquals("neoforge", new MedusaGameProvider().getLoaderId());
        assertEquals("sponge", new SpongePoweredGameProvider().getLoaderId());
        assertEquals("rift", new RiftGameProvider().getLoaderId());
        assertEquals("nilloader", new NilGameProvider().getLoaderId());
        assertEquals("minecraft", new FabricGameProvider().getGameId());
    }

    @Test
    void systemPropertiesOverrideGameAndLoaderVersions() {
        System.setProperty("featurecreep.game.version", " 26.3 ");
        System.setProperty("featurecreep.loader.version", "0.19.0");
        MinecraftGameProvider provider = new MinecraftGameProvider();
        assertEquals("26.3", provider.getGameVersion());
        assertEquals("0.19.0", provider.getLoaderVersion());
        MinecraftGameIdentity identity = provider.getGameIdentity();
        assertEquals("26.3", identity.gameVersion());
        assertEquals("0.19.0", identity.loaderVersion());
    }

    @Test
    void parsesTopLevelVersionIdFromVersionJson() {
        assertEquals("26.3", MinecraftGameProvider.parseVersionId("{\n  \"id\" : \"26.3\",\n  \"type\": \"release\"\n}"));
        assertEquals(MinecraftGameIdentity.UNKNOWN, MinecraftGameProvider.parseVersionId("{\"type\":\"release\"}"));
        assertEquals(MinecraftGameIdentity.UNKNOWN, MinecraftGameProvider.parseVersionId(null));
    }

    @Test
    void identityRecordReportsUnknownForBlankValues() {
        MinecraftGameIdentity identity = new MinecraftGameIdentity(" ", null, "forge", "", "12");
        assertEquals(MinecraftGameIdentity.UNKNOWN, identity.gameId());
        assertEquals(MinecraftGameIdentity.UNKNOWN, identity.gameVersion());
        assertEquals("forge", identity.loaderId());
        assertEquals(MinecraftGameIdentity.UNKNOWN, identity.loaderVersion());
        assertEquals("12", identity.featureCreepVersion());
    }

    @Test
    void exposesExpectedHostFirstPackages() {
        MinecraftGameProvider provider = new MinecraftGameProvider();
        assertTrue(provider.getNeededPackages().contains("net/minecraft"));
        assertTrue(provider.getNeededPackages().contains("com/mojang"));
        assertTrue(provider.getNeededPackages().contains("org/spongepowered/asm"));
        assertTrue(provider.getNeededPackages().contains("featurecreep/loader"));
    }

    @Test
    void derivesModsDirectoryFromGameWorkingDirectory() {
        MinecraftGameProvider provider = new MinecraftGameProvider();
        Path expected = provider.getGameWorkingDirectory().resolve("mods").normalize();
        assertTrue(expected.equals(provider.getModulePKZipLocations()[0]));
    }

    @Test
    void launchAndClassLoaderInformationIsAvailable() {
        MinecraftGameProvider provider = new MinecraftGameProvider();
        assertNotNull(provider.getGameClassLoader());
        assertNotNull(provider.getGameLaunchPath());
        List<ClassLoader> hosts = provider.getHostClassLoaders();
        assertTrue(hosts.contains(provider.getGameClassLoader()));
    }
}
