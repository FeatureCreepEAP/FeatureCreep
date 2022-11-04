package featurecreep.api.ui;

import featurecreep.api.ui.tabs.vanilla.BrewingVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.BuildingVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.CombatVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.DecorVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.FoodVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.MaterialsVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.MiscVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.RedStoneVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.ToolsVanillaCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.item.ItemGroup;

public class FCCreativeTabs
{
	
	public static BuildingVanillaCreativeTab BUILDING_BLOCKS = new BuildingVanillaCreativeTab(ItemGroup.BUILDING_BLOCKS);
	public static BrewingVanillaCreativeTab BREWING = new BrewingVanillaCreativeTab(ItemGroup.BREWING);
	public static CombatVanillaCreativeTab COMBAT = new CombatVanillaCreativeTab(ItemGroup.COMBAT);
	public static DecorVanillaCreativeTab DECORATIONS = new DecorVanillaCreativeTab(ItemGroup.DECORATIONS);
	public static FoodVanillaCreativeTab FOOD = new FoodVanillaCreativeTab(ItemGroup.FOOD);
	public static MaterialsVanillaCreativeTab MATERIALS = new MaterialsVanillaCreativeTab(ItemGroup.MATERIALS);
	public static MiscVanillaCreativeTab MISC = new MiscVanillaCreativeTab(ItemGroup.MISC);
	public static RedStoneVanillaCreativeTab REDSTONE = new RedStoneVanillaCreativeTab(ItemGroup.REDSTONE);
	public static ToolsVanillaCreativeTab TOOLS = new ToolsVanillaCreativeTab(ItemGroup.TOOLS);
	public static VanillaCreativeTab TRANSPORTATION = new VanillaCreativeTab(ItemGroup.TRANSPORTATION);
	
	public static void onInitialise() {
	// TODO Auto-generated method stub
	
}
	
	
}