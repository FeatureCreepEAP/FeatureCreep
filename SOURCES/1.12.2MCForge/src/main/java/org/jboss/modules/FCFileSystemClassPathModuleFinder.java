package org.jboss.modules;

import java.io.File;
import java.nio.file.Path;
import java.util.Set;
import java.util.jar.Attributes;

import featurecreep.loader.FCLoaderBasic;

public class FCFileSystemClassPathModuleFinder extends FileSystemClassPathModuleFinder {

  FCLoaderBasic load;
  public static Set<String> jdk_paths = JDKSpecific.getJDKPaths();

  public FCFileSystemClassPathModuleFinder(ModuleLoader baseModuleLoader, FCLoaderBasic load) {
    super(baseModuleLoader);
    // TODO Auto-generated constructor stub
    this.load = load;
  }

  @Override
  public void addSystemDependencies(final ModuleSpec.Builder builder) {

    //This need to be reworked to account for already loaded modules

    LocalLoader lod = JDKSpecific.getSystemLocalLoader();

    
    
    
    
    
    builder.addDependency(new LocalDependencySpecBuilder()
      .setLocalLoader(lod)
      .setLoaderPaths(load.getNeededPackages())
      .build());

    for (int j = 0; j < load.getCombinedFiles().size(); j++) {

      File file = load.getCombinedFiles().get(j);

      //System.out.println(FCLoaderBasicR5.fcfile);
      if (file != null && file.exists() && !file.toString().contains(".nil.jar")) {
      //  System.out.println("Adding Dependancy to Module " + file); soon enable for debug mode

        final ModuleLoader loader;
        final ModuleLoader environmentLoader;
        environmentLoader = load.getBootModuleLoader();

        loader = new ModuleLoader(new FileSystemClassPathModuleFinder(environmentLoader));
        try {
          Module agentModule = loader.loadModule(new File(file.toString()).getAbsolutePath());
          builder.addDependency(
            new ModuleDependencySpecBuilder()
            //           .setModuleLoader(agentModule.getModuleLoader())
            .setName(agentModule.getName())
            .build()
          );

        } catch (ModuleLoadException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      }

    }

  }
  
  public static String getMainClass(Module mod) {
	return  mod.getMainClass();
  }
  
  @Override //the parent often causes crashes and is not needed
  void addClassPathDependencies(final ModuleSpec.Builder builder, final ModuleLoader moduleLoader, final Path path, final Attributes mainAttributes) {
     
  }

}

