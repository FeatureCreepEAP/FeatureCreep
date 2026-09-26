package asbestosstar.bootstrap.minecraft;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class MinecraftGameProviderTest {

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
