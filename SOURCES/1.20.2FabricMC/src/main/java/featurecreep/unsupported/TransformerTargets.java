package featurecreep.unsupported;

//This class is only for transformers which require you to predefine the classes before hand e.g. CPW Modlauncher transformers
public class TransformerTargets {

	public static String[] denylisted_packages = {
			"net.minecrell.terminalconsole.",
			"net.fabricmc.api",
			"net.fabricmc.impl",
			"com.llamalad7.mixinextras",
			"net.jodah.",
			"com.electronwill.nightconfig.",
			"com.mojang.",
			"org.antlr.v4",
			"META-INF.",
			"oshi.",
			"io.netty.", //Could there be more Netty? not included in MC? may need to make exceptions
			"org.openjdk.",
			"sun.",
			"com.sun.",
			"java.",
			"jdk.",
			"org.spongepowered.asm",
			"org.spongepowered.tools.agent",
			"org.spongepowered.tools.obfuscation",
			"org.apache.maven",
			"org.lwjgl.",
			"org.apache.logging",
			"joptsimple.",
			"org.joml",
			"org.jline",
			"ca.weblite",
			"com.ibm.icu.",
			"com.google.common",
			"com.google.thirdparty",
			"com.google.gson",
			"it.unimi",
			"codechicken.diffpatch",
			"codechicken.repack",
			"org.apache.commons",
			"org.objectweb.asm", //Maybe do Javassist and similar to?
			"org.apache.http",
			"org.slf4j"//maybe too much because of other slf4j?
			
			
	};
	
	
	public static String[] getSpongeMixinClassTargets() {
		return SpongeMixinUtils.getSpongeMixinClassTargets();	
	}
	
}
