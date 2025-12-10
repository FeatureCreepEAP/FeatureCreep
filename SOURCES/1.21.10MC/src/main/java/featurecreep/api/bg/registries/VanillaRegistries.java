package featurecreep.api.bg.registries;

import java.util.ArrayList;

import featurecreep.api.bg.blocks.vanilla.VanillaBlock;
import featurecreep.api.bg.items.vanilla.VanillaItem;

public class VanillaRegistries {

	public static ArrayList<VanillaBlock> BLOCKS = new ArrayList<VanillaBlock>();
	public static ArrayList<VanillaItem> ITEMS = new ArrayList<VanillaItem>();

	public static void registerItem(VanillaItem item) {
		ITEMS.add(item);
		GlobalRegistries.ITEMS.add(item);
	}

	public static void registerBlock(VanillaBlock block) {
		BLOCKS.add(block);
		GlobalRegistries.BLOCKS.add(block);
	}

	// Soon I gotta make vanilla specific getters
}
