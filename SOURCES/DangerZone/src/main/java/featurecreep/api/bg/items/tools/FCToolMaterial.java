package featurecreep.api.bg.items.tools;

import dangerzone.blocks.Block;
import dangerzone.items.Item;
import featurecreep.api.bg.blocks.FCBlock;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCOre;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.items.FCItemAPI;

public class FCToolMaterial 
{

	public  int harvest;
	public  int durability;
	public  int speed;
	public  int attack;
	public  int enchantness;
	public  FCIngredient repair;
	public  Item repair_item;
	
	private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCIngredient repairMaterial)
	{
		harvest = harvestLevel;
		durability = maxUses;
		speed = (int)efficiency;
		attack = (int)damage;
		enchantness = enchantability;
		repair = repairMaterial;
		//repair = new Lazy<Ingredient>(repairMaterial);
		
	}
	

	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCItemAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem.get()));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCBlockAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem.get()));
	}
	
	
	
	
	
	public int getToolMaxUses() {
		// TODO Auto-generated method stub
		return durability;
	}

	public float getToolEfficiency() {
		// TODO Auto-generated method stub
		return speed;
	}

	public float getToolAttackDamage() {
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
	
	public Item getToolRepairItem() {
		// TODO Auto-generated method stub
		return repair_item;
	}

	
	
	
	
	
}
