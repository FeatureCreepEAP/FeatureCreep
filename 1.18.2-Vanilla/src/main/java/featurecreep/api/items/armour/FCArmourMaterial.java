package featurecreep.api.items.armour;

import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCIngredient;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class FCArmourMaterial implements ArmorMaterial{

	public static int durability;
	public static ArmourProtectionValuesArray protection;
	public static int enchantability;
	public static Ingredient repair;
	public static int toughness;
	public static String name;
	public static int knockback_resistance;
	
	
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, Ingredient repair, String name, int toughness, int knockback_resistance)
{
this.durability = durability;
this.protection = protection;
this.enchantability = enchantability;
this.repair = repair;
this.name = name;
this.toughness = toughness;
this.knockback_resistance = knockback_resistance;
	
	
}
	

public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, Item repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair), name, toughness, knockback_resistance);
}

public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, Block repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair), name, toughness, knockback_resistance);
}

public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCItem repair, String name, int toughness, int knockback_resistance)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair), name, toughness, knockback_resistance);
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



    public int getDurability(EquipmentSlot var1)
    {
    	return this.durability;	
    }

    public int getProtectionAmount(EquipmentSlot var1)
    {
    	if (var1.equals(EquipmentSlot.HEAD)){
    	return this.protection.getHelmetProtectionValue();
    	}
    	else if (var1.equals(EquipmentSlot.CHEST)){
        	return this.protection.getChestplateProtectionValue();
        	}
    	else if (var1.equals(EquipmentSlot.LEGS)){
        	return this.protection.getLeggingsProtectionValue();
        	}
    	else if (var1.equals(EquipmentSlot.FEET)){
        	return this.protection.getBootsProtectionValue();
        	}
    	else {
        	return this.protection.getHelmetProtectionValue();
        	}
    	
    	}

    public int getEnchantability()
    {
    	return this.enchantability;
    }

    public SoundEvent getEquipSound() // Sound I will do Later
    {
    	return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    public Ingredient getRepairIngredient()
    {
    	return this.repair;
    }

    public String getName()
    {
    	return this.name;
    }

    public float getToughness()
    {
    	return this.toughness;	
    }

    public float getKnockbackResistance()
    {
    	return this.knockback_resistance;
    }

    
    
}
