package featurecreep.api.bg.items.armour;

import java.util.HashMap;
import java.util.Map;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.tools.FCIngredient;
import featurecreep.api.soundeffects.AbstractSoundEffect;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

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


	
	public ArmorMaterial get() {
	    // 1. Convert protection values to defense map
	    Map<ArmorType, Integer> defense = new HashMap<>();
	    defense.put(ArmorType.HELMET, this.protection.getHelmetProtectionValue());
	    defense.put(ArmorType.CHESTPLATE, this.protection.getChestplateProtectionValue());
	    defense.put(ArmorType.LEGGINGS, this.protection.getLeggingsProtectionValue());
	    defense.put(ArmorType.BOOTS, this.protection.getBootsProtectionValue());

	    // 2. Get equip sound
	    Holder<SoundEvent> equipSound = this.sound.getEntry();

	    // 3. Define repair tag 
	    TagKey<Item> repairTag = ItemTags.PLANKS; // Use a default tag or your custom tag

	    // 4. Define asset ID (use a default or your specific asset)
	    ResourceKey<EquipmentAsset> assetId = EquipmentAssets.ARMADILLO_SCUTE;

	    // 5. Create the ArmorMaterial
	    ArmorMaterial material = new ArmorMaterial(
	        this.durability,           
	        defense,                   
	        this.enchantability,       
	        equipSound,                
	        (float) this.toughness,    
	        (float) this.knockback_resistance, 
	        repairTag,                 
	        assetId                    
	    );

	    // 6. Correctly create and return a Holder
	    return material;
	}

	
	
	
}