package featurecreep.unsupported;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import featurecreep.api.GameInjections;
import featurecreep.loader.utils.ClassPathUtils;

//This class is only for transformers which require you to predefine the classes before hand e.g. CPW Modlauncher transformers
public class TransformerTargets {

	public static String[] denylisted_packages = { "net.minecraftforge.", "net.minecrell.terminalconsole.",
			"net.jodah.", "com.electronwill.nightconfig.", "com.mojang.", "cpw.mods.jarhandling", "cpw.mods.util.Lazy",
			"cpw.mods.util.LambdaExceptionUtils", "cpw.mods.niofs.union", "cpw.mods.cl",
			"cpw.mods.modlauncher", "org.antlr.v4", "META-INF.", "oshi.", "io.netty.", // Could there be more Netty? not
																						// included in MC? may need to
																						// make exceptions
			"org.openjdk.", "sun.", "com.sun.", "java.", "jdk.", "org.spongepowered.asm",
			"org.spongepowered.tools.agent", "org.spongepowered.tools.obfuscation", "org.apache.maven", "org.lwjgl.",
			"org.apache.logging", "joptsimple.", "org.joml", "org.jline", "ca.weblite", "com.ibm.icu.",
			"com.google.common", "com.google.thirdparty", "com.google.gson", "it.unimi", "codechicken.diffpatch",
			"codechicken.repack", "org.apache.commons", "org.objectweb.asm", // Maybe do Javassist and similar to?
			"org.apache.http", "javassist.","org.jboss.","com.asbestosstar.AssistMixer","com.asbestosstar.assistremapper.","com.asbestosstar.cpiojava","com.asbestosstar.dnfjava","com.asbestosstar.mixerlogger","com.asbestosstar.rpmjava","com.asbestosstar.shadowassist","asbestosstar.","featurecreep.bytecode","org.slf4j"// maybe too much because of other slf4j?

	};

	public static String[] getSpongeMixinClassTargets() {
		return SpongeMixinUtils.getSpongeMixinClassTargets();
	}
	
	public static List<String> getCPWTransformerTargets() {

		List<String> all_classes = new ArrayList<String>();
		for (String arc : ClassPathUtils.getAllFilesFromDirectory(new File(GameInjections.modpath))) {
			List<String> archivos = ClassPathUtils.getFilesFromJarWithoutSwitchingFromSlashToDot(new File(arc));
			if (archivos.contains("META-INF/mods.toml") || archivos.contains("META-INF/neoforge.mods.toml")
					|| archivos.contains("riftmod.json") || archivos.contains("litemod.json")
					|| archivos.contains("quilt.mod.json") || archivos.contains("fabric.mod.json")) { // Nesesito volver
																										// a ecribir
				for (String clazzz : archivos) {
					if (clazzz.endsWith(".class")) {
						all_classes.add(clazzz.replace("/", ".").substring(0, clazzz.length() - 6));
					}
				}
			}

		}

		List<String> filtered_classes = all_classes.stream()
				.filter(className -> Arrays.stream(TransformerTargets.denylisted_packages)
						.noneMatch(denylistedPackage -> className.startsWith(denylistedPackage)))
				.filter(className -> className.contains(".")) // Add this filter to remove classes without a dot
				.collect(Collectors.toList());

		List<String> unfiltered_classes = new ArrayList<String>();

		for (String entry : GameInjections.reverse_mappings.getClasses().values()) { // Cunado tenemos FCI vainilla
																						// nesesitemos cambiar este
			unfiltered_classes.add(entry);
		}

		List<String> completa = new ArrayList<String>();
		completa.addAll(unfiltered_classes);

		for (String clase : filtered_classes) {

			// TODO Auto-generated catch block
			if (!completa.contains(clase)) {
				completa.add(clase);

			}

		}
		return completa;
	}
	
	

}
