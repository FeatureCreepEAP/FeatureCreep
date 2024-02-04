package featurecreep.api.bg.items.tools;

import game.Block;
import game.BlockAsItem;
import game.Item;
import game.ToolRepairIngredient;

public class FCIngredient {

	public static ToolRepairIngredient ingredientFromItem(Item item)
	{
		return ToolRepairIngredient.ofItems(item);

	}
	public static ToolRepairIngredient ingredientFromItem(Block item)
	{
		return ToolRepairIngredient.ofItems(new BlockAsItem(item));

	}

}