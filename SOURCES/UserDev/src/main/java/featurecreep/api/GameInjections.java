package featurecreep.api;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.asbestosstar.assistremapper.Mappings;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.PackLoader;
import featurecreep.bytecode.ClassFileUtils;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Opcode;

public class GameInjections {

	
	public static Mappings reverse_mappings = FeatureCreep.mappings.getMappings().getReverse();
	public boolean agent_mode = false;
	
	
	
	public static ByteBuffer inject(String nombre,ByteBuffer buff) {



		if (nombre.equals(reverse_mappings.getClassMappedName("game.TitleScreen"))) {
			return TitleScreenInjection(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.OverWorldBiomeCreator"))) {
			return defaultbiomefeaturestransform(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.ResourcePackManager"))) {
			return transformresourcemanager(buff);
		} else if (nombre.equals(reverse_mappings.getClassMappedName("game.GameConfig"))) {
			return GameOptionsInjection(buff);
		}

		return buff;

	}

	//From Fabric API
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
				code.addLdc("fcpack_" + Integer.toString(PackLoader.pack_version));
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

	
	
	
	// Odddly Doesnt Trigger
	public static ByteBuffer defaultbiomefeaturestransform(ByteBuffer basicClass) {
		// TODO Auto-generated method stub

//			public void createBiome(BiomeGenerationSettings.Builder builder) {
//				System.out.println("Adding FCOres");
//				featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.spawnOre(builder);
//			}

		String desc = reverse_mappings.renameClassesInMethodDescriptor(
				"(ZFFIILjava/lang/Integer;Ljava/lang/Integer;Lgame/MobSpawnSettings$Builder;Lgame/BiomeGenerationSettings$Builder;Lgame/MusicSound;)Lgame/Biome;");

		try {
			ClassFile file = ClassFileUtils.classFileFromByteBuffer(basicClass);
			String target = reverse_mappings.getDefMappedName(
					"game.OverWorldBiomeCreator.createBiome(ZFFIILjava/lang/Integer;Ljava/lang/Integer;Lgame/MobSpawnSettings$Builder;Lgame/BiomeGenerationSettings$Builder;Lgame/MusicSound;)Lgame/Biome;");

			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, desc);
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());
				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Adding FCOres");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V");
				code.addAload(1);
				code.addInvokestatic("featurecreep/api/bg/orespawn/OrespawnBasicFeatureParser", "spawnOre",
						reverse_mappings.renameClassesInMethodDescriptor("(Lgame/BiomeGenerationSettings$Builder;)V"));

				coat.iterator().begin();
				coat.iterator().insert(code.get());
				return ClassFileUtils.classFileToByteBuffer(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Injecting Datapack");

		return basicClass;

	}
	
	

}
