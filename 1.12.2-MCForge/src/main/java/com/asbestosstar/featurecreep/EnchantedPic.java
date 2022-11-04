package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnchantedPic extends ItemPickaxe implements IHadModels {
	public EnchantedPic(String name, ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
		
			setUnlocalizedName(name);
			setRegistryName(name);
	//		setCreativeTab(CreativeTabs.TOOLS);
			FeatureCreepMC.ITEMS.add(this);

	}
				
		@Override
		public void registerModels() {
			// TODO Auto-generated method stub
		 FeatureCreepMC.proxy.registerItemRenderer(this, 0, "inventory");
		}



		public void onCreated(World worldIn, EntityPlayer player, ItemStack stack) {

			
			
			
			stack.addEnchantment(Enchantments.UNBREAKING, 5);
stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
stack.addEnchantment(Enchantments.FIRE_ASPECT, 5);
stack.addEnchantment(Enchantments.FLAME, 5);
stack.addEnchantment(Enchantments.KNOCKBACK, 5);
stack.addEnchantment(Enchantments.MENDING, 5);
stack.addEnchantment(Enchantments.EFFICIENCY, 5);
stack.addEnchantment(Enchantments.FORTUNE, 5);
stack.addEnchantment(Enchantments.INFINITY, 5);
stack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, 5);
stack.addEnchantment(Enchantments.LURE, 5);
stack.addEnchantment(Enchantments.POWER, 5);
stack.addEnchantment(Enchantments.RESPIRATION, 5);
stack.addEnchantment(Enchantments.SHARPNESS, 5);
stack.addEnchantment(Enchantments.SMITE, 5);
stack.addEnchantment(Enchantments.THORNS, 5);
stack.addEnchantment(Enchantments.SILK_TOUCH, 5);
stack.addEnchantment(Enchantments.LOOTING, 5);	
		
		
		}

   public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
     int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
     if (lvl <= 0) {
    	 stack.addEnchantment(Enchantments.UNBREAKING, 5);
 		stack.addEnchantment(Enchantments.BANE_OF_ARTHROPODS, 5);
 		stack.addEnchantment(Enchantments.FIRE_ASPECT, 5);
 		stack.addEnchantment(Enchantments.FLAME, 5);
 		stack.addEnchantment(Enchantments.KNOCKBACK, 5);
 		stack.addEnchantment(Enchantments.MENDING, 5);
 		stack.addEnchantment(Enchantments.EFFICIENCY, 5);
 		stack.addEnchantment(Enchantments.FORTUNE, 5);
 		stack.addEnchantment(Enchantments.INFINITY, 5);
 		stack.addEnchantment(Enchantments.LUCK_OF_THE_SEA, 5);
 		stack.addEnchantment(Enchantments.LURE, 5);
 		stack.addEnchantment(Enchantments.POWER, 5);
 		stack.addEnchantment(Enchantments.RESPIRATION, 5);
 		stack.addEnchantment(Enchantments.SHARPNESS, 5);
 		stack.addEnchantment(Enchantments.SMITE, 5);
 		stack.addEnchantment(Enchantments.THORNS, 5);
 		stack.addEnchantment(Enchantments.SILK_TOUCH, 5);		     
 		stack.addEnchantment(Enchantments.LOOTING, 5);		     
 		
   }

   

   }
   
    public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
	     onUsingTick(stack, (EntityPlayer)null, 0);
    }
   




}
