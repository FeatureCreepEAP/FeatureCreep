package featurecreep.pizzamixin;

import org.apache.commons.lang3.SerializationUtils;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.launchwrapper.IClassTransformer;

public class Core implements IClassTransformer{

	
	
	public static byte[] GameOptionsInjection(byte[] bites)
	{
		
byte[] arr = bites;
		
		System.out.println("Starting Javaassit");
		
		try {

			
			
			
System.out.println("trying to inject");			
			
			
			//this was hard with the weirdness of Lists
	
		
			
	    ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.settings.GameSettings", arr));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.entity.player.EnumPlayerModelParts",SerializationUtils.serialize(EnumPlayerModelParts.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.entity.player.EntityPlayer$EnumChatVisibility",SerializationUtils.serialize(net.minecraft.entity.player.EntityPlayer.EnumChatVisibility.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.util.EnumHandSide",SerializationUtils.serialize(net.minecraft.util.EnumHandSide.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.tutorial.TutorialSteps",SerializationUtils.serialize(net.minecraft.client.tutorial.TutorialSteps.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.settings.KeyBinding",SerializationUtils.serialize(net.minecraft.client.settings.KeyBinding.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.Minecraft",SerializationUtils.serialize(net.minecraft.client.Minecraft.class)));

    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.world.EnumDifficulty",SerializationUtils.serialize(net.minecraft.world.EnumDifficulty.class)));

    	
	    
	    
	    
	    pool.appendSystemPath();
	    CtClass cc = pool.get("net.minecraft.client.settings.GameSettings");
	    CtMethod m = cc.getDeclaredMethod("func_74300_a");
//	   CtMethod m = CtNewMethod.make(
 //               "public void fcpackadd() {}",
  //              cc);

	    //" + "this." + MCObfList.GameOptions_ResourcePacks + ".add(\"fcpack_9\");" +  "System.out.println(\\\"fcpack_9\\\"); 
	    
	    
//	    CtField f = cc.getField(MCObfList.GameOptions_ResourcePacks);
	    m.insertAfter( "this.field_151453_l.add(\"fcpack_3\"); System.out.println(\"Adding FCPack3 Boycott Modrinth\");", true);
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
	

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		// TODO Auto-generated method stub

		if (transformedName.equals("net.minecraft.client.settings.GameSettings"))
		{
			return GameOptionsInjection(basicClass);
		//return PizzaMixer.mix(TitleScreenMixin.class, basicClass);
		}
		
		return basicClass;
		
	}
	
	
}
