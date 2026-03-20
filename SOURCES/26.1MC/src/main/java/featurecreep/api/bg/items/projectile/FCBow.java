package featurecreep.api.bg.items.projectile;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;

@Deprecated(forRemoval = true, since = "13")
public class FCBow extends BowItem implements FCItemAPI<FCBow> {

	public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();

	@Override
	public featurecreep.api.bg.items.ItemFieldHolder holder() {
		return holder;
	}

	public FCBow(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(new Item.Properties());
		initialise(id, modid, name, group);

	}

}
