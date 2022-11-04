package com.asbestosstar.featurecreep;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(FeatureCreepMC.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(FeatureCreepMC.BLOCKS.toArray(new Block[0]));
	}
	
	
	
	//@SubscribeEvent
//@SideOnly(Side.CLIENT)
//	public static void onModelRegister(ModelRegistryEvent event)
//	{
//for (Item item : FeatureCreepMC.ITEMS)
//{
//	if(item instanceof IHadModels)
//	{
//	((IHadModels)item).registerModels();	
//	}
//	
//}
//for (Block block : FeatureCreepMC.BLOCKS)
//{
//	if(block instanceof IHadModels)
//	{
//	((IHadModels)block).registerModels();	
//	}
////	
//}
////	
//	}

	
	public static void otherRegistries()
	{
		GameRegistry.registerWorldGenerator(new OreGen(), 0);
		
	}
	

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item : FeatureCreepMC.ITEMS)
		{
			FeatureCreepMC.proxy.registerItemRenderer(item, 0, "inventory");
		}
		
		for (Block block : FeatureCreepMC.BLOCKS)
		{
			FeatureCreepMC.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
		}
	}
	
	
	
	
}
