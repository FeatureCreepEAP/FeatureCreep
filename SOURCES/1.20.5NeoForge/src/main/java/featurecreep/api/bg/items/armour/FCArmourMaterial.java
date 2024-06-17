package featurecreep.api.bg.items.armour;

import java.util.EnumMap;
import java.util.List;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;
import featurecreep.api.soundeffects.AbstractSoundEffect;
import game.Armour;
import game.ArmourMaterial;
import game.BuiltInRegistries;
import game.DivisionDesigner;
import game.RegistryEntry;
import game.RegistryInterface;
import game.ResourceLocation;
import game.SoundPoolComponent;
import game.ToolRepairIngredient;

public class FCArmourMaterial {
	public int durability;
	public ArmourProtectionValuesArray protection;
	public int enchantability;
	public ToolRepairIngredient repair;
	public int toughness;
	public String name;
	public int knockback_resistance;
	public AbstractSoundEffect sound;

	public FCArmourMaterial(int durability, ArmourProtectionValuesArray protection, int enchantability,
			ToolRepairIngredient repair, String name, int toughness, int knockback_resistance,
			AbstractSoundEffect sound) {
		this.durability = durability;
		this.protection = protection;
		this.enchantability = enchantability;
		this.repair = repair;
		this.name = name;
		this.toughness = toughness;
		this.knockback_resistance = knockback_resistance;
		this.sound = sound;
	}

	public FCArmourMaterial(int durability, ArmourProtectionValuesArray protection, int enchantability,
			FCBlockAPI repair, String name, int toughness, int knockback_resistance, AbstractSoundEffect sound) {
		this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair.get()), name, toughness,
				knockback_resistance, sound);
	}

	public FCArmourMaterial(int durability, ArmourProtectionValuesArray protection, int enchantability,
			FCItemAPI repair, String name, int toughness, int knockback_resistance, AbstractSoundEffect sound) {
		this(durability, protection, enchantability, FCIngredient.ingredientFromItem(repair.get()), name, toughness,
				knockback_resistance, sound);
	}

	public int getFCDurability() {
		return this.durability;
	}

	public ArmourProtectionValuesArray getProtectionValueArray() {
		return this.protection;
	}

	public int getFCEnchantability() {
		return this.enchantability;
	}

	public int getFCToughness() {
		return this.toughness;
	}

	public int getFCKnockBackResistance() {
		return this.knockback_resistance;
	}

	public String getFCName() {
		return this.name;
	}

	public int getFCProtection(FCArmourSlot slot) {
		if (slot.equals(FCArmourSlots.HELMET)) {
			return this.protection.getHelmetProtectionValue();
		}
		if (slot.equals(FCArmourSlots.TUBIC)) {
			return this.protection.getChestplateProtectionValue();
		}
		if (slot.equals(FCArmourSlots.LEGGINGS)) {
			return this.protection.getLeggingsProtectionValue();
		}
		if (slot.equals(FCArmourSlots.BOOTS)) {
			return this.protection.getLeggingsProtectionValue();
		}
		return this.protection.getHelmetProtectionValue();
	}

	public int getFCTextureNumber(FCArmourSlot slot) {
		if (slot.equals(FCArmourSlots.LEGGINGS)) {
			return 2;
		}
		return 1;
	}

	public int getDurability(DivisionDesigner var1) {
		return this.durability;
	}

	public int getProtectionAmount(DivisionDesigner var1) {
		if (var1.equals(DivisionDesigner.HEAD)) {
			return this.protection.getHelmetProtectionValue();
		}
		if (var1.equals(DivisionDesigner.CHEST)) {
			return this.protection.getChestplateProtectionValue();
		}
		if (var1.equals(DivisionDesigner.LEGS)) {
			return this.protection.getLeggingsProtectionValue();
		}
		if (var1.equals(DivisionDesigner.FEET)) {
			return this.protection.getBootsProtectionValue();
		}
		return this.protection.getHelmetProtectionValue();
	}


	
	public RegistryEntry<ArmourMaterial> get() {
		RegistryEntry<SoundPoolComponent> vainilla_sound = this.sound.getEntry();
        List<ArmourMaterial.ArmourLayer> $$7 = List.of(new ArmourMaterial.ArmourLayer(new ResourceLocation(name)));
        EnumMap<Armour.ArmourPeice, Integer> $$8 = new EnumMap<Armour.ArmourPeice, Integer>(Armour.ArmourPeice.class);
        for (Armour.ArmourPeice $$9 : Armour.ArmourPeice.values()) {
            $$8.put($$9, this.getProtectionAmount($$9.getDivisionDesigner()));
        }
        ArmourMaterial mat = new ArmourMaterial($$8, getFCEnchantability(), vainilla_sound, () -> this.repair, $$7, this.getFCToughness(), this.getFCKnockBackResistance());
        return RegistryInterface.registerReference(BuiltInRegistries.ARMOUR_MATERIAL, new ResourceLocation(name), mat);
        
        
	}
	
	
	
	
}
