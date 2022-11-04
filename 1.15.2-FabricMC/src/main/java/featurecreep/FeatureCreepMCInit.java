package featurecreep;

import java.util.logging.Logger;

import featurecreep.legacy.FeatureCreepMC;
import net.fabricmc.api.ModInitializer;


public class FeatureCreepMCInit implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = Logger.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
LOGGER.info("Loading FeatureCreep Initialisation Class");

     	FeatureCreep.onInitialise();
 FeatureCreepMC.onInitialize();
     	
}



}
