package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

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

	/**
	 * Registers a block through the vanilla registry system.
	 */
	public static FCBlockAPI registerBlock(FCBlockAPI block) {

		Block vanilla = (Block) block;
		ResourceLocation id = BuiltInRegistries.BLOCK.getKey(vanilla);

		if (id == null) {
			throw new IllegalStateException("Block has no registry name: " + block);
		}

		Registry.register(BuiltInRegistries.BLOCK, id, vanilla);

		/* Automatically create block item like vanilla does */

		Item blockItem = new BlockItem(vanilla, new Item.Properties());

		Registry.register(BuiltInRegistries.ITEM, id, blockItem);

		return block;
	}

	/**
	 * Registers an item through the vanilla registry system.
	 */
	public static FCItemAPI registerItem(FCItemAPI item) {

		Item vanilla = (Item) item;

		ResourceLocation id = BuiltInRegistries.ITEM.getKey(vanilla);

		if (id == null) {
			throw new IllegalStateException("Item has no registry name: " + item);
		}

		Registry.register(BuiltInRegistries.ITEM, id, vanilla);

		return item;
	}

}