package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Hoe;
import game.Item;

public class FCHoe extends Hoe implements ToolsAPI<FCHoe> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCHoe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(material, attackDamage, attackSpeed, new Item.Info());
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
