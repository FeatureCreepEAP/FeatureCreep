package featurecreep.unsupported;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.dmr.ModelNode;
import org.spongepowered.asm.service.MixinService;

import featurecreep.FeatureCreep;
import featurecreep.api.GameInjections;
import featurecreep.loader.utils.ClassPathUtils;

public class SpongeMixinUtils {

	// ¡Asco! Pero finalmente funciona, nesesito volver a escribo. Mucho de este
	// existe porque la seguridad de SpongeMixin hace mas dificil
	public static String[] getSpongeMixinClassTargets() {
		List<String> all_classes = new ArrayList<String>();
		List<String> paquetes = new ArrayList<String>();
		for (String arc : ClassPathUtils.getAllFilesFromDirectory(new File(FeatureCreep.modpath))) {
			List<String> archivos = ClassPathUtils.getFilesFromJarWithoutSwitchingFromSlashToDot(new File(arc));
			if (archivos.contains("META-INF/mods.toml") || archivos.contains("META-INF/neoforge.mods.toml")
					|| archivos.contains("riftmod.json") || archivos.contains("litemod.json")
					|| archivos.contains("quilt.mod.json") || archivos.contains("fabric.mod.json")) { // Nesesito volver
																										// a ecribir
				for (String clazzz : archivos) {
					if (clazzz.endsWith(".class")) {
						all_classes.add(clazzz.replace("/", ".").substring(0, clazzz.length() - 6));
					} else if (clazzz.endsWith(".json")) {
						try {
							InputStream stream = SpongeMixinUtils.class.getClassLoader().getResourceAsStream(clazzz);

							if (stream != null) {
								ModelNode node = ModelNode.fromJSONStream(stream);
								if (node.has("package")) {
									paquetes.add(node.get("package").asString());
								}

							} else {
								System.out.println(clazzz);
							}

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

		}

		// 使用Java流API过滤类名
//        List<String> filtered_classes = all_classes.stream()  
//                .filter(className -> Arrays.stream(TransformerTargets.denylisted_packages)  
//                        .noneMatch(denylistedPackage -> className.startsWith(denylistedPackage)))  
//                .collect(Collectors.toList());  For vainilla

		// 使用Java流API过滤类名
		List<String> filtered_classes = all_classes.stream()
				.filter(className -> Arrays.stream(TransformerTargets.denylisted_packages)
						.noneMatch(denylistedPackage -> className.startsWith(denylistedPackage)))
				.filter(className -> className.contains(".")) // Add this filter to remove classes without a dot
				.collect(Collectors.toList());

		List<String> unfiltered_classes = new ArrayList<String>();
		// unfiltered_classes.addAll(filtered_classes);

		for (String entry : GameInjections.reverse_mappings.getClasses().values()) { // Cunado tenemos FCI vainilla
																						// nesesitemos cambiar este
			unfiltered_classes.add(entry);
		}

		List<String> completa = new ArrayList<String>();
		completa.addAll(unfiltered_classes);

		for (String clase : filtered_classes) {

			// TODO Auto-generated catch block
			if (!completa.contains(clase) && !MixinService.getService().getClassTracker().isClassLoaded(clase)) {
				for (String paquete : paquetes) {
					if (!clase.startsWith(paquete)) {// una manera mejor probelmente existe por subpaquetes
						completa.add(clase);
					}
				}
			}

		}

		completa.remove("featurecreep.unsupported.PluginFalso");

		// 打印过滤后的类名列表
		// filtered_classes.forEach(System.out::println);
		return completa.toArray(new String[0]);

	}

}
