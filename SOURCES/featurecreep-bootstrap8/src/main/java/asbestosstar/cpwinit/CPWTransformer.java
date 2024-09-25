package asbestosstar.cpwinit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.objectweb.asm.tree.ClassNode;

import cpw.mods.modlauncher.api.IEnvironment;
//import cpw.mods.modlauncher.api.IModuleLayerManager;
//import cpw.mods.modlauncher.api.IModuleLayerManager.Layer;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.ITransformerVotingContext;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import cpw.mods.modlauncher.api.TransformerVoteResult;
import net.minecraftforge.fml.loading.FMLLoader;


public class CPWTransformer implements ITransformationService  {

	public static int launchplugin = obtainirLaunchPlugin();
//	ModuleLayer layer;
	//public static IModuleLayerManager layer_manager;
	public static boolean actividades_launch = false;
	public static ClassLoader cargadora_juego;
	public static Method def;
	public static File mod_jar;

	public static int obtainirLaunchPlugin() {
		// TODO Auto-generated method stub
		try {

			Enumeration<URL> resources = CPWTransformer.class.getClassLoader().getResources("META-INF/jarjar/");

			if (resources.hasMoreElements()) {

				URL url = resources.nextElement();
				if (url.getProtocol().equals("jar")) {

					String jarPath = url.getPath().substring(0, url.getPath().indexOf('!')); // 去除 jar:file: 和 ! 后的部分
					actividadesStream(new URL(jarPath).openStream());

				}else {
					File carpetera_mods = new File(Paths.get(System.getProperty("user.dir")).toString() + "/mods/");
					for (File potencial : carpetera_mods.listFiles()) {
						if(!potencial.isDirectory() && potencial.getName().endsWith(".jar")) {
						actividadesStream(new FileInputStream(potencial));
						}
						
					}
				
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	
	public static void actividadesStream(InputStream stream) {
		try (JarInputStream jar = new JarInputStream(stream)) {

			JarEntry entry;
			while ((entry = jar.getNextJarEntry()) != null) {

				if (entry.getName().startsWith("META-INF/jarjar/featurecreep") && entry.getName().endsWith(".noarch.fpm.jar") && !entry.isDirectory()) {
					// 使用 classLoader 来获取输入流，以便正确处理缓存等
					System.out.println(entry.getName());
					InputStream inputStream = CPWTransformer.class.getClassLoader()
							.getResourceAsStream(entry.getName());
					if (inputStream != null) {

						String output_location = Paths.get(System.getProperty("user.dir")).toString()
								+ "/mods/";

						BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
						String simple = entry.getName().split("/")[entry.getName().split("/").length - 1];
						File out = new File(output_location + simple);
						mod_jar = out;
						FileOutputStream fileOutputStream = new FileOutputStream(out);
						byte[] buffer = new byte[1024];
						int len;
						while ((len = bufferedInputStream.read(buffer)) > 0) {
							fileOutputStream.write(buffer, 0, len);
						}

						fileOutputStream.close();

					}

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(IEnvironment arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "featurecreep";
	}

	@Override
	public void onLoad(IEnvironment arg0, Set<String> arg1) throws IncompatibleEnvironmentException {
		// TODO Auto-generated method stub
		//layer_manager = arg0.findModuleLayerManager().get();
	}


	@Override
	public List<ITransformer> transformers() {
		// TODO Auto-generated method stub

		// actualmente nesesita estar despues de initalize por que usa funciones
		// initalizado de initalize
		// LaunchActivities.preLaunchActivities();

		// ModuleLayer layer = Launcher.INSTANCE.findLayerManager().flatMap(lm ->
		// lm.getLayer(IModuleLayerManager.Layer.GAME)).orElseThrow();

//		try {
//			Field trans = Launcher.class.getDeclaredField("classLoader");
//			trans.setAccessible(true);
//			//(ClassLoader) trans.get(Launcher.INSTANCE);
//			ClassLoader cargadora =  layer.modules().stream().findFirst().map(Module::getClassLoader).get();
//			Class clase = Class.forName("featurecreep.unsupported.CPWTransformer", true, cargadora);
//			Method def = clase.getDeclaredMethod("listo_de_transformers", null);
//			return (List)def.invoke(null, null);
//		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		ArrayList<ITransformer> transformers = new ArrayList<ITransformer>();
		transformers.add(new CPWITransformer());
		return transformers;
		// return new ArrayList<ITransformer>();
	}

	public class CPWITransformer implements ITransformer<ClassNode> {

		@Override
		public ClassNode transform(ClassNode input, ITransformerVotingContext context) {
			// TODO Auto-generated method stub
			if (!CPWTransformer.actividades_launch) {
				System.out.println("*****************************************************************" + input.name);
				CPWTransformer.actividades_launch = true;

		//		ModuleLayer layer = CPWTransformer.layer_manager.getLayer(Layer.GAME).get();
				CPWTransformer.cargadora_juego = FMLLoader.getLaunchClassLoader(); //getClass().getClassLoader(); //layer.modules().stream().findFirst().map(Module::getClassLoader).get();
				try {
					Class actividates_prelaunch = Class.forName("featurecreep.unsupported.LaunchActivities", true,
							CPWTransformer.cargadora_juego);
					actividates_prelaunch.getDeclaredMethod("preLaunchActivities", null).invoke(null, null);

					Class transformer = Class.forName("featurecreep.unsupported.CPWTransformer$CPWITransformer", true,
							CPWTransformer.cargadora_juego);
					CPWTransformer.def = transformer.getDeclaredMethod("transform", ClassNode.class);

				} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (CPWTransformer.actividades_launch && CPWTransformer.def != null) {
				try {
					return (ClassNode) CPWTransformer.def.invoke(null, input);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block

					e.printStackTrace();
					return input;

				}
			}

			return input;
		}

//		public static boolean esBoloqued(String nombre) {
//			ArrayList<String> list = new ArrayList<String>();
////			list.add("featurecreep/");
////			// list.add("featurecreep/loader");
////			list.add("org/jboss/modules");
////			list.add("org/jboss/logging");
////			list.add("org/jboss/logmanager");
////			list.add("asbestossstar/");
////			list.add("com/asbestosstar/assistremapper");
////			list.add("com/asbestosstar/AssistMixer");
////			list.add("com/asbestosstar/dnfjava");
////			list.add("com/asbestosstar/rpmjava");
////			list.add("com/asbestosstar/shadowassist");
////			list.add("com/asbestosstar/cpiojava");
////			list.add("com/asbestosstar/mixerlogger");
////			list.add("featurecreep/unsupported");
////			// list.add("featurecreep/api/bg/BGSide");
////			list.add("fpmbuild");
////			list.add("javassist");
//
////Nesesito Cambiar por la transformacion de las clases de FeatureCreep
//
//			for (String paquete : list) {
//				if (nombre.startsWith(paquete)) {
//					return true;
//				}
//			}
//
//			return false;
//		}

		@Override
		public TransformerVoteResult castVote(ITransformerVotingContext context) {
			// TODO Auto-generated method stub
			return TransformerVoteResult.YES;
		}

		@Override
		public Set<Target> targets() {
			// TODO Auto-generated method stub
			Set<Target> resulto = new HashSet<Target>();
			for (String target : TargetGetter.obtainirTodos()) {
				resulto.add(Target.targetClass(target));
			}

			return resulto;
		}
		

		

	}

	@Override
	public void beginScanning(IEnvironment environment) {
		// TODO Auto-generated method stub
		
	}

//	public class CPWITransformer implements ITransformer<ClassNode>{
//
//		@Override
//		public  ClassNode transform(ClassNode input, ITransformerVotingContext context) {
//			// TODO Auto-generated method stub
//			
//			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//			input.accept(cw);
//
//			ByteBuffer transformed = GameInjections.cargador.getMainTransformer().transform(CPWITransformer.class.getClassLoader(), // cambiar
//					input.name.replace(".", "/"), CPWITransformer.class.getProtectionDomain(), // camiar
//					ByteBuffer.wrap(cw.toByteArray()));
//
//			ClassReader classReader = new ClassReader(transformed.array());
//
//			// 使用ClassNode来接收解析后的类  
//	        ClassNode classNode = new ClassNode();  
//			
//			classReader.accept(classNode, 0);
//			
//			
//			
//			return classNode;
//		}
//
//		@Override
//		public  TransformerVoteResult castVote(ITransformerVotingContext context) {
//			// TODO Auto-generated method stub
//			return TransformerVoteResult.YES;
//		}
//
//		@Override
//		public  Set<Target> targets() {
//			// TODO Auto-generated method stub
//		
//			Set<Target> resulto = new HashSet<Target>();
//			for(String target : TransformerTargets.getCPWTransformerTargets())
//			{
//				resulto.add(Target.targetClass(target));
//			}
//			
//			return resulto;
//		}
//		
//	}
//	

}
