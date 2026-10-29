package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.config.FCMixinDiagnostics;
import net.minecraft.client.gui.screens.TitleScreen;

/** Diagnostic mixin proving that the FeatureCreep client config was applied. */
@Mixin(TitleScreen.class)
public abstract class MixinTitleScreenFCTest {

    @Inject(method = "init", at = @At("RETURN"), remap = false)
    private void featurecreep$markMixinApplied(CallbackInfo ci) {
        FCMixinDiagnostics.markTitleScreenTestMixinApplied();
    }
}
