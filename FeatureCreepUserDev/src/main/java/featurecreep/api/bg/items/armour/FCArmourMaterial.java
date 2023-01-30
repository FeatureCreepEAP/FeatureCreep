package featurecreep.api.bg.items.armour;

import featurecreep.api.bg.blocks.FCBlock;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;

public class FCArmourMaterial{

	public  int durability;
	public  ArmourProtectionValuesArray protection;
	public  int enchantability;
	public  FCIngredient repair;
	public  int toughness;
	public  String name;
	public  int knockback_resistance;
	
	
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCIngredient repair, String name, int toughness, int knockback_resistance)
{
this.durability = durability;
this.protection = protection;
this.enchantability = enchantability;
this.repair = repair;
this.name = name;
this.toughness = toughness;
this.knockback_resistance = knockback_resistance;
	
	
}
	


public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCItem repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair), name, toughness, knockback_resistance);
}


public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCBlock repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair), name, toughness, knockback_resistance);
}
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCBlockAPI repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem((FCBlock)repair), name, toughness, knockback_resistance);
}
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCItemAPI repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem((FCItem)repair), name, toughness, knockback_resistance);
}



public int getFCDurability()
{
return this.durability;	
}

public ArmourProtectionValuesArray getProtectionValueArray()
{
	return this.protection;
}

public int getFCEnchantability()
{
return this.enchantability;
}


public int getFCToughness()
{
return this.toughness;	
}

public int getFCKnockBackResistance()
{
return this.knockback_resistance;	
}

public String getFCName()
{
return this.name;	
}



public  int getFCProtection(FCArmourSlot slot)
{
	if (slot.equals(FCArmourSlots.HELMET))
	{
		return protection.getHelmetProtectionValue();
	}
	else if (slot.equals(FCArmourSlots.TUBIC))
	{
		return protection.getChestplateProtectionValue();
	}else if  (slot.equals(FCArmourSlots.LEGGINGS))
	{
		return protection.getLeggingsProtectionValue();
	}else if  (slot.equals(FCArmourSlots.BOOTS))
	{
		return protection.getLeggingsProtectionValue();
	}else
	{
		return protection.getHelmetProtectionValue();
	}
		
}


public  int getFCTextureNumber(FCArmourSlot slot)
{
	if (slot.equals(FCArmourSlots.LEGGINGS))
	{
		return 2;
	}else return 1;

}

    
    
}
