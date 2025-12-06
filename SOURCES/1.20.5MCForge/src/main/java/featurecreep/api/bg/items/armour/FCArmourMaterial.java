package featurecreep.api.bg.items.armour;

import java.util.EnumMap;
import java.util.List;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;
import featurecreep.api.soundeffects.AbstractSoundEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial.Layer;
import net.minecraft.world.item.crafting.Ingredient;

public class FCArmourMaterial {
	public int durability;
	public ArmourProtectionValuesArray protection;
	public int enchantability;
	public Ingredient repair;
	public int toughness;
	public String name;
	public int knockback_resistance;
	public AbstractSoundEffect sound;

	public FCArmourMaterial(int durability, ArmourProtectionValuesArray protection, int enchantability,
			Ingredient repair, String name, int toughness, int knockback_resistance,
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

	public int getDurability(EquipmentSlot var1) {
		return this.durability;
	}

	public int getProtectionAmount(EquipmentSlot var1) {
		if (var1.equals(EquipmentSlot.HEAD)) {
			return this.protection.getHelmetProtectionValue();
		}
		if (var1.equals(EquipmentSlot.CHEST)) {
			return this.protection.getChestplateProtectionValue();
		}
		if (var1.equals(EquipmentSlot.LEGS)) {
			return this.protection.getLeggingsProtectionValue();
		}
		if (var1.equals(EquipmentSlot.FEET)) {
			return this.protection.getBootsProtectionValue();
		}
		return this.protection.getHelmetProtectionValue();
	}


	
	public Holder<ArmorMaterial> get() {
		Holder<SoundEvent> vainilla_sound = this.sound.getEntry();
        List<Layer> $$7 = List.of(new Layer(new ResourceLocation(name)));
        EnumMap<Type, Integer> $$8 = new EnumMap<Type, Integer>(Type.class);
        for (Type $$9 : Type.values()) {
            $$8.put($$9, this.getProtectionAmount($$9.getSlot()));
        }
        ArmorMaterial mat = new ArmorMaterial($$8, getFCEnchantability(), vainilla_sound, () -> this.repair, $$7, (float)this.getFCToughness(), (float)this.getFCKnockBackResistance());
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(name), mat);
        
        
	}
	
	
	
	
}