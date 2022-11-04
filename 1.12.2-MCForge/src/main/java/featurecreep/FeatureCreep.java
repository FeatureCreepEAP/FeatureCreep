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
import featurecreep.loader.FCLoaderBasicR5;
import featurecreep.loader.GetPackagesFromClassClassLoader;
import net.minecraft.launchwrapper.Launch;

public class FeatureCreep {

public ModuleLoader modloader;
public static Path gamepath = Launch.minecraftHome.toPath();
public static String modpath = gamepath + ("/mods/");	
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	public static String[] packages_needed = {""};
	
//TODO Make Packages Needed list all forge packages as its not linear like Fabric
	
	public static void onInitialise() {
		
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		
		String packlist = "";
	    for (int l = 0; l < Package.getPackages().length; l++) {
	    	packlist = packlist + Package.getPackages()[l].getName().replace(".", "/") +  ":";
	    }
	    packlist = packlist+ "net/minecraftforge/fml/javafmlmod:net/minecraft/item:net/minecraft/creativetab:net/minecraft/item/crafting:net/minecraft/util:net/minecraft/block:net/minecraft/init:net/minecraft/client/renderer/block/model";
	    System.out.println(packlist);
	    packages_needed =  packlist.split(":");
		
		// TODO Auto-generated method stub
	//Log4J1 has been removed from JbossLogging files  and all but HostName and GetHostinfo from SmallRyeCommonNet
		//GameData.unfreezeData();	
//System.out.println(dependancies[1]+ ":" + dependancies[0]);
		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCBlocks.onInitialise();

		//FCLoaderBasicR2.loadMods(modpaths, dependancies);

		packages_needed = GetPackagesFromClassClassLoader.getPacakgesFromClassLoaderClassAsStringArray(FeatureCreep.class);
		FCLoaderBasicR5.loadMods(modpaths, dependancies, packages_needed);

		DataParseContent.parseContent();
		
		
		PackLoader.loadPacks(FCLoaderBasicR5.modules);
	
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();

		
	}
	
	
	
	
}
