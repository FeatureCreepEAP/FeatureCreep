package featurecreep.api.bg.items.tools;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class FCIngredient {
   public static Ingredient ingredientFromItem(Item item) {
      return Ingredient.of(new ItemLike[]{item});
   }

   public static Ingredient ingredientFromItem(Block item) {
      return Ingredient.of(new ItemLike[]{item});
   }
}
