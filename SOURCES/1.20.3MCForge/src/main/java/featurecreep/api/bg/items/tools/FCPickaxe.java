package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Item;
import game.Pickaxe;

public class FCPickaxe extends Pickaxe implements ToolsAPI<FCPickaxe> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCPickaxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(material, attackDamage, attackSpeed, new Item.Info());
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
