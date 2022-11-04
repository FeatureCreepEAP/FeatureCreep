package featurecreep;

import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;

public class FeatureCreep {

	public static String modid = "featurecreep";
	
	
	public static void onInitialise() {
		// TODO Auto-generated method stub
				System.out.println("Running FC on " + io.smallrye.common.os.OS.current() + " with Process ID " + io.smallrye.common.os.Process.getProcessId());

		FCCreativeTabs.onInitialise();
		FCItems.onInitialise();
		}
	
	
	
	
}
