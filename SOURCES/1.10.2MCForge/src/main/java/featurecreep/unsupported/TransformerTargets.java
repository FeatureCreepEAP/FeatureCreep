package featurecreep.unsupported;

//This class is only for transformers which require you to predefine the classes before hand e.g. CPW Modlauncher transformers
public class TransformerTargets {

	public static String[] denylisted_packages = { "javassist.","org.jboss.","com.asbestosstar.AssistMixer","com.asbestosstar.assistremapper.","com.asbestosstar.cpiojava","com.asbestosstar.dnfjava","com.asbestosstar.mixerlogger","com.asbestosstar.rpmjava","com.asbestosstar.shadowassist","asbestosstar.","featurecreep.bytecode",// maybe too much because of other slf4j?

	};

	public static String[] getSpongeMixinClassTargets() {
		return new String[] {};
	}

}
