package featurecreep.api;

import net.minecraft.creativetab.CreativeTabs;

public interface BlockOrItem {

	public String getModId();
	public String getUnlocName();
	public int getNumberID();
	public CreativeTabs getDefaultCreativeTab();
	
}
