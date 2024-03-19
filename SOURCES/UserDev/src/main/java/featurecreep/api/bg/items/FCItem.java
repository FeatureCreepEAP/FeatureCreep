package featurecreep.api.bg.items;

import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCItem implements FCItemAPI<FCItem> {
	public ItemFieldHolder holder = new ItemFieldHolder();

	@Override
	public ItemFieldHolder holder() {
		return holder;
	}

	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group) {
		initialise(id, modid, name, group);
	}

}
