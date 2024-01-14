package featurecreep;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import net.minecraft.launchwrapper.IClassTransformer;
import obf.class_unknown_1559;

public class FCCoreMod implements IClassTransformer{

	
	
	public static byte[] GameOptionsInjection(byte[] bites)
	{
		
byte[] arr = bites;
		
		System.out.println("Starting Javaassit");
		
		try {

			
			
			
System.out.println("trying to inject");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("bid", arr));

    	pool.insertClassPath(new ByteArrayClassPath("aee",SerializationUtils.serialize(class_unknown_1559.class)));

	    
	    
	    
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
