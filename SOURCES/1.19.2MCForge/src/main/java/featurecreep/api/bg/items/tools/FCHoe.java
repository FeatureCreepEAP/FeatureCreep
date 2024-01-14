package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.registries.FCRegistries;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class FCHoe extends HoeItem implements ToolsAPI<FCHoe>
{
	

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}


	
	public FCHoe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Settings().group(group.get()));
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
FCRegistries.registerItem(this);

		}

		

	
	
}
