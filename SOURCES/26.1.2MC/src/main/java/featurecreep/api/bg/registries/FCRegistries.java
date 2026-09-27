package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * Legacy registry bridge for FC4 API.
 *
 * @deprecated This class will be removed in version 13.
 */
@Deprecated(forRemoval = true, since = "13")
public class FCRegistries {

	/** Registers a block through the vanilla registry system. */
	public static FCBlockAPI registerBlock(FCBlockAPI block) {
		Block vanilla = (Block) block;
		Identifier id = Identifier.tryBuild(block.getModId(), block.getUnlocName());

		if (id == null) {
			throw new IllegalStateException("Block has no registry name: " + block);
		}

		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
		Registry.register(BuiltInRegistries.BLOCK, blockKey, vanilla);

		// 26.1+ Item construction requires the registry key in Item.Properties.
		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
		Item blockItem = new BlockItem(vanilla, new Item.Properties().setId(itemKey));
		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		return block;
	}

	/** Registers an item through the vanilla registry system. */
	public static FCItemAPI registerItem(FCItemAPI item) {
		Item vanilla = (Item) item;
		Identifier id = Identifier.tryBuild(item.getModId(), item.getUnlocName());

		if (id == null) {
			throw new IllegalStateException("Item has no registry name: " + item);
		}

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
		Registry.register(BuiltInRegistries.ITEM, itemKey, vanilla);
		return item;
	}
}
