package featurecreep.api;

import dangerzone.Utils;
import dangerzone.items.Item;
import dangerzone.items.Items;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;

public class FCRegistries
{
	
	
	public static void registerItem(FCItem item)
	{
		//Items.registerItem(item);
	
	
		itemRegisterDZ(item);
	
	}
	public static void registerItem(FCPickaxe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCAxe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCHoe item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCShovel item)
	{
		Items.registerItem(item);
	}
	public static void registerItem(FCSword item)
	{
		Items.registerItem(item);
	}
	
	
	//DZ Only
	private static int itemRegisterDZ(Item item)
	{

		int i=0;
			
			for(i=1;i<Items.itemsMAX;i++){
				if(Items.ItemArray[i] != null){
					if(Items.ItemArray[i].uniquename.equals(item.uniquename)){ //duplicate!!!
						return 0; //duplicate!!!
					}
				}
			}
			
			for(i=1;i<Items.itemsMAX;i++){
				if(Items.ItemArray[i] == null)break;
			}
			if(i >= Items.itemsMAX-1)return 0;
			
			if(Items.prop != null)i = Utils.getPropertyInt(Items.prop, item.uniquename, 1, Items.itemsMAX-1, i);
			
			//existing or new item
			if(Items.ItemArray[i] == null){ //Slot is open, this is good. Give it the same ID it had last time!
				//System.out.printf("Empty spot at %d for item %s\n", i, b.uniquename);
				Items.ItemArray[i] = item;
	            item.itemID = i;
				if(Items.prop != null)Items.prop.setProperty(item.uniquename, String.format("%d", i)); //next time we will find it!
				return i;
			}else{ 
				//System.out.printf("NON-Empty spot at %d for item %s\n", i, b.uniquename );
				//Oops. Slot already taken.
				//Give this slot to the existing one, and bump the intruder to a new slot.
				//Should only happen when adding new mods and they get loaded before old ones.
				Item intruder = Items.ItemArray[i];
				int isave = i;
				Items.ItemArray[i] = item;
				item.itemID = i;
				for(i=1;i<Items.itemsMAX;i++){
					if(Items.ItemArray[i] == null){
						Items.ItemArray[i] = intruder;
						intruder.itemID = i;
						//System.out.printf("Original moved to %d for item %s\n", i, intruder.uniquename );
						if(Items.prop != null)Items.prop.setProperty(intruder.uniquename, String.format("%d", i)); //next time we will find it!
						return isave;
					}
				}
				return 0;
			}
			
	}
	
	
	
	
}