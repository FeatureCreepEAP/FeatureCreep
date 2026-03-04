package featurecreep.api.bg.craftingzone;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.items.vanilla.VanillaItems;

public class CraftingZone {

	public static ArrayList<CraftObject> objects = new ArrayList<CraftObject>();
	public static ArrayList<MeltObject> melts = new ArrayList<MeltObject>();

	public static void addShapedCrafting(BlockOrItem result, int quantity, BlockOrItem ingredient0,
			BlockOrItem ingredient1, BlockOrItem ingredient2, BlockOrItem ingredient3, BlockOrItem ingredient4,
			BlockOrItem ingredient5, BlockOrItem ingredient6, BlockOrItem ingredient7, BlockOrItem ingredient8) {

		objects.add(new CraftObject(result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4,
				ingredient5, ingredient6, ingredient7, ingredient8));

	}

	public static void addPickaxeCrafting(BlockOrItem item, BlockOrItem pickaxe) {

		addShapedCrafting(pickaxe, 1, item, item, item, null, VanillaItems.STICK, null, null, VanillaItems.STICK, null);

	}

	public static void addSwordCrafting(BlockOrItem item, BlockOrItem sword) {

		addShapedCrafting(sword, 1, null, item, null, null, item, null, null, VanillaItems.STICK, null);

	}

	public static void addShovelCrafting(BlockOrItem item, BlockOrItem shovel) {

		addShapedCrafting(shovel, 1, null, item, null, null, VanillaItems.STICK, null, null, VanillaItems.STICK, null);

	}

	public static void addHoeCrafting(BlockOrItem item, BlockOrItem hoe) {

		addShapedCrafting(hoe, 1, null, item, item, null, VanillaItems.STICK, null, null, VanillaItems.STICK, null);

		addShapedCrafting(hoe, 1, item, item, null, null, VanillaItems.STICK, null, null, VanillaItems.STICK, null);

	}

	public static void addAxeCrafting(BlockOrItem item, BlockOrItem axe) {

		addShapedCrafting(axe, 1, null, item, item, null, VanillaItems.STICK, item, null, VanillaItems.STICK, null);

		addShapedCrafting(axe, 1, item, item, null, item, VanillaItems.STICK, null, null, VanillaItems.STICK, null);

	}

	public static void addMelting(BlockOrItem item, BlockOrItem result, int xp, int cooking_time, String group) {
		melts.add(new MeltObject(item, result, xp, cooking_time, group));
	}

	public static String getCorrectNameSpace(String old) {
		String new_string = new String(old);

		if (new_string.contains("vanilla:")) {
			new_string = new_string.replace("vanilla:", "minecraft:");
		}

		if (new_string.contains("dangerzone:")) {
			new_string = new_string.replace("dangerzone:", "minecraft:");
		}

		return new_string;
	}

}