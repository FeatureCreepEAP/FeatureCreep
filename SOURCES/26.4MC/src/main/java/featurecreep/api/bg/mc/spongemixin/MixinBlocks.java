package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.api.bg.blocks.FCBlockStateCaches;
import net.minecraft.world.level.block.Blocks;

@Mixin(Blocks.class)
public abstract class MixinBlocks {

    @Inject(method = "<clinit>", at = @At("TAIL"), remap = false)
    private static void featurecreep$vanillaBlocksRegistered(CallbackInfo ci) {
        FCBlockStateCaches.vanillaBlocksRegistered();
    }
}
