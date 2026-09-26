package asbestosstar.bootstrap.minecraft;

import java.lang.reflect.InvocationTargetException;

/** Main class used by Vanilla Launcher/TLauncher profiles installed by FeatureCreep. */
public final class VanillaMinecraftLauncher {
    private VanillaMinecraftLauncher() {}

    public static void main(String[] args) throws Exception {
        System.setProperty("featurecreep.minecraft.host", "vanilla");
        MinecraftCommonStartup.bootstrap();
        MinecraftCommonStartup.start();
        invokeMain("net.minecraft.client.main.Main", args);
    }

    static void invokeMain(String className, String[] args) throws Exception {
        try {
            Class<?> main = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
            main.getMethod("main", String[].class).invoke(null, (Object) args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Exception ex) throw ex;
            if (cause instanceof Error err) throw err;
            throw e;
        }
    }
}
