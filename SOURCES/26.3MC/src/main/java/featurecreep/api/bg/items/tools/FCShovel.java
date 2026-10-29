package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

@Deprecated(forRemoval = true, since = "13")

public class FCShovel extends Item implements ToolsAPI<FCShovel> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(new Item.Properties().shovel(material.toMinecraftToolMaterial(), attackDamage, attackSpeed)
				.setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(modid, name))));
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
