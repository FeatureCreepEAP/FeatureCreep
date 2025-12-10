package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;

public class FCShovel extends ShovelItem implements ToolsAPI<FCShovel> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
        super(material.toMinecraftToolMaterial(), attackDamage, attackSpeed, new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),ResourceLocation.fromNamespaceAndPath(modid, name)) ));


		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
