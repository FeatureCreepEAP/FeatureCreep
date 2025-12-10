package featurecreep.api.bg.items.projectile;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;

public class FCBow extends BowItem implements FCItemAPI<FCBow> {

	public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();

	@Override
	public featurecreep.api.bg.items.ItemFieldHolder holder() {
		return holder;
	}

	public FCBow(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),ResourceLocation.fromNamespaceAndPath(modid, name)) ));
		initialise(id, modid, name, group);

	}

}
