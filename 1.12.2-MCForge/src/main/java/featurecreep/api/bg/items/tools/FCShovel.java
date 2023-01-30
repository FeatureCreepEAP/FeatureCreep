package featurecreep.api.bg.items.tools;

import featurecreep.api.EnumModder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;

public class FCShovel extends ItemSpade implements ToolsAPI<FCShovel>
{
	
	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	
	public FCShovel(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super((ToolMaterial)EnumModder.newEnumInstance(ToolMaterial.class, name,  material.getToolHarvestLevel(), material.getToolMaxUses(), material.getToolEfficiency(), material.getToolAttackDamage(), material.getToolEnchantability()));
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
		}

	
}
