package featurecreep.api.items.armour;

import dangerzone.items.Item;
import dangerzone.items.ItemArmor;
import featurecreep.FeatureCreep;
import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCArmour extends ItemArmor implements FCItemAPI{

	public String public_modid;
	public String public_name;
	public int number_id;
	public FCCreativeTab default_tab;
	
	public FCArmour(int id, String modid, String name, FCCreativeTab group, FCArmourMaterial material, FCArmourSlot slot) {
		super(modid + ":" + name, FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png", FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/minecraft/textures/models/armor/" + name + "_layer_1.png", FeatureCreep.gamepath+"/resourcepacks/fcpack_8/assets/minecraft/textures/models/armor/" + name + "_layer_2.png", material.getFCProtection(slot), material.getFCDurability(),slot.getSlot());
		// TODO Auto-generated constructor stub
		public_modid = modid;
		public_name = name;
		registerModels(this);
		this.default_tab = group;
		this.number_id = id;
	
	}

	
	public FCArmour(int id, String modid, String name, VanillaCreativeTab group, FCArmourMaterial material, FCArmourSlot slot) {
		this(id, modid, name, (FCCreativeTab)null, material, slot);
		// TODO Auto-generated constructor stub

	}
	

	

	
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
