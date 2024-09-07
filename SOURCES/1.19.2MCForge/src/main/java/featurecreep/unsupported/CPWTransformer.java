package featurecreep.unsupported;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import featurecreep.api.GameInjections;

public class CPWTransformer implements ITransformationService {

	public static int launchplugin = obtainirLaunchPlugin();
	
	
	
	public static int obtainirLaunchPlugin() {
		// TODO Auto-generated method stub
//		try {
			//https://github.com/xfl03/MoreCrashInfo/blob/master/2Loader/src/main/java/me/xfl03/morecrashinfo/modlauncher/ModuleHelper.java
//			Launcher launch = Launcher.INSTANCE;
//Field f_moduleLayerHandler =	Launcher.class.getDeclaredField("moduleLayerHandler");
//f_moduleLayerHandler.setAccessible(true);
//ModuleLayerHandler moduleLayerHandler = (ModuleLayerHandler)f_moduleLayerHandler.get(launch);
//URL urlSrg = CPWTransformer.class.getResource("/featurecreep/api/bg");
//Path pathSrg = Paths.get(URI.create(urlSrg.toString()));
//SecureJar secure = SecureJar.from(new Path[]{pathSrg});
//
//Method addToLayerDef = ModuleLayerHandler.class.getDeclaredMethod("addToLayer", Layer.class,SecureJar.class);
//addToLayerDef.setAccessible(true);
//addToLayerDef.invoke(moduleLayerHandler, Layer.GAME,secure);
//		
//		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		return 0;
	}
	
	
	@Override
	public void initialize(IEnvironment arg0) {
		// TODO Auto-generated method stub
		System.out.println("Initing");
	}

	

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "featurecreep";
	}

	@Override
	public void onLoad(IEnvironment arg0, Set<String> arg1) throws IncompatibleEnvironmentException {
		// TODO Auto-generated method stub
	}



	@Override
	public List<ITransformer> transformers() {
		// TODO Auto-generated method stub
		
		//actualmente nesesita estar despues de initalize por que usa funciones initalizado de initalize
	//	 LaunchActivities.preLaunchActivities();
	//	 ModuleLayer layer = Launcher.INSTANCE.findLayerManager().flatMap(lm -> lm.getLayer(IModuleLayerManager.Layer.GAME)).orElseThrow();
//		ClassLoader cargadora =  layer.modules().stream().findFirst().map(Module::getClassLoader).get();
		 ArrayList<ITransformer> transformers = new ArrayList<ITransformer>();
	//	transformers.add(new CPWITransformer());
		return transformers;
	}
	
	
	public static List<ITransformer> listo_de_transformers() {
		// TODO Auto-generated method stub
		
		//actualmente nesesita estar despues de initalize por que usa funciones initalizado de initalize
		 LaunchActivities.preLaunchActivities();
//		 ModuleLayer layer = Launcher.INSTANCE.findLayerManager().flatMap(lm -> lm.getLayer(IModuleLayerManager.Layer.GAME)).orElseThrow();
//		ClassLoader cargadora =  layer.modules().stream().findFirst().map(Module::getClassLoader).get();
		 ArrayList<ITransformer> transformers = new ArrayList<ITransformer>();
//		transformers.add(new CPWITransformer());
		return transformers;
	}
	
	public static class CPWITransformer implements ITransformer<ClassNode>{

		@Override
		public  ClassNode transform(ClassNode input, ITransformerVotingContext context) {
			// TODO Auto-generated method stub

			return transform(input);
		}

		
		
		public static ClassNode transform(ClassNode input) {
			// TODO Auto-generated method stub
			
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			input.accept(cw);

			ByteBuffer transformed = GameInjections.cargador.getMainTransformer().transform(CPWITransformer.class.getClassLoader(), // cambiar
					input.name.replace(".", "/"), CPWITransformer.class.getProtectionDomain(), // camiar
					ByteBuffer.wrap(cw.toByteArray()));

			ClassReader classReader = new ClassReader(transformed.array());

			// 使用ClassNode来接收解析后的类  
	        ClassNode classNode = new ClassNode();  
			
			classReader.accept(classNode, 0);
			
			
			
			return classNode;
		}

		
		
		
		@Override
		public  TransformerVoteResult castVote(ITransformerVotingContext context) {
			// TODO Auto-generated method stub
			return TransformerVoteResult.YES;
		}

		@Override
		public  Set<Target> targets() {
			// TODO Auto-generated method stub
		
			Set<Target> resulto = new HashSet<Target>();
			for(String target : TransformerTargets.getCPWTransformerTargets())
			{
				resulto.add(Target.targetClass(target));
			}
			
			return resulto;
		}
		
	}
	

}

