package featurecreep;

import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.PackLoader;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasic;
import net.minecraftforge.registries.GameData;

public class FeatureCreep {

public ModuleLoader modloader;
	public static Path gamepath = FeatureCreepMCInit.gamepath;
	public static String modpath = FeatureCreepMCInit.modpath;	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};

	
	public static void onInitialise() {
		// TODO Auto-generated method stub
	//Log4J1 has been removed from JbossLogging files  and all but HostName and GetHostinfo from SmallRyeCommonNet
		GameData.unfreezeData();	

		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCLoaderBasicR2.loadMods(modpaths, dependancies);
		PackLoader.loadPacks();

	}
	
	
	
	
}
