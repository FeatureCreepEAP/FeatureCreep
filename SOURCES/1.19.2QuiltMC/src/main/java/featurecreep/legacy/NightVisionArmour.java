package featurecreep.legacy;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class NightVisionArmour extends ArmorItem {
	
	
	public NightVisionArmour(ArmorMaterial materialIn, EquipmentSlot slot, Item.Settings builderIn) {
		super(materialIn, slot, builderIn);
		
	}
	
	 public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
	    

		 user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1, 2));
		
	 }
		
	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}
	
}