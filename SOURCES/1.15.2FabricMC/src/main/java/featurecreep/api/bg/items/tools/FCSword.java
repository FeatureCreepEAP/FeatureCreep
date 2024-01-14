package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Item;
import game.Sword;

public class FCSword extends Sword implements ToolsAPI<FCSword>
{

	
	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	
	public FCSword(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Info().setCreativeTab(group.get()));
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
		}

	
	
}

