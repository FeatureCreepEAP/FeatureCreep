package featurecreep.content;

import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.ui.FCCreativeTabs;

@Deprecated(forRemoval = true, since = "13")

public class FCItems {

	public static FCItem AMETHYST = new FCItem(3000, "featurecreep", "amethyst", FCCreativeTabs.MATERIALS);

	public static void onInitialise() {
		// TODO Auto-generated method stub

		FCRegistries.registerItem(AMETHYST);

	}

}
