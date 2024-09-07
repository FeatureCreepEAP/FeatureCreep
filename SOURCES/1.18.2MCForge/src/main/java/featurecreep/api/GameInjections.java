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
import net.minecraftforge.fml.loading.FMLLoader;

public class GameInjections {

	public static boolean agent_mode = false;
	public static boolean debug_mode = false;
	public static Path gamepath = Paths.get(System.getProperty("user.dir"));// The one for mcforge does not work too early sometimes
	// or has other issues even if its suggested
	public static String modpath = gamepath.toString() + "/mods/";
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	public static double version = 3.919;// GA will be 4.0 for now 3.9pre will work
	public static String game_version = FMLLoader.versionInfo().mcVersion();
	public static ActiveMapping mappings = ActiveMapping.PARCHSRG;// This is the default active mappings
	public static SuperLoader super_loader = SuperLoader.MINECRAFTFORGE;// Need to detect this eventually
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
	public static int texture_pack_version = 8;
	public static int data_pack_version = 9;
	
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

		if (nombre.equals(reverse_mappings.getClassMappedName("game.TitleScreen"))) {
			return TitleScreenInjection(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.ResourcePackManager"))) {
			return transformresourcemanager(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.GameConfig"))) {
			return GameOptionsInjection(buff);
		}

		return buff;

	}

	// From Fabric API
	public static ByteBuffer GameOptionsInjection(ByteBuffer buff) {

		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			String target = reverse_mappings.getDefMappedName("game.GameConfig.load()V");
			String packs = reverse_mappings.getVarMappedName("game.GameConfig.texture_packs:Ljava/util/List;");

			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());

//				public List<String> texture_packs;
//
//				public void load() {
//
//					this.texture_packs.add("fcpack_22");
//					System.out.println("Adding FCPack");
//				}

				code.addAload(0);
				code.addGetfield(file.getName().replace(".", "/"), packs, "Ljava/util/List;");
				code.addLdc("fcpack_" + Integer.toString(texture_pack_version));
				code.addInvokeinterface("java/util/List", "add", "(Ljava/lang/Object;)Z", 2);
				code.addOpcode(Opcode.POP);

				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Adding FCPack");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");

				int len = coat.iterator().getCodeLength();
				coat.iterator().insert(len - 1, code.get());

				return ClassFileUtils.classFileToByteBuffer(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return buff;

	}

	public static ByteBuffer TitleScreenInjection(ByteBuffer buff) {

		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			String target = reverse_mappings.getDefMappedName("game.TitleScreen.initalise()V");
			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());
				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Testing JA with TitleScreen");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V"); // System.out.println("Testing
																									// JA");
				coat.iterator().begin();
				coat.iterator().insert(code.get());
				return ClassFileUtils.classFileToByteBuffer(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Yay Javaassist Worked!, though its capability is limted");

		return buff;

	}

	public static ByteBuffer transformresourcemanager(ByteBuffer basicClass) {
		// TODO Auto-generated method stub
//game.ResourcePackManager.reloadPacksFromFinders()V
		// game.ResourcePackManager.providers:Ljava/util/Set;

		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(basicClass);
			String target = reverse_mappings.getDefMappedName("game.ResourcePackManager.reloadPacksFromFinders()V");
			String providers = reverse_mappings.getVarMappedName("game.ResourcePackManager.providers:Ljava/util/Set;");

			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());

				code.addAload(0);
				code.addGetfield(file.getName().replace(".", "/"), providers, "Ljava/util/Set;");
				code.addNew("featurecreep/api/bg/FCPackLoad");
				code.addOpcode(Opcode.DUP);// Luckily this one was the example in javassist
				code.addNew("java/io/File");
				code.addOpcode(Opcode.DUP);
				code.addGetstatic("featurecreep/api/bg/datapacks/DataPackLoader", "datapacklocation",
						"Ljava/lang/String;");
				code.addInvokespecial("java/io/File", "<init>", "(Ljava/lang/String;)V");
				code.addInvokespecial("featurecreep/api/bg/FCPackLoad", "<init>", "(Ljava/io/File;)V");
				code.addInvokeinterface("java/util/Set", "add", "(Ljava/lang/Object;)Z", 2); // providers.add(new
																								// featurecreep.api.bg.FCPackLoad(new
																								// java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));
				code.addOpcode(Opcode.POP);

				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Testing JA On Resource Manager");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V"); // System.out.println("Testing
																									// JA");

				coat.iterator().begin();
				coat.iterator().insert(code.get());

				MethodInfo constr = ClassFileUtils.getMethodInfoWithDescriptor(file, "<init>",
						reverse_mappings.renameClassesInMethodDescriptor("(Lgame/ResourcePackProvider;)V"));
				if (constr != null) {
					CodeAttribute constrcoat = def.getCodeAttribute();
					Bytecode constrcode = new Bytecode(file.getConstPool());
					constrcode.addOpcode(Opcode.GOTO);
					constrcode.addOpcode(Opcode.ACONST_NULL);
					constrcode.addAstore(3);
					constrcode.addAload(0);
					constrcode.addAload(1);
					constrcode.addInvokestatic("featurecreep/api/io/BasicIO", "setFromArray",
							"([Ljava/lang/Object;)Ljava/util/Set;");
					constrcode.addPutfield(file.getName().replace(".", "/"), providers, "Ljava/util/Set;");
					CodeIterator consiter = constrcoat.iterator();
					consiter.append(constrcode.get());

				}
				return ClassFileUtils.classFileToByteBuffer(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return basicClass;
	}

	

}
