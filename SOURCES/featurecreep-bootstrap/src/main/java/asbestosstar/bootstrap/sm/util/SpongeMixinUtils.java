package asbestosstar.bootstrap.sm.util;

import org.jboss.modules.Module;
import org.spongepowered.asm.mixin.Mixins;
import asbestosstar.bootstrap.BootstrapCommon;

public class SpongeMixinUtils {

    public static void injectSpongeMixins() {
        for (Module mod : BootstrapCommon.loader.getModules()) {
            String configPath = mod.getProperty("SpongeMixinConfig");

            if (configPath != null) {
                // Store the original ClassLoader
                ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
                
                try {
                    // SWAP: Set the ContextClassLoader to your module's loader
                    // This allows Mixin to see the config file, the refmap, and the mixin classes
                    Thread.currentThread().setContextClassLoader(mod.getClassLoader());
                    
                    // Use the standard API to register the config
                    // Mixin will use the context classloader to find the file
                    Mixins.addConfiguration(configPath);
                    System.out.println("Registered mixin config: " + configPath);

                } catch (Exception e) {
                    System.err.println("Failed to register mixin config " + configPath);
                    e.printStackTrace();
                } finally {
                    // RESTORE: Always put the original loader back
                    Thread.currentThread().setContextClassLoader(originalClassLoader);
                }
            }
        }
    }
}