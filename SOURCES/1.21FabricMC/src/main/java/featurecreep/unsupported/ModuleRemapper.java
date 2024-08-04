package featurecreep.unsupported;

import java.io.File;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.asbestosstar.mixerlogger.MixerLoggerMain;

import featurecreep.FeatureCreep;
import featurecreep.api.GameInjections;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;

public class ModuleRemapper {



	public static boolean completa = false;


	public static void remapMods() {

		GameInjections.cargador.setMainTransformer(new RemappingClassFileTransformer(GameInjections.cargador));
		String debugpath = FeatureCreep.gamepath + "/etc/fcdebug";
		if (new File(debugpath).exists()) {
			FeatureCreep.debug_mode = true;
		}

		File temp_mapping_dir = new File(FeatureCreep.temp_mapping_location); // es obsoleto , solo existe ahora por que
																				// este carpeta existe en Pre18
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

		//List<File> fci_jars = new ArrayList<File>();

		for (File file : GameInjections.cargador.getCombinedFiles()) {

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
						for (String clazz : ClassPathUtils.getFilesFromJar(file)) {
							RemappingClassFileTransformer.fcied_classes.put(clazz.replace("/", "."),
									FeatureCreep.mappings.getMappings().getReverse());
						}

					} else if (mappings != null && "fci-english".equals(mappings)) {
						// System.out.println("The JAR file does not have the 'Mappings'
						// attribute set to 'fci'.");
						for (String clazz : ClassPathUtils.getFilesFromJar(file)) {
							RemappingClassFileTransformer.fcied_classes.put(clazz.replace("/", "."),
									FeatureCreep.mappings.getEnglishToNativeMappings().getReverse());
						}
					} // añadir más idiomas
				} else {
					System.out.println("The JAR file does not contain a manifest.");
				}
			} catch (IOException e) {
				// e.printStackTrace();
			}
		}

		completa = true;

		GameInjections.cargador.addNeededPackages(GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
		if (GameInjections.agent_mode) {
			GameInjections.cargador.setInstrumentation(GameInjections.instrumentation);
		}

		GameInjections.cargador.loadMods();
		if (GameInjections.agent_mode) {
			GameInjections.instrumentation.addTransformer(FCLoaderBasic.fromClassTransformer(new FCClassTransformers()), true);
		} else {
			GameInjections.cargador.addTransformer(new FCClassTransformers()); // si usas la agente heche problemas
		}
		GameInjections.cargador.runAgents();
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
	}

}
