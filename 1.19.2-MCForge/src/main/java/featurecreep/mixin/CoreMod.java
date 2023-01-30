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
import javassist.CtMethod;
import javassist.NotFoundException;

public class CoreMod implements IMixinConfigPlugin{


	  public byte[] transform(String name, String transformedName, byte[] basicClass) {

		  
		  
		  
		   if (transformedName.equals("net.minecraft.server.packs.repository.PackRepository"))
		    {
		    	return transformresourcemanager(basicClass);
		    }

		    return basicClass;

		  }
	
	
	
	
	
	
	
public byte[] transformresourcemanager(byte[] basicClass) {
		// TODO Auto-generated method stub
		
	 try {
			ClassPool pool = ClassPool.getDefault();
			  pool.insertClassPath(new ByteArrayClassPath("net.minecraft.server.packs.repository.PackRepository", basicClass));

			  pool.appendSystemPath();
			  CtClass cc = pool.get("net.minecraft.server.packs.repository.PackRepository");
			  CtMethod m = cc.getDeclaredMethod("m_10506_");
			  m.insertBefore("System.out.println(\"Testin JA\");");
			  m.insertBefore("f_10497_.add(new featurecreep.api.bg.FCPackLoad(new java.io.File(featurecreep.api.bg.datapacks.DataPackLoader.datapacklocation)));");
System.out.println("Injecting Datapack");
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
