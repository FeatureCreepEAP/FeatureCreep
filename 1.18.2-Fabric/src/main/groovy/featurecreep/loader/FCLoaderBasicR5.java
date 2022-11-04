package featurecreep.loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
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

public class FCLoaderBasicR5 {

  public static String[] getJars;
  public static File fcfile;
  public static String[] packages_needed;
  public static List < Module > modules = new ArrayList < Module > ();
  public static ModuleLoader loader = new ModuleLoader(new FCFileSystemClassPathModuleFinder(FCBootModuleLoader.INSTANCE));

  //private static Module mod;

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

      for (int i = 0; i < otherclasspathlocations.length; i++) {
        System.out.println(otherclasspathlocations[i]);

      }

      if (otherclasspathlocations != null) {
        for (int j = 0; j < otherclasspathlocations.length; j++) {

          alljars = alljars + otherclasspathlocations[j] + File.pathSeparator;

        }

      }

      alljars = alljars + allmods;
      String[] allmodsarray = allmods.split(File.pathSeparator);

      getJars = alljars.split(File.pathSeparator);

      System.out.println(getJars.length);

      System.out.println(alljars.toString());

      System.out.println("List of files and directories in the specified directory:");
      //I need to make this multicore

      if (allmodsarray != null) {
        for (int i = 0; i < allmodsarray.length; i++) {

        }

      }

      if (allmodsarray != null) {
        for (int i = 0; i < allmodsarray.length; i++) {
          System.out.println("FeatureCreep is trying to load " + allmodsarray[i]);

          JarFile jar = null;
          try {
            jar = new JarFile(allmodsarray[i]);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          File jarfile = new File(allmodsarray[i]);

          try {

            String oldclasspath = System.getProperty("java.class.path");
            String mods = String.join(File.pathSeparator + pathstring, allmodsarray);
            String classpath = oldclasspath + File.pathSeparator + alljars;

            Module mod = loader.loadModule(new File(jarfile.toString()).getAbsolutePath());

            if (!modules.contains(mod)) {
              modules.add(mod);
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

    for (int m = 0; m < modules.size(); m++) {

      String[] args = new String[] {
        ""
      };

      //					            System.out.println(modules.get(m).getClassLoader());
      //  System.out.println(agentModule.getImportedPaths());
      try {

        System.out.println("Module:" + modules.get(m));

        modules.get(m).run(args);
      } catch (NoSuchMethodException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

  }

}