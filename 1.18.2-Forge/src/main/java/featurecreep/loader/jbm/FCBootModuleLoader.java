package featurecreep.loader.jbm;

import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.jboss.modules.LocalModuleLoader;
import org.jboss.modules.ModuleLoader;

import featurecreep.loader.FCLoaderBasicR4;

@SuppressWarnings({ "deprecation", "removal" })
public class FCBootModuleLoader {

	

    public static final ModuleLoader INSTANCE;

    private FCBootModuleLoader() {
    }

    static {
        INSTANCE = AccessController.doPrivileged(new PrivilegedAction<ModuleLoader>() {
            public ModuleLoader run() {
                final String loaderClass = System.getProperty("boot.module.loader", LocalModuleLoader.class.getName());
                try {
                    return Class.forName(loaderClass, true, FCBootModuleLoader.class.getClassLoader()).asSubclass(ModuleLoader.class).getConstructor().newInstance();
                  //  return Class.forName(LocalModuleLoader.class.getName(), true, FCLoaderBasicR4.class.getClassLoader()).asSubclass(ModuleLoader.class).getConstructor().newInstance();
              //      return Class.forName(LocalModuleLoader.class.getName(), true, FCLoaderBasicR4.class.getClassLoader()).asSubclass(ModuleLoader.class).getConstructor().newInstance();

                } catch (InstantiationException e) {
                    throw new InstantiationError(e.getMessage());
                } catch (IllegalAccessException e) {
                    throw new IllegalAccessError(e.getMessage());
                } catch (InvocationTargetException e) {
                    try {
                        throw e.getCause();
                    } catch (RuntimeException cause) {
                        throw cause;
                    } catch (Error cause) {
                        throw cause;
                    } catch (Throwable t) {
                        throw new Error(t);
                    }
                } catch (NoSuchMethodException e) {
                    throw new NoSuchMethodError(e.getMessage());
                } catch (ClassNotFoundException e) {
                    throw new NoClassDefFoundError(e.getMessage());
                }
            }
        });
    }
	
	
	
}
