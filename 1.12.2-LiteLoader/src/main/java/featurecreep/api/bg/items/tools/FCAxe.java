package featurecreep.api.bg.items.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import featurecreep.api.EnumModder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;

public class FCAxe extends ItemTool implements ToolsAPI<FCAxe>
{
	

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	

	public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super((ToolMaterial)EnumModder.newEnumInstance(ToolMaterial.class, name,  material.getToolHarvestLevel(), material.getToolMaxUses(), material.getToolEfficiency(), material.getToolAttackDamage(), material.getToolEnchantability()), EFFECTIVE_ON);
initialise(id,modid,name, group,material,attackDamage,attackSpeed);

		}


		
		
		
		
		


		private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
	    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};



		
}
