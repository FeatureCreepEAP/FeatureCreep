package featurecreep.api.bg.items.tools;

import dangerzone.items.ItemHoe;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCHoe extends ItemHoe implements ToolsAPI<FCHoe> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCHoe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(modid + ":" + name,
				FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png",
				material.durability, material.attack, material.speed);
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
