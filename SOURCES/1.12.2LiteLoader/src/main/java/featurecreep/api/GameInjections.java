package featurecreep.api;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Map;

import org.jboss.logging.Logger;
import org.jboss.modules.ModuleLoader;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.assistremapper.remapper.JarRemapper;
import com.mumfrey.liteloader.core.LiteLoaderVersion;

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
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;
import net.minecraft.launchwrapper.Launch;

public class GameInjections {

	public static boolean agent_mode = false;
	public static boolean debug_mode = false;
	public static Path gamepath = Launch.minecraftHome.toPath();
	public static String modpath = gamepath + ("/mods/");
	public static String[] packages_needed = GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader();
	public static String modid = "featurecreep";
	public static final Logger LOGGER = Logger.getLogger("FeatureCreep");
	public static double version = 3.919;// GA will be 4.0 for now 3.9pre will work
	public static String game_version = LiteLoaderVersion.CURRENT.getMinecraftVersion(); //Do not know where vesion is located and plus its obf so only 1 version is supported in most cases
	public static ActiveMapping mappings = ActiveMapping.SRG;// This is the default active mappings
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
	public static int texture_pack_version = 3;
	public static int data_pack_version = 3;
	
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
		if (nombre.equals(reverse_mappings.getClassMappedName("game.GameConfig"))) {
			return GameOptionsInjection(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.Item"))) {
			return itemInjection(buff);
		}else if (nombre.equals(reverse_mappings.getClassMappedName("game.DecoratorComponent"))) {
			return DecoratorComponentInjection(buff);
		}else if (nombre.equals(reverse_mappings.getClassMappedName("obf.class_unknown_0_"))) {
			return itemRendererInjection(buff);
		}else if (nombre.equals(reverse_mappings.getClassMappedName("game.RegionFileStorage"))) {
			return regionFileStorageInjection(buff);
		}
		
		return buff;

	}

	public static ByteBuffer regionFileStorageInjection(ByteBuffer buff) {
		// TODO Auto-generated method stub
		
		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			String target = reverse_mappings.getDefMappedName("game.RegionFileStorage.close()V");

			MethodInfo close = new MethodInfo(file.getConstPool(),target,"()V");
			Bytecode close_bytes 	= new Bytecode(close.getConstPool());
			close_bytes.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
			close_bytes.addLdc("RegionFileClosing is Disabled because it had issues with FC Ores for some reason");//Closing seems to cause issues, luckily c is the only null type so :p
			close_bytes.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V"); 
			close_bytes.addOpcode(Opcode.ACONST_NULL);
			close_bytes.addOpcode(Opcode.ARETURN);
			close.setCodeAttribute(close_bytes.toCodeAttribute());
	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return buff;
	}

	public static ByteBuffer itemRendererInjection(ByteBuffer buff) {
		// TODO Auto-generated method stub
		String target = reverse_mappings.getDefMappedName("obf.class_unknown_0_.registerGuiModels()V");

		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			
			
			/*
			 * public static void (Block block, Item item){
			 * BLOCK_ITEMS.put(block,item);
			 * }
			 * 
			 */
			MethodInfo rereg = new MethodInfo(file.getConstPool(),"rereg","()V");
			Bytecode rereg_bytes 	= new Bytecode(rereg.getConstPool());
			rereg_bytes.addAload(0);
			rereg_bytes.addInvokevirtual(reverse_mappings.getClassMappedName("obf.class_unknown_0_").replace("/", "."), target, "()V");
			rereg_bytes.addOpcode(Opcode.RETURN);
			rereg.setCodeAttribute(rereg_bytes.toCodeAttribute());
			try {
				file.addMethod(rereg);
			} catch (DuplicateMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			Bytecode def_bytes 	= new Bytecode(rereg.getConstPool());
			def_bytes.addAload(0);
			def_bytes.addInvokevirtual(reverse_mappings.getClassMappedName("obf.class_unknown_0_").replace("/", "."), "fcRegister", "()V");
			
			
			
			// initialise
			
			
				/*
				 * 
				 * 		+ "for (int i = 0; i < featurecreep.api.bg.registries.FCRegistries.ITEMS.size(); i++) {"
						+" featurecreep.api.bg.items.FCItemAPI ap = (featurecreep.api.bg.items.FCItemAPI)featurecreep.api.bg.registries.FCRegistries.ITEMS.get(i);"
						+ "this.registerModel(ap.get(), ap.getFCRegistryName());"
						+"System.out.println(ap.getFCRegistryName());"
						
						+ "}"
				      	+ "for (int b = 0; b < featurecreep.api.bg.registries.FCRegistries.BLOCKS.size(); b++) {"
				    	+" featurecreep.api.bg.blocks.FCBlockAPI ap = (featurecreep.api.bg.blocks.FCBlockAPI)featurecreep.api.bg.registries.FCRegistries.BLOCKS.get(b);"
						+ "this.registerModel(ap.get(), ap.getFCRegistryName());"
						+"System.out.println(ap.getFCRegistryName());"
						+ "}"
						+ "");
				
				 * 
				 */
			MethodInfo addFCOres = new MethodInfo(file.getConstPool(),"fcRegister","()V");
			Bytecode fc_ore_bytes 	= new Bytecode(rereg.getConstPool());
			fc_ore_bytes.addIconst(0);
			fc_ore_bytes.addIstore(1);
			fc_ore_bytes.add(Opcode.GOTO, 47);
			fc_ore_bytes.addGetstatic("featurecreep/api/bg/registries/FCRegistries", "ITEMS", "Ljava/util/ArrayList;");
			fc_ore_bytes.addIload(1);
			fc_ore_bytes.addInvokevirtual("java/util/ArrayList", "get", "(I)Ljava/lang/Object;");
			fc_ore_bytes.addCheckcast("featurecreep/api/bg/items/FCItemAPI");
			fc_ore_bytes.addAstore(2);
			fc_ore_bytes.addAload(0);
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/items/FCItemAPI", "get", reverse_mappings.renameClassesInMethodDescriptor("()Lgame/Item;"), 1);
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/items/FCItemAPI", "getFCRegistryName", "()Ljava/lang/String;", 1);
			fc_ore_bytes.addInvokevirtual(reverse_mappings.getClassMappedName("obf.class_unknown_0_"), reverse_mappings.getDefMappedName("obf.class_unknown_0_.registerModel(Lgame/Item;Ljava/lang/String;)V"), reverse_mappings.renameClassesInMethodDescriptor("(Lgame/Item;Ljava/lang/String;)V"));
			fc_ore_bytes.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/items/FCItemAPI", "getFCRegistryName", "()Ljava/lang/String;", 1);
			fc_ore_bytes.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			fc_ore_bytes.add(Opcode.IINC, 1);//No se si functiona
			fc_ore_bytes.addIload(1);
			fc_ore_bytes.addGetstatic("featurecreep/api/bg/registries/FCRegistries", "ITEMS", "Ljava/util/ArrayList;");
			fc_ore_bytes.addInvokevirtual("java/util/ArrayList", "size", "()I");
			fc_ore_bytes.add(Opcode.IF_ICMPLT, 5);
			fc_ore_bytes.addIconst(0);
			fc_ore_bytes.addIstore(1);
			
			
			
			
			fc_ore_bytes.add(Opcode.GOTO, 104);
			fc_ore_bytes.addGetstatic("featurecreep/api/bg/registries/FCRegistries", "BLOCKS", "Ljava/util/ArrayList;");
			fc_ore_bytes.addIload(1);
			fc_ore_bytes.addInvokevirtual("java/util/ArrayList", "get", "(I)Ljava/lang/Object;");
			fc_ore_bytes.addCheckcast("featurecreep/api/bg/blocks/FCBlockAPI");
			fc_ore_bytes.addAstore(2);
			fc_ore_bytes.addAload(0);
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/blocks/FCBlockAPI", "get", reverse_mappings.renameClassesInMethodDescriptor("()Lgame/Block;"), 1);
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/blocks/FCBlockAPI", "getFCRegistryName", "()Ljava/lang/String;", 1);
			fc_ore_bytes.addInvokevirtual(reverse_mappings.getClassMappedName("obf.class_unknown_0_"), reverse_mappings.getDefMappedName("obf.class_unknown_0_.registerModel(Lgame/Block;Ljava/lang/String;)V"), reverse_mappings.renameClassesInMethodDescriptor("(Lgame/Item;Ljava/lang/String;)V"));
			fc_ore_bytes.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
			fc_ore_bytes.addAload(2);
			fc_ore_bytes.addInvokeinterface("featurecreep/api/bg/blocks/FCBlockAPI", "getFCRegistryName", "()Ljava/lang/String;", 1);
			fc_ore_bytes.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");
			fc_ore_bytes.add(Opcode.IINC, 1);//No se si functiona
			fc_ore_bytes.addIload(1);
			fc_ore_bytes.addGetstatic("featurecreep/api/bg/registries/FCRegistries", "BLOCKS", "Ljava/util/ArrayList;");
			fc_ore_bytes.addInvokevirtual("java/util/ArrayList", "size", "()I");
			fc_ore_bytes.add(Opcode.IF_ICMPLT, 62);
			fc_ore_bytes.addOpcode(Opcode.RETURN);
			
			
			
			addFCOres.setCodeAttribute(fc_ore_bytes.toCodeAttribute());
			try {
				file.addMethod(addFCOres);
			} catch (DuplicateMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Yay Javaassist Worked!, though its capability is limted");
		
		return buff;

		
		
		
		
	}

	public static ByteBuffer DecoratorComponentInjection(ByteBuffer buff) {
		// TODO Auto-generated method stub

		
		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			String target = reverse_mappings.getDefMappedName("game.DecoratorComponent.placeVeins(Lgame/World;Ljava/util/Random;)V");

			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, reverse_mappings.renameClassesInMethodDescriptor("(Lgame/World;Ljava/util/Random;)V"));
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());

//				featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.applyOres($1, $2, this);
				code.addAload(1);
				code.addAload(2);
				code.addAload(0);
				code.addInvokestatic("featurecreep/api/bg/orespawn/OrespawnBasicFeatureParser", "applyOres", reverse_mappings.renameClassesInMethodDescriptor("(Lgame/World;Ljava/util/Random;Lgame/DecoratorComponent;)V"));
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

	/**
	 * Inyecciones en la clase de Item
	 * @param buff
	 * @return
	 */
	public static ByteBuffer itemInjection(ByteBuffer buff) {
		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(buff);
			
			
			/*
			 * public static void (Block block, Item item){
			 * BLOCK_ITEMS.put(block,item);
			 * }
			 * 
			 */
			MethodInfo blocktoitemadd = new MethodInfo(file.getConstPool(),"blocktoitemadd",reverse_mappings.renameClassesInMethodDescriptor("(Lgame/Block;Lgame/Item;)V"));
			Bytecode blocktoitemadd_bytes 	= new Bytecode(blocktoitemadd.getConstPool());
			blocktoitemadd_bytes.addGetstatic(reverse_mappings.getClassMappedName("game.Item").replace(".", "/"), reverse_mappings.getVarMappedName("game.Item.BLOCK_ITEMS:Ljava/util/Map;"), "Ljava/util/Map;");
			blocktoitemadd_bytes.addAload(0);
			blocktoitemadd_bytes.addAload(1);
			blocktoitemadd_bytes.addInvokeinterface("java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 3);
			blocktoitemadd_bytes.addOpcode(Opcode.POP);
			blocktoitemadd_bytes.addOpcode(Opcode.RETURN);
			blocktoitemadd.setCodeAttribute(blocktoitemadd_bytes.toCodeAttribute());
			try {
				file.addMethod(blocktoitemadd);
			} catch (DuplicateMemberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			String target = reverse_mappings.getDefMappedName("game.Item.canDestroySpecialBlock(Lgame/BlockPropertiesData;)Z");
			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "(Lgame/BlockPropertiesData;)Z");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());
				code.addAload(1);
				code.addInvokevirtual(reverse_mappings.getClassMappedName("game.BlockPropertiesData").replace(".", "/"), reverse_mappings.getDefMappedName("game.BlockPropertiesData.getBlock:()Lgame/Block"), reverse_mappings.renameClassesInMethodDescriptor("()Lgame/Block"));				
				code.addInstanceof("featurecreep/api/bg/blocks/FCBlockAPI");
				code.addOpcode(Opcode.IFEQ);
				code.add(12);
				code.addIconst(1);																		// JA");
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

	

	
}
