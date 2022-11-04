package featurecreep.api.items.tools;

import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCShovel implements FCItemAPI
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public FCCreativeTab default_tab;
	public FCToolMaterial mat;
	public int damage;
	public int attackspeed;
	
	public FCShovel(int id, String modid, String name, FCCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		public_modid = modid;
		public_name = name;
		this.default_tab = group;
	this.number_id = id;
	this.mat = material;
	this.damage = attackDamage;
	attackspeed = attackSpeed;
		}

		
		public FCShovel(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{this(id, modid, name, (FCCreativeTab)null, material, attackDamage, attackSpeed);}

		
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
		
		
		public FCToolMaterial getFCToolMaterial()	{return mat;}
		public int getToolAttackDamage() {return damage;}
		public int getAttackSpeed() {return attackspeed;}
	
	
	
	
}
