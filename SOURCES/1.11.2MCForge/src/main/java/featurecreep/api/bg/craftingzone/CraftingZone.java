package featurecreep.api.bg.craftingzone;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.vanilla.VanillaItems;
import game.FurnaceRecipes;
import game.ItemStack;
import game.RecipeManager;
import game.ShapedRecipe;

public class CraftingZone {

  public static ArrayList < CraftObject > objects = new ArrayList < CraftObject > ();
  public static ArrayList < MeltObject > melts = new ArrayList < MeltObject > ();

  
  
  public static void addShapedCrafting(BlockOrItem result, int quantity, BlockOrItem ingredient0, BlockOrItem ingredient1, BlockOrItem ingredient2, BlockOrItem ingredient3, BlockOrItem ingredient4, BlockOrItem ingredient5, BlockOrItem ingredient6, BlockOrItem ingredient7, BlockOrItem ingredient8) {

	  int hash = new CraftObject (result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8).get112ModelNode().hashCode();

	  ItemStack res;
	  if (result instanceof FCItemAPI) {
		FCItemAPI it = (FCItemAPI)result;
		res = new ItemStack (it.get(), quantity);
	}else {
		FCBlockAPI bl = (FCBlockAPI)result;
		res = new ItemStack (bl.get(), quantity);
	}


	  
	  CraftObject obj = new CraftObject (result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8);
	  objects.add( obj);
 
 
 
	  ArrayList<ItemStack> list  = new ArrayList<ItemStack>();
//	  String[] stringarr = new String[] {new String(obj.ing0letter+obj.ing1letter+obj.ing2letter), new String(obj.ing3letter+obj.ing4letter+obj.ing5letter), new String(obj.ing6letter+obj.ing7letter+obj.ing8letter)};

 //list.add(stringarr);
 
 if (!obj.ing0letter.equals(" ")) {
	// list.add(obj.ing0letter);
	 list.add(ingredient0.toStack(1));
 }else {list.add(ItemStack.EMPTY);}
 if (!obj.ing1letter.equals(" ")) {
	 //list.add(obj.ing1letter);
	 list.add(ingredient1.toStack(1));
 }else {list.add(ItemStack.EMPTY);}


 if (!obj.ing2letter.equals(" ")) {
//	 list.add(obj.ing2letter);
	 list.add(ingredient2.toStack(1));
 }else {list.add(ItemStack.EMPTY);}


 if (!obj.ing3letter.equals(" ")) {
//	 list.add(obj.ing3letter);
	 list.add(ingredient3.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 if (!obj.ing4letter.equals(" ")) {
	// list.add(obj.ing4letter);
	 list.add(ingredient4.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 if (!obj.ing5letter.equals(" ")) {
	// list.add(obj.ing5letter);
	 list.add(ingredient5.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 if (!obj.ing6letter.equals(" ")) {
	// list.add(obj.ing6letter);
	 list.add(ingredient6.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 if (!obj.ing7letter.equals(" ")) {
	// list.add(obj.ing7letter);
	 list.add(ingredient7.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 if (!obj.ing8letter.equals(" ")) {
	// list.add(obj.ing8letter);
	 list.add(ingredient8.toStack(1));
 }else {list.add(ItemStack.EMPTY);}

 
 
 
 for (int i =0; i<list.toArray().length; i++) {
	 System.out.println (list.toArray()[i]); 
 }
 
 
 
 RecipeManager.getStatic().getRecipes().add(new ShapedRecipe(3, 3, new ItemStack[list.size()], res));
 
 
 
//	  CraftingManager.getInstance().addRecipe(res, list.toArray());
  	  
  }

  public static void addPickaxeCrafting(BlockOrItem item, BlockOrItem pickaxe) {
	  
	  addShapedCrafting(pickaxe, 1, 
			  item, item, item
			  ,null, VanillaItems.STICK, null
			  ,null, VanillaItems.STICK, null
			  );

  }
  
  public static void addSwordCrafting(BlockOrItem item, BlockOrItem sword) {
	  
	  addShapedCrafting(sword, 1, 
			  null, item, null
			  ,null, item, null
			  ,null, VanillaItems.STICK, null
			  );

  }
  
  public static void addShovelCrafting(BlockOrItem item, BlockOrItem shovel) {
	  
	  addShapedCrafting(shovel, 1, 
			  null, item, null
			  ,null, VanillaItems.STICK, null
			  ,null, VanillaItems.STICK, null
			  );

  }
  
  public static void addHoeCrafting(BlockOrItem item, BlockOrItem hoe) {
	  
	  addShapedCrafting(hoe, 1, 
			  null, item, item
			  ,null, VanillaItems.STICK, null
			  ,null, VanillaItems.STICK, null
			  );

	  addShapedCrafting(hoe, 1, 
			  item, item, null
			  ,null, VanillaItems.STICK, null
			  ,null, VanillaItems.STICK, null
			  );	  
	  
  }
  
  public static void addAxeCrafting(BlockOrItem item, BlockOrItem axe) {
	  
	  addShapedCrafting(axe, 1, 
			  null, item, item
			  ,null, VanillaItems.STICK, item
			  ,null, VanillaItems.STICK, null
			  );

	  addShapedCrafting(axe, 1, 
			  item, item, null
			  ,item, VanillaItems.STICK, null
			  ,null, VanillaItems.STICK, null
			  );	  
	  
  }
  
  
  
  
  public static void addCrystalPickaxeCrafting(BlockOrItem item, BlockOrItem pickaxe) {
	  
	  addShapedCrafting(pickaxe, 1, 
			  item, item, item
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );

  }
  
  public static void addCrystalSwordCrafting(BlockOrItem item, BlockOrItem sword) {
	  
	  addShapedCrafting(sword, 1, 
			  null, item, null
			  ,null, item, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );

  }
  
  public static void addCrystalShovelCrafting(BlockOrItem item, BlockOrItem shovel) {
	  
	  addShapedCrafting(shovel, 1, 
			  null, item, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );

  }
  
  public static void addCrystalHoeCrafting(BlockOrItem item, BlockOrItem hoe) {
	  
	  addShapedCrafting(hoe, 1, 
			  null, item, item
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );

	  addShapedCrafting(hoe, 1, 
			  item, item, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );	  
	  
  }
  
  public static void addCrystalAxeCrafting(BlockOrItem item, BlockOrItem axe) {
	  
	  addShapedCrafting(axe, 1, 
			  null, item, item
			  ,null, VanillaItems.CRYSTAL_STICK, item
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );

	  addShapedCrafting(axe, 1, 
			  item, item, null
			  ,item, VanillaItems.CRYSTAL_STICK, null
			  ,null, VanillaItems.CRYSTAL_STICK, null
			  );	  
	  
  }
  
  public static void addMelting(BlockOrItem item, BlockOrItem result, int xp, int cooking_time, String group)
  {
	  
	  FurnaceRecipes inst = FurnaceRecipes.getStatic();
	  
	  ItemStack stack;
	  if (result instanceof FCBlockAPI) {
		  FCBlockAPI block = (FCBlockAPI)result;
		  stack = new ItemStack(block.get());
		  }else {
			  FCItemAPI items = (FCItemAPI)result;
			  stack = new ItemStack(items.get()); 
		  }
	  
	  
	  if (item instanceof FCBlockAPI) {
		  FCBlockAPI block = (FCBlockAPI)item;
		  inst.smelt_block(block.get(), stack, xp);
		  }else
		  {
			  FCItemAPI items = (FCItemAPI)item;
			  inst.smelt_item(items.get(), stack, xp);
		  }
	  
	  
	  
	melts.add(new MeltObject(item, result, xp, cooking_time, group));  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  public static String getCorrectNameSpace(String old) {
    String new_string = new String(old);

    if (new_string.contains("vanilla:")) {
      new_string = new_string.replace("vanilla:", "minecraft:");
    }

    if (new_string.contains("dangerzone:")) {
      new_string = new_string.replace("dangerzone:", "minecraft:");
    }

    return new_string;
  }
  
  
  
  
  
  
  
  

}