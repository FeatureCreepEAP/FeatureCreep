package featurecreep;

import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.GameInjections;
import featurecreep.api.PackLoader;
import featurecreep.api.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCBlocks;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR4;
import net.fabricmc.loader.api.FabricLoader;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath = FabricLoader.getInstance().getGameDir();	
	public static String modpath = FabricLoader.getInstance().getGameDir() + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {FabricDirs.getMCIntermediary(),""};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed = {"net/minecraft"};

	
	public static void onInitialise() {
		// TODO Auto-generated method stub
		
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		
		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCBlocks.onInitialise();
		//FCLoaderBasicR2.loadMods(modpath);
		FCLoaderBasicR4.loadMods(modpaths, dependancies, packages_needed);

		DataParseContent.parseContent();
		
		
		PackLoader.loadPacks(FCLoaderBasicR4.modules);
		
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();

		
	
	
	
	
	}
	
	
	
	
}
