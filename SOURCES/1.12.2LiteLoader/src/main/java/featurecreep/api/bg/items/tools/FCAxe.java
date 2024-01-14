package featurecreep.api.bg.items.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import featurecreep.api.EnumModder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Block;
import game.Blocks;
import game.Item.ToolMaterialEnum;
import game.MiningTool;

public class FCAxe extends MiningTool implements ToolsAPI<FCAxe>
{
	

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	

	public FCAxe(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super((ToolMaterialEnum)EnumModder.newEnumInstance(ToolMaterialEnum.class, name,  material.getToolHarvestLevel(), material.getToolMaxUses(), material.getToolEfficiency(), material.getToolAttackDamage(), material.getToolEnchantability()), EFFECTIVE_ON);
initialise(id,modid,name, group,material,attackDamage,attackSpeed);

		}


		
		
		
		
		


		private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.WOODEN_PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG0, Blocks.CHESS, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.WATERMELON, Blocks.LADDER, Blocks.WOOD_BUTTON, Blocks.WOOD_PRESSURE_PLATE);
	    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
	    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};



		
}
