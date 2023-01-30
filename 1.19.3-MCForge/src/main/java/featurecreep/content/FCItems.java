package featurecreep.content;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.craftingzone.CraftingZone;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.items.armour.ArmourProtectionValuesArray;
import featurecreep.api.bg.items.armour.FCArmour;
import featurecreep.api.bg.items.armour.FCArmourMaterial;
import featurecreep.api.bg.items.armour.FCArmourSlots;
import featurecreep.api.bg.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.bg.items.tools.FCAxe;
import featurecreep.api.bg.items.tools.FCHoe;
import featurecreep.api.bg.items.tools.FCPickaxe;
import featurecreep.api.bg.items.tools.FCShovel;
import featurecreep.api.bg.items.tools.FCSword;
import featurecreep.api.bg.items.tools.FCToolMaterial;
import featurecreep.api.bg.items.tools.datafied.dmr.FCAxeAsDMR;
import featurecreep.api.bg.items.tools.datafied.dmr.FCHoeAsDMR;
import featurecreep.api.bg.items.tools.datafied.dmr.FCPickaxeAsDMR;
import featurecreep.api.bg.items.tools.datafied.dmr.FCShovelAsDMR;
import featurecreep.api.bg.items.tools.datafied.dmr.FCSwordAsDMR;
import featurecreep.api.bg.registries.DatafiedObjectRegistration;
import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.ui.FCCreativeTabs;

public class FCItems {

public static FCItem AMETHYST = new FCItem(3000, FeatureCreep.modid, "amethyst", FCCreativeTabs.MATERIALS);
public static FCItemAsDMR DMR_TEST = new FCItemAsDMR(3011, FeatureCreep.modid, "dmr_test", FCCreativeTabs.MATERIALS);
public static FCItemAsDMR DMR_BINARY_TEST = new FCItemAsDMR(3012, FeatureCreep.modid, "dmr_binary_test", FCCreativeTabs.MATERIALS);


	
	
//Tools		
public static FCToolMaterial AMETHYST_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, AMETHYST);
public static FCPickaxe AMETHYST_PICKAXE = new FCPickaxe(3001, FeatureCreep.modid, "amethyst_pickaxe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0); 
public static FCShovel AMETHYST_SHOVEL = new FCShovel(3002, FeatureCreep.modid, "amethyst_shovel", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCHoe AMETHYST_HOE = new FCHoe(3003, FeatureCreep.modid, "amethyst_hoe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCSword AMETHYST_SWORD = new FCSword(3004, FeatureCreep.modid, "amethyst_sword", FCCreativeTabs.COMBAT, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCAxe AMETHYST_AXE = new FCAxe(3005, FeatureCreep.modid, "amethyst_axe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);

	


//DMR Items do not currently work as repair materials
public static FCToolMaterial DMR_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, AMETHYST);
public static FCPickaxeAsDMR DMR_PICKAXE = new FCPickaxeAsDMR(3006, FeatureCreep.modid, "dmr_pickaxe", FCCreativeTabs.TOOLS, DMR_TOOL_MATERIAL, 0, 0); 
public static FCShovelAsDMR DMR_SHOVEL = new FCShovelAsDMR(3007, FeatureCreep.modid, "dmr_shovel", FCCreativeTabs.TOOLS, DMR_TOOL_MATERIAL, 0, 0);
public static FCHoeAsDMR DMR_HOE = new FCHoeAsDMR(3008, FeatureCreep.modid, "dmr_hoe", FCCreativeTabs.TOOLS, DMR_TOOL_MATERIAL, 0, 0);
public static FCSwordAsDMR DMR_SWORD = new FCSwordAsDMR(3009, FeatureCreep.modid, "dmr_sword", FCCreativeTabs.COMBAT, DMR_TOOL_MATERIAL, 0, 0);
public static FCAxeAsDMR DMR_AXE = new FCAxeAsDMR(3010, FeatureCreep.modid, "dmr_axe", FCCreativeTabs.TOOLS, DMR_TOOL_MATERIAL, 0, 0);

	
public static FCArmourMaterial AMETHYST_ARMOUR = new FCArmourMaterial(25, new ArmourProtectionValuesArray(10, 20, 20, 10), 200, AMETHYST, "amethyst", 2, 0);
public static FCArmour AMETHYST_HELMET = new FCArmour(3013, FeatureCreep.modid, "amethyst_helmet", FCCreativeTabs.COMBAT, AMETHYST_ARMOUR, FCArmourSlots.HELMET);
public static FCArmour AMETHYST_CHESTPLATE = new FCArmour(3014, FeatureCreep.modid, "amethyst_chestplate", FCCreativeTabs.COMBAT, AMETHYST_ARMOUR, FCArmourSlots.TUBIC);
public static FCArmour AMETHYST_LEGGINS = new FCArmour(3015, FeatureCreep.modid, "amethyst_leggings", FCCreativeTabs.COMBAT, AMETHYST_ARMOUR, FCArmourSlots.LEGGINGS);
public static FCArmour AMETHYST_BOOTS = new FCArmour(3016, FeatureCreep.modid, "amethyst_boots", FCCreativeTabs.COMBAT, AMETHYST_ARMOUR, FCArmourSlots.BOOTS);



public static FCItem TITANIUM_INGOT = new FCItem(3018, FeatureCreep.modid, "titanium_ingot", FCCreativeTabs.MATERIALS);
public static FCItem TITANIUM_NUGGET = new FCItem(3019, FeatureCreep.modid, "titanium_nugget", FCCreativeTabs.MATERIALS);
public static FCItem URANIUM_INGOT = new FCItem(3020, FeatureCreep.modid, "uranium_ingot", FCCreativeTabs.MATERIALS);
public static FCItem URANIUM_NUGGET = new FCItem(3021, FeatureCreep.modid, "uranium_nugget", FCCreativeTabs.MATERIALS);
public static FCItem ALUMINIUM_INGOT = new FCItem(3022, FeatureCreep.modid, "aluminium_ingot", FCCreativeTabs.MATERIALS);
public static FCItem COPPER_INGOT = new FCItem(3023, FeatureCreep.modid, "copper_ingot", FCCreativeTabs.MATERIALS);
public static FCItem TIN_INGOT = new FCItem(3024, FeatureCreep.modid, "tin_ingot", FCCreativeTabs.MATERIALS);
public static FCItem SILVER_INGOT = new FCItem(3025, FeatureCreep.modid, "silver_ingot", FCCreativeTabs.MATERIALS);
public static FCItem OIL = new FCItem(3026, FeatureCreep.modid, "oil", FCCreativeTabs.MATERIALS);
public static FCItem GASOLINE_PETROL = new FCItem(3027, FeatureCreep.modid, "gasoline_petrol", FCCreativeTabs.MATERIALS);
public static FCItem RUBY = new FCItem(3028, FeatureCreep.modid, "ruby", FCCreativeTabs.MATERIALS);
public static FCItem TIGERS_EYE_INGOT = new FCItem(3028, FeatureCreep.modid, "tigers_eye_ingot", FCCreativeTabs.MATERIALS);




	public static void onInitialise() {
	// TODO Auto-generated method stub
	
		FCRegistries.registerItem(AMETHYST);
		FCRegistries.registerItem(AMETHYST_PICKAXE);
		FCRegistries.registerItem(AMETHYST_HOE);
		FCRegistries.registerItem(AMETHYST_SWORD);
		FCRegistries.registerItem(AMETHYST_AXE);
		FCRegistries.registerItem(AMETHYST_SHOVEL);
DatafiedObjectRegistration.registerDMRItem(DMR_TEST);
DatafiedObjectRegistration.registerDMRBinaryItem(DMR_BINARY_TEST);

DatafiedObjectRegistration.registerDMRItem(DMR_PICKAXE);
DatafiedObjectRegistration.registerDMRItem(DMR_SHOVEL);
DatafiedObjectRegistration.registerDMRItem(DMR_HOE);
DatafiedObjectRegistration.registerDMRItem(DMR_SWORD);
DatafiedObjectRegistration.registerDMRItem(DMR_AXE);
		
FCRegistries.registerItem(AMETHYST_HELMET);
FCRegistries.registerItem(AMETHYST_CHESTPLATE);
FCRegistries.registerItem(AMETHYST_LEGGINS);
FCRegistries.registerItem(AMETHYST_BOOTS);
		

FCRegistries.registerItem(TITANIUM_INGOT);
FCRegistries.registerItem(TITANIUM_NUGGET);
FCRegistries.registerItem(URANIUM_INGOT);
FCRegistries.registerItem(URANIUM_NUGGET);
FCRegistries.registerItem(ALUMINIUM_INGOT);
FCRegistries.registerItem(COPPER_INGOT);
FCRegistries.registerItem(TIN_INGOT);
FCRegistries.registerItem(SILVER_INGOT);
FCRegistries.registerItem(OIL);
FCRegistries.registerItem(GASOLINE_PETROL);
FCRegistries.registerItem(RUBY);
FCRegistries.registerItem(TIGERS_EYE_INGOT);



CraftingZone.addShapedCrafting(TITANIUM_INGOT, 1, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET, TITANIUM_NUGGET);
CraftingZone.addShapedCrafting(URANIUM_INGOT, 1, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET, URANIUM_NUGGET);




}



	
}



