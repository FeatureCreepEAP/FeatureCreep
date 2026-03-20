package featurecreep.pizzamixin;



import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import featurecreep.FeatureCreepMCInit;
import net.pizzacrust.mixin.Inject;
import net.pizzacrust.mixin.Mixin;

@Mixin("net.minecraft.class_442")
public class TitleScreenMixin {
    @Inject(Inject.Execution.AFTER)
    private void init(CallbackInfo info) {
		FeatureCreepMCInit.LOGGER.info("This line is printed by an example mod mixin!");
		FeatureCreepMCInit.LOGGER.info("Boycott Modrinth");
	}
//TunaByte gets rid of buttons

}
