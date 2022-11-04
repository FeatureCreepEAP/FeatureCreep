package featurecreep.content;

import featurecreep.FeatureCreep;
import featurecreep.api.DatafiedObjectRegistration;
import featurecreep.api.FCRegistries;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.armour.ArmourProtectionValuesArray;
import featurecreep.api.items.armour.FCArmour;
import featurecreep.api.items.armour.FCArmourMaterial;
import featurecreep.api.items.armour.FCArmourSlots;
import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import featurecreep.api.items.tools.FCToolMaterial;
import featurecreep.api.items.tools.datafied.dmr.FCAxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCHoeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCPickaxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCShovelAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCSwordAsDMR;
import featurecreep.api.ui.FCCreativeTabs;

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
		
}



	
}

