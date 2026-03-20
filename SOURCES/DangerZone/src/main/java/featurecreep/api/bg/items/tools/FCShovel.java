package featurecreep.api.bg.items.tools;

import dangerzone.items.ItemShovel;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

@Deprecated(forRemoval = true, since = "13")

public class FCShovel extends ItemShovel implements ToolsAPI<FCShovel> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(modid + ":" + name, "./resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png",
				material.durability, material.attack, material.speed);
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
