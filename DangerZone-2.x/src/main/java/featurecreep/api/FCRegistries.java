package featurecreep.api;

import dangerzone.blocks.Block;
import dangerzone.blocks.Blocks;
import dangerzone.items.Item;
import dangerzone.items.Items;
import featurecreep.api.blocks.FCBlock;
import featurecreep.api.blocks.FCBlockAPI;
import featurecreep.api.blocks.FCOre;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.FCItemAPI;
import featurecreep.api.items.armour.FCArmour;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
		//Items.registerItem(item);
		Items.registerItem(item);	
	}
	public static void registerItem(FCPickaxe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCAxe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCHoe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCShovel item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCSword item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCArmour item)
	{
		//Items.registerItem(item);
		Items.registerItem(item);	
	}
	
	public static void registerBlock(FCBlock block)
	{
		//Items.registerItem(item);
		Blocks.registerBlock(block);	
	}
	
	public static void registerBlock(FCOre block)
	{
		//Items.registerItem(item);
		Blocks.registerBlock(block);	
	}
	
	public static void registerBlock(FCBlockAPI block)
	{
		//Items.registerItem(item);
		Blocks.registerBlock((Block)block);	
	}

	public static void registerBlock(FCItemAPI block)
	{
		//Items.registerItem(item);
		Items.registerItem((Item)block);	
	}
	
}