package featurecreep.api.bg.blocknitem;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

import featurecreep.api.bg.ui.FCCreativeTab;

public interface BlockOrItem<T> {


	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group) {
	setModId(modid);
	setUnlocName(name);
	setDefaultCreativeTab(group.get());
	setNumberID(id);
			System.out.println("Initalising "+getFCRegistryName());
	}
	
	public BlocknItemFieldHolder holder();
		public void registerModels();
	public default String getModId() {
		return holder().public_modid;
	}
	public default String getUnlocName() {
		return holder().public_name;
	}
	public default int getNumberID() {
		return holder().number_id;
	}
	public default FCCreativeTab getDefaultCreativeTab() {
		return holder().default_tab;
	}
	
	
	public default void setModId(String modid) {
		holder().public_modid = modid;
	}
	public default void setUnlocName(String name) {
		holder().public_name = name;
	}
	public default void setNumberID(int id) {
		holder().number_id = id;
	}
	public default void setDefaultCreativeTab(FCCreativeTab group) {
		holder().default_tab = group;
	}
	
	public default String getFCRegistryName()
	{
		return (getModId()+":"+getUnlocName());
	}
	
	
public Object getObject();
	
	
	
}
