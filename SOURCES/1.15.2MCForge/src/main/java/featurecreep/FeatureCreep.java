package featurecreep;

import java.io.File;
import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import asbestosstar.fcdnf.FCDNF;
import featurecreep.api.GameInjections;
import featurecreep.api.bg.BGSide;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.datapacks.DataPackLoader;
import featurecreep.api.bg.items.vanilla.VanillaItems;
import featurecreep.api.bg.mapping_converter.ActiveMapping;
import featurecreep.api.bg.mapping_converter.MappingConverter;
import featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser;
import featurecreep.api.bg.ui.FCCreativeTabs;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.platform.super_.SuperLoader;
import featurecreep.content.FCBlocks;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import game.CommandDispatcher;
import game.CommandSourceStack;

public class FeatureCreep {


	public static Path gamepath = FeatureCreepMCInit.gamepath;
	public static String modpath = FeatureCreepMCInit.modpath;	
		public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
public static FCLoaderBasic loader = new FCLoaderBasicR8(new Path[] {new File(modpath).toPath()}, new Path[] {}, packages_needed, 4, true, BGSide.getExecutionSide());
	public static ModuleLoader modloader = loader.getLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	
private static String[] dependancies = {""};
	public static String[] modpaths = {modpath};
	public static FCDNF fcdnf = new FCDNF();
	public static MappingConverter mappings_converter = new MappingConverter();
	public static ActiveMapping mappings = ActiveMapping.SRG;//This is the default active mappings
	public static SuperLoader super_loader = SuperLoader.MINECRAFTFORGE;//Need to detect this eventually
	

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
		loader.runAgents();
		loader.runMods();//Soon I got to load before transforming and then run now
		DataParseContent.parseContent();
		PackLoader.loadPacks(loader.getModules());
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
		DataPackLoader.onInitialise();
			
		}	
	
	
	   //TOCHANGE
          public static void registerFCDNF(com.mojang.brigadier.CommandDispatcher<CommandSourceStack> dispatcher) {
                 dispatcher.register(CommandDispatcher.literal("fcdnf")
                         .executes(context -> {
                             // Code to execute when the command is executed

                 
                             
                // fcdnf.parseArgs(context.getInput().replace("/", "").split(" "))   ;                 

                 fcdnf.parseArgs(new String[] {"dnf","install","modrinth-downloader"})   ;                 

                 
                 
 for (String arg: context.getInput().replace("/", "").split(" ")) {
     System.out.println(arg);
 }
                         return 1;
                         })
                 );

          } 
	
	
	
}
