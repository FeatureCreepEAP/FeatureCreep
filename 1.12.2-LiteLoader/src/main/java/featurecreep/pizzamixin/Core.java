package featurecreep.pizzamixin;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
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
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.entity.player.EntityPlayer$EnumChatVisibility",SerializationUtils.serialize(net.minecraft.entity.player.EntityPlayer.EnumChatVisibility.class)));
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.util.EnumHandSide",SerializationUtils.serialize(net.minecraft.util.EnumHandSide.class)));
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.tutorial.TutorialSteps",SerializationUtils.serialize(net.minecraft.client.tutorial.TutorialSteps.class)));
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.settings.KeyBinding",SerializationUtils.serialize(net.minecraft.client.settings.KeyBinding.class)));
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.client.Minecraft",SerializationUtils.serialize(net.minecraft.client.Minecraft.class)));
//
//    	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.world.EnumDifficulty",SerializationUtils.serialize(net.minecraft.world.EnumDifficulty.class)));

    	
	    
	    
	    
	    pool.appendSystemPath();
	    CtClass cc = pool.get("net.minecraft.client.settings.GameSettings");
	    CtMethod m = cc.getDeclaredMethod("func_74300_a");

	    m.insertAfter( "this.field_151453_l.add(\"fcpack_3\"); System.out.println(\"Adding FCPack3 Boycott Modrinth\");", true);
	    m.insertAfter( "this.field_151453_l.add(\"fcdatapack3\"); System.out.println(\"Adding FCDataPack3 Boycott Modrinth\");", true);

	    
	    arr =  cc.toBytecode();

			
		}catch (Throwable e)
		{
			e.printStackTrace();
		}
		
	
		
		System.out.println("Yay Javaassist Worked!, though its capability is limted");

			return arr;

	}
	
	
	public byte[] biomeDecorationsInjections(byte[] basicClass) {
		// TODO Auto-generated method stub
	    try {
			ClassPool pool = ClassPool.getDefault();
			pool.appendSystemPath();
			pool.insertClassPath(new ByteArrayClassPath("net.minecraft.world.biome.BiomeDecorator", basicClass));
		//	pool.insertClassPath(new ByteArrayClassPath("net.minecraft.world.World",SerializationUtils.serialize(Class.forName("net.minecraft.world.World"))));
	    	//pool.insertClassPath(new ByteArrayClassPath("featurecreep.api.orespawn.OrespawnBasicFeatureParser",SerializationUtils.serialize(featurecreep.api.orespawn.OrespawnBasicFeatureParser.class)));

CtClass osclass = pool.makeClass("featurecreep.api.bg.orespawn.OrespawnBasicFeatureParser")	;
CtMethod apply = CtNewMethod.make("public static void applyOres(net.minecraft.world.World world, java.util.Random rand, net.minecraft.world.biome.BiomeDecorator dec){}", osclass);
osclass.addMethod(apply);
			
	    	CtClass cc = pool.get("net.minecraft.world.biome.BiomeDecorator");
			CtMethod meth = cc.getDeclaredMethod("func_76797_b");
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

		if (transformedName.equals("net.minecraft.client.settings.GameSettings"))
		{
			return GameOptionsInjection(basicClass);
		//return PizzaMixer.mix(TitleScreenMixin.class, basicClass);
		}else if (transformedName.equals("net.minecraft.world.biome.BiomeDecorator")) {
			return	biomeDecorationsInjections(basicClass);
		}else {
			//System.out.println(transformedName);
		if(!transformedName.contains("javassist."))//Gotta change this later
		{
			return addtopool(basicClass, transformedName);
		}
		
		}
		
	return basicClass;
		
	}



	
	
}
