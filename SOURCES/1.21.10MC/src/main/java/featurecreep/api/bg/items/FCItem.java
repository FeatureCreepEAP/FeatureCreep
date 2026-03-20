package featurecreep.api.bg.items;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.Item;

@Deprecated(forRemoval = true, since = "13")

public class FCItem extends Item implements FCItemAPI<FCItem> {

	public ItemFieldHolder holder = new ItemFieldHolder();

	@Override
	public ItemFieldHolder holder() {
		return holder;
	}

	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(new Properties());
		initialise(id, modid, name, group);

	}

}
