package featurecreep.api;

import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
		// Registry.ITEM.register(new Identifier(item.public_modid + ":" + item.public_name), item);
		//	registerItem(6073, new , STEEL_INGOT);
			Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	public static void registerItem(FCPickaxe item)
	{
		Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	public static void registerItem(FCAxe item)
	{
		Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	public static void registerItem(FCHoe item)
	{
		Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	public static void registerItem(FCShovel item)
	{
		Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	public static void registerItem(FCSword item) {
	
		Item.REGISTRY.register(item.getNumberID(), new ResourceLocation (item.getModId(), item.getUnlocName()), item);
	}
	
	
}