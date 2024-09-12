package featurecreep.unsupported;

import java.util.Map;

import featurecreep.unsupported.FCLaunchWrapperTransformer;
import featurecreep.unsupported.LaunchActivities;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

//From Resource-Loader
@IFMLLoadingPlugin.SortingIndex(1001)
public class FCIFMLLoadingPlugin implements IFMLLoadingPlugin{
	  public String main = getMain();

	public String[] getASMTransformerClass()
	{
		return new String[] { FCLaunchWrapperTransformer.class.getName() };
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




	
	
    public String getMain() {
    // TODO Auto-generated method stub

	LaunchActivities.preLaunchActivities();
    return "OverWorldBiomeCreator";
  }

	
	
	
	
}
