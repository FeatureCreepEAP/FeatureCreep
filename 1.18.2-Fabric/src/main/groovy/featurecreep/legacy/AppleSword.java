package featurecreep.legacy;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.potion.Potion;

public class AppleSword extends SwordItem {

	public AppleSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		// TODO Auto-generated constructor stub
	}

	 @Override
	 public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity entity) {
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 2));
			target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 10, 2));
  		    return true;
		  }
	
	
}
