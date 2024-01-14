package featurecreep.api.bg.items.vanilla;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.FCCreativeTabs;
import game.Item;

/**
TRY TO AVOID THIS CLASS, USE IN THE WRONG CIRCUMSTANCES CAN LEAD TO CRASH/MOD NOT LOADING. STAY WITHIN DOCUMENTATION UNLESS YOU REALLY KNOW WHAT YOU ARE DOING
**/
public class VanillaItem implements FCItemAPI<VanillaItem>{

public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}
	
	
	public Item vanilla_item; //BE VERY CAREFUL
	
	public VanillaItem(Item item, String registry_name)
	{
		vanilla_item=item; //Set the Item to be returned
		setModId(registry_name.split(":")[0]);
		setUnlocName(registry_name.split(":")[1]);
		//registerModels(); We do not need this at this time, maybe in the future we can do something with it
		setDefaultCreativeTab(FCCreativeTabs.TOOLS.get()); //May not work on all versions, we may need to remove this. Yay it works on 1.13 and newer, we will need to check about 1.19.3 though, 1.19.3 does not work but i can put a generic tab. Soon I will make a porting tab though
		setNumberID(item.getID(item));//Will just be 0 in some versions most likely
	}
	
	public VanillaItem(FCItemAPI item, String registry_name) //As a backup or for ported items
	{
		this (item.get(),  registry_name);
	}
	
	
	@Override
	public Item get()
	{
return 	vanilla_item; //BE VERY CAREFUL
	}
	
}
