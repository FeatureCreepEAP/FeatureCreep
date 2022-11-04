package featurecreep;

import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;
import org.quiltmc.loader.api.QuiltLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.PackLoader;
import featurecreep.api.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCBlocks;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR4;
import featurecreep.loader.FCLoaderBasicR5;
import featurecreep.loader.GetPackagesFromClassClassLoader;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath = QuiltLoader.getGameDir();	
	public static String modpath = QuiltLoader.getGameDir() + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed = {"net/minecraft"};

	
	public static void onInitialise() {
		// TODO Auto-generated method stub
		
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		//FCLoaderBasicR2.loadMods(modpath);
		FCBlocks.onInitialise();


		packages_needed = GetPackagesFromClassClassLoader.getPacakgesFromClassLoaderClassAsStringArray(FeatureCreep.class);
		FCLoaderBasicR5.loadMods(modpaths, dependancies, packages_needed);

		DataParseContent.parseContent();
		
		
		PackLoader.loadPacks(FCLoaderBasicR5.modules);
		
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();

		
	
	
	
	
	}
	
	
	
	
}
