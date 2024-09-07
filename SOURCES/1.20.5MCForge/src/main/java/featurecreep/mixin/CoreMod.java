package featurecreep.mixin;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.jboss.dmr.ModelNode;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import cpw.mods.modlauncher.TransformingClassLoader;
import featurecreep.api.GameInjections;
import featurecreep.unsupported.LaunchActivities;
import featurecreep.unsupported.SpongeMixinUtils;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.MemberValue;
import javassist.bytecode.annotation.StringMemberValue;

public class CoreMod implements IMixinConfigPlugin {

	public String main = LaunchActivities.preLaunchActivities();

	@Override
	public void onLoad(String mixinPackage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getRefMapperConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// TODO Auto-generated method stub

	}

	// ¡Asco! Pero finalmente funciona, nesesito volver a escribo. Mucho de este
	// existe porque la seguridad de SpongeMixin hace mas dificil
	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub

		List<String> fakemixins = new ArrayList<String>();

		if (!GameInjections.agente_init) {
			GameInjections.agente_init = true;

			ClassFile archivo = new ClassFile(true, "falso.spongemixin.Falso", "java.lang.Object");
			ConstPool pool = archivo.getConstPool();

			ArrayMemberValue objectivo = new ArrayMemberValue(new StringMemberValue(pool), pool);

			ArrayList<MemberValue> nombres_clases = new ArrayList<MemberValue>();
			for (String clase : SpongeMixinUtils.getSpongeMixinClassTargets()) {
				nombres_clases.add(new StringMemberValue(clase, pool));
			}
			MemberValue[] arr = nombres_clases.toArray(new MemberValue[0]);
			Annotation mixinan = new Annotation("org.spongepowered.asm.mixin.Mixin", pool);
			mixinan.addMemberValue("targets", objectivo);
			objectivo.setValue(arr);
			AnnotationsAttribute atr = new AnnotationsAttribute(pool, AnnotationsAttribute.invisibleTag);
			atr.addAnnotation(mixinan);
			archivo.addAttribute(atr);

			try {

				ModelNode node = new ModelNode();
				node.get("required").set(true);
				node.get("minVersion").set("0.1");
				node.get("package").set("falso.spongemixin");
				node.get("compatibilityLevel").set("JAVA_6");
				node.get("mixins").add("Falso");
				node.get("plugin").set("featurecreep.unsupported.PluginFalso");

				File jar = new File("tmp/smfalsofc.jar");
				try (JarOutputStream jarOut = new JarOutputStream(Files.newOutputStream(jar.toPath()))) {

// 添加.class文件到JAR  

					JarEntry classEntry = new JarEntry("falso/spongemixin/Falso.class");

					jarOut.putNextEntry(classEntry);

					archivo.write(new DataOutputStream(jarOut));

					jarOut.closeEntry();

// 添加JSON文件到JAR（如果需要作为资源加载）  

					JarEntry jsonEntry = new JarEntry("falsosm.json");

					jarOut.putNextEntry(jsonEntry);

// 使用OutputStreamWriter将OutputStream转换为Writer，以便我们可以写入字符  
					// 这里我们指定了字符集为UTF-8
					Writer writer = new OutputStreamWriter(jarOut, StandardCharsets.UTF_8);

					// 使用BufferedWriter包装Writer，以提高写入性能（尽管对于小量数据可能不明显）
					// 如果不需要缓冲，可以直接使用writer
					// Writer bufferedWriter = new BufferedWriter(writer);

					// 直接使用writer写入字符串
					writer.write(node.toJSONString(true));

					// 如果使用了BufferedWriter，需要调用flush()方法确保所有数据都被写入
					writer.flush();

					// 关闭Writer，这将自动关闭底层的OutputStream（如果它们被包装在一起）
					writer.close();

//Files.copy(jsonInJarPath, jarOut);  
					jarOut.flush();
//jarOut.closeEntry();  

				}

				Class clasedeclassloader = CoreMod.class.getClassLoader().getClass();
				Method def = null;
				try {
					def = clasedeclassloader.getDeclaredMethod("addUrlFwd", URL.class);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					try {
						def = clasedeclassloader.getDeclaredMethod("addUrl", URL.class);
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
				
						try {
							def = clasedeclassloader.getDeclaredMethod("addUrl", URL.class);
						} catch (NoSuchMethodException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					
					
					
					
					}
					// e.printStackTrace();
				}

				URL url = jar.toURL();

				System.out.println(url);
				def.setAccessible(true);
				def.invoke(CoreMod.class.getClassLoader(), url);
				Mixins.addConfiguration("falsosm.json");
				// fakemixins.add("Falso");
			} catch (IOException | SecurityException | IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return fakemixins;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub

	}

}
