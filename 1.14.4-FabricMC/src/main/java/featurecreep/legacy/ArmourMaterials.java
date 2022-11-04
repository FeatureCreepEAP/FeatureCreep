package featurecreep.legacy;

import java.util.function.Supplier;

import featurecreep.content.FCItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ArmourMaterials implements ArmorMaterial {
   EMERALD("emerald", 25, new int[]{15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
   }),
   AMETHYST("amethyst", 25, new int[]{10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FCItems.AMETHYST});
   }),
   RUBY("ruby", 50, new int[]{15, 25, 25, 15}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.RUBY});
   }),
   TIGERS_EYE("tigers_eye", 35, new int[]{15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIGERS_EYE});
   }),
   ULTIMATE("ultimate", 35, new int[]{50, 75, 75, 50}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TITANIUM, (ItemConvertible)FeatureCreepMC.URANIUM});
   }),
   EXPERIENCE("experience", 25, new int[]{15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.EMERALD});
   }),
   ROYAL_GUARDIAN("royal_guardian", 35, new int[]{100, 150, 150, 100}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.URANIUM});
   }),
   QUEEN_SCALE("queen_scale", 20, new int[]{150, 200, 200, 150}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.QueenScale});
   }),
   MOBZILLA_SCALE("mobzilla_scale", 20, new int[]{75, 100, 100, 75}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.MobzillaScale});
   }),
   LAPIS_BLOCK("lapis_block", 25, new int[]{5, 10, 10, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.LAPIS_BLOCK});
   }),
   LAVA_EEL("lava_eel", 35, new int[]{20, 25, 25, 20}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.LAVA_EEL});
   }),
   PEACOCK_FEATHER("peacock_feather", 35, new int[]{15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PEACKOCK_FEATHER});
   }),
   PINK_TOURMALINE("pink_tourmaline", 35, new int[]{15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PINK_TOURMALINE});
   }),
   COPPER("copper", 15, new int[]{2, 5, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.COPPER_INGOT});
   }),
   SILVER("silver", 20, new int[]{4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SILVER_INGOT});
   }),
   ALUMINIUM("aluminium", 18, new int[]{3, 13, 13, 8}, 3, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.ALUMINIUM});
   }),
   PLATINUM("platinum", 20, new int[]{4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.PLATINUM_INGOT});
   }),
   MOTH_SCALE("moth_scale", 18, new int[]{20, 30, 30, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.MothScale});
   }),
   SAPPHIRE("sapphire", 25, new int[]{10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.SAPPHIRE});
   }),
   CZ_SLOW("cz_slow", 25, new int[]{2, 3, 3, 2}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{Items.DIRT});
   }),
   TIN("tin", 35, new int[]{15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
      return Ingredient.ofItems(new ItemConvertible[]{(ItemConvertible)FeatureCreepMC.TIN_INGOT});
   });

	
	
	 private final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	   private final String name;
	   private final int durability;
	   private final int[] damageReductionAmountArray;
	   private final int enchantability;
	   private final SoundEvent soundOnEquip;
	   private final float toughness;
	   private final float knockbackResistance;
	   private final Supplier<Ingredient> repairMaterial;

	   private ArmourMaterials(String nameIn, int durabilityIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundOnEquip, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairMaterialIn) {
	      this.name = nameIn;
	      this.durability = durabilityIn;
	      this.damageReductionAmountArray = damageReductionAmountArrayIn;
	      this.enchantability = enchantabilityIn;
	      this.soundOnEquip = soundOnEquip;
	      this.toughness = toughnessIn;
	      this.knockbackResistance = knockbackResistanceIn;
	      this.repairMaterial = repairMaterialIn;
	   }
	
	
	
	
	
	
@Override
public int getDurability(EquipmentSlot var1) {
	// TODO Auto-generated method stub
    return this.MAX_DAMAGE_ARRAY[var1.getEntitySlotId()] * this.durability;
}

@Override
public int getProtectionAmount(EquipmentSlot var1) {
	// TODO Auto-generated method stub
    return this.damageReductionAmountArray[var1.getEntitySlotId()];
    }

@Override
public int getEnchantability() {
	// TODO Auto-generated method stub
    return this.enchantability;
    }

@Override
public SoundEvent getEquipSound() {
	// TODO Auto-generated method stub
    return this.soundOnEquip;
}

@Override
public Ingredient getRepairIngredient() {
	// TODO Auto-generated method stub
	   return (Ingredient)this.repairMaterial.get();
	   
}

@Override
public String getName() {
	// TODO Auto-generated method stub
    return this.name;
    
}

@Override
public float getToughness() {
	// TODO Auto-generated method stub
    return this.toughness;
    
}




}

