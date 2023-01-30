package featurecreep;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import org.jboss.modules.ModuleLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.util.HotSwapper;
import net.minecraft.Bootstrap;
import net.minecraft.MinecraftVersion;
import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;



//I need to eventually make a JBoss Modules wrapper around this
public class FeatureCreepMCInit {

	
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
public ModuleLoader modloader;

public static Path gamepath = Paths.get(System.getProperty("user.dir"));
protected static String modpath = gamepath.toString() + "/mods/";
	
//Gotta soon make these the actual ones once i figure out the args correctly

	public static void main(String[] args) {

		
		LOGGER.info("StartingMCClass");
		HotSwapper hs = null;
		try {new TitleScreen().tick();}catch(Exception e) {}
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
		
		
		try {Bootstrap.println("Adding FC Init to BootStrap");}catch(Exception e) {}// Dunno Why this method even exist in bootstrap, it is just System.out.println, but it works and is the most simple
		try {
			CtClass bootstrap = pool.get(Bootstrap.class.getCanonicalName());
		//	bootstrap.getDeclaredMethod("a").insertAfter("if (!b){featurecreep.FeatureCreep.onInitialise();}");
			bootstrap.getDeclaredMethod("a").insertAfter("featurecreep.FeatureCreep.onInitialise();");
			bootstrap.getDeclaredMethod("a", new CtClass[]{pool.get(Supplier.class.getCanonicalName())}).setBody(null);//Yay I can search for methods by name with array
			System.out.println("Reloading Bootstrap Class");
			hs.reload(Bootstrap.class.getCanonicalName(), bootstrap.toBytecode());
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Setting Shared Game Version Constant");
		SharedConstants.setGameVersion(MinecraftVersion.create());
		System.out.println("Adding Registry Injection");

		try {Registry.ACTIVITY.hashCode();}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(Registry.class.getCanonicalName());
			registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
		//	registry.getDeclaredMethod("j").setBody("return this;");
			System.out.println("Generating Bytecode");
			byte[] code = registry.toBytecode();
			System.out.println("Reloading Registry Class");
			hs.reload(Registry.class.getCanonicalName(), code);
		} catch (NotFoundException | CannotCompileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Simple Registry Injection");
		try {SimpleRegistry.ACTIVITY.hashCode();}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(SimpleRegistry.class.getCanonicalName());
			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
			registry.getDeclaredMethod("j").setBody("return this;");
			System.out.println("Generating Bytecode");
			byte[] code = registry.toBytecode();
			System.out.println("Reloading SimpleRegistry Class");
			hs.reload(SimpleRegistry.class.getCanonicalName(), code);
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
		
		
		
		try {ChunkGenerator.method_41049(null, null);}catch(Exception e) {}// Do nothing
		try {
			
			CtClass registry = pool.get(ChunkGenerator.class.getCanonicalName());
			//registry.getDeclaredMethod("i").setBody(null);//Null returns null or 0 or does nothing which is very convinient indeed, turned out we do not really need this one because we are disabling all freeze instances, but eh who cares now lets not remove it
		//	registry.getDeclaredMethod("e", new CtClass[] {pool.get(GenerationSettings.Builder.class.getCanonicalName())}).insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.applyOres($1);");
			registry.getDeclaredMethod("a", new CtClass[] {pool.get(StructureWorldAccess.class.getCanonicalName()),pool.get(Chunk.class.getCanonicalName()), pool.get(StructureAccessor.class.getCanonicalName())}).insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.applyOres($0, $1, $2, $3);");//Dunno how this worked, according to wag your tail mapping viewer 1 should be the one with GenSettingBuilder but i tried 0 expecting to fail but it worked, maybe wag tail does not do in correct order or it was another one that genbuilder with the name e? Not the 1st time WagYourTail mapping viewer had out of order for obf
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
		
try {new ResourcePackManager((ResourcePackProfile.Factory)null, null);}catch(Exception e) {}// Do nothing
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
		
		
		
		System.out.println("Starting Game");
		net.minecraft.client.main.Main.main(args);


		
	}



}
