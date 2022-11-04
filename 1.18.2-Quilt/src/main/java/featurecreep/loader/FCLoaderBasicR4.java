package featurecreep.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.jboss.modules.FCFileSystemClassPathModuleFinder;
import org.jboss.modules.Module;
import org.jboss.modules.ModuleLoader;

import featurecreep.FeatureCreep;
import featurecreep.loader.jbm.FCBootModuleLoader;



//This one does not have JBoss Code but the next will
public class FCLoaderBasicR4 {
	
	
	public static String[] getJars;
public static File fcfile;
	public static String[] packages_needed;
	public static List<Module> modules = new ArrayList<Module>();	
	//Arraylists use size and get in their for loops, you can also use iterator if you want next features
	
  //Soon We will use this
/*
  public static void loadMods(String pathstring) {
    File file = new File(pathstring);

    System.out.println(file.toString());

    String contents[] = file.list();
    System.out.println("List of files and directories in the specified directory:");
    //I need to make this multicore

    
    URLAdder urlloader = new URLAdder(FCLoaderBasicR4.class.getClassLoader());
    if (contents != null) {
        for (int i = 0; i < contents.length; i++) {
            File fil = new File(contents[i]);

        	  try {
      			urlloader.addURL(fil.toURL());
      		} catch (MalformedURLException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
            		
        	
        	
        }
        }
    
    
    if (contents != null) {
      for (int i = 0; i < contents.length; i++) {
        System.out.println("FeatureCreep is trying to load " + contents[i]);

        try {

          String oldclasspath = System.getProperty("java.class.path");
          String mods = String.join(File.pathSeparator + pathstring, contents);
          String classpath = oldclasspath + File.pathSeparator + pathstring + mods;

          JarFile jar = new JarFile(file + "/" + contents[i]);
          Manifest man = jar.getManifest();
          Attributes mainAttributes = man.getMainAttributes();
          String mainClass = mainAttributes.getValue(Attributes.Name.MAIN_CLASS);

          File fcfile = new File(FeatureCreep.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

          System.out.println(mainClass);
          if (mainClass != null) {

            String[] args = new String[] {
              "-cp",
              classpath,
              mainClass
            };

           // org.jboss.modules.Main.main(args);



            Class clazz = urlloader.loadClass(mainClass, true);

            Method method = clazz.getDeclaredMethod("main");
            Object instance = clazz.newInstance();
            Object result = method.invoke(instance);

            
            
            
          }

        } catch (Throwable e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          //I need to make this display different messages if it is detected to be a mod from another platform. It is planned
        }

      }

    } else {
      FeatureCreep.LOGGER.info("No Mods Found in Mods Folder");
    }

  }
*/
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //Soon We will use this
  public static void loadMods(String[] pathstring, String[] otherclasspathlocations, String[] packnames) {
    String alljars = "";
    String allmods = "";
packages_needed = packnames;
    
    
    for (int l = 0; l < pathstring.length; l++) {
      File modfile = new File(pathstring[l]);

     
      
      
      
      String[] pathstringcontents = modfile.list();
      for (int c = 0; c < pathstringcontents.length; c++) {

        allmods = allmods + pathstring[l] + pathstringcontents[c] + File.pathSeparator;
      }

      
      
	
      
      //System.out.print(otherclasspathlocations[0]);

      //System.out.print(otherclasspathlocations[1]);
      for (int i = 0; i < otherclasspathlocations.length; i++) {
        System.out.println(otherclasspathlocations[i]);
     
      
      }

      if (otherclasspathlocations != null) {
        for (int j = 0; j < otherclasspathlocations.length; j++) {
          //File depfile = new File(otherclasspathlocations[j]);
          //String[] deppathstringcontents = depfile.list();
          //for(int p=0; p<pathstringcontents.length; p++) {

          alljars = alljars + otherclasspathlocations[j] + File.pathSeparator;

          //}

        }

      }

      alljars = alljars + allmods;
      String[] allmodsarray = allmods.split(File.pathSeparator);

      
      
      getJars = alljars.split(File.pathSeparator);
      
      System.out.println(getJars.length);
      
      System.out.println(alljars.toString());

      //String contents[] = file.list();
      System.out.println("List of files and directories in the specified directory:");
      //I need to make this multicore


      
      
      
      
      if (allmodsarray != null) {
        for (int i = 0; i < allmodsarray.length; i++) {
        	
       
      		
      
        	
        }
        
        }
      
      
      
      if (allmodsarray != null) {
        for (int i = 0; i < allmodsarray.length; i++) {
          System.out.println("FeatureCreep is trying to load " + allmodsarray[i]);

          
      

          
          
          
          try {

            String oldclasspath = System.getProperty("java.class.path");
            String mods = String.join(File.pathSeparator + pathstring, allmodsarray);
            String classpath = oldclasspath + File.pathSeparator + alljars;

            // System.out.println(classpath);

            JarFile jar = new JarFile(allmodsarray[i]);
            File jarfile = new File(allmodsarray[i]);

            
           // List<Module> listA = new ArrayList<Module>();
            final ModuleLoader loader;
          final ModuleLoader environmentLoader;
            environmentLoader = FCBootModuleLoader.INSTANCE;
//            final Path rootPath = Paths.get("").toAbsolutePath();
//              final String moduleName;
            
      
            
            loader = new ModuleLoader(new FCFileSystemClassPathModuleFinder(environmentLoader));

//            moduleName = rootPath.resolve(allmodsarray[i]).normalize().toString();



            Module  agentModule = loader.loadModule(new File(jarfile.toString()).getAbsolutePath());
            
            
            
            if (jar.getManifest() != null) {
              Manifest man = jar.getManifest();
              Attributes mainAttributes = man.getMainAttributes();
              String mainClass = mainAttributes.getValue(Attributes.Name.MAIN_CLASS);

              File fcjar =   new File(FeatureCreep.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());  
 fcfile = fcjar;
              System.out.println(mainClass);
              if (mainClass != null && mainClass != "") {
                // String[] args = new String[] { "-cp", classpath, mainClass};
                //  org.jboss.modules.Main.main(args);

            	  // TODO we need to make it take better advantage of JBOSS Modules in R4
            	  
            	  
                //FCConcurrentClassLoader loader = new FCConcurrentClassLoader();

            	  
            	  

//                Class clazz = urlloader.loadClass(mainClass, true);

//                Method method = clazz.getDeclaredMethod("main");
  //              Object instance = clazz.newInstance();
    //            Object result = method.invoke(instance);

                
                
                
                
                
            	  
            	  
            
                    String[] args = new String[] { ""};
              	
                    
                    System.out.println(agentModule.getClassLoader());
                //  System.out.println(agentModule.getImportedPaths());
                  agentModule.run(args);


                
                  
                
                
                
                
             //   String[] args = new String[] { "-class", mainClass};
           //     org.jboss.modules.Main.main(args);
                
                
                
              }

            }

            modules.add(agentModule);

            
          } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //I need to make this display different messages if it is detected to be a mod from another platform. It is planned
          }

          
          
          
        }

      } else {
        FeatureCreep.LOGGER.info("No Mods Found in Mods Folder");
      }

    }

  }

  
  
  
  
  
}