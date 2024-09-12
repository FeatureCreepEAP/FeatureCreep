package featurecreep.unsupported;

public class LiteLoaderTransformer extends FCLaunchWrapperTransformer {

	public String main = getMain();

	public String getMain() {
		// TODO Auto-generated method stub

		LaunchActivities.preLaunchActivities();
		return "OverWorldBiomeCreator";
	}


}
