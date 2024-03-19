package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class SingleSidedBlock extends FCBlock {

	public SingleSidedBlock(int id, String modid, String name, UnifiedItemGroupGetter group,
			UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
		super(id, modid, name, group, material, strength, drops);
		isSingleSided(true);
	}

}
