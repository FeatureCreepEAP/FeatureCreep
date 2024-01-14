package featurecreep.api.bg.craftingzone;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.vanilla.VanillaItems;
import game.FurnaceRecipes;
import game.IRecipe;
import game.ItemStack;
import game.RecipeManager;

public class CraftingZone {

  public static ArrayList < CraftObject > objects = new ArrayList < CraftObject > ();
  public static ArrayList < MeltObject > melts = new ArrayList < MeltObject > ();

  
  
  public static void addShapedCrafting(BlockOrItem result, int quantity, BlockOrItem ingredient0, BlockOrItem ingredient1, BlockOrItem ingredient2, BlockOrItem ingredient3, BlockOrItem ingredient4, BlockOrItem ingredient5, BlockOrItem ingredient6, BlockOrItem ingredient7, BlockOrItem ingredient8) {

	  int hash = new CraftObject (result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8).get112ModelNode().hashCode();
	  
	  //https://stackoverflow.com/questions/5128442/how-to-convert-a-string-to-jsonobject-using-gson-library#15116323
	  JsonParser jsonParser = new JsonParser();
	  JsonObject jo = (JsonObject)jsonParser.parse(new CraftObject (result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8).get112ModelNode().toJSONString(false));
	  IRecipe rec = null;
	try {
	Method	meth = RecipeManager.class.getDeclaredMethod("a", JsonObject.class);
	meth.setAccessible(true);
	rec = (IRecipe)meth.invoke(null, jo);
	Method reg = RecipeManager.class.getDeclaredMethod("a", String.class,IRecipe.class);
	reg.setAccessible(true);
	reg.invoke(String.valueOf(hash), rec);//On others use CraftingManager.register also to replace hashcode with base64, this may also be buggy
	objects.add( new CraftObject (result, quantity, ingredient0, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8));

	} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
			| SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  	  	  
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