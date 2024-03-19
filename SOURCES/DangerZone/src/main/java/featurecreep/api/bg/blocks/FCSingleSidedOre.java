package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCSingleSidedOre extends FCOre {

	public FCSingleSidedOre(int id, String modid, String name, UnifiedItemGroupGetter group,
			UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
		super(id, modid, name, group, material, strength, drops, ore_material);
		isSingleSided(true);
	}

}
