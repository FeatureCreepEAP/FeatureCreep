package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.FeatureCreepMC;
import net.minecraft.core.registries.BuiltInRegistries;

@Mixin(BuiltInRegistries.class)
public abstract class MixinBuiltInRegistries {

    // Adding remap = false prevents the processor from looking for a refmap
    @Inject(
        method = "bootStrap",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/registries/BuiltInRegistries;createContents()V",
            shift = At.Shift.AFTER,
            remap = false // Also required for the target reference
        ),
        remap = false
    )
    private static void afterCreateContents(CallbackInfo ci) {
        FeatureCreepMC.init();
    }
}