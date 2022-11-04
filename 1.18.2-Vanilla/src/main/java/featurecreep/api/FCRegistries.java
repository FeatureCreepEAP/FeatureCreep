package featurecreep.api;

import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
	//	Registry.register(Registry.ITEM, new Identifier(item.public_modid, item.public_name), item);
	item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	public static void registerItem(FCPickaxe item)
	{
		item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	public static void registerItem(FCAxe item)
	{
		item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	public static void registerItem(FCHoe item)
	{
		item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	public static void registerItem(FCShovel item)
	{
		item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	public static void registerItem(FCSword item)
	{
		item.setRegistryName(new Identifier(item.public_modid, item.public_name));
	}
	
	
}