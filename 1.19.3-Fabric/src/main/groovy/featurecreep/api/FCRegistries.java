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
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCPickaxe item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCAxe item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCHoe item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCShovel item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCSword item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}
	public static void registerItem(FCArmour item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
		ItemGroupEvents.modifyEntriesEvent(item.default_tab).register((content) -> {
	        content.add(item);
	});
	}

	public static void registerBlock(FCBlock block)
	{
		Registry.register(Registry.BLOCK, new Identifier(block.public_modid, block.public_name), block);
		Registry.register(Registry.ITEM, new Identifier(block.public_modid, block.public_name), new BlockItem(block, new Item.Settings()));
		ItemGroupEvents.modifyEntriesEvent(block.default_tab).register((content) -> {
	        content.add(block);
	});
	}
	
	public static void registerBlock(FCOre block)
	{
		Registry.register(Registry.BLOCK, new Identifier(block.public_modid, block.public_name), block);
		Registry.register(Registry.ITEM, new Identifier(block.public_modid, block.public_name), new BlockItem(block, new Item.Settings()));

		ItemGroupEvents.modifyEntriesEvent(block.default_tab).register((content) -> {
	        content.add(block);
	});
	
	}
	

	public static void registerBlock(FCBlockAPI block)
	{
		Registry.register(Registry.BLOCK, new Identifier(block.getModId(), block.getUnlocName()), (Block)block);
		Registry.register(Registry.ITEM, new Identifier(block.getModId(), block.getUnlocName()), new BlockItem((Block)block, new Item.Settings()));
		ItemGroupEvents.modifyEntriesEvent(block.getDefaultCreativeTab()).register((content) -> {
	        content.add((Block)block);
	});
	}
	
	public static void registerItem(FCItemAPI item)
	{
		Registry.register(Registry.ITEM, new Identifier(item.getModId(), item.getUnlocName()), (Item)item);

		ItemGroupEvents.modifyEntriesEvent(item.getDefaultCreativeTab()).register((content) -> {
	        content.add((Item)item);
	});
	}
	
	
}