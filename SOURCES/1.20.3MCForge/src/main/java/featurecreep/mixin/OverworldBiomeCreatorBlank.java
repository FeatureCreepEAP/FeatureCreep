package featurecreep.mixin;

import org.spongepowered.asm.mixin.Mixin;

import game.OverWorldBiomeCreator;
import game.ResourcePackManager;
import obf.class_unknown_840;

@Mixin({ OverWorldBiomeCreator.class, ResourcePackManager.class, class_unknown_840.class })
public class OverworldBiomeCreatorBlank {
//Leave blank, Javassist is much easier to use, this only exists so the SpongeMixinPlugin puts this in the transformer, i plan to auto gen these eventually
}
