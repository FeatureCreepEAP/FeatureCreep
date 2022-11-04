package featurecreep.api.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public interface FCItemAPI
{

	public void registerModels(Item item);
	public String getModId();
	public String getUnlocName();
	public int getNumberID();
	public ItemGroup getDefaultCreativeTab();
	
}