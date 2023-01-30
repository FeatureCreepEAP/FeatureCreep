package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class FCShovel extends ShovelItem implements ToolsAPI<FCShovel>
{


	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}


	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Settings().group(group.get()));
	initialise(id,modid,name, group,material,attackDamage,attackSpeed);
	}

	
	
}

