package featurecreep.api.bg.registries;

import java.util.ArrayList;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;

public class GlobalRegistries {

	public static ArrayList<FCBlockAPI> BLOCKS = new ArrayList<FCBlockAPI>();
	public static ArrayList<FCItemAPI> ITEMS = new ArrayList<FCItemAPI>();
	public static ArrayList<UnifiedItemGroupGetter> ITEMGROUPS = new ArrayList<UnifiedItemGroupGetter>();

	public static UnifiedItemGroupGetter getItemGroupByName(String name)
	{
		for (int g = 0; g < ITEMGROUPS.size(); g++) {

			if (ITEMGROUPS.get(g) instanceof VanillaCreativeTab) {
				VanillaCreativeTab van = (VanillaCreativeTab)ITEMGROUPS.get(g);
				if (van.tabname.equals(name)) {
					return ITEMGROUPS.get(g);
				}
				
			}
			
			if (ITEMGROUPS.get(g).getTabName().equals(name)) {
				return ITEMGROUPS.get(g);
			}	
			
	
		}	
				
	 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	}
	public static UnifiedItemGroupGetter getItemGroupByID(int id)
	{
		
		for (int g = 0; g < ITEMGROUPS.size(); g++) {

			if (id == ITEMGROUPS.get(g).getID())
			{
				return ITEMGROUPS.get(g);
			}
			
		}		
		
		 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	
	}
	
	
	public static FCItemAPI getItemByName(String name)
	{
		for (int g = 0; g < ITEMS.size(); g++) {
			
			if (ITEMS.get(g).getFCRegistryName().equals(name)) {
				return ITEMS.get(g);
			}	

		}	
				
	 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	}
	public static FCItemAPI getItemByID(int id)
	{
		
		for (int g = 0; g < ITEMS.size(); g++) {

			if (id == ITEMS.get(g).getNumberID())
			{
				return ITEMS.get(g);
			}
			
		}		
		
		 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	
	}
	
	
	public static FCBlockAPI getBlockByName(String name)
	{
		for (int g = 0; g < BLOCKS.size(); g++) {
			
			if (BLOCKS.get(g).getFCRegistryName().equals(name)) {
				return BLOCKS.get(g);
			}	

		}	
				
	 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	}
	public static FCBlockAPI getBlockByID(int id)
	{
		
		for (int g = 0; g < BLOCKS.size(); g++) {

			if (id == BLOCKS.get(g).getNumberID())
			{
				return BLOCKS.get(g);
			}
			
		}		
		
		 return null; //Something is wrong, maybe i should make it default to some fallback tab?
	
	}
	
	
}
