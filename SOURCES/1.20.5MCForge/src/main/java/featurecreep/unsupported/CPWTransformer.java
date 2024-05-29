package featurecreep.unsupported;

import java.util.List;
import java.util.Set;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import featurecreep.mixin.CoreMod;

public class CPWTransformer implements ITransformationService {

	// String main = CoreMod.getMain();

	@Override
	public void initialize(IEnvironment arg0) {
		// TODO Auto-generated method stub
		System.out.println("Initing");
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "featurecreep";
	}

	@Override
	public void onLoad(IEnvironment arg0, Set<String> arg1) throws IncompatibleEnvironmentException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ITransformer> transformers() {
		// TODO Auto-generated method stub
		return List.of();
	}

}
