package featurecreep.api.items.tools;

import featurecreep.api.blocks.FCBlock;
import featurecreep.api.blocks.FCBlockAPI;
import featurecreep.api.blocks.FCOre;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.FCItemAPI;
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
	public int getMiningLevel() {
		// TODO Auto-generated method stub
	return	this.getToolHarvestLevel();
	}


	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
	return	this.getToolEnchantability();
	}

//Do not Use
	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
//return this.getToolRepairIngredient();
 return FCIngredient.ingredientFromItem(repair_item);
	}


	@Override
	public int getDurability() {
		// TODO Auto-generated method stub
		return this.getToolMaxUses();
	}


	@Override
	public float getMiningSpeed() {
		// TODO Auto-generated method stub
		return	this.getToolEfficiency();
	}
	
	
	
	
	
}