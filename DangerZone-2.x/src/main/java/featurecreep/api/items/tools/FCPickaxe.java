package featurecreep.api.items.tools;

import dangerzone.items.Item;
import dangerzone.items.ItemPickAxe;
import featurecreep.FeatureCreep;
import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCPickaxe extends ItemPickAxe implements FCItemAPI
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public FCCreativeTab default_tab;
	

	public FCPickaxe(int id, String modid, String name, FCCreativeTab group, FCToolMaterial material, int attackDamage, float attackSpeed)
	{
		super(modid + ":" + name, FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png", material.durability, material.attack, material.speed);
		public_modid = modid;
		public_name = name;
		registerModels(this);
		this.default_tab = group;
		this.itemID = id;
		this.number_id = id;
	}

			
		public FCPickaxe(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, float attackSpeed)
	{this(id, modid, name, (FCCreativeTab)null, material, attackDamage, attackSpeed);}
		
	

		
		
	
	public void registerModels(Item item) {
		   
	      
	}




		
		public String getModId() {
			// TODO Auto-generated method stub
			return public_modid;
		}




		
		public String getUnlocName() {
			// TODO Auto-generated method stub
			return public_name;
		}




		
		public int getNumberID() {
			// TODO Auto-generated method stub
			return number_id;
		}




		
		public FCCreativeTab getDefaultCreativeTab() {
			// TODO Auto-generated method stub
			return default_tab;
		}
		
		
	
	
	
	
}
