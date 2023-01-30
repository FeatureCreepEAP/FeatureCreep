package featurecreep.api.bg.items.vanilla;

import dangerzone.items.Item;
import dangerzone.items.Items;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.registries.VanillaRegistries;
import featurecreep.api.bg.ui.FCCreativeTabs;

public class VanillaItems {

public static VanillaItem STICK = new VanillaItem(Items.stick, "minecraft:stick");
public static FCItem CRYSTAL_STICK_FC = new FCItem(3017, "vanilla", "crystal_stick", FCCreativeTabs.MATERIALS);
public static VanillaItem CRYSTAL_STICK = new VanillaItem((Item)CRYSTAL_STICK_FC, "minecraft:crystal_stick");

	
	public static void onInitialise()
	{
		VanillaRegistries.registerItem(STICK);
		FCRegistries.registerItem(CRYSTAL_STICK_FC);
		VanillaRegistries.registerItem(CRYSTAL_STICK);
	}
	
}
