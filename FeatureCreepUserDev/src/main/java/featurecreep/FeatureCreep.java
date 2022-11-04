package featurecreep;

import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import featurecreep.api.PackLoader;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasicR4;

public class FeatureCreep {

	public ModuleLoader modloader;
	public static Path gamepath;	
	public static String modpath;	
	public static String modid;
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
	private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed = {""};


	public static void onInitialise() {
		// TODO Auto-generated method stub
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		//GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCLoaderBasicR4.loadMods(modpaths, dependancies, packages_needed);
		DataParseContent.parseContent();

		PackLoader.loadPacks(FCLoaderBasicR4.modules);
		
		
		
		}
	
	
	
	
}
