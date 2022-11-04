package featurecreep.api;

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
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCPickaxe item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCAxe item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCHoe item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCShovel item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCSword item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	public static void registerItem(FCArmour item)
	{
		 Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
	}
	
	public static void registerBlock(FCBlock block)
	{
		Registry.Registry.register(new Identifier(block.public_modid, block.public_name), block);
		Registry.ITEM.register(new Identifier(block.public_modid, block.public_name), new BlockItem(block, new Item.Settings().group(block.default_tab)));

	}
	
	public static void registerBlock(FCOre block)
	{
		Registry.Registry.register(new Identifier(block.public_modid, block.public_name), block);
		Registry.ITEM.register(new Identifier(block.public_modid, block.public_name), new BlockItem(block, new Item.Settings().group(block.default_tab)));

	}
	

	public static void registerBlock(FCBlockAPI block)
	{
		Registry.Registry.register(new Identifier(block.getModId(), block.getUnlocName()), (Block)block);
		Registry.ITEM.register(new Identifier(block.getModId(), block.getUnlocName()), new BlockItem((Block)block, new Item.Settings().group(block.getDefaultCreativeTab())));

	}
	
	public static void registerItem(FCItemAPI item)
	{
		Registry.ITEM.register(new Identifier(item.getModId(), item.getUnlocName()), (Item)item);
	}
	
	
}