package featurecreep.mixin;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class CoreMod implements IMixinConfigPlugin{


	  public byte[] transform(String name, String transformedName, byte[] basicClass) {

		  
		  if (transformedName.equals("net.minecraft.class_1792")){
			  return itemtransformer(name, basicClass);
		  }
		  
		

		    return basicClass;

		  }








private byte[] itemtransformer(String name, byte[] basicClass) {
		// TODO Auto-generated method stub
	 ClassPool pool = ClassPool.getDefault();
	    pool.insertClassPath(new ByteArrayClassPath(name, basicClass));
	    pool.appendSystemPath();
	    CtClass cc;
	    try {
	      cc = pool.get(name);
	      cc.defrost();
	     CtClass state = pool.makeClass("net.minecraft.class_2680");
	     pool.makeClass("net.minecraft.class_2248");
	     pool.makeClass("featurecreep.api.bg.blocks.FCBlockAPI");
	     state.addMethod(CtNewMethod.make("public net.minecraft.class_2248 method_11614(){return null;}", state));
	      CtMethod c = cc.getDeclaredMethod("method_7856");

	      //c.insertAfter("featurecreep.api.orespawn.OrespawnBasicFeatureParser.place(this);");
	      c.insertBefore("System.out.println(\"Testing\");" +
	        "if ($1.method_11614() instanceof featurecreep.api.bg.blocks.FCBlockAPI){return true;}" +
	        "");

	      System.out.println("JA");
	      //    cc.writeFile("net");
	      return cc.toBytecode();
	    } catch (NotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (CannotCompileException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }

	    return basicClass;
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
	public List<String> getMixins() {
		// TODO Auto-generated method stub
		return null;
	}









	@Override
	public void postApply(String targetClassName, org.spongepowered.asm.lib.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	}








	@Override
	public void preApply(String targetClassName, org.spongepowered.asm.lib.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
	    System.out.println(targetClassName);
	    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
	    targetClass.accept(cw);
	    ClassReader classReader = new ClassReader(transform(targetClassName, targetClassName, cw.toByteArray()));

	    targetClass.fields.removeAll(targetClass.fields);
	    targetClass.methods.removeAll(targetClass.methods);

	    classReader.accept(targetClass, 0);
		
		
	}

}
