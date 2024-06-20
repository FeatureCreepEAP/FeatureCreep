package featurecreep.mixin;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.transformers.MixinClassReader;

import com.asbestosstar.assistremapper.Mappings;
import com.asbestosstar.mixerlogger.MixerLoggerMain;

import featurecreep.FeatureCreep;
import featurecreep.api.PKZipUtils;
import featurecreep.api.bg.BGSide;
import featurecreep.api.hashing.Sha256;
import featurecreep.bytecode.ClassFileUtils;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;
import game.TitleScreen;
import javassist.CtClass;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;

public class CoreMod implements IMixinConfigPlugin {

	public boolean transformers_activated = false;

	public String main = getMain();

	public static FCLoaderBasic loader = new FCLoaderBasicR8(FeatureCreep.modpaths, FeatureCreep.dependancies,
			FeatureCreep.packages_needed, 4, true, BGSide.getExecutionSide());

	public static Mappings reverse_mappings = FeatureCreep.mappings.getMappings().getReverse();

	public byte[] titlescreenja(byte[] arr) {

		try {
			ClassFile file = ClassFileUtils.classFileFromBytes(arr);
			String target = reverse_mappings.getDefMappedName("game.TitleScreen.initalise()V");
			System.out.println(target);
			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());
				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Testing JA");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V"); // System.out.println("Testing
																									// JA");
				coat.iterator().begin();
				coat.iterator().insert(code.get());
				return ClassFileUtils.classFileToBytes(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Yay Javaassist Worked!, though its capability is limted");

		return arr;

	}

	public String getMain() {
		// TODO Auto-generated method stub

		String debugpath = FeatureCreep.gamepath + "/etc/fcdebug";
		if (new File(debugpath).exists()) {
			FeatureCreep.debug_mode = true;
		}

		File temp_mapping_dir = new File(FeatureCreep.temp_mapping_location);
		if (temp_mapping_dir.exists()) {
			try {
				FileUtils.deleteFolderWithFiles(temp_mapping_dir);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (FeatureCreep.debug_mode) {
			MixerLoggerMain.doit();
		}

		List<File> fci_jars = new ArrayList<File>();

		for (File file : loader.getCombinedFiles()) {

			try (JarFile jar = new JarFile(file)) {
				// Get the manifest from the JAR file
				Manifest manifest = jar.getManifest();

				if (manifest != null) {
					// Get the main attributes from the manifest
					Attributes mainAttributes = manifest.getMainAttributes();

					// Check if the "Mappings" attribute exists and has the value "fci"
					String mappings = mainAttributes.getValue("Mappings");
					if (mappings != null && "fci".equals(mappings)) {
						// System.out.println("The JAR file has the 'Mappings' attribute
						// set to 'fci'.");
						fci_jars.add(file);
						loader.known_nils().add(file.getName());
						FeatureCreep.loader.known_nils().add(file.getName());
					} else {
						// System.out.println("The JAR file does not have the 'Mappings'
						// attribute set to 'fci'.");
					}
				} else {
					System.out.println("The JAR file does not contain a manifest.");
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		List<String> hashes = new ArrayList<String>();
//		FeatureCreep.remapper.addToClasspathJar(GameJar.getFCIShadow(),false);Should not be needed :)

		for (String cp : ClassPathUtils.getClassPath(FeatureCreep.loader)) {

			try {
				if (new File(cp).isFile()) {
					FeatureCreep.remapper.addToClasspathJar(new JarFile(cp), false);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File native_mods_folder = new File(FeatureCreep.natively_mapped_mods_folder);

		native_mods_folder.mkdirs();
		temp_mapping_dir.mkdirs();

		for (File fci_jar : fci_jars) {
			try {
				String hash = Sha256.getHashFromFileAsString(fci_jar);
				File mapped_jar = new File(
						FeatureCreep.natively_mapped_mods_folder + File.pathSeparator + hash + ".jar");
				List<File> to_map = new ArrayList<File>();
				if (!mapped_jar.exists()) {
					System.out.println("remapping " + fci_jar + ". Subsequent runs will be faster");
					to_map.add(mapped_jar);
					FeatureCreep.remapper.remapJar(new JarFile(fci_jar)); // I soon need to account for Jar in Jar
					PKZipUtils.zipDirectory(temp_mapping_dir, mapped_jar.getName());
					FileUtils.deleteFolderWithFiles(temp_mapping_dir);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (File to_delete : new File(FeatureCreep.natively_mapped_mods_folder).listFiles()) {
			boolean verdict = true;
			for (String hash : hashes) {
				if (new String(FeatureCreep.natively_mapped_mods_folder + File.pathSeparator + hash + ".jar")
						.equals("hash")) {
					verdict = false;
				} else {
					to_delete.delete();
				}

			}

		}

		try {
			FileUtils.deleteFolderWithFiles(temp_mapping_dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Done remapping");

		loader.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
		loader.loadMods();
		loader.runAgents();
		// CtClass sm =
		// FeatureCreep.classpool.makeClass("featurecreep.mixin.OverWorldBiomeCreator");
		// ClassFile clazz = sm.getClassFile();
		//
		// AnnotationsAttribute annotationsAttribute = new
		// AnnotationsAttribute(clazz.getConstPool(),
		// AnnotationsAttribute.visibleTag); Annotation annotation = new
		// Annotation("org.spongepowered.asm.mixin.Mixin", clazz.getConstPool());
		//
		// List<StringMemberValue> strings = new
		// ArrayList<StringMemberValue>(); for(String string:
		// SpongeMixinUtils.getSpongeMixinClassTargets()) { strings.add(new
		// StringMemberValue(string,clazz.getConstPool()));
		// }
		// ArrayMemberValue arr = new ArrayMemberValue(clazz.getConstPool());
		// arr.setValue(strings.toArray(new StringMemberValue[0]));
		// annotation.addMemberValue("target", arr);
		// annotationsAttribute.setAnnotation(annotation);
		//
		// clazz.addAttribute(annotationsAttribute);
		//
		// try {
		// Class output_clazz = sm.toClass(this.getClass());
		// } catch (CannotCompileException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		//
		//
		return "OverWorldBiomeCreator";
	}

	public byte[] overworldbiomecreation(byte[] basicClass) {
		// TODO Auto-generated method stub
		/*
		 * try { ClassPool pool = ClassPool.getDefault(); pool.insertClassPath(new
		 * ByteArrayClassPath("net.minecraft.class_5478", basicClass));
		 * 
		 * pool.appendSystemPath(); CtClass cc = pool.get("net.minecraft.class_5478");
		 * CtMethod m = cc.getDeclaredMethod("method_39151");
		 * 
		 * m.insertBefore(
		 * "featurecreep.api.orespawn.OrespawnBasicFeatureParser.spawnOre($7);");
		 * 
		 * m.insertBefore("System.out.println(\"Adding FCOres\");");
		 * 
		 * basicClass = cc.toBytecode(); cc.writeFile();
		 * 
		 * } catch (Throwable e) { e.printStackTrace(); }
		 * 
		 * System.out.println("Yay Javaassist Worked!, though its capability is limted"
		 * ); we dont need to do anything
		 */
		return basicClass;

	}

	public byte[] transform(String name, String transformedName, byte[] basicClass) {

		if (transformedName.equals(reverse_mappings.getClassMappedName("game.TitleScreen"))) {
			return titlescreenja(basicClass);
		} else if (transformedName.equals(reverse_mappings.getClassMappedName("game.OverWorldBiomeCreator"))) {
			return overworldbiomecreation(basicClass);
		} else if (transformedName.equals(reverse_mappings.getClassMappedName("game.ResourcePackManager"))) {
			return transformresourcemanager(basicClass);
		}

		return basicClass;

	}

	public byte[] transformresourcemanager(byte[] basicClass) {
		// TODO Auto-generated method stub
//game.ResourcePackManager.reloadPacksFromFinders()V
		// game.ResourcePackManager.providers:Ljava/util/Set;

		try {
			ClassFile file = ClassFileUtils.classFileFromBytes(basicClass);
			String target = reverse_mappings.getDefMappedName("game.ResourcePackManager.reloadPacksFromFinders()V");
			String providers = reverse_mappings.getVarMappedName("game.ResourcePackManager.providers:Ljava/util/Set;");

			System.out.println(target);
			// initialise
			MethodInfo def = ClassFileUtils.getMethodInfoWithDescriptor(file, target, "()V");
			if (def != null) {
				CodeAttribute coat = def.getCodeAttribute();
				Bytecode code = new Bytecode(file.getConstPool());

				code.addAload(0);
				code.addGetfield(file.getName().replace(".", "/"), providers, "Ljava/util/Set;");
				code.addNew("featurecreep/api/bg/FCPackLoad");
				code.addOpcode(code.DUP);// Luckily this one was the example in javassist
				code.addNew("java/io/File");
				code.addOpcode(code.DUP);
				code.addGetstatic("featurecreep/api/bg/datapacks/DataPackLoader", "datapacklocation",
						"Ljava/lang/String;");
				code.addInvokespecial("java/io/File", "<init>", "(Ljava/lang/String;)V");
				code.addInvokespecial("featurecreep/api/bg/FCPackLoad", "<init>", "(Ljava/io/File;)V");
				code.addInvokeinterface("java/util/Set", "add", "(Ljava/lang/Object;)Z", 2); // providers.add(new
																								// featurecreep.api.bg.FCPackLoad(new
																								// java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));
				code.addOpcode(code.POP);

				code.addGetstatic("java/lang/System", "out", "Ljava/io/PrintStream;");
				code.addLdc("Testing JA");
				code.addInvokevirtual("java/io/PrintStream", "println", "(Ljava/lang/String;)V"); // System.out.println("Testing
																									// JA");

				coat.iterator().begin();
				coat.iterator().insert(code.get());

				MethodInfo constr = ClassFileUtils.getMethodInfoWithDescriptor(file, "<init>",
						reverse_mappings.renameClassesInMethodDescriptor("(Lgame/ResourcePackProvider;)V"));
				if (constr != null) {
					CodeAttribute constrcoat = def.getCodeAttribute();
					Bytecode constrcode = new Bytecode(file.getConstPool());
					constrcode.addOpcode(constrcode.GOTO);
					constrcode.addOpcode(constrcode.ACONST_NULL);
					constrcode.addAstore(3);
					constrcode.addAload(0);
					constrcode.addAload(1);
					constrcode.addInvokestatic("featurecreep/api/io/BasicIO", "setFromArray",
							"([Ljava/lang/Object;)Ljava/util/Set;");
					constrcode.addPutfield(file.getName().replace(".", "/"), providers, "Ljava/util/Set;");
					CodeIterator consiter = constrcoat.iterator();
					consiter.append(constrcode.get());

				}

				return ClassFileUtils.classFileToBytes(file);
			}

		} catch (IOException | BadBytecode e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return basicClass;
	}

	@Override
	public void onLoad(String mixinPackage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRefMapperConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub

		List<String> fakemixins = new ArrayList<String>();

		return fakemixins;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

		System.out.println(CtClass.class.getCanonicalName());

		System.out.println(targetClassName);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		targetClass.accept(cw);
		ClassReader classReader = new MixinClassReader(transform(targetClassName, targetClassName, cw.toByteArray()),
				targetClassName);

		targetClass.fields.removeAll(targetClass.fields);
		targetClass.methods.removeAll(targetClass.methods);

		classReader.accept(targetClass, 0);

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

	}

	class Loader extends ClassLoader {

		public byte[] catchall;

		public Loader(byte[] catchall) {
			this.catchall = catchall;
		}

		@Override
		public Class findClass(String classname) {

			try {
				return super.findClass(classname);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return this.defineClass(classname, catchall);
		}

		Class defineClass(String name, byte[] bytes) {
			return this.defineClass(name, bytes, 0, bytes.length);
		}
	}

}
