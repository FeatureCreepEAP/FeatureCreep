package featurecreep;

import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.PackLoader;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR2;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath;	
	public static String modpath;	
	public static String modid;
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	

	public static void onInitialise() {
		// TODO Auto-generated method stub
		//GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCLoaderBasicR2.loadMods(modpaths, dependancies);
		PackLoader.loadPacks();
		
		
		
		}
	
	
	
	
}
