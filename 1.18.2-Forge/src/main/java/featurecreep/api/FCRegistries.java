package featurecreep.api;

import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;

public class FCRegistries
{
	
	
	
	
	
	public static void registerItem(FCItem item)
	{
		GameData.unfreezeData();
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	public static void registerItem(FCPickaxe item)
	{
		GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCAxe item)
	{
		GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCHoe item)
	{
		GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCShovel item)
	{
		GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	public static void registerItem(FCSword item)
	{
		GameData.unfreezeData();	
		FCForgeRegistries.ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, item.public_modid);
		FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

	}
	
	
	
}