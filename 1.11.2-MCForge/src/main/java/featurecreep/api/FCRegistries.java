package featurecreep.api;

import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;

public class FCRegistries
{
	
	
	//https://github.com/Trikzon/Snow-Variants/blob/1.13.2/src/main/java/trikzon/snowvariants/init/ModBlocks.java
	
	
	public static void registerItem(FCItem item)
	{
		//GameData.unfreezeData();
		//FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
		
		//FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
        //FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	item.setRegistryName(item.public_modid, item.public_name);
	}
	public static void registerItem(FCPickaxe item)
	{
		//GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

	}
	public static void registerItem(FCAxe item)
	{
		//GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
//        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

	}
	public static void registerItem(FCHoe item)
	{
	//	GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
      //  FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

	}
	public static void registerItem(FCShovel item)
	{
	//	GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
      //  FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

	}
	public static void registerItem(FCSword item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);

	}
	
	
	
}