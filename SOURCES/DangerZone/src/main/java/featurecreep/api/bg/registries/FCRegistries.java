package featurecreep.api.bg.registries;

import java.util.ArrayList;

import dangerzone.blocks.Blocks;
import dangerzone.items.Items;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;

public class FCRegistries {

	public static ArrayList<FCBlockAPI> BLOCKS = new ArrayList<FCBlockAPI>();
	public static ArrayList<FCItemAPI> ITEMS = new ArrayList<FCItemAPI>();

	/**
	 * Registers a block and automatically registers a corresponding BlockItem.
	 */
	public static FCBlockAPI registerBlock(FCBlockAPI block) {
		String registryName = block.getFCRegistryName();

		// Check if block already exists in DangerZone registry
		if (Blocks.findByName(registryName) == 0) { // findByName returns 0 if not found

			// Register custom blocks
			Blocks.registerBlock(block.get());
			BLOCKS.add(block);

		} else {
			System.out.println("The following block already exists in the Registry: " + registryName);

		}
		return block;
	}

	/**
	 * Registers an item directly.
	 */
	public static FCItemAPI registerItem(FCItemAPI item) {
		String registryName = item.getFCRegistryName();

		// Check if item already exists in DangerZone registry
		if (Items.findByName(registryName) == 0) { // findByName returns 0 if not found

			Items.registerItem(item.get());
			ITEMS.add(item);

		} else {
			System.out.println("The following item already exists in the Registry: " + registryName);

		}
		return item;
	}

	public static void generateModels() {
		for (int i = 0; i < ITEMS.size(); i++) {
			ITEMS.get(i).registerModels();
		}
		for (int b = 0; b < BLOCKS.size(); b++) {
			BLOCKS.get(b).registerModels();
		}
	}

}