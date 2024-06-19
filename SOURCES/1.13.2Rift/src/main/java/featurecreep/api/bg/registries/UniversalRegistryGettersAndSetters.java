package featurecreep.api.bg.registries;

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
import game.GameRegistriesInterface;
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
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(id), GameRegistriesInterface.ITEMS.getName(GameRegistries.getItemFromGameRegistries(id)).toString());
		}
				
	}

	public static void registerItem(Item item, String registry_name, CreativeTab default_tab, int id)
	{
		GameRegistriesInterface.ITEMS.register(new ResourceLocation(registry_name), item);		
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
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(id), GameRegistriesInterface.BLOCKS.getName(GameRegistries.getBlockFromGameRegistries(id)).toString() );
		}
				
	}

	public static void registerBlock(Block block, String registry_name, CreativeTab default_tab, int id)
	{
		GameRegistriesInterface.BLOCKS.register(new ResourceLocation(registry_name), block);
		BlockAsItem item = new BlockAsItem(block, new Item.Info().setCreativeTab(default_tab));
		GameRegistriesInterface.ITEMS.register(new ResourceLocation(registry_name), item);
	Item.BLOCK_ITEMS.put(block, item);
	Block.BLOCKSTATE_IDS.add(block.getDefaultState());
	}
	
	public static void registerBlock(FCBlockAPI block)
	{
		registerBlock(block.get(),block.getFCRegistryName(), block.getDefaultCreativeTab(), block.getNumberID());
	}
	
	
}
