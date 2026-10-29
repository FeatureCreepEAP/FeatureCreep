package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.mc.config.FCKeyBridge;
import featurecreep.api.bg.mc.config.FCKeyMappingAccess;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;

/**
 * Appends FeatureCreep key mappings to {@code Options.keyMappings}.
 *
 * <p>The field is {@code public final}, so it is made mutable here and replaced with
 * a merged copy. Merging runs before {@link Options#load()} reads the saved
 * {@code key_<name>} values, so saved bindings for FeatureCreep keys are honoured.
 */
@Mixin(Options.class)
public abstract class MixinOptionsKeyBridge implements FCKeyMappingAccess {

    @Shadow
    @Mutable
    public KeyMapping[] keyMappings;

    @Inject(method = "load", at = @At("HEAD"), remap = false)
    private void featurecreep$mergeKeyMappingsBeforeLoad(CallbackInfo ci) {
        this.keyMappings = FCKeyBridge.merge(this.keyMappings);
    }

    @Override
    @Unique
    public void featurecreep$applyPendingKeyMappings() {
        this.keyMappings = FCKeyBridge.merge(this.keyMappings);
    }
}
