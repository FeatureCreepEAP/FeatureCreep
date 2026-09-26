package asbestosstar.bootstrap;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.net.URI;

import javax.annotation.Nullable;

import featurecreep.attach.Attach;
import featurecreep.loader.FCLoaderBasic;

/** Shared bootstrap utilities used by the FeatureCreep launch integrations. */
public class BootstrapCommon {

    /** True after the Java agent has supplied an Instrumentation instance. */
    public static boolean agent_activated = false;
    public static Instrumentation instrument;
    public static FCLoaderBasic loader;

    /**
     * Activates the FeatureCreep agent using the direct v12 HotSpot attach path.
     *
     * @return true if an Instrumentation instance was obtained
     */
    public static boolean initDefault() {
        if (instrument != null && agent_activated) {
            return true;
        }

        activateAgent(getJar());

        if (instrument != null && agent_activated) {
            return true;
        }

        // The agent can be loaded by a different class loader. Recover the
        // Instrumentation instance from the system-visible agent class when needed.
        try {
            ClassLoader sys = ClassLoader.getSystemClassLoader();
            Class<?> agentClass = Class.forName("asbestosstar.bootstrap.FeatureCreepAgent", false, sys);
            Method m = agentClass.getMethod("getInstrumentation");
            Object got = m.invoke(null);

            if (got instanceof Instrumentation instrumentation) {
                instrument = instrumentation;
                agent_activated = true;
                return true;
            }
        } catch (Throwable t) {
            System.err.println("[BootstrapCommon] Could not fetch Instrumentation from agent loader: " + t);
        }

        return false;
    }

    /**
     * Activates the agent in the current JVM using FeatureCreep API's direct
     * HotSpot attach implementation. This intentionally does not use
     * {@code jdk.attach.VirtualMachine} and does not depend on
     * {@code jdk.attach.allowAttachSelf}.
     *
     * @param pathToAgent path to the FeatureCreep agent JAR
     * @return the Instrumentation instance if the agent has already published it
     */
    public static @Nullable Instrumentation activateAgent(String pathToAgent) {
        if (instrument != null) {
            return instrument;
        }
        if (pathToAgent == null || pathToAgent.isBlank()) {
            return null;
        }

        try {
            Attach.attach(pathToAgent, "");
        } catch (Throwable t) {
            System.err.println("[BootstrapCommon] FeatureCreep direct attach failed: " + t);
            t.printStackTrace(System.err);
        }

        return instrument;
    }

    /** Checks whether a class is available without initializing it. */
    public static boolean classExists(String name) {
        try {
            Class.forName(name, false, BootstrapCommon.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /** Returns the current bootstrap/agent JAR path when it can be determined. */
    public static @Nullable String getJar() {
        String jar = null;

        try {
            URI uriJar = BootstrapCommon.class.getProtectionDomain().getCodeSource().getLocation().toURI();
            String uriJarString = uriJar.toString();

            if (uriJarString.startsWith("union:")) { // ModLauncher
                uriJarString = uriJarString.replace("union:", "file://");
            }
            if (uriJarString.startsWith("jar:")) {
                uriJarString = uriJarString.substring(4);
            }

            URI codeSourceUri = new URI(uriJarString);
            String codeSourcePath = codeSourceUri.getPath();
            System.out.println("Found FC Jar " + codeSourcePath);
            jar = new File(codeSourcePath).getAbsolutePath().split("\\.jar", 2)[0] + ".jar";
        } catch (Exception e) {
            System.err.println("Could Not Find FeatureCreep Jar, this could cause problems");
            e.printStackTrace();
        }
        return jar;
    }
}
