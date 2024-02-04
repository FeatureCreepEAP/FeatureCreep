package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCShovel implements ToolsAPI<FCShovel>
{

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}

	
	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
	}

		
	
	
	
}
