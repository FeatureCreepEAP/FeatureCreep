package featurecreep.api;

import featurecreep.api.ui.FCCreativeTab;

public interface BlockOrItem {

	public String getModId();
	public String getUnlocName();
	public int getNumberID();
	public FCCreativeTab getDefaultCreativeTab();
	
	
}
