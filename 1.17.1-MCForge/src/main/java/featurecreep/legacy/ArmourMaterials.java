package featurecreep.legacy;

import java.util.function.Supplier;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ArmourMaterials implements ArmorMaterial {

    //Name, Durability multiplier, Damage Reduction multiplier, Damage Reduction, Enchantability, Sound Events, Toughness, Knockback Resistance, Repair Material
	   EMERALD(FeatureCreepMC.MODID + ":emerald",
			   25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(Items.EMERALD);
	    }),
	   AMETHYST(FeatureCreepMC.MODID + ":amethyst",
			   25, new int[]  {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(featurecreep.content.FCItems.AMETHYST);
	    }),
	   RUBY(FeatureCreepMC.MODID + ":ruby",
			   50, new int[] {15, 25, 25, 15}, 60, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.RUBY.get());
	    }),
	   TIGERS_EYE(FeatureCreepMC.MODID + ":tigers_eye",
			   35, new int[] {15, 20, 20, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.TIGERS_EYE.get());
	    }),
	   ULTIMATE(FeatureCreepMC.MODID + ":ultimate",
			   35, new int[] {50, 75, 75, 50}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.TITANIUM.get(), FCItems.URANIUM.get());
	    }),
	   EXPERIENCE(FeatureCreepMC.MODID + ":experience"
			   , 25, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(Items.EMERALD);
	    }),
	   ROYAL_GUARDIAN(FeatureCreepMC.MODID + ":royal_guardian",
			   35, new int[] {100, 150, 150, 100}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.URANIUM.get());
	    }),
	   QUEEN_SCALE(FeatureCreepMC.MODID + ":queen_scale",
			   20, new int[] {150, 200, 200, 150}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.QueenScale.get());
	    }),
	   MOBZILLA_SCALE(FeatureCreepMC.MODID + ":mobzilla_scale",
			   20, new int[] {75, 100, 100, 75}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.MobzillaScale.get());
	    }),
	   LAPIS_BLOCK(FeatureCreepMC.MODID + ":lapis_block",
			   25, new int[] {5, 10, 10, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(Items.LAPIS_BLOCK);
	    }),
	   LAVA_EEL(FeatureCreepMC.MODID + ":lava_eel",
			   35, new int[] {20, 25, 25, 20}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.LAVA_EEL.get());
	    }),
	   PEACOCK_FEATHER(FeatureCreepMC.MODID + ":peacock_feather",
			   35, new int[] {15, 25, 25, 15}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.PEACKOCK_FEATHER.get());
	    }),
	   PINK_TOURMALINE(FeatureCreepMC.MODID + ":pink_tourmaline", 
			   35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.PINK_TOURMALINE.get());
	    }),
	   COPPER(FeatureCreepMC.MODID + ":copper", 
			   15, new int[] {2, 5, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.COPPER_INGOT.get());
	    }),
	   SILVER(FeatureCreepMC.MODID + ":silver", 
			   20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.SILVER_INGOT.get());
	    }),
	   ALUMINIUM(FeatureCreepMC.MODID + ":aluminium", 
			   18, new int[] {3, 13, 13, 8}, 3, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.ALUMINIUM.get());
	    }),
	   PLATINUM(FeatureCreepMC.MODID + ":platinum",
			   20, new int[] {4, 15, 15, 10}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.PLATINUM_INGOT.get());
	    }),
	   MOTH_SCALE(FeatureCreepMC.MODID + ":moth_scale",
			   18, new int[] {20, 30, 30, 20}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.MothScale.get());
	    }),
	   SAPPHIRE(FeatureCreepMC.MODID + ":sapphire",
			   25, new int[] {10, 20, 20, 10}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.SAPPHIRE.get());
	    }),
	   CZ_SLOW(FeatureCreepMC.MODID + ":cz_slow",
			   25, new int[] {2, 3, 3, 2}, 50, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(Items.DIRT);
	    }),
	   TIN(FeatureCreepMC.MODID + ":tin",
			   35, new int[] {15, 25, 25, 15}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2f, 0f, () -> {
	        return Ingredient.ofItems(FCItems.TIN_INGOT.get());
	    });

    private final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    private final String name;
    private final int durability;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundOnEquip;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairMaterial;

    ArmourMaterials(String nameIn, int durabilityIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundOnEquip, float toughnessIn,
                   float knockbackResistanceIn, Supplier<Ingredient> repairMaterialIn) {

        this.name = nameIn;
        this.durability = durabilityIn;
        this.damageReductionAmountArray = damageReductionAmountArrayIn;
        this.enchantability = enchantabilityIn;
        this.soundOnEquip = soundOnEquip;
        this.toughness = toughnessIn;
        this.knockbackResistance = knockbackResistanceIn;
        this.repairMaterial = repairMaterialIn;

    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {

        return this.name;

    }

    @Override
    public int getDurability(EquipmentSlot slotIn) {

        return MAX_DAMAGE_ARRAY[slotIn.getEntitySlotId()] * this.durability;

    }

    @Override
    public int getProtectionAmount(EquipmentSlot slotIn) {

        return this.damageReductionAmountArray[slotIn.getEntitySlotId()];

    }

    @Override
    public int getEnchantability() {

        return this.enchantability;

    }

    @Override
    public SoundEvent getEquipSound() {
        return this.soundOnEquip;
    }

    @Override
    public float getToughness() {

        return this.toughness;

    }

    @Override
    public float getKnockbackResistance() {

        return this.knockbackResistance;

    }

    @Override
    public Ingredient getRepairIngredient() {

        return this.repairMaterial.get();

    }

}
