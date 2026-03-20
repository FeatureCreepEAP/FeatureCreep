package featurecreep.content;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocks.FCBlock;
import featurecreep.api.bg.blocks.FCOre;
import featurecreep.api.bg.blocks.FCSingleSidedOre;
import featurecreep.api.bg.blocks.SingleSidedBlock;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.bg.blocks.drop.SelfBlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.VanillaBlockMaterials;
import featurecreep.api.bg.craftingzone.CraftingZone;
import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.FCCreativeTabs;

public class FCBlocks {

	public static FCBlock TEST_BLOCK = new FCBlock(700, FeatureCreep.modid, "test_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF});
	public static SingleSidedBlock SINGLE_SIDED_BLOCK = new SingleSidedBlock(701, FeatureCreep.modid, "single_sided_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF});
	public static FCOre ORE_BLOCK = new FCOre(702, FeatureCreep.modid, "test_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF}, FCItems.AMETHYST);
	public static FCSingleSidedOre SINGLE_SIDED_ORE_BLOCK = new FCSingleSidedOre(703, FeatureCreep.modid, "single_sided_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF}, FCItems.AMETHYST);

	
	public static FCOre AMETHYST_ORE = new FCOre(704, FeatureCreep.modid, "amethyst_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.AMETHYST).isSingleSided(true);
	public static FCOre RUBY_ORE = new FCOre(705, FeatureCreep.modid, "ruby_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.RUBY).setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.RUBY).isSingleSided(true);
	public static FCOre TIGERS_EYE_ORE = new FCOre(706, FeatureCreep.modid, "tigers_eye_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.TIGERS_EYE_INGOT).isSingleSided(true);
	public static FCOre TITANIUM_ORE = new FCOre(707, FeatureCreep.modid, "titanium_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.TITANIUM_INGOT).isSingleSided(true);
	public static FCOre URANIUM_ORE = new FCOre(708, FeatureCreep.modid, "uranium_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.URANIUM_INGOT).isSingleSided(true);
	public static FCOre ALUMINIUM_ORE = new FCOre(709, FeatureCreep.modid, "aluminium_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.ALUMINIUM_INGOT).isSingleSided(true);
	public static FCOre UNPROCESSED_OIL_ORE = new FCOre(710, FeatureCreep.modid, "unprocessed_oil_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.GASOLINE_PETROL).addDrop(FCItems.GASOLINE_PETROL).addDrop(FCItems.GASOLINE_PETROL).setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.GASOLINE_PETROL).isSingleSided(true);
	public static FCOre GASOLINE_PETROL_ORE = new FCOre(711, FeatureCreep.modid, "gasoline_petrol_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.OIL).addDrop(FCItems.OIL).addDrop(FCItems.OIL).addDrop(FCItems.OIL).addDrop(FCItems.OIL).setRequiredToolType(ToolTypes.PICKAXE)}, FCItems.OIL).isSingleSided(true);
	public static FCBlock AMETHYST_BLOCK = new FCBlock(712, FeatureCreep.modid, "amethyst_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);
	public static FCBlock RUBY_BLOCK = new FCBlock(713, FeatureCreep.modid, "ruby_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);
	public static FCBlock TIGERS_EYE_BLOCK = new FCBlock(714, FeatureCreep.modid, "tigers_eye_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);
	public static FCBlock TITANIUM_BLOCK = new FCBlock(715, FeatureCreep.modid, "titanium_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);
	public static FCBlock URANIUM_BLOCK = new FCBlock(716, FeatureCreep.modid, "uranium_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);
	public static FCBlock ALUMINIUM_BLOCK = new FCBlock(717, FeatureCreep.modid, "aluminium_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new SelfBlockDropArrayObject().setRequiredToolType(ToolTypes.PICKAXE)}).isSingleSided(true);

	
	
	public static void onInitialise() {
FCRegistries.registerBlock(TEST_BLOCK);
FCRegistries.registerBlock(SINGLE_SIDED_BLOCK);

FCRegistries.registerBlock(ORE_BLOCK);
FCRegistries.registerBlock(SINGLE_SIDED_ORE_BLOCK);

FCRegistries.registerBlock(AMETHYST_ORE);
FCRegistries.registerBlock(RUBY_ORE);
FCRegistries.registerBlock(TIGERS_EYE_ORE);
FCRegistries.registerBlock(TITANIUM_ORE);
FCRegistries.registerBlock(URANIUM_ORE);
FCRegistries.registerBlock(ALUMINIUM_ORE);
FCRegistries.registerBlock(UNPROCESSED_OIL_ORE);
FCRegistries.registerBlock(GASOLINE_PETROL_ORE);
FCRegistries.registerBlock(AMETHYST_BLOCK);
FCRegistries.registerBlock(RUBY_BLOCK);
FCRegistries.registerBlock(TIGERS_EYE_BLOCK);
FCRegistries.registerBlock(TITANIUM_BLOCK);
FCRegistries.registerBlock(URANIUM_BLOCK);
FCRegistries.registerBlock(ALUMINIUM_BLOCK);


CraftingZone.addMelting(AMETHYST_ORE, FCItems.AMETHYST, 1, 50, "");
CraftingZone.addMelting(RUBY_ORE, FCItems.RUBY, 1, 50, "");
CraftingZone.addMelting(TIGERS_EYE_ORE, FCItems.TIGERS_EYE_INGOT, 1, 50, "");
CraftingZone.addMelting(TITANIUM_ORE, FCItems.TITANIUM_NUGGET, 1, 50, "");
CraftingZone.addMelting(URANIUM_ORE, FCItems.URANIUM_NUGGET, 1, 50, "");
CraftingZone.addMelting(ALUMINIUM_ORE, FCItems.ALUMINIUM_INGOT, 1, 50, "");
CraftingZone.addMelting(UNPROCESSED_OIL_ORE, FCItems.OIL, 1, 50, "");
CraftingZone.addMelting(GASOLINE_PETROL_ORE, FCItems.GASOLINE_PETROL, 1, 50, "");


	}
	
}




