package featurecreep.api.ui;

import featurecreep.api.registries.GlobalRegistries;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCCreativeTabs
{
	
	public static VanillaCreativeTab BUILDING_BLOCKS = new VanillaCreativeTab("BUILDING_BLOCKS");
	public static VanillaCreativeTab BREWING = new VanillaCreativeTab("BREWING");
	public static VanillaCreativeTab COMBAT = new VanillaCreativeTab("COMBAT");
	public static VanillaCreativeTab DECORATIONS = new VanillaCreativeTab("DECORATIONS");
	public static VanillaCreativeTab FOOD = new VanillaCreativeTab("FOOD");
	public static VanillaCreativeTab MATERIALS = new VanillaCreativeTab("MATERIALS");
	public static VanillaCreativeTab MISC = new VanillaCreativeTab("MISC");
	public static VanillaCreativeTab REDSTONE = new VanillaCreativeTab("REDSTONE");
	public static VanillaCreativeTab TOOLS = new VanillaCreativeTab("TOOLS");
	public static VanillaCreativeTab TRANSPORTATION = new VanillaCreativeTab("TRANSPORTATION");
	
	public static void onInitialise() {
	// TODO Auto-generated method stub
	
		GlobalRegistries.ITEMGROUPS.add(BREWING);
		GlobalRegistries.ITEMGROUPS.add(BUILDING_BLOCKS);
		GlobalRegistries.ITEMGROUPS.add(COMBAT);
		GlobalRegistries.ITEMGROUPS.add(DECORATIONS);
		GlobalRegistries.ITEMGROUPS.add(FOOD);
		GlobalRegistries.ITEMGROUPS.add(MATERIALS);
		GlobalRegistries.ITEMGROUPS.add(MISC);
		GlobalRegistries.ITEMGROUPS.add(REDSTONE);
		GlobalRegistries.ITEMGROUPS.add(TOOLS);
		GlobalRegistries.ITEMGROUPS.add(TRANSPORTATION);

		
}
	
	
}
