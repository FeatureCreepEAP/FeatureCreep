package featurecreep.api.items.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FCToolMaterial implements ToolMaterial
{

	public static int harvest;
	public static int durability;
	public static int speed;
	public static int attack;
	public static int enchantness;
	public static Ingredient repair;
	public static Item repair_item;
	
	private FCToolMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Ingredient repairMaterial)
	{
		harvest = harvestLevel;
		durability = maxUses;
		speed = (int)efficiency;
		attack = (int)damage;
		enchantness = enchantability;
		repair = repairMaterial;
		//repair = new Lazy<Ingredient>(repairMaterial);
		
	}
	
	
	public FCToolMaterial (int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repairItem)
	{
		
	this(harvestLevel, maxUses, efficiency, damage, enchantability, Ingredient.ofItems(repairItem));
	}
	
	public FCToolMaterial (int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Block repairItem)
	{
		this(harvestLevel, maxUses, efficiency, damage, enchantability, Ingredient.ofItems(repairItem));
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

	public Ingredient getToolRepairIngredient() {
		// TODO Auto-generated method stub
		return repair;
	}
	
	public ItemConvertible getToolRepairItem() {
		// TODO Auto-generated method stub
		return repair_item;
	}


	@Override
	public float getMiningSpeedMultiplier() {
		// TODO Auto-generated method stub
	return	this.getToolEfficiency();
	}


	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
	return	this.getToolAttackDamage();
	}


	@Override
	public int getMiningLevel() {
		// TODO Auto-generated method stub
	return	this.getToolHarvestLevel();
	}


	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
	return	this.getToolEnchantability();
	}


	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
return this.getToolRepairIngredient();
	}


	@Override
	public int getDurability() {
		// TODO Auto-generated method stub
		return this.getToolMaxUses();
	}
	
	
	
	
	
}