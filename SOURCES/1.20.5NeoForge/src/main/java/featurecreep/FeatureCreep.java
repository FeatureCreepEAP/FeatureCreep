package featurecreep;

import java.io.File;
import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;
import java.io.IOException;
import featurecreep.loader.filesystem.DirectoryReader;

import com.asbestosstar.assistremapper.remapper.JarRemapper;
import java.util.ArrayList;
import java.util.List;
import featurecreep.api.clausewitz.mod.FileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.Mod;
import featurecreep.api.clausewitz.mod.ModuleClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileFileSystemClausewitzModLoader;
import featurecreep.api.clausewitz.mod.WithoutModFileModuleClausewitzModLoader;
import org.jboss.logging.Logger;
import org.jboss.modules.Module;
import asbestosstar.fcdnf.FCDNF;
import featurecreep.api.ClassPoolNewer1st;
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
import featurecreep.unsupported.RemappingClassFileTransformer;
import game.CommandDispatcher;
import game.CommandOriginStack;
import javassist.ClassPool;
import net.neoforged.neoforge.registries.GameData;

public class FeatureCreep {

	public static boolean debug_mode = GameInjections.debug_mode;
	public static Path gamepath = GameInjections.gamepath;
	public static String modpath = GameInjections.modpath;
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = GameInjections.LOGGER;
	public static double version = GameInjections.version;
	public static String game_version = GameInjections.game_version;
	public static ActiveMapping mappings = GameInjections.mappings;
	public static SuperLoader super_loader = GameInjections.super_loader;
	public static ClassPool classpool = new ClassPool(true);
	public boolean classpool_newer = ClassPoolNewer1st.setClassPoolToNewer1st(classpool, true);// To make sure to
																								// prioritise our own
																								// classes 1st then and
																								// reuse
	public static String natively_mapped_mods_folder = GameInjections.natively_mapped_mods_folder;
	public static String temp_mapping_location = GameInjections.temp_mapping_location;
	public static Path[] dependancies = {};
	public static Path[] modpaths = { new File(modpath).toPath(), new File(natively_mapped_mods_folder).toPath() };
	public static FCLoaderBasic loader = new FCLoaderBasicR8(modpaths, dependancies, packages_needed, 4, true,
			BGSide.getExecutionSide());
	public static ModuleLoader modloader = loader.getLoader();
	public static FCDNF fcdnf = GameInjections.fcdnf;
	public static MappingConverter mappings_converter = GameInjections.mappings_converter;
public static JarRemapper remapper = GameInjections.remapper;
public static boolean main_init =false;
public static ModuleClausewitzModLoader clausewitz_module_modloader = new ModuleClausewitzModLoader();
public static FileSystemClausewitzModLoader clausewitz_filesystem_modloader = new FileSystemClausewitzModLoader();
public static WithoutModFileModuleClausewitzModLoader clausewitz_module_modloader_no_modfile = new WithoutModFileModuleClausewitzModLoader();
public static WithoutModFileFileSystemClausewitzModLoader clausewitz_filesystem_modloader_no_modfile = new WithoutModFileFileSystemClausewitzModLoader();
	
	
//TODO Make Packages Needed list all forge packages as its not linear like Fabric

	public static void onInitialise() {
		// TODO Auto-generated method stub

	if(!main_init) {
			main_init=true;

		GameData.unfreezeData();

		System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID "
				+ io.smallrye.common.os.Process.getProcessId());
		FCCreativeTabs.onInitialise();
		VanillaItems.onInitialise();
		FCItems.onInitialise();
		FCBlocks.onInitialise();
		loader.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());	
				if(GameInjections.agent_mode) {
			loader.setInstrumentation(GameInjections.instrumentation);
		}
		loader.setMainTransformer(new RemappingClassFileTransformer(loader));
		loader.getTransformers().addAll(GameInjections.cargador.getTransformers());
		loader.loadMods();
		
		
		try {
			clausewitz_filesystem_modloader.search(new DirectoryReader(gamepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Module mod:loader.getModules()) {
			clausewitz_module_modloader.search(mod);
			clausewitz_module_modloader_no_modfile.search(mod);//Maybe try to make one 1 work in the future
		}
		
		loader.runMods();// Soon I got to load before transforming and then run now
		DataParseContent.parseContent();
		PackLoader.loadPacks(loader.getModules());
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
		DataPackLoader.onInitialise();

}


	}
	
		public static List<Mod> getClausewitzMods(){
		ArrayList<Mod> list = new ArrayList<Mod>();
		list.addAll(clausewitz_module_modloader.getMods());
		list.addAll(clausewitz_filesystem_modloader.getMods());
		list.addAll(clausewitz_module_modloader_no_modfile.getMods());
		list.addAll(clausewitz_filesystem_modloader_no_modfile.getMods());
		return list;
	}
	
	
	

	// TOCHANGE
	public static void registerFCDNF(com.mojang.brigadier.CommandDispatcher<CommandOriginStack> dispatcher) {
		dispatcher.register(CommandDispatcher.literal("fcdnf").executes(context -> {
			// Code to execute when the command is executed

			// fcdnf.parseArgs(context.getInput().replace("/", "").split(" ")) ;
			System.out.println("Running Command");
			fcdnf.parseArgs(new String[] { "dnf", "install", "featurecreep" });

			for (String arg : context.getInput().replace("/", "").split(" ")) {
				System.out.println(arg);
			}

			return 1;
		}));

	}

}
