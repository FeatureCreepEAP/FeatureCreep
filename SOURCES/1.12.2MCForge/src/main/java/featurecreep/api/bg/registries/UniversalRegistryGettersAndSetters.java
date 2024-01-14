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
import game.gfx_asset;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
			return new VanillaItem(GameRegistries.getItemFromGameRegistries(id), GameRegistries.getItemFromGameRegistries(id).getRegistryName().toString());
		}
				
	}

	public static void registerItem(Item item, String registry_name, CreativeTab default_tab, int id)
	{
		item.setRegistryName(registry_name);
		item.setUnlocalisedName(registry_name.split(":")[0] + "!" +  registry_name.split(":")[1]);
		item.setCreativeTab(default_tab);
		ForgeRegistries.ITEMS.register(item);	//Took me so damn long to find, Down with Poor Documentation!
		ModelLoader.setCustomModelResourceLocation(item, 0, new gfx_asset(item.getRegistryName(), "inventory"));	
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
			return new VanillaBlock(GameRegistries.getBlockFromGameRegistries(id), GameRegistries.getBlockFromGameRegistries(id).getRegistryName().toString());
		}
				
	}

	public static void registerBlock(Block block, String registry_name, CreativeTab default_tab, int id)
	{
		block.setRegistryName(registry_name);
		block.setUnlocalisedName(registry_name.split(":")[0] + "!" +  registry_name.split(":")[1]);
		block.setCreativeTab(default_tab);
		ForgeRegistries.BLOCKS.register(block);	
		ForgeRegistries.ITEMS.register(new BlockAsItem(block).setRegistryName(block.getRegistryName()));	
		ModelLoader.setCustomModelResourceLocation(Item.fromBlock(block), 0, new gfx_asset(registry_name, "inventory"));
		
	}
	
	public static void registerBlock(FCBlockAPI block)
	{
		registerBlock(block.get(),block.getFCRegistryName(), block.getDefaultCreativeTab(), block.getNumberID());
	}
	
	
	
}
