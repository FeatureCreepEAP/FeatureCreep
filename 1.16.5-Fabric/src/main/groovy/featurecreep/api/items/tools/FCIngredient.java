package featurecreep.api.items.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;

public class FCIngredient {

	public static Ingredient ingredientFromItem(Item item)
	{
		return Ingredient.ofItems(item);

	}
	public static Ingredient ingredientFromItem(Block item)
	{
		return Ingredient.ofItems(item);

	}
	
	
	
}
