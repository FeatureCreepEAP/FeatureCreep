package dmrtest0;

import featurecreep.api.DatafiedObjectRegistration;
import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.ui.FCCreativeTabs;
import featurecreep.content.FCItems;

public class dmrexample {

	
	
	public static FCItemAsDMR DMR_EXAMPLE = new FCItemAsDMR(3012, "sample_dmr", "dmr_sample", FCCreativeTabs.MATERIALS);
	public static FCPickaxe TEST_PICKAXE = new FCPickaxe(3099, "item_0", "item_test_pickaxe", FCCreativeTabs.TOOLS, FCItems.AMETHYST_TOOL_MATERIAL, 0, 0); 

	
	
	public static void main ()
	{
		DatafiedObjectRegistration.registerDMRItem(DMR_EXAMPLE);

	}
	
	
	
}
