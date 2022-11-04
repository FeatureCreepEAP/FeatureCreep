package featurecreep.api.items.tools.datafied.dmr;

import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.FCToolMaterial;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCShovelAsDMR extends FCItemAsDMR 
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public String default_tab;
	public FCToolMaterial mat;
	public int damage;
	public int attackspeed;
	
	
	public FCShovelAsDMR(int id, String modid, String name, FCCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
super (id, modid,name, group);
		public_modid = modid;
		public_name = name;
		this.default_tab = group.id;
	this.number_id = id;
	this.mat = material;
	this.damage = attackDamage;
	attackspeed = attackSpeed;	
		}

		
	public FCShovelAsDMR(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super (id, modid,name, group);
		public_modid = modid;
		public_name = name;
		this.default_tab = group.tabname;
	this.number_id = id;
	this.mat = material;
	this.damage = attackDamage;
	attackspeed = attackSpeed;	
	}
		

		
	

	
	public FCToolMaterial getFCToolMaterial()	{return mat;}
	public int getToolAttackDamage() {return damage;}
	public int getAttackSpeed() {return attackspeed;}
	
	
	
	
}
