package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

@Deprecated(forRemoval = true, since = "13")

public class FCAxe extends Item implements ToolsAPI<FCAxe> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {

		// Minecraft 26.1.x configures axes through Item.Properties rather than AxeItem.
		super(new Item.Properties().axe(material.toMinecraftToolMaterial(), attackDamage, attackSpeed)
				.setId(ResourceKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(modid, name))));

		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
