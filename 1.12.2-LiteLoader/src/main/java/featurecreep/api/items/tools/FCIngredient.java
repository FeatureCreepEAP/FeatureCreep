package featurecreep.api.items.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.Ingredient;

public class FCIngredient {

	public static Ingredient ingredientFromItem(Item item)
	{
		return Ingredient.func_193368_a(item);

	}
	public static Ingredient ingredientFromItem(Block item)
	{
		return Ingredient.func_193368_a(new ItemBlock(item));

	}

}