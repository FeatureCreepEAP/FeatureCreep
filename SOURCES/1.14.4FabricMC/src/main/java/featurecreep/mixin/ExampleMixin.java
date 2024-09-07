package featurecreep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.FeatureCreepMCInit;
import featurecreep.api.bg.blocks.FCBlockAPI;
import game.BlockPropertiesData;
import game.TitleScreen;

@Mixin(TitleScreen.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		FeatureCreepMCInit.LOGGER.info("This line is printed by an example mod mixin!");
		FeatureCreepMCInit.LOGGER.info("Boycott Modrinth");
	}
//TunaByte gets rid of buttons
	
	
}
