package featurecreep.mixin;

import java.util.List;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class CoreMod implements IMixinConfigPlugin{


	  public byte[] transform(String name, String transformedName, byte[] basicClass) {

		  
		  
		  
		

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
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	    System.out.println(targetClassName);
	    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
	    targetClass.accept(cw);
	    ClassReader classReader = new ClassReader(transform(targetClassName, targetClassName, cw.toByteArray()));

	    targetClass.fields.removeAll(targetClass.fields);
	    targetClass.methods.removeAll(targetClass.methods);

	    classReader.accept(targetClass, 0);
		
		
		
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// TODO Auto-generated method stub
		
	}

}
