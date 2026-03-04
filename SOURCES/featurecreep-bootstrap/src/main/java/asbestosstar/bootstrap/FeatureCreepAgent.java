package asbestosstar.bootstrap;

import java.lang.instrument.Instrumentation;

/**
 * Java agent for FeatureCreep bootstrap. - premain: when supplied via
 * -javaagent at JVM start - agentmain: when attached dynamically via jdk.attach
 */
public final class FeatureCreepAgent {

	private FeatureCreepAgent() {
	}

	private static volatile Instrumentation AGENT_INSTRUMENTATION;

	public static Instrumentation getInstrumentation() {
		return AGENT_INSTRUMENTATION;
	}

	/** JVM startup attach (-javaagent:path=agent.jar[=args]) */
	public static void premain(String agentArgs, Instrumentation inst) {
		install(inst);
	}

	/** Dynamic attach (VirtualMachine#loadAgent) */
	public static void agentmain(String agentArgs, Instrumentation inst) {
		install(inst);
	}

	private static void install(Instrumentation inst) {
		if (FeatureCreepAgent.class.getClassLoader().getClass().getCanonicalName()
				.equals("org.jboss.modules.ModuleClassLoader")) {
			return;
		}

		BootstrapCommon.instrument = inst;
		BootstrapCommon.agent_activated = true;

		AGENT_INSTRUMENTATION = inst;

		// Optional marker (useful for debugging / checks)
		System.setProperty("featurecreep.agent.active", "true");

		System.out.println("[FeatureCreepAgent] Instrumentation installed. Redef=" + inst.isRedefineClassesSupported()
				+ " Retransform=" + inst.isRetransformClassesSupported());
	}
}
