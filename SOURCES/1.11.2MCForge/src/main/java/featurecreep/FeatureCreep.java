package featurecreep;

import java.io.File;
import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.datapacks.DataPackLoader;
import featurecreep.api.bg.items.vanilla.VanillaItems;
import featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.bg.ui.FCCreativeTabs;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.content.FCBlocks;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR7;
import featurecreep.loader.GetPackagesFromClassLoader;
import net.minecraft.launchwrapper.Launch;

public class FeatureCreep {


public static Path gamepath = Launch.minecraftHome.toPath();
public static String modpath = gamepath + ("/mods/");	
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static FCLoaderBasic loader = new FCLoaderBasicR7(new Path[] {new File(modpath).toPath()}, new Path[] {}, packages_needed, 4, true);
	public static ModuleLoader modloader = loader.getLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	

		public static void onInitialise() {
		// TODO Auto-generated method stub
			System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());
			GameInjections.inject();
			FCCreativeTabs.onInitialise();
			VanillaItems.onInitialise();
			FCItems.onInitialise();
			FCBlocks.onInitialise();
loader.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
		loader.loadMods();
		loader.runMods();//Soon I got to load before transforming and then run now
			DataParseContent.parseContent();
			PackLoader.loadPacks(loader.getModules());
			OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
			DataPackLoader.onInitialise();
			
		}
	
	
	
}
