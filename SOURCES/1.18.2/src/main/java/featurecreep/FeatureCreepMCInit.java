package featurecreep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.jboss.modules.ModuleLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import featurecreep.api.PKZipUtils;
import featurecreep.api.bg.BGSide;
import featurecreep.api.hashing.Sha256;
import featurecreep.loader.ExecutionSide;
import featurecreep.loader.FCLoaderBasic;
import featurecreep.loader.FCLoaderBasicR8;
import featurecreep.loader.GetPackagesFromClassLoader;
import featurecreep.loader.utils.ClassPathUtils;
import featurecreep.loader.utils.FileUtils;
import game.Chunk;
import game.ChunkGenerator;
import game.ClientMain;
import game.CommandDispatcher;
import game.NudgerBuildings;
import game.RegistryBootstrap;
import game.GameRegistriesInterface;
import game.ResourcePackInfo.IFactory;
import game.ResourcePackManager;
import game.SharedConstants;
import game.SimpleRegistryMap;
import game.StructureWorldGenerationAccessLevel;
import game.TitleScreen;
import game.VersionInfo;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.util.HotSwapper;








//I need to eventually make a JBoss Modules wrapper around this
public class FeatureCreepMCInit {

	

public boolean transformers_activated = false;

public String main = getMain();

	public static FCLoaderBasic loader = new FCLoaderBasicR8(FeatureCreep.modpaths, FeatureCreep.dependancies, FeatureCreep.packages_needed, 4, true, BGSide.getExecutionSide());


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
  

//if(FeatureCreep.debug_mode) {
//MixerLoggerMain.doit();	  
//}
  
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
//FeatureCreep.remapper.addToClasspathJar(GameJar.getFCIShadow());

for (String cp : ClassPathUtils.getClassPath(FeatureCreep.loader)) {

  try {
if(new File(cp).isFile()) {
	  FeatureCreep.remapper.addToClasspathJar(new JarFile(cp),false);
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

	
	
	
	
	
	
	
	
	public static ExecutionSide launch_side = ExecutionSide.CLIENT; //On Server do SERVER, this must be on both GameProviders when the Server one is made, this is only for CLIENT or SERVER, not CLIENT_ONLY or SERVER_ONLY, This should not be called, instead use @BGSide.getExecutionSide()
	
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
public ModuleLoader modloader;

public static Path gamepath = Paths.get(System.getProperty("user.dir"));
protected static String modpath = gamepath.toString() + "/mods/";
	
//Gotta soon make these the actual ones once i figure out the args correctly

	public static void main(String[] args) {

		
		LOGGER.info("StartingMCClass");
		HotSwapper hs = null;
		try {new TitleScreen().onTick();}catch(Exception e) {}
		ClassPool pool = ClassPool.getDefault();
		try {
			CtClass clazz = pool.get(TitleScreen.class.getCanonicalName());
			clazz.getDeclaredMethod("b").insertAfter("System.out.println(\"Works. This is printed by an example Mixin. Boycott Modrinth JA Works\");");
			  hs = new HotSwapper(8000);  // 8000 is a port number.
			  System.out.println("Reloading TitleScreen Class");
			  hs.reload(TitleScreen.class.getCanonicalName(), clazz.toBytecode());
			 } catch (NotFoundException | CannotCompileException | IOException | IllegalConnectorArgumentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {RegistryBootstrap.println("Adding FC Init to BootStrap");}catch(Exception e) {}// Dunno Why this method even exist in bootstrap, it is just System.out.println, but it works and is the most simple
		try {
			CtClass bootstrap = pool.get(RegistryBootstrap.class.getCanonicalName());
		//	bootstrap.getDeclaredMethod("a").insertAfter("if (!b){featurecreep.FeatureCreep.onInitialise();}");
			bootstrap.getDeclaredMethod("a").insertAfter("featurecreep.FeatureCreep.onInitialise();");
			bootstrap.getDeclaredMethod("a", new CtClass[]{pool.get(Supplier.class.getCanonicalName())}).setBody(null);//Yay I can search for methods by name with array
			System.out.println("Reloading Bootstrap Class");
			hs.reload(RegistryBootstrap.class.getCanonicalName(), bootstrap.toBytecode());
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Setting Shared Game Version Constant");
		SharedConstants.setVersion(VersionInfo.get());
		System.out.println("Adding Registry Injection");

		try {GameRegistriesInterface.ACTIVITY.hashCode();}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(GameRegistriesInterface.class.getCanonicalName());
			registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
		//	registry.getDeclaredMethod("j").setBody("return this;");
			System.out.println("Generating Bytecode");
			byte[] code = registry.toBytecode();
			System.out.println("Reloading Registry Class");
			hs.reload(GameRegistriesInterface.class.getCanonicalName(), code);
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Simple Registry Injection");
		try {SimpleRegistryMap.ACTIVITY.hashCode();}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(SimpleRegistryMap.class.getCanonicalName());
			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
			registry.getDeclaredMethod("j").setBody("return this;");
			System.out.println("Generating Bytecode");
			byte[] code = registry.toBytecode();
			System.out.println("Reloading SimpleRegistry Class");
			hs.reload(SimpleRegistryMap.class.getCanonicalName(), code);
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("Adding Orespawn Injector");
//		try {new DefaultBiomeFeatures();}catch(Exception e) {}// Do nothing
//		try {
//			
//			CtClass registry = pool.get(DefaultBiomeFeatures.class.getCanonicalName());
//			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
//		//	registry.getDeclaredMethod("e", new CtClass[] {pool.get(GenerationSettings.Builder.class.getCanonicalName())}).insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.applyOres($1);");
//			registry.getDeclaredMethods("e")[0].insertAfter("featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.applyOres($1);");//Dunno how this worked, according to wag your tail mapping viewer 1 should be the one with GenSettingBuilder but i tried 0 expecting to fail but it worked, maybe wag tail does not do in correct order or it was another one that genbuilder with the name e? Not the 1st time WagYourTail mapping viewer had out of order for obf
//			hs.reload(DefaultBiomeFeatures.class.getCanonicalName(), registry.toBytecode());
//		} catch (NotFoundException | CannotCompileException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		try {ChunkGenerator.allConfigurations(null, null);}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(ChunkGenerator.class.getCanonicalName());
			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
		//	registry.getDeclaredMethod("e", new CtClass[] {pool.get(GenerationSettings.Builder.class.getCanonicalName())}).insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.applyOres($1);");
			registry.getDeclaredMethod("a", new CtClass[] {pool.get(StructureWorldGenerationAccessLevel.class.getCanonicalName()),pool.get(Chunk.class.getCanonicalName()), pool.get(NudgerBuildings.class.getCanonicalName())}).insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.applyOres($0, $1, $2, $3);");//Dunno how this worked, according to wag your tail mapping viewer 1 should be the one with GenSettingBuilder but i tried 0 expecting to fail but it worked, maybe wag tail does not do in correct order or it was another one that genbuilder with the name e? Not the 1st time WagYourTail mapping viewer had out of order for obf
			System.out.println("Reloading ChunkGenerator Class");
			hs.reload(ChunkGenerator.class.getCanonicalName(), registry.toBytecode());
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
System.out.println("Adding Resource and Data Pack Injector");
		
//		try {new GameOptions(null, null);}catch(Exception e) {}// Do nothing
//		try {
//			
//			CtClass options = pool.get(GameOptions.class.getCanonicalName());
//			options.defrost();
//			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
//			options.getDeclaredMethod("a").insertAfter("System.out.println(\"adding FCPack\");");// i realised you needed the finally from the 1.12.2 version, oh well, looks like there is no finally/return on this version
//
//			options.getDeclaredMethod("a").insertAfter("this.s.add(\"fcpack_8\");");// i realised you needed the finally from the 1.12.2 version, better remove the true
//			hs.reload(GameOptions.class.getCanonicalName(), options.toBytecode());
//		} catch (NotFoundException | CannotCompileException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
try {new ResourcePackManager((IFactory)null, null);}catch(Exception e) {}// Do nothing
try {
	
	  CtClass cc = pool.get(ResourcePackManager.class.getCanonicalName());
	  CtMethod m = cc.getDeclaredMethod("a");
	  m.insertBefore("System.out.println(\"Testin JA\");");
	  m.insertBefore("a.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));");
	  m.insertBefore("a.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.PackLoader.fc_pack_location)));");
	  CtConstructor cons = cc.getDeclaredConstructors()[0];
	  cons.insertAfter("a = featurecreep.api.io.BasicIO.setFromArray($2);");
	  System.out.println("Reloading ResourcePackageManager Class");
	  hs.reload(ResourcePackManager.class.getCanonicalName(), cc.toBytecode());
	  

} catch (NotFoundException | CannotCompileException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
try {CommandDispatcher.validate();}catch(Exception e) {}// Do nothing
try {
	
	  CtClass cc = pool.get(CommandDispatcher.class.getCanonicalName());
	  CtConstructor cons = cc.getDeclaredConstructors()[0];
	  cons.insertAfter("a = featurecreep.FeatureCreep.registerFCDNF(dispatcher);");//No remap needed :)
	  System.out.println("Reloading CommandDispatcher Class");
	  hs.reload(CommandDispatcher.class.getCanonicalName(), cc.toBytecode());
	  

} catch (NotFoundException | CannotCompileException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		System.out.println("Starting Game");
		ClientMain.main(args);


		
	}



}
