package featurecreep.api;

import net.minecraft.item.ItemGroup;

public interface BlockOrItem {

	public String getModId();
	public String getUnlocName();
	public int getNumberID();
	public ItemGroup getDefaultCreativeTab();
	
}
