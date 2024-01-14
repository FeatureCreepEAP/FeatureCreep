package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import game.Item;

public class FCToolMaterial 
{

	public  int harvest;
	public  int durability;
	public  int speed;
	public  int attack;
	public  int enchantness;
	public  FCIngredient repair;
	public  Item repair_item;
	
	private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability)
	{
		harvest = harvestLevel;
		durability = maxUses;
		speed = (int)efficiency;
		attack = (int)damage;
		enchantness = enchantability;
		//repair = new Lazy<Ingredient>(repairMaterial);
		
	}

	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCItemAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability);
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCBlockAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability);
	}
	

	public int getToolMaxUses() {
		// TODO Auto-generated method stub
		return durability;
	}

	public int getToolEfficiency() {
		// TODO Auto-generated method stub
		return speed;
	}

	public int getToolAttackDamage() {
		// TODO Auto-generated method stub
		return attack;
	}

	public int getToolHarvestLevel() {
		// TODO Auto-generated method stub
		return harvest;
	}

	
	public int getToolEnchantability() {
		// TODO Auto-generated method stub
		return enchantness;
	}
	public FCIngredient getToolRepairIngredient() {
		// TODO Auto-generated method stub
		return repair;
	}
	
	
	
	
	
	
}
