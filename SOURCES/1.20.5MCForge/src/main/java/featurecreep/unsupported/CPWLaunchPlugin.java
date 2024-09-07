package featurecreep.unsupported;

import java.util.EnumSet;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;

public class CPWLaunchPlugin implements ILaunchPluginService{

	@Override
	public String name() {
		// TODO Auto-generated method stub
		LaunchActivities.preLaunchActivities();
		return "featurecreep";
	}

	@Override
	public EnumSet<Phase> handlesClass(Type classType, boolean isEmpty) {
		// TODO Auto-generated method stub
		return EnumSet.of(Phase.BEFORE);
	}

	@Override
    public boolean processClass(final Phase phase, ClassNode classNode, final Type classType) {
        throw new IllegalStateException("YOU NEED TO OVERRIDE ONE OF THE processClass methods");
    }
	
	
}
