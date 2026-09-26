package asbestosstar.bootstrap;

import asbestosstar.bootstrap.installer.FeatureCreepInstaller;

/** Standalone entry point. Under JBoss Modules the installer is never started. */
public final class FeatureCreepMain {
    private FeatureCreepMain() {}

    public static void main(String[] args) {
        if (isFeatureCreepModuleRuntime()) {
            System.out.println("[FeatureCreep] Running under FeatureCreep/JBoss Modules; standalone installer suppressed.");
            return;
        }
        FeatureCreepInstaller.launch(args);
    }

    public static boolean isFeatureCreepModuleRuntime() {
        if (Boolean.getBoolean("featurecreep.runtime")) return true;
        if (isJbossLoader(FeatureCreepMain.class.getClassLoader())) return true;
        if (isJbossLoader(Thread.currentThread().getContextClassLoader())) return true;
        return BootstrapCommon.loader != null;
    }

    private static boolean isJbossLoader(ClassLoader cl) {
        for (ClassLoader p = cl; p != null; p = p.getParent()) {
            String name = p.getClass().getName();
            if (name.startsWith("org.jboss.modules.") || name.contains("ModuleClassLoader")) return true;
        }
        return false;
    }
}
