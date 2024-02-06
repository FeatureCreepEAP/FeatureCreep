package featurecreep;

import java.io.File;
import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import asbestosstar.fcdnf.FCDNF;
import featurecreep.api.GameInjections;
import featurecreep.api.bg.BGSide;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.mapping_converter.ActiveMapping;
import featurecreep.api.bg.mapping_converter.MappingConverter;
import featurecreep.api.bg.ui.FCCreativeTabs;
import featurecreep.api.parsers.DataParseContent;
import featurecreep.api.platform.super_.SuperLoader;
import featurecreep.content.FCBlocks;
import featurecreep.content.FCItems;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import javassist.ClassPool;

public class FeatureCreep {


	public static Path gamepath;	
	public static String modpath;	
		public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid;
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");

	
	public static ActiveMapping mappings = ActiveMapping.DANGERZONE;//This is the default active mappings
	public static SuperLoader super_loader = SuperLoader.DANGERZONE_BUILTIN_LOADER;//Need to detect this eventually
	
	public static ClassPool classpool = ClassPool.getDefault();
	public static String natively_mapped_mods_folder = gamepath+"/usr/share/natively_mapped_mods/"+mappings.name+"/";
	private static Path[] dependancies = {};
	public static Path[] modpaths = {new File(modpath).toPath(),new File(natively_mapped_mods_folder).toPath()};
	public static FCLoaderBasic loader = new FCLoaderBasicR8(modpaths, dependancies, packages_needed, 4, true, BGSide.getExecutionSide());
	public static ModuleLoader modloader = loader.getLoader();
	public static FCDNF fcdnf = new FCDNF();
	public static MappingConverter mappings_converter = new MappingConverter();
	public static RemapperInstance remapper = new RemapperInstance(mappings.getMappings(),classpool,natively_mapped_mods_folder);
	
	
		public static void onInitialise() {
		// TODO Auto-generated method stub
		System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());
		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		FCBlocks.onInitialise();
loader.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
		loader.loadMods();
		loader.runMods();//Soon I got to load before transforming and then run now
		DataParseContent.parseContent();
		PackLoader.loadPacks(loader.getModules());
//		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
			
		}
	
	
	
	
}
