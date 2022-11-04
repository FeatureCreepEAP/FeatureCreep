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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCRegistries
{
	
	
	
	
	
	public static void registerItem(FCItem item)
	{
		//GameData.unfreezeData();
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	public static void registerItem(FCPickaxe item)
	{
		//GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCAxe item)
	{
		//GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCHoe item)
	{
	//	GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCShovel item)
	{
	//	GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCSword item)
	{
	//	GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	
	public static void registerItem(FCArmour item)
	{
	//	GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	
	public static void registerBlock(FCBlock item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
	
		ForgeRegistries.BLOCKS.register(item);	
	    ForgeRegistries.ITEMS.register(new BlockItem(item, new Item.Settings().group(item.default_tab)).setRegistryName(item.public_modid, item.public_name));

	}
	
	
	public static void registerBlock(FCOre item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

		ForgeRegistries.BLOCKS.register(item);	
//		ForgeRegistries.ITEMS.register(Block.getIdFromBlock(item), Block.REGISTRY.getNameForObject(item), new ItemBlock(item));	
	    ForgeRegistries.ITEMS.register(new BlockItem(item, new Item.Settings().group(item.default_tab)).setRegistryName(item.public_modid, item.public_name));


	}
	
	
	
	public static void registerBlock(FCBlockAPI items)
	{
	Block item = (Block)items;	
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(items.getModId(), items.getUnlocName());
		ForgeRegistries.BLOCKS.register(item);	
//		ForgeRegistries.ITEMS.register(Block.getIdFromBlock(item), Block.REGISTRY.getNameForObject(item), new ItemBlock(item));	
	    ForgeRegistries.ITEMS.register(new BlockItem((Block)items, new Item.Settings().group(items.getDefaultCreativeTab())).setRegistryName(items.getModId(), items.getUnlocName()));


	}
	
	
	public static void registerItem(FCItemAPI items)
	{
		Item item = (Item)items;
item.setRegistryName(items.getModId(), items.getUnlocName());
ForgeRegistries.ITEMS.register(item);	//Took me so damn long to find, Down with Poor Documentation!
//FeatureCreepMC.proxy.registerItemRenderer(item, 0, "inventory");
	}
	
}