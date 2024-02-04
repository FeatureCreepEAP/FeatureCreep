package featurecreep.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.FeatureCreep;
import game.TitleScreen;

@Mixin(TitleScreen.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "func_231160_c_()V")
	private void init(CallbackInfo info) {
		FeatureCreep.LOGGER.info("This line is printed by an example mod mixin!");
		FeatureCreep.LOGGER.info("Boycott Modrinth");
	}
//TunaByte gets rid of buttons

}
