package featurecreep.api.bg.items;

import dangerzone.items.Item;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

@Deprecated(forRemoval = true, since = "13")

public class FCItem extends Item implements FCItemAPI<FCItem> {
	public ItemFieldHolder holder = new ItemFieldHolder();

	@Override
	public ItemFieldHolder holder() {
		return holder;
	}

	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(modid + ":" + name, "./resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png");
		initialise(id, modid, name, group);
	}
}
