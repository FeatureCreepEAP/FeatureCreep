package featurecreep.api.bg.items;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class FCItem extends Item implements FCItemAPI<FCItem> {

	public ItemFieldHolder holder = new ItemFieldHolder();

	@Override
	public ItemFieldHolder holder() {
		return holder;
	}

	public FCItem(int id, String modid, String name, UnifiedItemGroupGetter group) {
		super(new Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),Identifier.fromNamespaceAndPath(modid, name)) ));
		initialise(id, modid, name, group);

	}

}
