package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;

public class FCSword implements ToolsAPI<FCSword> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCSword(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
