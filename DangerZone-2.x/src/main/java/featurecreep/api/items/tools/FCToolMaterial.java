package featurecreep.api.items.tools;

import dangerzone.blocks.Block;
import dangerzone.items.Item;
import featurecreep.api.blocks.FCBlock;
import featurecreep.api.blocks.FCBlockAPI;
import featurecreep.api.blocks.FCOre;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.FCItemAPI;
import featurecreep.api.items.FCItem;

public class FCToolMaterial 
{

	public static int harvest;
	public static int durability;
	public static int speed;
	public static int attack;
	public static int enchantness;
	public static FCIngredient repair;
	public static Item repair_item;
	
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
	
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, Item repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, Block repairItem)
	{
		this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem));
	}
	
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCItem repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCItemAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem((Item)repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCBlockAPI repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem((Block)repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCBlock repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, FCOre repairItem)
	{
		this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repairItem));
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