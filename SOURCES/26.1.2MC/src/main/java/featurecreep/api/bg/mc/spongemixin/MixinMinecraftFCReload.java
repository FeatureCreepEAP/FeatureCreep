package featurecreep.api.bg.mc.spongemixin;

import java.util.concurrent.CompletableFuture;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import featurecreep.api.bg.mc.config.FCMinecraftEvents;
import net.minecraft.client.Minecraft;

/**
 * Runs FeatureCreep config reloaders after a client resource reload completes.
 *
 * <p>Hooks the public no-argument {@code reloadResourcePacks()}. The private overload
 * used for recovery is not hooked, so a recovery reload does not run config reloaders.
 */
@Mixin(Minecraft.class)
public abstract class MixinMinecraftFCReload {

    @Inject(method = "reloadResourcePacks()Ljava/util/concurrent/CompletableFuture;", at = @At("RETURN"),
            cancellable = true, remap = false)
    private void featurecreep$runConfigReloaders(CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        CompletableFuture<Void> reload = cir.getReturnValue();
        if (reload != null) {
            cir.setReturnValue(reload.thenRun(FCMinecraftEvents::publishConfigReload));
        }
    }
}
