package featurecreep.api.bg.mc.config;

import featurecreep.api.FCLoaderObtainer;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.eventviewer.EventViewer;
import net.minecraft.client.KeyMapping;

/** Minecraft-owned EventViewer adapters; this class has no ukulib dependency. */
public final class FCMinecraftEvents {
    // Event name defined here in FeatureCreep; ukulib hardcodes the same string.
    private static final String CONFIG_RELOAD = "featurecreep.config.reload";
    private static boolean installed;

    private FCMinecraftEvents() {
    }

    public static synchronized void install() {
        if (installed) return;
        FCLoaderBasic loader = FCLoaderObtainer.getFCLoaderBasic(FCMinecraftEvents.class);
        if (loader == null) return;
        EventViewer viewer = loader.getEventViewer();
        if (viewer == null) return;
        // Event name defined here in FeatureCreep; ukulib hardcodes the same string.
        viewer.registerMonoFunctionListener("featurecreep.key_mapping.register",
                payload -> registerKey(payload));
        viewer.registerSupplierFunctionListener(CONFIG_RELOAD,
                () -> {
                    FCMinecraftConfig.reloadAll();
                    return null;
                });
        installed = true;
    }

    public static boolean registerKey(Object payload) {
        if (!(payload instanceof KeyMapping mapping)) {
            if (payload != null) {
                System.err.println("FeatureCreep ignored non-Minecraft key mapping event payload: " + payload);
            }
            return false;
        }
        return FCKeyBridge.register(mapping);
    }

    public static void publishConfigReload() {
        FCLoaderBasic loader = FCLoaderObtainer.getFCLoaderBasic(FCMinecraftEvents.class);
        if (loader == null || loader.getEventViewer() == null) {
            FCMinecraftConfig.reloadAll();
            return;
        }
        try {
            loader.getEventViewer().invokeEvent(CONFIG_RELOAD);
        } catch (Throwable failure) {
            System.err.println("FeatureCreep config reload event failed: " + failure);
            FCMinecraftConfig.reloadAll();
        }
    }
}
