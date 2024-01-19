package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Axe;

public class FCAxe extends Axe implements ToolsAPI<FCAxe>
{


	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	
	public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Info().setCreativeTab(group.get()));
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
		}

	
	
	
}

