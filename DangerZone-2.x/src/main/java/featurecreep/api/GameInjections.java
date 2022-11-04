package featurecreep.api;

import java.io.IOException;
import java.util.List;

import javassist.CannotCompileException;
import javassist.NotFoundException;





public class GameInjections {

	
	
	public static void inject()
	{
		try {

			//AddInjections();

			GameOptionsInjection();
			TitleScreenInjection();
			

			
			
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void GameOptionsInjection() throws NotFoundException, CannotCompileException, IOException
	{
		

		
	/*	
		
		try {

			
			
			
			System.out.println(Arrays.toString(GameInjections.class.getClassLoader().getDefinedPackages()));
		    
System.out.println("trying to inject");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    CtClass cc = pool.get("net.minecraft.class_315");
	  //  CtMethod m = cc.getDeclaredMethod(MCObfList.GameOptions_Load);
	    CtMethod m = CtNewMethod.make(
                "public void fcpackadd() {}",
                cc);

	    //" + "this." + MCObfList.GameOptions_ResourcePacks + ".add(\"fcpack_9\");" +  "System.out.println(\\\"fcpack_9\\\"); 
	    
	    
	    CtField f = cc.getField(MCObfList.GameOptions_ResourcePacks);
	    m.insertBefore("this." + MCObfList.GameOptions_ResourcePacks + ".add(\"fcpack_9\");");
	  //  cc.writeFile();
	    
	    //byte[] classFile = cc.toBytecode();
	    
	    
	    //inst.redefineClasses(new ClassDefinition(GameOptions.class, cc.toBytecode()));
	    
	    
	    //System.out.println(GameInjections.class.getClassLoader().getDefinedPackages());
	//	System.out.println(Arrays.toString(GameInjections.class.getClassLoader().getDefinedPackages()));

	    
	   cc.toClass(net.minecraft.client.option.GameOptions.class);

	    
	    
	    Method meth = net.minecraft.client.option.GameOptions.class.getDeclaredMethod("fcpackadd");
	    meth.invoke(null);
	    
	    
	    
		System.out.println(cc.toString());
			
			//MinecraftClient.getInstance().options.resourcePacks.add("fcpack_9");
			
		}catch (Throwable e)
		{
			e.printStackTrace();
		}
		
		*/
		
		
			
		
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
