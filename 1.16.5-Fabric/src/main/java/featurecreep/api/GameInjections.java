package featurecreep.api;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.options.GameOptions;

public class GameInjections {

	public static void inject()
	{
		try {
			GameOptionsInjection();
			TitleScreenInjection();
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void GameOptionsInjection() throws NotFoundException, CannotCompileException, IOException
	{
	    ClassPool pool = ClassPool.getDefault();
	    CtClass cc = pool.get(GameOptions.class.getCanonicalName());
	    CtMethod m = cc.getDeclaredMethod("load");
	    CtField f = cc.getField("resourcePacks");
	    m.insertBefore("{this.resourcePacks.add(featurecreep.api.PackLoader.fc_pack.getName());}");
	    cc.writeFile();
		System.out.println(cc.toString());
	}
	

	public static void TitleScreenInjection() throws NotFoundException, CannotCompileException, IOException
	{
		
	    ClassPool pool = ClassPool.getDefault();
	    CtClass cc = pool.get(TitleScreen.class.getCanonicalName());
	    CtMethod m = cc.getDeclaredMethod("init");
	    m.insertBefore("System.out.println(featurecreep.FeatureCreep.modpath);");
	    cc.writeFile();
	}
	
	
	
	
}
