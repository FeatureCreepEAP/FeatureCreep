package featurecreep.legacy;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

public class CZSlowArmour extends ArmorItem {
	
	
	public CZSlowArmour(ArmorMaterial materialIn, EquipmentSlot slot, Item.Settings builderIn) {
		super(materialIn, slot, builderIn);
		
	}
	
	public void onArmorTick(World worldIn, PlayerEntity player, ItemStack stack) {
		

		player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1, 2));
		}
		
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
}