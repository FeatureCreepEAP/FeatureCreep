package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class FCAxe extends AxeItem implements ToolsAPI<FCAxe>
{


	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	
	public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Settings());
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
	FCRegistries.registerItem(this);

		}

	
	
	
}

