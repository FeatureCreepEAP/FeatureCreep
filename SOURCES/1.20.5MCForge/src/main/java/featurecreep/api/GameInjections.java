package featurecreep.api;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.util.Map;

import org.jboss.logging.Logger;
import org.jboss.modules.ClassTransformer;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.remapper.JarRemapper;

import featurecreep.api.bg.mapping_converter.ActiveMapping;
import featurecreep.api.bg.mapping_converter.MappingConverter;
import featurecreep.api.platform.super_.SuperLoader;
import featurecreep.bytecode.ClassFileUtils;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.unsupported.RemappingClassFileTransformer;
import javassist.ClassPool;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

public class GameInjections implements ClassTransformer {

	public static boolean agent_mode = false;
	public static boolean debug_mode = false;
	public static Path gamepath = Paths.get(System.getProperty("user.dir"));// The one for mcforge does not work too
																			// early sometimes
	// or has other issues even if its suggested
	public static String modpath = gamepath.toString() + "/mods/";
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	public static double version = 3.919;// GA will be 4.0 for now 3.9pre will work
	public static String game_version = "1.20.5";
	public static ActiveMapping mappings = ActiveMapping.PARCHMENT;// This is the default active mappings
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

	// public static FCDNF fcdnf = new FCDNF();
	public static MappingConverter mappings_converter = new MappingConverter();
	public static JarRemapper remapper = new JarRemapper(mappings.getMappings().getReverse(), classpool,
			temp_mapping_location);
	public static Mappings reverse_mappings = mappings.getMappings().getReverse();
	public static boolean agente_init = false;
	public static int texture_pack_version = 32;
	public static int data_pack_version = 41;

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
	    if (nombre.equals("net.minecraft.client.gui.screens.TitleScreen")) {
	        return TitleScreenInjection(buff);
	    } else if (nombre.equals("net.minecraft.data.worldgen.biome.OverworldBiomes")) {
	        return defaultbiomefeaturestransform(buff);
	    } else if (nombre.equals("net.minecraft.server.packs.repository.PackRepository")) {
	        return transformPackRepository(buff);
	    } else if (nombre.equals("net.minecraft.client.Options")) {
	        return GameOptionsInjection(buff);
	    }

	    return buff;
	}


	public static ByteBuffer GameOptionsInjection(ByteBuffer buff) {
	    try {
	        ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
	        String className = file.getName(); // e.g. "net/minecraft/client/Options"
	        String fieldName = "resourcePacks"; // public List<String> resourcePacks;

	        // Target: public void load() { ... }
	        MethodInfo loadMethod = ClassFileUtils.getMethodInfoWithDescriptor(file, "load", "()V");
	        if (loadMethod == null) {
	            // Try load(Z) if needed, but usually load() delegates to load(Z)
	            loadMethod = ClassFileUtils.getMethodInfoWithDescriptor(file, "load", "(Z)V");
	            if (loadMethod == null) return buff;
	        }

	        CodeAttribute codeAttr = loadMethod.getCodeAttribute();
	        if (codeAttr == null) return buff;

	        Bytecode code = new Bytecode(file.getConstPool());

	        // this.resourcePacks.add("fcpack_XX");
	        code.addAload(0); // this
	        code.addGetfield(className, fieldName, "Ljava/util/List;");
	        code.addLdc("fcpack_" + texture_pack_version);
	        code.addInvokeinterface("java/util/List", "add", "(Ljava/lang/Object;)Z", 2);
	        code.addOpcode(Opcode.POP); // discard boolean result

	        // Optional: log
	        code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
	        code.addLdc("Adding FCPack to resourcePacks");
	        code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");

	        // Insert at the very end, just before return
	        int end = codeAttr.iterator().getCodeLength();
	        codeAttr.iterator().insert(end - 1, code.get());

	        return ClassFileUtils.classFileToByteBuffer(file);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return buff;
	}

	public static ByteBuffer TitleScreenInjection(ByteBuffer buff) {
	    try {
	        ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
	        // MojMap method name is "init" with descriptor "()V"
	        MethodInfo initMethod = ClassFileUtils.getMethodInfoWithDescriptor(file, "init", "()V");
	        if (initMethod == null) {
	            System.err.println("TitleScreen.init()V not found!");
	            return buff;
	        }

	        CodeAttribute codeAttr = initMethod.getCodeAttribute();
	        if (codeAttr == null) return buff;

	        Bytecode code = new Bytecode(file.getConstPool());
	        code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
	        code.addLdc("Testing JavaAgent with TitleScreen");
	        code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");

	        // Insert at the very beginning of the method
	        codeAttr.iterator().begin();
	        codeAttr.iterator().insert(code.get());

	        return ClassFileUtils.classFileToByteBuffer(file);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return buff;
	}
	
	
	public static ByteBuffer transformPackRepository(ByteBuffer basicClass) {
	    try {
	        ClassFile file = ClassFileUtils.classFileFromByteBuffer(basicClass);
	        // MojMap class name
	        if (!"net/minecraft/server/packs/repository/PackRepository".equals(file.getName())) {
	            return basicClass;
	        }

	        // Target: public void reload()
	        MethodInfo reloadMethod = ClassFileUtils.getMethodInfoWithDescriptor(file, "reload", "()V");
	        if (reloadMethod == null) {
	            return basicClass;
	        }

	        CodeAttribute codeAttr = reloadMethod.getCodeAttribute();
	        if (codeAttr == null) return basicClass;

	        Bytecode code = new Bytecode(file.getConstPool());

	        // this.addPackFinder(YourPackFinder.INSTANCE);
	        code.addAload(0); // this
	        code.addGetstatic("featurecreep/api/bg/FCPackLoad", "INSTANCE", "Lnet/minecraft/server/packs/repository/RepositorySource;");
	        code.addInvokevirtual("net/minecraft/server/packs/repository/PackRepository", "addPackFinder", "(Lnet/minecraft/server/packs/repository/RepositorySource;)V");

	        // Optional: log
	        code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
	        code.addLdc("Injected FCPack into PackRepository");
	        code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");

	        // Insert at the very beginning of reload()
	        codeAttr.iterator().begin();
	        codeAttr.iterator().insert(code.get());

	        return ClassFileUtils.classFileToByteBuffer(file);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return basicClass;
	}
	
	
	// Odddly Doesnt Trigger
	public static ByteBuffer defaultbiomefeaturestransform(ByteBuffer basicClass) {
	    try {
	        ClassFile file = ClassFileUtils.classFileFromByteBuffer(basicClass);
	        // Target: private static void globalOverworldGeneration(BiomeGenerationSettings.Builder)
	        MethodInfo method = ClassFileUtils.getMethodInfoWithDescriptor(file, "globalOverworldGeneration", "(Lnet/minecraft/world/level/biome/BiomeGenerationSettings$Builder;)V");
	        if (method == null) {
	            return basicClass;
	        }

	        CodeAttribute codeAttr = method.getCodeAttribute();
	        if (codeAttr == null) return basicClass;

	        Bytecode code = new Bytecode(file.getConstPool());
	        code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
	        code.addLdc("Adding FCOres via globalOverworldGeneration");
	        code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");

	        code.addAload(0); // the BiomeGenerationSettings.Builder
	        code.addInvokestatic("featurecreep/api/bg/orespawn/OrespawnBasicFeatureParser", "spawnOre",
	            "(Lnet/minecraft/world/level/biome/BiomeGenerationSettings$Builder;)V");

	        // Insert at the **end** of the method, after all default features are added
	        int insertPos = codeAttr.iterator().getCodeLength() - 1;
	        codeAttr.iterator().insert(insertPos, code.get());

	        return ClassFileUtils.classFileToByteBuffer(file);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return basicClass;
	}
	@Override
	public ByteBuffer transform(ClassLoader loader, String className, ProtectionDomain protectionDomain,
			ByteBuffer classBytes) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return inject(className, classBytes);
	}

}
