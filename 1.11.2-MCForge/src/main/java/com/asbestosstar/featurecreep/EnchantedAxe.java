package com.asbestosstar.featurecreep;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedAxe extends EmeraldAxe {

	protected EnchantedAxe(String name, ToolMaterial material) {
		super(name, material);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
	 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
	}


	public void onCreated(World worldIn, EntityPlayer player, ItemStack stack) {

		if(this == FeatureCreepMC.EMERALD_PICKAXE) {

 		stack.addEnchantment(Enchantments.SILK_TOUCH, 5);		     

		}

	
	
	}

public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
 int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
	if(this == FeatureCreepMC.EMERALD_PICKAXE) {

 if (lvl <= 0) {

	 
		stack.addEnchantment(Enchantments.SILK_TOUCH, 5);		     
		
}


	}
}

public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
     onUsingTick(stack, (EntityPlayer)null, 0);
}



	
	
	
}
