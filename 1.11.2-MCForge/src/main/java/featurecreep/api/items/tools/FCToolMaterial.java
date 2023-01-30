package featurecreep.api.items.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Item.ToolMaterial;

public class FCToolMaterial 
{

	public  int harvest;
	public  int durability;
	public  int speed;
	public  int attack;
	public  int enchantness;
	public  Ingredient repair;
	public  Item repair_item;
	
	private FCToolMaterial(int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, Ingredient repairMaterial)
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
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repair_item));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, int efficiency, int damage, int enchantability, Block repairItem)
	{
		this(harvestLevel, maxUses, efficiency, damage, enchantability, FCIngredient.ingredientFromItem(repair_item));
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
	private Ingredient getToolRepairIngredient() {
		// TODO Auto-generated method stub
		return repair;
	}
	
	
	
	
	
	
	
}
