package asbestosstar.bootstrap.minecraft;

/** Main class for FeatureCreep-managed vanilla dedicated servers. */
public final class VanillaMinecraftServerLauncher {
    private VanillaMinecraftServerLauncher() {}

    public static void main(String[] args) throws Exception {
        System.setProperty("featurecreep.minecraft.host", "vanilla");
        MinecraftCommonStartup.bootstrap();
        MinecraftCommonStartup.start();
        VanillaMinecraftLauncher.invokeMain("net.minecraft.server.Main", args);
    }
}
