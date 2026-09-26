package asbestosstar.bootstrap.minecraft;

/** Centralized host detection so startup, Mixin policy and installers agree. */
public final class MinecraftHostDetector {
    private MinecraftHostDetector() {}

    public static MinecraftHostKind detect() {
        String forced = System.getProperty("featurecreep.minecraft.host");
        if (forced != null && !forced.isBlank()) {
            try {
                return MinecraftHostKind.valueOf(forced.trim().toUpperCase());
            } catch (IllegalArgumentException ignored) {
                System.err.println("[FeatureCreep] Unknown forced Minecraft host: " + forced);
            }
        }

        // Sponge can coexist with Forge/NeoForge, so identify it before those hosts.
        if (classExists("org.spongepowered.api.Sponge") || classExists("org.spongepowered.plugin.PluginContainer")) {
            return MinecraftHostKind.SPONGE;
        }
        if (classExists("net.neoforged.fml.common.Mod") || classExists("net.neoforged.fml.loading.FMLLoader")) {
            return MinecraftHostKind.NEOFORGE;
        }
        if (classExists("net.minecraftforge.fml.common.Mod") || classExists("net.minecraftforge.fml.loading.FMLLoader")) {
            return MinecraftHostKind.FORGE;
        }
        if (classExists("net.fabricmc.loader.api.FabricLoader") || classExists("net.fabricmc.loader.impl.launch.knot.Knot")) {
            return MinecraftHostKind.FABRIC;
        }
        if (classExists("org.dimdev.riftloader.launch.RiftLoaderClientTweaker")
                || classExists("org.dimdev.riftloader.RiftLoader")) {
            return MinecraftHostKind.RIFT;
        }
        if (classExists("nilloader.NilAgent") || classExists("nilloader.api.ClassTransformer")) {
            return MinecraftHostKind.NIL;
        }
        return MinecraftHostKind.VANILLA;
    }

    static boolean classExists(String className) {
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        try {
            Class.forName(className, false, context != null ? context : MinecraftHostDetector.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException | LinkageError e) {
            return false;
        }
    }
}
