package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.SwordItem;

@Deprecated(forRemoval = true, since = "13")

public class FCSword extends SwordItem implements ToolsAPI<FCSword> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCSword(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(material, new Properties());
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);
	}

}
