package featurecreep.api.bg.mc.spongemixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import featurecreep.api.bg.blocks.FCBlockStateCaches;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

@Mixin(MappedRegistry.class)
public abstract class MixinMappedRegistry {

    @Inject(method = "register", at = @At("RETURN"), remap = false)
    private void featurecreep$initBlockStateCaches(ResourceKey<?> key, Object value, RegistrationInfo info, CallbackInfoReturnable<?> cir) {
        if ((Object) this == BuiltInRegistries.BLOCK && value instanceof Block block) {
            FCBlockStateCaches.onBlockAdded(block);
        }
    }
}
