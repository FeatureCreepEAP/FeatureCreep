package featurecreep.api.bg.registries;

import java.util.ArrayList;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.vanilla.VanillaBlock;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.datafied.dmr.DMRItem;
import featurecreep.api.bg.items.vanilla.VanillaItem;

public class FCRegistries {

	public static ArrayList<FCBlockAPI> BLOCKS = new ArrayList<FCBlockAPI>();
	public static ArrayList<FCItemAPI> ITEMS = new ArrayList<FCItemAPI>();

	// https://github.com/Trikzon/Snow-Variants/blob/1.13.2/src/main/java/trikzon/snowvariants/init/ModBlocks.java

	public static FCBlockAPI registerBlock(FCBlockAPI block) {
		if (!GameRegistries.BlockKeyExistsInRegistry(block.getFCRegistryName())) {

			if (block instanceof VanillaBlock) {
				VanillaRegistries.registerBlock((VanillaBlock) block);
			} else { // When Dataified Blocks come out we need to make a similar trap
				UniversalRegistryGettersAndSetters.registerBlock(block);
				BLOCKS.add(block);
				GlobalRegistries.BLOCKS.add(block);
			}

		} else {
			if (FeatureCreep.debug_mode) {
				System.out.println("The following block already exists in the Registry." + block.getFCRegistryName());
			}
		}
		return block;
	}

	public static FCItemAPI registerItem(FCItemAPI item) {

		if (!GameRegistries.ItemKeyExistsInRegistry(item.getFCRegistryName())) {

			if (item instanceof VanillaItem) {
				VanillaRegistries.registerItem((VanillaItem) item);
			} else if (item instanceof DMRItem) // When other datafied come out I need to do for those to
			{
				DatafiedObjectRegistration.registerDMRItem((DMRItem) item);
			} else { // When Dataified Blocks come out we need to make a similar trap
				UniversalRegistryGettersAndSetters.registerItem(item);
				ITEMS.add(item);
				GlobalRegistries.ITEMS.add(item);
			}

		} else {
			if (FeatureCreep.debug_mode) {
				System.out.println("The following item already exists in the Registry." + item.getFCRegistryName());
			}
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
