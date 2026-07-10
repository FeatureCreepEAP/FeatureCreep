package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.FeatureCreepMC;
import net.minecraft.core.registries.BuiltInRegistries;

@Mixin(BuiltInRegistries.class)
public abstract class MixinBuiltInRegistries {

    @Inject(
        method = "bootStrap",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/registries/BuiltInRegistries;freeze()V",
            shift = At.Shift.BEFORE,
            remap = false
        ),
        remap = false
    )
    private static void featurecreep$beforeFreeze(CallbackInfo ci) {
        FeatureCreepMC.init();
    }
}