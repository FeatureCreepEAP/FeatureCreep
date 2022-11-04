package featurecreep.content;

import featurecreep.FeatureCreep;
import featurecreep.api.FCRegistries;
import featurecreep.api.blocks.FCBlock;
import featurecreep.api.blocks.FCOre;
import featurecreep.api.blocks.FCSingleSidedOre;
import featurecreep.api.blocks.SingleSidedBlock;
import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.blocks.materials.VanillaBlockMaterials;
import featurecreep.api.tooltypes.ToolTypes;
import featurecreep.api.ui.FCCreativeTabs;

public class FCBlocks {

	public static FCBlock TEST_BLOCK = new FCBlock(700, FeatureCreep.modid, "test_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF});
	public static SingleSidedBlock SINGLE_SIDED_BLOCK = new SingleSidedBlock(701, FeatureCreep.modid, "single_sided_block", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF});
	public static FCOre ORE_BLOCK = new FCOre(702, FeatureCreep.modid, "test_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF}, FCItems.AMETHYST);
	public static FCSingleSidedOre SINGLE_SIDED_ORE_BLOCK = new FCSingleSidedOre(703, FeatureCreep.modid, "single_sided_ore", FCCreativeTabs.BUILDING_BLOCKS, VanillaBlockMaterials.STONE, 1, new BlockDropArrayObject[] {new BlockDropArrayObject().addDrop(FCItems.AMETHYST).addDrop(FCItems.AMETHYST_AXE).setRequiredToolType(ToolTypes.PICKAXE), BlockDropArrayObjects.SELF}, FCItems.AMETHYST);

	
	
	public static void onInitialise() {
FCRegistries.registerBlock(TEST_BLOCK);
FCRegistries.registerBlock(SINGLE_SIDED_BLOCK);

FCRegistries.registerBlock(ORE_BLOCK);
FCRegistries.registerBlock(SINGLE_SIDED_ORE_BLOCK);

	}
	
}
