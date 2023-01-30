package featurecreep;

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
import featurecreep.loader.FCLoaderBasicR5;
import featurecreep.loader.GetPackagesFromClassClassLoader;
import net.fabricmc.loader.api.FabricLoader;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath = FabricLoader.getInstance().getGameDir();	
	public static String modpath = FabricLoader.getInstance().getGameDir() + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {FabricDirs.getMCIntermediary(),""};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed;

	
		public static void onInitialise() {
		// TODO Auto-generated method stub
			System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());
			GameInjections.inject();
			FCCreativeTabs.onInitialise();
			VanillaItems.onInitialise();
			FCItems.onInitialise();
			FCBlocks.onInitialise();
			packages_needed = GetPackagesFromClassClassLoader.getPacakgesFromClassLoaderClassAsStringArray(FeatureCreep.class);
			FCLoaderBasicR5.loadMods(modpaths, dependancies, packages_needed);
			DataParseContent.parseContent();
			FCRegistries.generateModels();
			PackLoader.loadPacks(FCLoaderBasicR5.modules);	
			OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
			DataPackLoader.onInitialise();
			
		}
	
	
	
	
}
