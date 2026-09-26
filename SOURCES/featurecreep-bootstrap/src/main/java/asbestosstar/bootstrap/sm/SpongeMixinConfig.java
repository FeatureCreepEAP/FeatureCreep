package asbestosstar.bootstrap.sm;

import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.objectweb.asm.tree.ClassNode;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/**
 * Legacy compatibility Mixin plugin.
 *
 * <p>Current superloader metadata advertises {@code featurecreepimpl.mixins.json}
 * directly. If an older integration still instantiates this plugin, it only
 * performs an idempotent registration of that same implementation config.</p>
 *
 * <p>This class never bootstraps or downloads Sponge Mixin; it assumes a Mixin
 * runtime is already active.</p>
 */
public final class SpongeMixinConfig implements IMixinConfigPlugin {
    public static final String IMPLEMENTATION_CONFIG = "featurecreepimpl.mixins.json";
    private static final AtomicBoolean IMPLEMENTATION_REGISTERED = new AtomicBoolean();

    static {
       // registerImplementationConfigIfPresent();
    }

    /**
     * Register the version-specific FeatureCreep implementation config once it is
     * visible to the active Mixin service/superloader.
     *
     * @return true when the config is already registered or was registered now
     */
    public static boolean registerImplementationConfigIfPresent() {
        if (IMPLEMENTATION_REGISTERED.get()) {
            return true;
        }
        if (!resourceVisible(IMPLEMENTATION_CONFIG)) {
            System.out.println("[FeatureCreep] Bootstrap Mixin plugin is active; "
                    + IMPLEMENTATION_CONFIG + " is not visible yet.");
            return false;
        }
        try {
            // Mixin itself de-duplicates configuration names, so this remains safe
            // even when SuperInjection also advertised the implementation config.
            Mixins.addConfiguration(IMPLEMENTATION_CONFIG);
            IMPLEMENTATION_REGISTERED.set(true);
            System.out.println("[FeatureCreep] Registered internal Mixin config " + IMPLEMENTATION_CONFIG);
            return true;
        } catch (Throwable t) {
            System.err.println("[FeatureCreep] Failed to register internal Mixin config "
                    + IMPLEMENTATION_CONFIG + ": " + t);
            return false;
        }
    }

    private static boolean resourceVisible(String name) {
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        if (resource(context, name) != null) return true;

        ClassLoader own = SpongeMixinConfig.class.getClassLoader();
        if (resource(own, name) != null) return true;

        ClassLoader system = ClassLoader.getSystemClassLoader();
        return resource(system, name) != null;
    }

    private static URL resource(ClassLoader loader, String name) {
        return loader == null ? ClassLoader.getSystemResource(name) : loader.getResource(name);
    }

    @Override
    public void onLoad(String mixinPackage) {
        // Retry after Mixin has fully constructed the config/plugin context. This
        // covers hosts where the implementation JAR becomes visible slightly later.
        //registerImplementationConfigIfPresent();
    }

    @Override
    public String getRefMapperConfig() {
registerImplementationConfigIfPresent();
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // FeatureCreep's bootstrap plugin does not filter target classes.
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass,
            String mixinClassName, IMixinInfo mixinInfo) {
        // No pre-apply mutation; registration is the only responsibility here.
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass,
            String mixinClassName, IMixinInfo mixinInfo) {
        // No post-apply mutation; registration is the only responsibility here.
    }
}
