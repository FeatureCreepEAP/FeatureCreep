package featurecreep.api.bg.items.tools;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import game.Item;
import game.ItemConvertible;
import game.ToolMaterial;
import game.ToolRepairIngredient;

public class FCToolMaterial implements ToolMaterial
{

	public  int harvest;
	public  int durability;
	public  int speed;
	public  int attack;
	public  int enchantness;
	public  ToolRepairIngredient repair;
	public  Item repair_item;
	
	private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, ToolRepairIngredient repairMaterial)
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

	
	// TODO make these return stuff eventually and public
	private ToolRepairIngredient getToolRepairIngredient() {
		// TODO Auto-generated method stub
		return repair;
	}
	
	private ItemConvertible getToolRepairItem() {
		// TODO Auto-generated method stub
		return repair_item;
	}



	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
	return	this.getToolAttackDamage();
	}


	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
	return	this.getToolHarvestLevel();
	}


	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
	return	this.getToolEnchantability();
	}

	@Override
	public ToolRepairIngredient getRepairIngredient() {
		// TODO Auto-generated method stub
//return this.getToolRepairIngredient();
 return getToolRepairIngredient();
	}


	@Override
	public int getMaxUses() {
		// TODO Auto-generated method stub
		return this.getToolMaxUses();
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return	this.getToolEfficiency();
		}
	
	
	
	
	
}
