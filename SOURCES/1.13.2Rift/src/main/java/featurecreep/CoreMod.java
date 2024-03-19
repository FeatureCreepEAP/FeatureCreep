package featurecreep;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.asbestosstar.mixerlogger.MixerLoggerMain;

import featurecreep.api.PKZipUtils;
import featurecreep.api.bg.BGSide;
import featurecreep.api.bg.GameJar;
import featurecreep.api.hashing.Sha256;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;
import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import net.minecraft.launchwrapper.IClassTransformer;

public class CoreMod implements IClassTransformer {

	  public boolean transformers_activated = false;

	  public String main = getMain();

		public static FCLoaderBasic loader = new FCLoaderBasicR8(FeatureCreep.modpaths, FeatureCreep.dependancies, FeatureCreep.packages_needed, 4, true, BGSide.getExecutionSide());

	
	
  @Override
  public byte[] transform(String arg0, String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    if (arg1.equals("ayu")) {
      return transformbiome(arg0, arg2);
    } if (arg1.equals("ata")) {
        return transformeitem(arg0, arg2);
      }

    return arg2;
  }


  
  public String getMain() {
  // TODO Auto-generated method stub

	  
	  String debugpath = FeatureCreep.gamepath+"/etc/fcdebug";  
	  if(new File (debugpath).exists()) {
		  FeatureCreep.debug_mode=true;
	  }
	  
	  
	  
	    File temp_mapping_dir = new File(FeatureCreep.temp_mapping_location);
if(temp_mapping_dir.exists()) {
	try {
		FileUtils.deleteFolderWithFiles(temp_mapping_dir);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	  

if(FeatureCreep.debug_mode) {
	MixerLoggerMain.doit();	  
}
	  
  List<File> fci_jars = new ArrayList<File>();

  for (File file : loader.getCombinedFiles()) {

    try (JarFile jar = new JarFile(file)) {
      // Get the manifest from the JAR file
      Manifest manifest = jar.getManifest();

      if (manifest != null) {
        // Get the main attributes from the manifest
        Attributes mainAttributes = manifest.getMainAttributes();

        // Check if the "Mappings" attribute exists and has the value "fci"
        String mappings = mainAttributes.getValue("Mappings");
        if (mappings != null && "fci".equals(mappings)) {
          //    System.out.println("The JAR file has the 'Mappings' attribute
          //    set to 'fci'.");
          fci_jars.add(file);
          loader.known_nils().add(file.getName());
          FeatureCreep.loader.known_nils().add(file.getName());
        } else {
          //   System.out.println("The JAR file does not have the 'Mappings'
          //   attribute set to 'fci'.");
        }
      } else {
        System.out.println("The JAR file does not contain a manifest.");
      }
    } catch (IOException e) {
   //   e.printStackTrace();
    }
  }

  List<String> hashes = new ArrayList<String>();
  FeatureCreep.remapper.addToClasspathJar(GameJar.getFCIShadow());

  for (String cp : ClassPathUtils.getClassPath(FeatureCreep.loader)) {

    try {
 if(new File(cp).isFile()) {
  	  FeatureCreep.remapper.addToClasspathJar(new JarFile(cp));
 }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  File native_mods_folder =
      new File(FeatureCreep.natively_mapped_mods_folder);

  native_mods_folder.mkdirs();
  temp_mapping_dir.mkdirs();

  for (File fci_jar : fci_jars) {
    try {
      String hash = Sha256.getHashFromFileAsString(fci_jar);
      File mapped_jar = new File(FeatureCreep.natively_mapped_mods_folder +
      		File.pathSeparator + hash + ".jar");
      List<File> to_map = new ArrayList<File>();
      if (!mapped_jar.exists()) {
        System.out.println("remapping " + fci_jar +
                           ". Subsequent runs will be faster");
        to_map.add(mapped_jar);
        FeatureCreep.remapper.remapJar(
            new JarFile(fci_jar)); // I soon need to account for Jar in Jar
        PKZipUtils.zipDirectory(temp_mapping_dir, mapped_jar.getName());
        FileUtils.deleteFolderWithFiles(temp_mapping_dir);
      }
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  for(File to_delete:new File(FeatureCreep.natively_mapped_mods_folder).listFiles())
  {
  	boolean verdict=true;
  	for(String hash:hashes) {
  	 if (new String(FeatureCreep.natively_mapped_mods_folder + File.pathSeparator + hash + ".jar").equals("hash")) {
  		 verdict=false;
  	 }else {
  		 to_delete.delete();
  	 }

  	}
  	
  }

  try {
		FileUtils.deleteFolderWithFiles(temp_mapping_dir);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  
  System.out.println("Done remapping");

  loader.addNeededPackages(
      GetPackagesFromClassLoader.getPackageNamesInCurrentClassLoader());
  loader.loadMods();
  loader.runAgents();
  //		CtClass sm =
  //FeatureCreep.classpool.makeClass("featurecreep.mixin.OverWorldBiomeCreator");
  //		ClassFile clazz = sm.getClassFile();
  //
  //		  AnnotationsAttribute annotationsAttribute = new
  //AnnotationsAttribute(clazz.getConstPool(),
  //AnnotationsAttribute.visibleTag); 	    Annotation annotation = new
  //Annotation("org.spongepowered.asm.mixin.Mixin", clazz.getConstPool());
  //
  //	    List<StringMemberValue> strings = new
  //ArrayList<StringMemberValue>(); 	    for(String string:
  //SpongeMixinUtils.getSpongeMixinClassTargets()) { 		    strings.add(new
  //StringMemberValue(string,clazz.getConstPool()));
  //	    }
  //	    ArrayMemberValue arr =  new ArrayMemberValue(clazz.getConstPool());
  //	    arr.setValue(strings.toArray(new StringMemberValue[0]));
  //	    annotation.addMemberValue("target", arr);
  //	    annotationsAttribute.setAnnotation(annotation);
  //
  //	  clazz.addAttribute(annotationsAttribute);
  //
  // try {
  // Class output_clazz =	sm.toClass(this.getClass());
  //} catch (CannotCompileException  e) {
  //	// TODO Auto-generated catch block
  //	e.printStackTrace();
  //}
  //
  //
  //
  return "OverWorldBiomeCreator";
}  
  
  
  

private byte[] transformbiome(String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    ClassPool pool = ClassPool.getDefault();
    pool.insertClassPath(new ByteArrayClassPath(arg1, arg2));
    pool.appendSystemPath();
    CtClass cc;
    try {
      cc = pool.get(arg1);
      CtConstructor c = cc.getConstructors()[0];
      cc.defrost();
      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
      c.insertAfter("for (int f = 0; f < featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.configed.size(); f++) {" +
        "this.a(boq.b.e, (bpn)featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.configed.get(f));" +
        "}");
      c.insertAfter("System.out.println(\"HI\");");
      System.out.println("JA");
      //    cc.writeFile("net");
      return cc.toBytecode();
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return arg2;

  }

  private byte[] transformeitem(String arg1, byte[] arg2) {
    // TODO Auto-generated method stub

    ClassPool pool = ClassPool.getDefault();
    pool.insertClassPath(new ByteArrayClassPath(arg1, arg2));
    pool.appendSystemPath();
    CtClass cc;
    try {
      cc = pool.get(arg1);
      cc.defrost();
      CtMethod c = cc.getDeclaredMethod("a", new CtClass[] {
        pool.get("blc")
      });

      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
      c.insertBefore("System.out.println(\"Testing\");" +
        "if ($1.c() instanceof featurecreep.api.bg.blocks.FCBlockAPI){return true;}" +
        "");

      System.out.println("JA");
      //    cc.writeFile("net");
      return cc.toBytecode();
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return arg2;
  }

  
  
  
  
  
  
  
}