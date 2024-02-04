package featurecreep.api;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;






public class GameInjections{

	
	
	public static void inject()
	{
		try {

			
			
			//Method [] meths = GameSettings.class.getDeclaredMethods();
		//	for (int m = 0; m < meths.length; m++) {
// System.out.println(meths[m].toString());
	//		}
			
		//	try {
		//		GameSettings settings = (GameSettings) GameSettings.class.getDeclaredField("INSTANCE").get(GameSettings);
		//		Method meth = GameSettings.class.getDeclaredMethod("fcpackadd");
		//		meth.invoke(settings);
			
		//	} catch (NoSuchFieldException e) {
		///		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
			
			
			
			
			
			
			//AddInjections();

	//		GameOptionsInjection();
	//		TitleScreenInjection();
			

			
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static byte[] GameOptionsInjection(byte[] bites)
	{
		
byte[] arr = bites;
		
		System.out.println("Starting Javaassit");
		
		try {

			
			
			
System.out.println("trying to inject");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
//	    pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.settings.GameSettings", arr));


	  //  pool.appendSystemPath();
	    CtClass cc = pool.get("net.minecraft.client.settings.GameSettings");
//	    CtMethod m = cc.getDeclaredMethod("func_74300_a");
	   CtMethod m = CtNewMethod.make(
                "public void fcpackadd() {}",
                cc);

	    //" + "this." + MCObfList.GameOptions_ResourcePacks + ".add(\"fcpack_9\");" +  "System.out.println(\\\"fcpack_9\\\"); 
	    
	    
//	    CtField f = cc.getField(MCObfList.GameOptions_ResourcePacks);
	    m.insertBefore("this.field_151453_l.add(\"fcpack_3\"); System.out.println(\"Adding FCPack3\");");
	  //  cc.writeFile();
	    
	    //byte[] classFile = cc.toBytecode();
	    
	    
	    //inst.redefineClasses(new ClassDefinition(GameOptions.class, cc.toBytecode()));
	    
	    
	    //System.out.println(GameInjections.class.getClassLoader().getDefinedPackages());
	//	System.out.println(Arrays.toString(GameInjections.class.getClassLoader().getDefinedPackages()));

	    

	    
	    
	//    Method meth = net.minecraft.client.option.GameOptions.class.getDeclaredMethod("fcpackadd");
	 //   meth.invoke(null);
	    
	    
	    arr =  cc.toBytecode();

	//	System.out.println(cc.toString());
			
			//MinecraftClient.getInstance().options.resourcePacks.add("fcpack_9");
			
		}catch (Throwable e)
		{
			e.printStackTrace();
		}
		
	
		
		System.out.println("Yay Javaassist Worked!, though its capability is limted");

			return arr;

	}


		
	public static void TitleScreenInjection() throws NotFoundException, CannotCompileException, IOException
	{
		/*
	try {
	ClassPool pool = ClassPool.getDefault();
   CtClass cc = pool.get(TitleScreen.class.getName());
   CtMethod m = cc.getDeclaredMethod("method_25426");
   m.insertBefore("System.out.println(featurecreep.FeatureCreep.modpath + 'Is loading from injection');");
   cc.writeFile();
	}catch (Throwable e)
	{
		e.printStackTrace();
	}
	*/
	
	}











	
	
	
	
	
}
