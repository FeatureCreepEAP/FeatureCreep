package featurecreep;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.PackLoader;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR2;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath = Paths.get(System.getProperty("user.dir"));
	public static String modpath = gamepath + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {gamepath + "/DangerZone_lib/"};
	public static String[] modpaths = {modpath};
	
	public static void onInitialise() {
		// TODO Auto-generated method stub
		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCLoaderBasicR2.loadMods(modpaths, dependancies);
		PackLoader.loadPacks();
		
		
		
		}
	
	
	
	
}
