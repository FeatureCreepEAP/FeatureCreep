package org.jboss.modules;

import java.io.File;
import java.util.Set;

import featurecreep.loader.FCLoaderBasicR4;
import featurecreep.loader.FCLoaderBasicR5;
import featurecreep.loader.jbm.FCBootModuleLoader;

public class FCFileSystemClassPathModuleFinder extends FileSystemClassPathModuleFinder{

	public FCFileSystemClassPathModuleFinder(ModuleLoader baseModuleLoader) {
		super(baseModuleLoader);
		// TODO Auto-generated constructor stub
	}

	
	
	
@Override
	   void addSystemDependencies(final ModuleSpec.Builder builder) {

	
	Set<String> packs = new FastCopyHashSet<>(1024);
	
//	for (int p = 0; p < JDKSpecific.getJDKPaths().size(); p++) {

	//packs.add(JDKSpecific.getJDKPaths().addAll(packs));
packs.addAll(JDKSpecific.getJDKPaths());
	
//	}	
	
	
	
	
	   for (int c = 0; c < FCLoaderBasicR5.packages_needed.length; c++) {

	packs.add(FCLoaderBasicR5.packages_needed[c]);

	   }
	
		   builder.addDependency(new LocalDependencySpecBuilder()
		            .setLocalLoader(ClassLoaderLocalLoader.SYSTEM)
		            .setLoaderPaths(packs)
		            .build());

		   
		   for (int j = 0; j < FCLoaderBasicR5.getJars.length; j++) {
				  
			   File file = new File(FCLoaderBasicR5.getJars[j]);

			   //System.out.println(FCLoaderBasicR5.fcfile);
			   if (file != null && file.exists() && !file.toString().contains(".nil.jar")) {
				   System.out.println("Adding Dependancy to Module "+file);
			   
				   
	            	  final ModuleLoader loader;
	                  final ModuleLoader environmentLoader;
	                    environmentLoader = FCBootModuleLoader.INSTANCE;
                	            	                    
	                    loader = new ModuleLoader(new FileSystemClassPathModuleFinder(environmentLoader));
	                    try {
							Module  agentModule = loader.loadModule(new File(file.toString()).getAbsolutePath());
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
		   
		   
		   
}
