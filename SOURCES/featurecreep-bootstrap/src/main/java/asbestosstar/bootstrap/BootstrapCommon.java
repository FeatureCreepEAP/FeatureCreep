package asbestosstar.bootstrap;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.net.URI;

import featurecreep.api.annotations.Nullable;
import featurecreep.attach.Attach;

public class BootstrapCommon {

	/**
	 * True if the agent successfully activates.
	 */
	public static boolean agent_activated = false;
	public static Instrumentation instrument;

	/**
	 * Default init
	 * 
	 * @return
	 */
	public static boolean initDefault() {

		Instrumentation instrument = BootstrapCommon.activateAgent(BootstrapCommon.getJar());
		
		if (instrument != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Attempts to activate the agent in the current JVM.
	 * <p>
	 * Order of attempts:
	 * </p>
	 * <ol>
	 * <li>featurecreep.attach.Attach</li>
	 * <li>JDK Attach API (jdk.attach, Java 11+ only)</li>
	 * </ol>
	 */
	public static @Nullable Instrumentation activateAgent(String path_to_agent) {
		if (instrument != null) {
			return instrument;
		}

		if (path_to_agent == null) {
			return null;
		}

		// Step 1 – try FeatureCreep attach
		if (classExists("featurecreep.attach.Attach")) {
			try {
				Attach.attach(path_to_agent, "");
			} catch (Throwable t) {
				System.err.println("[BootstrapCommon] FeatureCreep attach failed: " + t);
			}
		}

		if (instrument != null) {
			return instrument;
		}

		// Step 2 – JDK attach fallback (Java 11+)
		try {
			jdkAttachSelf(path_to_agent, "");
		} catch (ClassNotFoundException e) {
			System.err.println(
					"[BootstrapCommon] No JDK Attach module present (jdk.attach missing). Skipping self-attach. Ths is only found in JDKs with tools.jar (Not JREs)");
		} catch (Throwable t) {
			System.err.println("[BootstrapCommon] JDK attach failed: " + t);
		}

		return instrument;
	}

	/**
	 * Attach to self using the standard JDK Attach API. Works only if the JVM
	 * includes the {@code jdk.attach} module (only true in full JDK builds with
	 * com.sun packages).
	 */
	private static void jdkAttachSelf(String agentPath, String args) throws Exception {
		// Ensure we’re running on a JDK with jdk.attach
		Class<?> vmClass = Class.forName("com.sun.tools.attach.VirtualMachine");

		Method attach = vmClass.getMethod("attach", String.class);
		Method loadAgent = vmClass.getMethod("loadAgent", String.class, String.class);
		Method detach = vmClass.getMethod("detach");

		// Java 11+ supports ProcessHandle
		String pid = Long.toString(ProcessHandle.current().pid());

		Object vm = null;
		try {
			vm = attach.invoke(null, pid);
			loadAgent.invoke(vm, agentPath, args == null ? "" : args);
		} finally {
			if (vm != null) {
				try {
					detach.invoke(vm);
				} catch (Throwable ignored) {
				}
			}
		}
	}

	/**
	 * Checks whether a class is available to the current class loader.
	 */
	public static boolean classExists(String name) {
		try {
			Class.forName(name, false, BootstrapCommon.class.getClassLoader());
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static @Nullable String getJar() {
		String jar = null;

		try {
			URI uriJar = BootstrapCommon.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			String uriJarString = uriJar.toString();

			if (uriJarString.startsWith("union:")) {// Para Modlauncher
				uriJarString = uriJarString.replace("union:", "file://");
			}

			if (uriJarString.startsWith("jar:")) {
				uriJarString = uriJarString.substring(4); // elimnar "jar:"
			}

			URI cd_uri = new URI(uriJarString);
			String cd_uri_path = cd_uri.getPath();
			System.out.println("Found FC Jar " + cd_uri_path);
			jar = new File(cd_uri_path).getAbsolutePath().split(".jar")[0] + ".jar";
		} catch (Exception e) {
			System.err.println("Could Not Find FeaturCreep Jar, this could cause problems");
			e.printStackTrace();
		}
		return jar;
	}

}
