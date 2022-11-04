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
import net.minecraftforge.registries.ForgeRegistries;

public class FCRegistries
{
	
	
	
	
	
	public static void registerItem(FCItem item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

	//	GameData.unfreezeData();
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	public static void registerItem(FCPickaxe item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCAxe item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

//		GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCHoe item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

//		GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCShovel item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

//		GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCSword item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

//		GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}

	public static void registerItem(FCArmour item)
	{
	    ForgeRegistries.ITEMS.register(item.public_modid+":"+item.public_name, item);

//		GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
//        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	
	public static void registerBlock(FCBlock block)
	{
	
	    ForgeRegistries.BLOCKS.register(block.public_modid+":"+block.public_name, block);

	    ForgeRegistries.ITEMS.register(block.public_modid+":"+block.public_name, new BlockItem(block, new Item.Settings().group(block.default_tab)));

		
	}
	
	public static void registerBlock(FCOre block)
	{
	    ForgeRegistries.BLOCKS.register(block.public_modid+":"+block.public_name, block);

	    ForgeRegistries.ITEMS.register(block.public_modid+":"+block.public_name, new BlockItem(block, new Item.Settings().group(block.default_tab)));

	}
	
	
	public static void registerBlock(FCBlockAPI block)
	{
	    ForgeRegistries.BLOCKS.register(block.getModId()+":"+block.getUnlocName(), (Block)block);

	    ForgeRegistries.ITEMS.register(block.getModId()+":"+block.getUnlocName(), new BlockItem((Block)block, new Item.Settings().group(block.getDefaultCreativeTab())));

	}
	
	public static void registerItem(FCItemAPI item)
	{
	    ForgeRegistries.ITEMS.register(item.getModId()+":"+item.getUnlocName(), (Item)item);

	//	GameData.unfreezeData();
//		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		
//		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
 //       FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
}