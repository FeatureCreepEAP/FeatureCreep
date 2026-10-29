package featurecreep.api.bg.mc.spongemixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class FeatureCreepMixinPlugin implements IMixinConfigPlugin {

    private static final Set<String> BLOCK_STATE_HOOKS = Set.of(
            "featurecreep.api.bg.mc.spongemixin.MixinMappedRegistry",
            "featurecreep.api.bg.mc.spongemixin.MixinBlocks");

    private static final String[] REGISTRY_OWNING_HOSTS = {
            "org.spongepowered.api.Sponge",
            "net.neoforged.fml.loading.FMLLoader",
            "net.minecraftforge.fml.loading.FMLLoader",
            "net.fabricmc.loader.api.FabricLoader",
    };

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return !BLOCK_STATE_HOOKS.contains(mixinClassName) || !hostOwnsBlockStateRegistration();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean hostOwnsBlockStateRegistration() {
        for (String marker : REGISTRY_OWNING_HOSTS) {
            if (classExists(marker)) {
                return true;
            }
        }
        return false;
    }

    private static boolean classExists(String className) {
        ClassLoader context = Thread.currentThread().getContextClassLoader();
        try {
            Class.forName(className, false, context != null ? context : FeatureCreepMixinPlugin.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException | LinkageError e) {
            return false;
        }
    }
}
