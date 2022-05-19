//Modified From JBossModuleLoader Example https://jboss-modules.github.io/jboss-modules/manual/#complete-plugin-example


package featurecreep.loader;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.jboss.modules.DependencySpec;
import org.jboss.modules.Module;
import org.jboss.modules.ModuleDependencySpecBuilder;
import org.jboss.modules.ModuleFinder;
import org.jboss.modules.ModuleLoadException;
import org.jboss.modules.ModuleLoader;
import org.jboss.modules.ModuleSpec;
import org.jboss.modules.PathUtils;
import org.jboss.modules.ResourceLoader;
import org.jboss.modules.ResourceLoaderSpec;
import org.jboss.modules.ResourceLoaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//NOT CURRENTLY IN USE




/**
 * This module loader loads module content from a plugin directory.  The
 * module name is the name of the JAR minus its extension (if any).
 */
public final class PluginModuleFinder implements ModuleFinder {
    private final Path basePath;
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    
    public PluginModuleFinder(Path basePath) {
        if (basePath == null) throw new IllegalArgumentException("null basePath");
        this.basePath = basePath;
    }
    
    
    public ModuleLoader modloader;

    
    
    
    
    
    
    

    public ModuleSpec findModule(String name , ModuleLoader delegateLoader) throws ModuleLoadException    {
        // Make sure nobody escapes using a .. in the plugin name
		LOGGER.info("Looking for Mod");

        name = PathUtils.relativize(PathUtils.canonicalize(name));
		LOGGER.info("Canonicalising Mod Name");

        Path jarPath = basePath.resolve(name + ".jar");
		LOGGER.info("Resolving Jar" + jarPath.toString());
        if (Files.exists(jarPath)) {
            ModuleSpec.Builder builder = ModuleSpec.build(name);
            // Add all JDK classes
            builder.addDependency(
                DependencySpec.createSystemDependencySpec(
                    PathUtils.getPathSet(null)
                )
            );
            
    		LOGGER.info("Adding JBoss Dependency");

            // Add the module's own content
            builder.addDependency(DependencySpec.OWN_DEPENDENCY);
            // Add my own module as a dependency
            final Module myModule = Module.forClass(getClass());
            builder.addDependency(
                new ModuleDependencySpecBuilder()
                   .setModuleLoader(myModule.getModuleLoader())
                    .setName(myModule.getName())
                    .build()
           );
            // Add the module JAR content
    		LOGGER.info("Adding JBoss Module Jar Content");

            URI uri = URI.create("jar:" + jarPath.toUri());
            FileSystem fs;
            try {
                fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
        		LOGGER.info("Loading FC Mods");
        		
            } catch (IOException e) {
                throw new ModuleLoadException(e);
            }
            final Path rootPath = fs.getRootDirectories().iterator().next();
    		LOGGER.info("Loading FC Mods Done");

            builder.addResourceRoot(
                ResourceLoaderSpec.createResourceLoaderSpec(
                    ResourceLoaders.createPathResourceLoader("root", rootPath)
                )
            );
     
            ModuleSpec moduleSpec = builder.create();
            try {
     				myModule.run(null);
     			} catch (NoSuchMethodException | InvocationTargetException | ClassNotFoundException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
            

            return moduleSpec;
        
        
        }
        
        
        
        else {
    		LOGGER.info("Could not find jar with name" + jarPath.toString());

        }
        
        
        
        
        return null; 
    }

}