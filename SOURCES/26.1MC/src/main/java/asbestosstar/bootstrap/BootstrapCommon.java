package asbestosstar.bootstrap;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;

import featurecreep.api.annotations.Nullable;
import featurecreep.attach.Attach;
import featurecreep.loader.FCLoaderBasic;

public class BootstrapCommon {

    /**
     * True if the agent successfully activates.
     */
    public static boolean agent_activated = false;
    public static Instrumentation instrument;
public static FCLoaderBasic loader;
    
    /**
     * Default init
     *
     * @return true if an Instrumentation instance was obtained
     */
    public static boolean initDefault() {
    	if(agent_activated) {
    		return true;
    	}
    	
    	Instrumentation inst = BootstrapCommon.activateAgent(BootstrapCommon.getJar());
        return inst != null;
    }

    /**
     * Attempts to activate the agent in the current JVM.
     * <p>
     * Order of attempts:
     * </p>
     * <ol>
     * <li>JDK Attach API (jdk.attach, preferred when available & self-attach allowed)</li>
     * <li>FeatureCreep attach (featurecreep.attach.Attach)</li>
     * </ol>
     */
    public static @Nullable Instrumentation activateAgent(String path_to_agent) {
        if (instrument != null) {
            return instrument;
        }

        if (path_to_agent == null) {
            return null;
        }

        //
        // Step 1 – JDK attach (preferred)
        //
        if (hasJdkAttach()) {
            try {
                boolean attached = jdkAttachSelf(path_to_agent, "");
                if (attached) {
                    // Agent loaded via JDK attach. The agent should
                    // set BootstrapCommon.instrument in its install().
                    return instrument;
                }
            } catch (Throwable t) {
                System.err.println(
                    "[BootstrapCommon] JDK attach failed (will try FeatureCreep attach): " + t);
            }
        } else {
            System.err.println(
                "[BootstrapCommon] jdk.attach module not present; skipping JDK self-attach.");
        }

        //
        // Step 2 – FeatureCreep attach fallback
        //
        if (classExists("featurecreep.attach.Attach")) {
            try {
                Attach.attach(path_to_agent, "");
            } catch (Throwable t) {
                System.err.println("[BootstrapCommon] FeatureCreep attach failed: " + t);
            }
        } else {
            System.err.println(
                "[BootstrapCommon] featurecreep.attach.Attach not found; cannot use FeatureCreep attach.");
        }

        return instrument;
    }

    /**
     * Attach to self using the standard JDK Attach API.
     *
     * @return true if attach was attempted and not explicitly blocked by
     *         AttachNotSupportedException; false if self-attach is clearly blocked.
     */
    private static boolean jdkAttachSelf(String agentPath, String args) throws Exception {
        // Ensure we’re running on a JDK with jdk.attach
        Class<?> vmClass = Class.forName("com.sun.tools.attach.VirtualMachine");
        Class<?> attachExClass = Class.forName("com.sun.tools.attach.AttachNotSupportedException");

        Method attach = vmClass.getMethod("attach", String.class);
        Method loadAgent = vmClass.getMethod("loadAgent", String.class, String.class);
        Method detach = vmClass.getMethod("detach");

        // Java 11+ supports ProcessHandle
        String pid = Long.toString(ProcessHandle.current().pid());

        Object vm = null;
        try {
            try {
                vm = attach.invoke(null, pid);
            } catch (InvocationTargetException ite) {
                Throwable cause = ite.getCause();
                if (cause != null && attachExClass.isInstance(cause)) {
                    // JDK Attach is present but self-attach is blocked
                    System.err.println(
                        "[BootstrapCommon] JDK attach present but self-attach is blocked: " + cause);
                    return false;
                }
                throw ite;
            }

            loadAgent.invoke(vm, agentPath, args == null ? "" : args);
            return true;
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
     * Check if jdk.attach is available in the current runtime.
     */
    private static boolean hasJdkAttach() {
        try {
            Class.forName("com.sun.tools.attach.VirtualMachine", false,
                          BootstrapCommon.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
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
            URI uriJar = BootstrapCommon.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI();

            String uriJarString = uriJar.toString();

            if (uriJarString.startsWith("union:")) { // For Modlauncher
                uriJarString = uriJarString.replace("union:", "file://");
            }

            if (uriJarString.startsWith("jar:")) {
                uriJarString = uriJarString.substring(4); // remove "jar:"
            }

            URI cd_uri = new URI(uriJarString);
            String cd_uri_path = cd_uri.getPath();
            System.out.println("Found FC Jar " + cd_uri_path);
            jar = new File(cd_uri_path).getAbsolutePath().split(".jar")[0] + ".jar";
        } catch (Exception e) {
            System.err.println("Could Not Find FeatureCreep Jar, this could cause problems");
            e.printStackTrace();
        }
        return jar;
    }
}
