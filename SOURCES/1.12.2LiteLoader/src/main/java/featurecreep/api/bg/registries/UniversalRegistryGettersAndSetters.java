package featurecreep.api.bg.registries;

import java.lang.reflect.InvocationTargetException;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.vanilla.VanillaBlock;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.vanilla.VanillaItem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;
import game.Block;
import game.BlockAsItem;
import game.CreativeTab;
import game.Item;
import game.ResourceLocation;

public class UniversalRegistryGettersAndSetters {

	
	public static Item getItembyName(String registry_name)
	{
		if (GlobalRegistries.getItemByName(registry_name) != null)
		{
			return GlobalRegistries.getItemByName(registry_name).get();
		}else {
			return GameRegistries.getItemFromGameRegistries(registry_name);
		}
				
	}
	
	public static Item getItembyID(int id)
	{
		if (GlobalRegistries.getItemByID(id) != null)
		{
			return GlobalRegistries.getItemByID(id).get();
		}else {
			return GameRegistries.getItemFromGameRegistries(id);
		}
				
	}
	
	
	public static FCItemAPI getFCItembyName(String registry_name)
	{
		if (GlobalRegistries.getItemByName(registry_name) != null)
		{
			return GlobalRegistries.getItemByName(registry_name);
		}else {
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(registry_name), registry_name);
		}
				
	}
	
	public static FCItemAPI getFCItembyID(int id)
	{
		if (GlobalRegistries.getItemByID(id) != null)
		{
			return GlobalRegistries.getItemByID(id);
		}else {
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(id), Item.registry.getName(GameRegistries.getItemFromGameRegistries(id)).toString());
		}
				
	}

	public static void registerItem(Item item, String registry_name, CreativeTab default_tab, int id)
	{
		Item.registry.def_unknown_80529(id, new ResourceLocation (registry_name.split(":")[0], registry_name.split(":")[1]), item);
		item.setUnlocalisedName(registry_name.split(":")[0] + "!" +  registry_name.split(":")[1]);
		item.setCreativeTab(default_tab);
		//May not be needed since we do start early and use vanilla registries
		//ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));	
	//	ModelBakery.registerItemVariants(item, new ModelResourceLocation(registry_name));
	//MC Forge does not clear the field we need so we can use it.
	/*	Map<Item, List<String>> map = null;
		try {
			java.lang.reflect.Field fie = ModelBakery.class.getDeclaredField("v");
			fie.setAccessible(true);
			map = (Map)fie.get(null);

		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
          	// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put(item, Lists.newArrayList(registry_name));

		
		ItemModelMesher mesh = null;
		try {
			java.lang.reflect.Field fie = RenderItem.class.getDeclaredField("d");
			fie.setAccessible(true);
			mesh = (ItemModelMesher)fie.get(ItemModelMesher.class);
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
mesh.register(item, 0, new ModelResourceLocation(registry_name, "inventory"));		
		*/

	}
	
	public static void registerItem(FCItemAPI item)
	{
		registerItem(item.get(),item.getFCRegistryName(), item.getDefaultCreativeTab(), item.getNumberID());
	}
	
	public static CreativeTab getItemGroupByName(String registry_name)
	{
		if (GlobalRegistries.getItemGroupByName(registry_name) != null)
		{
			return GlobalRegistries.getItemGroupByName(registry_name).get();
		}else {
			return GameRegistries.getItemGroupByName(registry_name);
		}
				
	}
	
	public static CreativeTab getItemGroupbyID(int id)
	{
		if (GlobalRegistries.getItemGroupByID(id) != null)
		{
			return GlobalRegistries.getItemGroupByID(id).get();
		}else {
			return GameRegistries.getItemGroupByID(id);
		}
				
	}
	
	
	public static UnifiedItemGroupGetter getFCItemGroupbyName(String registry_name)
	{
		if (GlobalRegistries.getItemGroupByName(registry_name) != null)
		{
			return GlobalRegistries.getItemGroupByName(registry_name);
		}else {
			return new VanillaCreativeTab(registry_name);
		}
				
	}
	
	public static UnifiedItemGroupGetter getFCItemGroupbyID(int id)
	{
		if (GlobalRegistries.getItemGroupByID(id) != null)
		{
			return GlobalRegistries.getItemGroupByID(id);
		}else {
			return new VanillaCreativeTab(getItemGroupbyID(id).getId());
		}
				
	}

	public static void registerItemGroup(CreativeTab tab, String registry_name, int id)
	{
		
	}
	
	public static void registerItemGroup(UnifiedItemGroupGetter tab)
	{
		registerItemGroup(tab.get(),tab.getTabName(), tab.getID());
	}
	
	
	
	
	
	
	
	
	
	
	public static Block getBlockbyName(String registry_name)
	{
		if (GlobalRegistries.getBlockByName(registry_name) != null)
		{
			return GlobalRegistries.getBlockByName(registry_name).get();
		}else {
			return GameRegistries.getBlockFromGameRegistries(registry_name);
		}
				
	}
	
	public static Block getBlockbyID(int id)
	{
		if (GlobalRegistries.getBlockByID(id) != null)
		{
			return GlobalRegistries.getBlockByID(id).get();
		}else {
			return GameRegistries.getBlockFromGameRegistries(id);
		}
				
	}
	
	
	public static FCBlockAPI getFCBlockbyName(String registry_name)
	{
		if (GlobalRegistries.getBlockByName(registry_name) != null)
		{
			return GlobalRegistries.getBlockByName(registry_name);
		}else {
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(registry_name), registry_name);
		}
				
	}
	
	public static FCBlockAPI getFCBlockbyID(int id)
	{
		if (GlobalRegistries.getBlockByID(id) != null)
		{
			return GlobalRegistries.getBlockByID(id);
		}else {
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(id), Block.registry.getName(GameRegistries.getBlockFromGameRegistries(id)).toString());
		}
				
	}

	public static void registerBlock(Block block, String registry_name, CreativeTab default_tab, int id)
	{
		block.setUnlocalisedName(registry_name.split(":")[0] + "!" +  registry_name.split(":")[1]);
		block.setCreativeTab(default_tab);

		Block.registry.def_unknown_80529(id, new ResourceLocation (registry_name.split(":")[0], registry_name.split(":")[1]), block);
		
		Block.BLOCKSTATE_IDS.set(block.defaultBlockstate(), id);
		Item item =	new BlockAsItem(block).setCreativeTab(default_tab);
		


		try {
			Item.class.getDeclaredMethod("blocktoitemadd", Block.class, Item.class).invoke(null, block, item);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	Item.registry.def_unknown_80529(id, new ResourceLocation (registry_name.split(":")[0], registry_name.split(":")[1]), item);

		
	}
	
	public static void registerBlock(FCBlockAPI block)
	{
		registerBlock(block.get(),block.getFCRegistryName(), block.getDefaultCreativeTab(), block.getNumberID());
	}
	



}
