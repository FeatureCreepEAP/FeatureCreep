package featurecreep.api.bg.items.tools;

import dangerzone.items.Item;
import dangerzone.items.ItemSword;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;

public class FCSword extends ItemSword implements ToolsAPI<FCSword>
{

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}

	
	public FCSword(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(modid + ":" + name, FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png", material.durability, material.attack);
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
		}

	
	
	
}
