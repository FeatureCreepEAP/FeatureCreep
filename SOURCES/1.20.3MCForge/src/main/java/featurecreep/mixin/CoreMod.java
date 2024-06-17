package featurecreep.mixin;

import featurecreep.FeatureCreep;
import featurecreep.api.PKZipUtils;
import featurecreep.api.bg.BGSide;
import featurecreep.api.bg.GameJar;
import featurecreep.api.hashing.Sha256;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.transformers.MixinClassReader;

import com.asbestosstar.mixerlogger.MixerLoggerMain;

public class CoreMod implements IMixinConfigPlugin {

	public boolean transformers_activated = false;

	public String main = getMain();

	public static FCLoaderBasic loader = new FCLoaderBasicR8(FeatureCreep.modpaths, FeatureCreep.dependancies,
			FeatureCreep.packages_needed, 4, true, BGSide.getExecutionSide());

	public byte[] transform(String name, String transformedName, byte[] basicClass) {

		if (transformedName.equals("net.minecraft.server.packs.repository.PackRepository")) {
			return transformresourcemanager(basicClass);
		} else if (transformedName.equals("net.minecraft.data.worldgen.BiomeDefaultFeatures")) {
			// return defaultbiomefeaturestransform(basicClass);
		}
		return basicClass;
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
//		FeatureCreep.remapper.addToClasspathJar(GameJar.getFCIShadow());
//
//		for (String cp : ClassPathUtils.getClassPath(FeatureCreep.loader)) {
//
//			try {
//				if (new File(cp).isFile()) {
//					FeatureCreep.remapper.addToClasspathJar(new JarFile(cp));
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

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

	// Odddly Doesnt Trigger
	public byte[] defaultbiomefeaturestransform(byte[] basicClass) {
		// TODO Auto-generated method stub

		try {
			ClassPool pool = ClassPool.getDefault();
			pool.insertClassPath(
					new ByteArrayClassPath("net.minecraft.data.worldgen.BiomeDefaultFeatures", basicClass));

			pool.appendSystemPath();
			CtClass cc = pool.get("net.minecraft.data.worldgen.BiomeDefaultFeatures");
			CtMethod m = cc.getDeclaredMethod("m_126814_");
			m.insertBefore("System.out.println(\"Adding FCOres\");");
			m.insertBefore("featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.spawnOre($1);");
			return cc.toBytecode();
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public byte[] transformresourcemanager(byte[] basicClass) {
		// TODO Auto-generated method stub

		try {
			ClassPool pool = ClassPool.getDefault();
			pool.insertClassPath(
					new ByteArrayClassPath("net.minecraft.server.packs.repository.PackRepository", basicClass));

			pool.appendSystemPath();
			CtClass cc = pool.get("net.minecraft.server.packs.repository.PackRepository");
			CtMethod m = cc.getDeclaredMethod("m_10506_");
			m.insertBefore("System.out.println(\"Testin JA\");");
			m.insertBefore(
					"f_10497_.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));");
			System.out.println("Injecting Datapack");
			return cc.toBytecode();
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
		// otherTargets.addAll(SpongeMixinUtils.getSpongeMixinClassTargets());
	}

	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub

		// List<String> output = new ArrayList<String>();
		// output.add(catch_all);

		return null;
		// return output;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

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
