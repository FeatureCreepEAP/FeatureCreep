package featurecreep.unsupported;

import java.nio.ByteBuffer;

import featurecreep.api.GameInjections;
import net.minecraft.launchwrapper.IClassTransformer;
//import obf.class_unknown_1559;

public class FCLaunchWrapperTransformer implements IClassTransformer{

	
	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		// TODO Auto-generated method stub

		ByteBuffer transformed = GameInjections.cargador.getMainTransformer().transform(
				FCLaunchWrapperTransformer.class.getClassLoader(), // cambiar
				transformedName.replace(".", "/"), FCLaunchWrapperTransformer.class.getProtectionDomain(), // camiar
				ByteBuffer.wrap(basicClass));

		return transformed.array();

	}

	
	
}
