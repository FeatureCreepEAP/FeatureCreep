package featurecreep;

import java.io.File;

import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.riftloader.listener.InitializationListener;
//import org.dimdev.riftloader.listener.InitializationListener;
import org.jboss.logging.Logger;

import featurecreep.api.bg.FCPackLoad;
import featurecreep.api.bg.PackLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.launchwrapper.Launch;


public class FeatureCreepMCInit implements ItemAdder, InitializationListener{
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = Logger.getLogger("modid");

//	@Override
//	public void onInitialization() {
		// TODO Auto-generated method stub
//		LOGGER.info("Loading FeatureCreep Initialisation Class");

//     	FeatureCreep.onInitialise();
	//}

	@Override
	public void registerItems() {
		// TODO Auto-generated method stub
     	FeatureCreep.onInitialise();
//FeatureCreepMC.registerItems();
//FeatureCreepMC.registerBlocks();     	

MinecraftClient.getInstance().getResourcePackManager().registerProvider(new FCPackLoad(new File(PackLoader.fc_pack_location)));

	}

	@Override
	public void onInitialization() {
		// TODO Auto-generated method stub
Launch.classLoader.registerTransformer("featurecreep.CoreMod");
//LW lets you add args which may help us in the future
	}


	//One of the easiest to load that works with biomes and other stuff post registration






}
