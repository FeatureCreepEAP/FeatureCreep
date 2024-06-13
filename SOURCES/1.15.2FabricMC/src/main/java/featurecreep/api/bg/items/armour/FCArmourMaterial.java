package featurecreep.api.bg.items.armour;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;
import featurecreep.api.soundeffects.AbstractSoundEffect;
import game.ArmourMaterial;
import game.DivisionDesigner;
import game.SoundPoolComponent;
import game.ToolRepairIngredient;

public class FCArmourMaterial implements ArmourMaterial{

	   public int durability;
	    public ArmourProtectionValuesArray protection;
	    public int enchantability;
	    public ToolRepairIngredient repair;
	    public int toughness;
	    public String name;
	    public int knockback_resistance;
	    public AbstractSoundEffect sound;
	
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, ToolRepairIngredient repair, String name, int toughness, int knockback_resistance, AbstractSoundEffect sound)
{
this.durability = durability;
this.protection = protection;
this.enchantability = enchantability;
this.repair = repair;
this.name = name;
this.toughness = toughness;
this.knockback_resistance = knockback_resistance;
this.sound = sound;	
	
}
	
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCBlockAPI repair, String name, int toughness, int knockback_resistance, AbstractSoundEffect sound)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair.get()), name, toughness, knockback_resistance, sound);
}
public FCArmourMaterial (int durability, ArmourProtectionValuesArray protection, int enchantability, FCItemAPI repair, String name, int toughness, int knockback_resistance, AbstractSoundEffect sound)
{
this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair.get()), name, toughness, knockback_resistance, sound);
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


@Override
    public int getDurability(DivisionDesigner var1)
    {
    	return this.durability;	
    }
    @Override
    public int getProtectionAmount(DivisionDesigner var1)
    {
    	if (var1.equals(DivisionDesigner.HEAD)){
    	return this.protection.getHelmetProtectionValue();
    	}
    	else if (var1.equals(DivisionDesigner.CHEST)){
        	return this.protection.getChestplateProtectionValue();
        	}
    	else if (var1.equals(DivisionDesigner.LEGS)){
        	return this.protection.getLeggingsProtectionValue();
        	}
    	else if (var1.equals(DivisionDesigner.FEET)){
        	return this.protection.getBootsProtectionValue();
        	}
    	else {
        	return this.protection.getHelmetProtectionValue();
        	}
    	
    	}
    @Override
    public int getEnchantability()
    {
    	return this.enchantability;
    }
    @Override
    public SoundPoolComponent getSoundEvent() // Sound I will do Later
    {
    	return sound.get();
    }
    @Override
    public ToolRepairIngredient getRepairIngredient()
    {
    	return this.repair;
    }
    @Override
    public String getName()
    {
    	return this.name;
    }
    @Override
    public float getToughness()
    {
    	return this.toughness;	
    }

    
}
