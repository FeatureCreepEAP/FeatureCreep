package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCOre implements FCBlockAPI<FCOre> {

	public BlockFieldHolder holder = new BlockFieldHolder();

	@Override
	public BlockFieldHolder holder() {
		return holder;
	}

	public Object resource;

	public FCOre(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material,
			int strength, BlockDropArrayObject[] drops, Object ore_material) {

		initialise(id, modid, name, group, material, strength, drops);
		resource = ore_material;
	}

	@Override
	public FCOre isSingleSided(boolean answer) {
		holder().single_sided = answer;
		return this;
	}

}
