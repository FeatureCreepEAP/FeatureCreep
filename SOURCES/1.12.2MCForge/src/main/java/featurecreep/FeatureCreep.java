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
import game.Server;
import net.minecraft.launchwrapper.Launch;
import obf.class_unknown_1682;
import obf.class_unknown_18521;
import obf.class_unknown_18549;

public class FeatureCreep {


public static Path gamepath = Launch.minecraftHome.toPath();
public static String modpath = gamepath + ("/mods/");	
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
	
		public static class registerFCDNF extends class_unknown_18521 {
			@Override
			public String def_unknown_80167() {
				// TODO Auto-generated method stub
				return "fcdnf";
			}
			@Override
			public String def_unknown_80169(class_unknown_1682 iCommandSender) {
				// TODO Auto-generated method stub
				return "fcdnf install <Package>"
						+ "Its pretty similar to DNF";
			}

			@Override
			public void def_unknown_80172(Server minecraftServer, class_unknown_1682 iCommandSender, String[] arr)
					throws class_unknown_18549 {
				// TODO Auto-generated method stub
		          // fcdnf.parseArgs(context.getInput().replace("/", "").split(" "))   ;                 

	            fcdnf.parseArgs(new String[] {"dnf","install","modrinth-downloader"})   ;                 

			}
			
		}
	
}
