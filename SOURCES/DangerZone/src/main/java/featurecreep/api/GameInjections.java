package featurecreep.api;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.remapper.JarRemapper;

import asbestosstar.fcdnf.FCDNF;
import dangerzone.DangerZone;
import featurecreep.api.bg.BGSide;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.mapping_converter.ActiveMapping;
import featurecreep.api.bg.mapping_converter.MappingConverter;
import featurecreep.api.platform.super_.SuperLoader;
import featurecreep.bytecode.ClassFileUtils;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.unsupported.RemappingClassFileTransformer;
import javassist.ClassPool;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

public class GameInjections {
	
	
	public static boolean agent_mode = false;
	public static boolean debug_mode = false;
	public static Path gamepath = Paths.get(System.getProperty("user.dir"));
	public static String modpath = gamepath + ("/mods/");
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	public static double version = 3.910;// GA will be 4.0 for now 3.9pre will work
	public static String game_version = DangerZone.versionstring;
	public static ActiveMapping mappings = ActiveMapping.DANGERZONE;// This is the default active mappings
	public static SuperLoader super_loader = SuperLoader.DANGERZONE_BUILTIN_LOADER;// Need to detect this eventually
	public static ClassPool classpool = new ClassPool(true);
	public boolean classpool_newer = ClassPoolNewer1st.setClassPoolToNewer1st(classpool, true);// To make sure to
																								// prioritise our own
																								// classes 1st then and
																								// reuse
	public static String natively_mapped_mods_folder = gamepath + "/usr/share/.natively_mapped_mods/" + mappings.name
			+ "/";
	public static String temp_mapping_location = gamepath + "/tmp/.remapping/";
	public static Path[] dependancies = {};
	public static Path[] modpaths = { new File(modpath).toPath(), new File(natively_mapped_mods_folder).toPath() };

	/**
	 * Este cargadora es solo para Agentes en GameInjections, en algunas platformas
	 * es lo mismo que FeatureCreep.loader. ¡No dependas de esto!
	 */
	public static FCLoaderBasic cargador = new FCLoaderBasicR8(modpaths, dependancies, packages_needed, 4, true,
			BGSide.getExecutionSide());

	public static ModuleLoader cargadormod = cargador.getLoader();
	public static FCDNF fcdnf = new FCDNF();
	public static MappingConverter mappings_converter = new MappingConverter();
	public static JarRemapper remapper = new JarRemapper(mappings.getMappings().getReverse(), classpool,
			temp_mapping_location);
	public static Mappings reverse_mappings = mappings.getMappings().getReverse();
	public static boolean agente_init=false;
	public static int texture_pack_version = 0;
	public static int data_pack_version = 0;
	
	/***
	 * Solo Existe cuando en modio agenta, generalmente esta null, usas
	 * featurecreep.api.HotSwapper
	 */
	public static Instrumentation instrumentation = null;

	public static Map<String, Mappings> getClassesToRemapToFeatureCreepIntermediaryBeforeTransforming() {
		return RemappingClassFileTransformer.to_be_fcied_classes;
	}

	public static Map<String, Mappings> getClassesToRemapFromFeatureCreepIntermediaryAfterTransforming() {
		return RemappingClassFileTransformer.fcied_classes;
	}

	public static ByteBuffer inject(String nombre, ByteBuffer buff) {



		return buff;

	}

	

}
