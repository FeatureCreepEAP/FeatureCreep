package examplemod;

import org.jboss.logging.Logger.Level;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocks.FCBlock;
import featurecreep.api.bg.blocks.FCOre;
import featurecreep.api.bg.blocks.FCSingleSidedOre;
import featurecreep.api.bg.blocks.SingleSidedBlock;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.materials.VanillaBlockMaterials;
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
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.FCCreativeTabs;
import featurecreep.api.soundeffects.VanillaSoundEffects;

public class ExampleMod {



public static FCItem EXAMPLE_ITEM = new FCItem(4000, "example", "example_item", FCCreativeTabs.MISC);
	public static FCItem EXAMPLE_ITEM_2 = new FCItem(4001, "example", "example_item_2", FCCreativeTabs.COMBAT);

	
	
	
	
	public static FCItemAsDMR DMR_EXAMPLE = new FCItemAsDMR(4002, "example", "dmr_example", FCCreativeTabs.MATERIALS);
	public static FCItemAsDMR DMR_BINARY_EXAMPLE = new FCItemAsDMR(4003, "example", "dmr_binary_example", FCCreativeTabs.MATERIALS);


		
		
	//Tools		
	public static FCToolMaterial EXAMPLE_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, EXAMPLE_ITEM);
	public static FCPickaxe EXAMPLE_PICKAXE = new FCPickaxe(4004, "example", "example_pickaxe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0); 
	public static FCShovel EXAMPLE_SHOVEL = new FCShovel(4005, "example", "example_shovel", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCHoe EXAMPLE_HOE = new FCHoe(4006, "example", "example_hoe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCSword EXAMPLE_SWORD = new FCSword(4007, "example", "example_sword", FCCreativeTabs.COMBAT, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCAxe EXAMPLE_AXE = new FCAxe(4008, "example", "example_axe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);

		


	//DMR Items do not currently work as repair materials
	public static FCToolMaterial EXAPLE_DMR_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, EXAMPLE_ITEM_2);
	public static FCPickaxeAsDMR EXAPLE_DMR_PICKAXE = new FCPickaxeAsDMR(4009, "example", "example_dmr_pickaxe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0); 
	public static FCShovelAsDMR EXAPLE_DMR_SHOVEL = new FCShovelAsDMR(4010, "example", "example_dmr_shovel", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCHoeAsDMR EXAPLE_DMR_HOE = new FCHoeAsDMR(4011, "example", "example_dmr_hoe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCSwordAsDMR EXAPLE_DMR_SWORD = new FCSwordAsDMR(4012, "example", "example_dmr_sword", FCCreativeTabs.COMBAT, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCAxeAsDMR EXAPLE_DMR_AXE = new FCAxeAsDMR(4013, "example", "example_dmr_axe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);

	
	public static FCArmourMaterial EXAMPLE_ARMOUR = new FCArmourMaterial(10, new ArmourProtectionValuesArray(5, 10, 8, 5), 20, EXAMPLE_ITEM, "example", 2, 0, VanillaSoundEffects.ARMOUR_EQUIP);
	public static FCArmour AMETHYST_HELMET = new FCArmour(4014, "example", "example_helmet", FCCreativeTabs.COMBAT, EXAMPLE_ARMOUR, FCArmourSlots.HELMET);
	public static FCArmour AMETHYST_CHESTPLATE = new FCArmour(4015, "example", "example_chestplate", FCCreativeTabs.COMBAT, EXAMPLE_ARMOUR, FCArmourSlots.TUBIC);
	public static FCArmour AMETHYST_LEGGINS = new FCArmour(4016, "example", "example_leggings", FCCreativeTabs.COMBAT, EXAMPLE_ARMOUR, FCArmourSlots.LEGGINGS);
	public static FCArmour AMETHYST_BOOTS = new FCArmour(4017, "example", "example_boots", FCCreativeTabs.COMBAT, EXAMPLE_ARMOUR, FCArmourSlots.BOOTS);

	
	public static FCBlock EXAMPLE_BLOCK = new FCBlock(900, "example", "example_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {BlockDropArrayObjects.SELF});
	public static SingleSidedBlock EXAMPLE_SINGLE_SIDED_BLOCK = new SingleSidedBlock(901, "example", "example_single_sided_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {BlockDropArrayObjects.SELF}); //Drops itself
	public static FCOre EXAMPLE_ORE_BLOCK = new FCOre(902, "example", "example_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(EXAMPLE_ITEM).setRequiredToolType(ToolTypes.PICKAXE)}, EXAMPLE_ITEM);
	public static FCSingleSidedOre EXAMPLE_SINGLE_SIDED_ORE_BLOCK = new FCSingleSidedOre(903, "example", "example_single_sided_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(EXAMPLE_ITEM).setRequiredToolType(ToolTypes.PICKAXE)}, EXAMPLE_ITEM);

	

	
	/**
	 * @param args
	 */

	public static void main(String[] main) {
		// TODO Auto-generated method stub

//		 String classpath = System.getProperty("java.class.path");
//	        String[] classPathValues = classpath.split(File.pathSeparator);
//	        for (String classPath: classPathValues) {
//	            System.out.println(classPath);
//	        }
	
		System.out.println("EXAMPLE MOD");
	
	
		
		
		
		
		
FCRegistries.registerItem(EXAMPLE_ITEM);
FCRegistries.registerItem(EXAMPLE_ITEM_2);



FCRegistries.registerItem(EXAMPLE_PICKAXE);
FCRegistries.registerItem(EXAMPLE_SHOVEL);
FCRegistries.registerItem(EXAMPLE_HOE);
FCRegistries.registerItem(EXAMPLE_SWORD);
FCRegistries.registerItem(EXAMPLE_AXE);



DatafiedObjectRegistration.registerDMRItem(DMR_EXAMPLE);
DatafiedObjectRegistration.registerDMRBinaryItem(DMR_BINARY_EXAMPLE);

DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_PICKAXE);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_SHOVEL);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_HOE);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_SWORD);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_AXE);






FCRegistries.registerItem(AMETHYST_HELMET);
FCRegistries.registerItem(AMETHYST_CHESTPLATE);
FCRegistries.registerItem(AMETHYST_LEGGINS);
FCRegistries.registerItem(AMETHYST_BOOTS);




FCRegistries.registerBlock(EXAMPLE_BLOCK);
FCRegistries.registerBlock(EXAMPLE_SINGLE_SIDED_BLOCK);
FCRegistries.registerBlock(EXAMPLE_ORE_BLOCK);
FCRegistries.registerBlock(EXAMPLE_SINGLE_SIDED_ORE_BLOCK);


CraftingZone.addShapedCrafting(EXAMPLE_BLOCK, 1, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM, EXAMPLE_ITEM);
CraftingZone.addMelting(EXAMPLE_ORE_BLOCK, EXAMPLE_ITEM, 1, 1, "");
CraftingZone.addAxeCrafting(EXAMPLE_ITEM, EXAMPLE_AXE);
CraftingZone.addHoeCrafting(EXAMPLE_ITEM, EXAMPLE_HOE);
CraftingZone.addPickaxeCrafting(EXAMPLE_ITEM, EXAMPLE_PICKAXE);
CraftingZone.addShovelCrafting(EXAMPLE_ITEM, EXAMPLE_SHOVEL);
CraftingZone.addSwordCrafting(EXAMPLE_ITEM, EXAMPLE_SWORD);
//	 String oldclasspath = System.getProperty("java.class.path");

//		System.out.println(oldclasspath);


FeatureCreep.LOGGER.log(Level.WARN, "FPMBuild Is Still in Development");



	}
	
	
	
	
	
	}