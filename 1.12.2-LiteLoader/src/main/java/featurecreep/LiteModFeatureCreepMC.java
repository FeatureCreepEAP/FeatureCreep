package featurecreep;

import java.io.File;

//import org.dimdev.riftloader.listener.InitializationListener;
import org.jboss.logging.Logger;

import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigPanel;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;


//@ExposableOptions(strategy = ConfigStrategy.Versioned, filename="featurecreep.json")
public class LiteModFeatureCreepMC implements Tickable , Configurable{	// This logger is used to write text to the console and the log file.
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
	public void init(File configfile) {
		// TODO Auto-generated method stub
	//	Launch.classLoader.registerTransformer("featurecreep.FCCoreMod");
     	FeatureCreep.onInitialise();
     	//featurecreep.legacy.LiteModFeatureCreepMC.initilise(configfile);
	}

	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "4.0";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "FeatureCreepMC";
	}



    @Override
    public Class<? extends ConfigPanel> getConfigPanelClass()
    {
      //  return ExampleModConfigPanel.class; 
    	return null;
    }


}
