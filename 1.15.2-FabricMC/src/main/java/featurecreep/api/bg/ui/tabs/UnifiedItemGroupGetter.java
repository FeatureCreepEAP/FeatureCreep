package featurecreep.api.bg.ui.tabs;

import net.minecraft.item.ItemGroup;

public interface UnifiedItemGroupGetter {

	
	public ItemGroup get();
	
	
	ItemGroupHolder holder = new ItemGroupHolder();
	String tab_name = holder.tab_name;
	int tab_id = holder.tab_id;
	public default void setID (int i)
	{
	holder.tab_id = i;	
	}
	public default int getID ()
	{
	return holder.tab_id;	
	}
	
	public default void setTabName (String name)
	{
	holder.tab_name = name;	
	}
	public default String getTabName ()
	{
	return holder.tab_name;	
	}
	
}
