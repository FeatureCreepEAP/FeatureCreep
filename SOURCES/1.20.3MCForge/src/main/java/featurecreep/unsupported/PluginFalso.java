package featurecreep.unsupported;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.transformers.MixinClassReader;

import featurecreep.api.GameInjections;
import featurecreep.mixin.CoreMod;

public class PluginFalso implements IMixinConfigPlugin{

	static{
		System.out.println("Config falso");
	}
	
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

	@Override
	public List<String> getMixins() {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		if(GameInjections.debug_mode)
			System.out.println("clase para transformacion: " + targetClassName);
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		targetClass.accept(cw);
//
//		
//		File old = new File("tmp/tmp/"+targetClassName+".class");
//		try {
//			old.createNewFile();
//			FileOutputStream outstream = new FileOutputStream(old);
//			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
//			byteout.write(cw.toByteArray());
//			byteout.writeTo(outstream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ByteBuffer pre = ByteBuffer.wrap(cw.toByteArray());
		
		ByteBuffer transformed = GameInjections.cargador.getMainTransformer().transform(CoreMod.class.getClassLoader(), // cambiar
				targetClassName.replace(".", "/"), CoreMod.class.getProtectionDomain(), // camiar
				pre);

		if(!pre.equals(transformed)) { //Solucion Temp
		
		ClassReader classReader = new MixinClassReader(transformed.array(), targetClassName);
		
		
		if(targetClass.fields!=null)
			targetClass.fields.clear();
		
		if(targetClass.methods!=null)
			targetClass.methods.clear();
		
		if(targetClass.invisibleAnnotations!=null)
			targetClass.invisibleAnnotations.clear();
		
		if(targetClass.visibleAnnotations!=null)
			targetClass.visibleAnnotations.clear();
		
		if(targetClass.innerClasses!=null)
			targetClass.innerClasses.clear();
		
		if(targetClass.attrs!=null)
			targetClass.attrs.clear();
		
		if(targetClass.interfaces!=null)
			targetClass.interfaces.clear();
		
		if(targetClass.invisibleTypeAnnotations!=null)
			targetClass.invisibleTypeAnnotations.clear();
		
	
		if(targetClass.visibleTypeAnnotations!=null)
			targetClass.visibleTypeAnnotations.clear();

		if(targetClass.permittedSubclasses!=null)
			targetClass.permittedSubclasses.clear();
		
		
		
		classReader.accept(targetClass, 0);
		
		
//		File nuevo = new File("tmp/tmp/"+targetClassName+"_nuevo.class");
//		try {
//			nuevo.createNewFile();
//			FileOutputStream outstream = new FileOutputStream(nuevo);
//			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
//			byteout.write(transformed.array());
//			byteout.writeTo(outstream);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		}
		
	}

}


