package featurecreep;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

//import org.apache.commons.lang3.SerializationUtils;

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
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import net.minecraft.launchwrapper.IClassTransformer;
//import obf.class_unknown_1559;

public class FCCoreMod implements IClassTransformer{

	
	
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
	    	  
	    //
	    //if(FeatureCreep.debug_mode) {
//	    	MixerLoggerMain.doit();	  
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
	       // FeatureCreep.remapper.addToClasspathJar(GameJar.getFCIShadow());

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

	    	
	    	
	
	
	
	public static byte[] GameOptionsInjection(byte[] bites)
	{
		
byte[] arr = bites;
		
		System.out.println("Starting Javaassit");
		
		try {

			
			
			
System.out.println("trying to inject");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("bid", arr));

    	//pool.insertClassPath(new ByteArrayClassPath("aee",SerializationUtils.serialize(class_unknown_1559.class)));

	    
	    
	    
	    pool.appendSystemPath();
	    CtClass cc = pool.get("bid");
	    CtMethod m = cc.getDeclaredMethod("a", null);//Obf names are often the same need to specify there are no args

	    m.insertAfter( "this.m.add(\"fcpack_3\"); System.out.println(\"Adding FCPack3 Boycott Modrinth\");", true);
	    m.insertAfter( "this.m.add(\"fcdatapack3\"); System.out.println(\"Adding FCDataPack3 Boycott Modrinth\");",true);
//It seems there is no finally this time
	    
	    arr =  cc.toBytecode();

			
		}catch (Throwable e)
		{
			e.printStackTrace();
		}
		
	
		
		System.out.println("Yay Javaassist Worked!, though its capability is limted");

			return arr;

	}
	
	
	public static byte[] renderItemInjection(byte[] bites)
	{
		
byte[] arr = bites;
			
	

			
			
			
System.out.println("trying to inject render item");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("bzw", arr));
	    try {
			CtClass clazz = pool.get("bzw");
			clazz.getDeclaredMethod("b", null).insertAfter("System.out.println(\"Loading FC Models\");"
					+ "for (int i = 0; i < featurecreep.api.bg.registries.FCRegistries.ITEMS.size(); i++) {"
					+" featurecreep.api.bg.items.FCItemAPI ap = (featurecreep.api.bg.items.FCItemAPI)featurecreep.api.bg.registries.FCRegistries.ITEMS.get(i);"
					+ "this.a(ap.get(), ap.getFCRegistryName());"
					+"System.out.println(ap.getFCRegistryName());"
					
					+ "}"
			      	+ "for (int b = 0; b < featurecreep.api.bg.registries.FCRegistries.BLOCKS.size(); b++) {"
			    	+" featurecreep.api.bg.blocks.FCBlockAPI ap = (featurecreep.api.bg.blocks.FCBlockAPI)featurecreep.api.bg.registries.FCRegistries.BLOCKS.get(b);"
					+ "this.a(ap.get(), ap.getFCRegistryName());"
					+"System.out.println(ap.getFCRegistryName());"
					+ "}"
					+ "");
			
			
			
			CtMethod apply = CtNewMethod.make("public void rereg(){b();}", clazz);
			clazz.addMethod(apply);
			
			
			
			return clazz.toBytecode();
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

	    
	    
	    
	    return arr;
	    
	    

		
	}
	
	
	public static byte[] itemInjection(byte[] bites)// Needed to make BLOCK TO ITEM easier to access because Reflection is hard to use and Mirror is having issues, we will also use lots of injection on the item class in future releases
	{
		
byte[] arr = bites;
			
	

			
			
			
System.out.println("trying to inject render item");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("ain", arr)); //Nice Item name, like the Instant Messanger, oh wait it was with an n
	    try {
			CtClass clazz = pool.get("ain");
					
			CtMethod blocktoitemadd = CtNewMethod.make("public static void blocktoitemadd(aow block, ain item){a.put(block, item);}", clazz);// Finally we get to use a. i accidentally did a. when trying to use a method *_*
			clazz.addMethod(blocktoitemadd);
			
			
			
		      CtMethod c = clazz.getDeclaredMethod("a", new CtClass[] {
		    	        pool.get("awt")
		    	      });

		    	      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
		    	      c.insertBefore("System.out.println(\"Testing\");" +
		    	        "if ($1.u() instanceof featurecreep.api.bg.blocks.FCBlockAPI){return true;}" +
		    	        "");
			
			
			
			return clazz.toBytecode();
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

	    
	    
	    
	    return arr;
	    
	    

		
	}
	
	
	
	public static byte[] regionFileInjection(byte[] bites)// Needed to make BLOCK TO ITEM easier to access because Reflection is hard to use and Mirror is having issues, we will also use lots of injection on the item class in future releases
	{
		
byte[] arr = bites;
			
	

			
			
			
System.out.println("trying to inject render item");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("ayj", arr)); //Nice Item name, like the Instant Messanger, oh wait it was with an n
	    try {
			CtClass clazz = pool.get("ayj");
					
			clazz.getDeclaredMethod("c", null).setBody("System.out.println(\"RegionFileClosing is Disabled because it had issues with FC Ores for some reason\");");//Closing seems to cause issues, luckily c is the only null type so :p
			
			
			return clazz.toBytecode();
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

	    
	    
	    
	    return arr;
	    
	    

		
	}
	
	
	
	
	
	
	public byte[] biomeDecorationsInjections(byte[] basicClass) {
		// TODO Auto-generated method stub
	    try {
			ClassPool pool = ClassPool.getDefault();
			pool.appendSystemPath();
			pool.insertClassPath(new ByteArrayClassPath("ank", basicClass));
		//	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.world.World",SerializationUtils.serialize(Class.forName("net.minecraft.world.World"))));
	    	//pool.insertClassPath(new ByteArrayClassPath("featurecreep.api.orespawn.OrespawnBasicFeatureParser",SerializationUtils.serialize(featurecreep.api.orespawn.OrespawnBasicFeatureParser.class)));

CtClass osclass = pool.makeClass("featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser")	;
CtMethod apply = CtNewMethod.make("public static void applyOres(amu world, java.util.Random rand, ank dec){}", osclass);
osclass.addMethod(apply);
			
	    	CtClass cc = pool.get("ank");
			CtMethod meth = cc.getDeclaredMethods()[0];
			meth.insertAfter("featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser.applyOres($1, $2, this);");
		//	meth.insertAfter("System.out.println($1.toString());");
		//	meth.insertAfter("System.out.println($2.toString());");
		//	meth.insertAfter("System.out.println(this.toString());");

			return cc.toBytecode();
		} catch (NotFoundException | CannotCompileException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	public byte[] addtopool(byte[] bit, String name)
	{
		ClassPool pool = ClassPool.getDefault();
		pool.appendClassPath(new ByteArrayClassPath(name, bit));
		
		return bit;
	}
	
	

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		// TODO Auto-generated method stub

		if (transformedName.equals("bid"))
		{
			return GameOptionsInjection(basicClass);
		//return PizzaMixer.mix(TitleScreenMixin.class, basicClass);
		}else if (transformedName.equals("ank")) {
			return	biomeDecorationsInjections(basicClass);
		}else if (transformedName.equals("bzw")) {
			return	renderItemInjection(basicClass);
		}else if (transformedName.equals("ain")) {
			return	itemInjection(basicClass);
		}else if (transformedName.equals("ayj")) {
			return	regionFileInjection(basicClass);
		}
		else if (transformedName.equals("b")) {
			System.out.println("CrashReportClassExists");//return	regionFileInjection(basicClass);
		}
		else {
			//System.out.println(transformedName);
		if(!transformedName.contains("javassist."))//Gotta change this later
		{
			return addtopool(basicClass, transformedName);
		}
		
		}
		
	return basicClass;
	
	}



	
	
}
