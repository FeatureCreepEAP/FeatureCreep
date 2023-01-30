package featurecreep.mixin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.transformers.MixinClassReader;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

public class CoreMod implements IMixinConfigPlugin {

  public String fakespongemixingen(String classname) {

    ClassPool pool = ClassPool.getDefault();
    CtClass clazz = pool.makeClass("featurecreep.mixin." + classname + "_fakemixin");
    ClassFile clazz_file = clazz.getClassFile();

    //https://stackoverflow.com/questions/50621480/adding-an-annotation-to-a-class-using-javassist
    ConstPool constpool = clazz_file.getConstPool();
    AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
    Annotation annotation = new Annotation("org.spongepowered.asm.mixin.Mixin", constpool);
    //annotation.addMemberValue("frequency", new IntegerMemberValue(clazz_file.getConstPool(), frequency));
    annotationsAttribute.setAnnotation(annotation);
    clazz_file.addAttribute(annotationsAttribute);

    try {
      clazz.writeFile();

      clazz.toClass();

    } catch (CannotCompileException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "featurecreep.mixin." + classname + "_fakemixin";

  }

  public byte[] titlescreenja(byte[] arr) {

    try {
      ClassPool pool = ClassPool.getDefault();
      pool.insertClassPath(new ByteArrayClassPath("net.minecraft.class_442", arr));

      pool.appendSystemPath();
      CtClass cc = pool.get("net.minecraft.class_442");
      CtMethod m = cc.getDeclaredMethod("method_25426");

      m.insertBefore("System.out.println(\"Testin JA\");");

      arr = cc.toBytecode();

      
    } catch (Throwable e) {
      e.printStackTrace();
    }

    System.out.println("Yay Javaassist Worked!, though its capability is limted");

    
    
    return arr;

  }

  public byte[] overworldbiomecreation(byte[] basicClass) {
    // TODO Auto-generated method stub
/*
    try {
      ClassPool pool = ClassPool.getDefault();
      pool.insertClassPath(new ByteArrayClassPath("net.minecraft.class_5478", basicClass));

      pool.appendSystemPath();
      CtClass cc = pool.get("net.minecraft.class_5478");
      CtMethod m = cc.getDeclaredMethod("method_39151");

      m.insertBefore("featurecreep.api.orespawn.OrespawnBasicFeatureParser.spawnOre($7);");

      m.insertBefore("System.out.println(\"Adding FCOres\");");

      basicClass = cc.toBytecode();
      cc.writeFile();

    } catch (Throwable e) {
      e.printStackTrace();
    }

    System.out.println("Yay Javaassist Worked!, though its capability is limted"); we dont need to do anything
*/
    return basicClass;

  }

  public byte[] transform(String name, String transformedName, byte[] basicClass) {

	  
	  
	  
    if (transformedName.equals("net.minecraft.class_442")) {
      return titlescreenja(basicClass);
    } else if (transformedName.equals("net.minecraft.class_5478")) {
      return overworldbiomecreation(basicClass);
    }else if (transformedName.equals("net.minecraft.class_3283"))
    {
    	return transformresourcemanager(basicClass);
    }

    return basicClass;

  }

  public byte[] transformresourcemanager(byte[] basicClass) {
	// TODO Auto-generated method stub
	  
	  
      try {
		ClassPool pool = ClassPool.getDefault();
		  pool.insertClassPath(new ByteArrayClassPath("net.minecraft.class_3283", basicClass));

		  pool.appendSystemPath();
		  CtClass cc = pool.get("net.minecraft.class_3283");
		//  CtMethod m = CtNewMethod.make(
		 //         "public void registerFCPack() { field_14227.add(new featurecreep.api.FCPackLoad(new java.io.File(featurecreep.api.datapacks.DataPackLoader.datapacklocation))); }",
		  //        cc);

		  CtMethod m = cc.getDeclaredMethod("method_14445");
		  
		  m.insertBefore("System.out.println(\"Testin JA\");");
		  m.insertBefore("field_14227.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));");
		  //cc.addMethod(m);
		  CtConstructor cons = cc.getDeclaredConstructors()[0];
	  cons.insertAfter("field_14227 = featurecreep.api.io.BasicIO.setFromArray($1);");
		  return cc.toBytecode();//SHould maybe also be redefining the providers in the constructor, but it works on TLauncher so maybe it works everywhere on fabric without it, ill still include it anyhow, may remove it if iknow it conflicts with some mods
	} catch (NotFoundException | CannotCompileException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  
      
	  
	  return null;
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
  public void acceptTargets(Set < String > myTargets, Set < String > otherTargets) {
    // TODO Auto-generated method stub

  }

  @Override
  public List < String > getMixins() {
    // TODO Auto-generated method stub

    List < String > fakemixins = new ArrayList < String > ();
    fakemixins.add(fakespongemixingen("net.minecraft.class_1792"));
   fakemixins.add(("featurecreep.mixin.OverworldBiomeCreatorBlank"));//Doesnt work

    
    return fakemixins;
  }

  @Override
  public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    // TODO Auto-generated method stub

 

    System.out.println(targetClassName);
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    targetClass.accept(cw);
    ClassReader classReader = new MixinClassReader(transform(targetClassName, targetClassName, cw.toByteArray()), targetClassName);

    targetClass.fields.removeAll(targetClass.fields);
    targetClass.methods.removeAll(targetClass.methods);

    classReader.accept(targetClass, 0);

  }

  @Override
  public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    // TODO Auto-generated method stub

  }

}