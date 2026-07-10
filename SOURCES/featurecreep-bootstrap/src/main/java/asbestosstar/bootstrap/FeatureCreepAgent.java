package asbestosstar.bootstrap;

import java.lang.instrument.Instrumentation;

import asbestosstar.bootstrap.sm.InstrumentationMixinTransformer;

/**
 * FeatureCreep Java agent.
 */
public final class FeatureCreepAgent {
	private static volatile Instrumentation instrumentation;
	private static volatile InstrumentationMixinTransformer mixinTransformer;

	private FeatureCreepAgent() {
	}

	public static Instrumentation getInstrumentation() {
		return instrumentation;
	}

	public static void premain(String agentArgs, Instrumentation inst) {
		install(inst);
	}

	public static void agentmain(String agentArgs, Instrumentation inst) {
		install(inst);
	}

	private static synchronized void install(Instrumentation inst) {
		if (instrumentation != null) {
			return;
		}

		instrumentation = inst;
		BootstrapCommon.instrument = inst;
		BootstrapCommon.agent_activated = true;

		mixinTransformer = new InstrumentationMixinTransformer();
		inst.addTransformer(mixinTransformer, inst.isRetransformClassesSupported());

		System.setProperty("featurecreep.agent.active", "true");

		System.out.println("[FeatureCreepAgent] Instrumentation installed. Redefine="
				+ inst.isRedefineClassesSupported() + ", retransform=" + inst.isRetransformClassesSupported());
	}
}
