package featurecreep.mixin;

import java.io.IOException;
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
import javassist.NotFoundException;

public class CoreMod implements IMixinConfigPlugin{


	  public byte[] transform(String name, String transformedName, byte[] basicClass) {

		  
		  
		  
		   if (transformedName.equals("net.minecraft.class_3283"))
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
			  CtMethod m = cc.getDeclaredMethod("method_14445");
			  m.insertBefore("System.out.println(\"Testin JA\");");
			  
			  m.insertBefore("field_14227.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));");
CtConstructor cons = cc.getDeclaredConstructors()[0];
cons.insertAfter("field_14227 = featurecreep.api.io.BasicIO.setFromArray($2);");

			  
			  return cc.toBytecode();
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
	public List<String> getMixins() {
		// TODO Auto-generated method stub
		return null;
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
