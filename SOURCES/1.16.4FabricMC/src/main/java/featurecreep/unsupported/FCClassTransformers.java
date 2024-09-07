package featurecreep.unsupported;

import java.nio.ByteBuffer;
import java.security.ProtectionDomain;

import org.jboss.modules.ClassTransformer;

import featurecreep.api.GameInjections;

public class FCClassTransformers implements ClassTransformer{

	@Override
	public ByteBuffer transform(ClassLoader loader, String className, ProtectionDomain protectionDomain,
			ByteBuffer classBytes) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(!className.equals("featurecreep/api/GameInjections") && !className.equals("featurecreep/unsupported") &&	!className.startsWith("javassist") && !className.startsWith("asbestosstar") && !className.startsWith("com/asbestosstar")) {
		return GameInjections.inject(className.replace("/", "."), classBytes);
		}
		return classBytes;
	}

	
	
	
	
}
