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
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class FCRegistries
{
	
	
	//https://github.com/Trikzon/Snow-Variants/blob/1.13.2/src/main/java/trikzon/snowvariants/init/ModBlocks.java
	
	
	public static void registerItem(FCItem item)
	{
item.setRegistryName(item.public_modid, item.public_name);
item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
ForgeRegistries.ITEMS.register(item);	//Took me so damn long to find, Down with Poor Documentation!
//FeatureCreepMC.proxy.registerItemRenderer(item, 0, "inventory");
ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	public static void registerItem(FCPickaxe item)
	{
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	public static void registerItem(FCAxe item)
	{
		//GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
//        FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);

		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	public static void registerItem(FCHoe item)
	{
	//	GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
      //  FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);

		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	public static void registerItem(FCShovel item)
	{
	//	GameData.unfreezeData();	
//		FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
      //  FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);

		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	public static void registerItem(FCSword item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	
	public static void registerItem(FCArmour item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
		ForgeRegistries.ITEMS.register(item);	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	
	
	public static void registerBlock(FCBlock item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
		item.setCreativeTab(item.default_tab);
		ForgeRegistries.BLOCKS.register(item);	
		ForgeRegistries.ITEMS.register(new ItemBlock(item).setRegistryName(item.getRegistryName()));	

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item), 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	
	
	public static void registerBlock(FCOre item)
	{
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(item.public_modid, item.public_name);
		item.setUnlocalizedName(item.getModId() + "!" +  item.public_name);
		item.setCreativeTab(item.default_tab);
		ForgeRegistries.BLOCKS.register(item);	
//		ForgeRegistries.ITEMS.register(Block.getIdFromBlock(item), Block.REGISTRY.getNameForObject(item), new ItemBlock(item));	
		ForgeRegistries.ITEMS.register(new ItemBlock(item).setRegistryName(item.getRegistryName()));	

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item), 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	
	
	
	public static void registerBlock(FCBlockAPI items)
	{
	Block item = (Block)items;	
	//	GameData.unfreezeData();	
	//	FCForgeRegistries.ITEMS = new DeferredRegister(ForgeRegistries.ITEMS, item.public_modid);
	//	FCForgeRegistries.ITEMS.register(item.public_name, () -> item);
     //   FCForgeRegistries.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		item.setRegistryName(items.getModId(), items.getUnlocName());
		item.setUnlocalizedName(items.getModId() + "!" +  items.getUnlocName());
		item.setCreativeTab(items.getDefaultCreativeTab());
		ForgeRegistries.BLOCKS.register(item);	
//		ForgeRegistries.ITEMS.register(Block.getIdFromBlock(item), Block.REGISTRY.getNameForObject(item), new ItemBlock(item));	
		ForgeRegistries.ITEMS.register(new ItemBlock(item).setRegistryName(item.getRegistryName()));	

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(item), 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

	}
	
	
	public static void registerItem(FCItemAPI items)
	{
		Item item = (Item)items;
item.setRegistryName(items.getModId(), items.getUnlocName());
item.setUnlocalizedName(items.getModId() + "!" +  items.getUnlocName());
ForgeRegistries.ITEMS.register(item);	//Took me so damn long to find, Down with Poor Documentation!
//FeatureCreepMC.proxy.registerItemRenderer(item, 0, "inventory");
ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	item.setCreativeTab(items.getDefaultCreativeTab());
	}
	
	
}