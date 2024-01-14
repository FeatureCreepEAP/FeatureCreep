package featurecreep;

import java.util.Map;

import featurecreep.pizzamixin.Core;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

//From Resource-Loader
@IFMLLoadingPlugin.SortingIndex(1001)
public class FCCoreMod implements IFMLLoadingPlugin{

	
	

	public String[] getASMTransformerClass()
	{
		return new String[] { Core.class.getName() };
	}

	@Override
	public String getModContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}




	
}
