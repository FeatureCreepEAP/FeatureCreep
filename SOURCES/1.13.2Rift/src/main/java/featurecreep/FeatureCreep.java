package featurecreep;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.nio.file.Path;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import com.asbestosstar.assistremapper.remapper.JarRemapper;

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
import featurecreep.unsupported.ModuleRemapper;
import featurecreep.unsupported.RemappingClassFileTransformer;
import game.CommandDispatcher;
import game.CommandOriginStack;
import javassist.ClassPool;
import net.minecraft.launchwrapper.Launch;

public class FeatureCreep {


public static boolean debug_mode = false;
	public static Path gamepath = Launch.minecraftHome.toPath();
	public static String modpath = gamepath + ("/mods/");	
		public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	public static double version = 3.918;//GA will be 4.0 for now 3.9pre will work
	public static String game_version = "1.13.2";//We only have 1.13.2 ATM and i cant find a way to find it in RiftLoader.
	
	
	public static ActiveMapping mappings = ActiveMapping.OBF;//This is the default active mappings
	public static SuperLoader super_loader = SuperLoader.RIFT;//Need to detect this eventually
	
	public static ClassPool classpool = new ClassPool(true);
	public boolean classpool_newer = ClassPoolNewer1st.setClassPoolToNewer1st(classpool, true);//To make sure to prioritise our own classes 1st then and reuse
	public static String natively_mapped_mods_folder = gamepath+"/usr/share/.natively_mapped_mods/"+mappings.name+"/";
	public static String temp_mapping_location = gamepath+"/tmp/.remapping/";
	public static Path[] dependancies = {};
	public static Path[] modpaths = {new File(modpath).toPath(),new File(natively_mapped_mods_folder).toPath()};
	public static FCLoaderBasic loader = new FCLoaderBasicR8(modpaths, dependancies, packages_needed, 4, true, BGSide.getExecutionSide());
	public static ModuleLoader modloader = loader.getLoader();	
	public static FCDNF fcdnf = new FCDNF();
	public static MappingConverter mappings_converter = new MappingConverter();
public static JarRemapper remapper = new JarRemapper(mappings.getMappings().getReverse(), classpool, temp_mapping_location);
	

/***
 * Solo Existe cuando en modio agenta, generalmente esta null, usas featurecreep.api.HotSwapper
 */
public static Instrumentation instrumentation = ModuleRemapper.instrumentation;

	
	public static void onInitialise() {
		// TODO Auto-generated method stub
		System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());
		GameInjections.inject();
		FCCreativeTabs.onInitialise();
		VanillaItems.onInitialise();
		FCItems.onInitialise();
		FCBlocks.onInitialise();
loader.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
if(GameInjections.agent_mode) {
			loader.setInstrumentation(instrumentation);
		}
loader.setMainTransformer(new RemappingClassFileTransformer(loader));
loader.getTransformers().addAll(ModuleRemapper.loader.getTransformers());

loader.loadMods();
	//	loader.runAgents();
		loader.runMods();//Soon I got to load before transforming and then run now
		DataParseContent.parseContent();
		PackLoader.loadPacks(loader.getModules());
		OrespawnBasicFeatureParser.spawnOresFromDefaultConfig();
		DataPackLoader.onInitialise();
			
		}
	
	   //TOCHANGE
          public static void registerFCDNF(com.mojang.brigadier.CommandDispatcher<CommandOriginStack> dispatcher) {
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
