package featurecreep.legacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class CZSlowArmour extends GenericArmour {
	
	
	

	public CZSlowArmour(CreativeTabs tab, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(tab, materialIn, renderIndexIn, equipmentSlotIn);
		// TODO Auto-generated constructor stub
	}


	public void onArmorTick(World worldIn, EntityPlayer player, ItemStack stack) {
		

		player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1, 2));
		
	}
		
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
}