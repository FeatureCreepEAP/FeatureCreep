package featurecreep;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.PackLoader;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR5;
import featurecreep.loader.GetPackagesFromClassClassLoader;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath = Paths.get(System.getProperty("user.dir"));
	public static String modpath = gamepath + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {gamepath + "/DangerZone_lib/"};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed = {"dangerzone", "dangerzone/biomes", "dangerzone/blocks", "dangerzone/entities", "dangerzone/gui", "dangerzone/items", "dangerzone/particles", "dangerzone/thingstodo", "dangerzone/threads"};

	
	public static void onInitialise() {
		// TODO Auto-generated method stub
		
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();

		packages_needed = GetPackagesFromClassClassLoader.getPacakgesFromClassLoaderClassAsStringArray(FeatureCreep.class);
		FCLoaderBasicR5.loadMods(modpaths, dependancies, packages_needed);

		DataParseContent.parseContent();
		
		
		PackLoader.loadPacks(FCLoaderBasicR5.modules);
	
		}
	
	
	
	
}
