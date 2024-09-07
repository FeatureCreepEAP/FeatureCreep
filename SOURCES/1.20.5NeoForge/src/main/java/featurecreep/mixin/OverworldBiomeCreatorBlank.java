package featurecreep.mixin;

import org.spongepowered.asm.mixin.Mixin;

import game.GameConfig;
import game.OverWorldBiomeCreator;
import game.ResourcePackManager;

@Mixin({ OverWorldBiomeCreator.class, ResourcePackManager.class,GameConfig.class })
public class OverworldBiomeCreatorBlank {
//Leave blank, Javassist is much easier to use, this only exists so the SpongeMixinPlugin puts this in the transformer, i plan to auto gen these eventually
}
