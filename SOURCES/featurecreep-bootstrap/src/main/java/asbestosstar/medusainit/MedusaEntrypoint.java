package asbestosstar.medusainit;

import asbestosstar.bootstrap.BootstrapCommon;
import net.neoforged.neoforgespi.earlywindow.GraphicsBootstrapper;

public class MedusaEntrypoint  implements GraphicsBootstrapper{

	
	public static boolean init_agent=BootstrapCommon.initDefault();

	
	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "featurecreep";
	}

	@Override
	public void bootstrap(String[] arguments) {
		// TODO Auto-generated method stub
		
	}

}
