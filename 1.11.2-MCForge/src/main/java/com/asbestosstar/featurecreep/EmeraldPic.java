package com.asbestosstar.featurecreep;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;

public class EmeraldPic extends ItemPickaxe implements IHadModels {
	public EmeraldPic(String name, ToolMaterial material) {
		super(material);
		// TODO Auto-generated constructor stub
		
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(CreativeTabs.TOOLS);
			FeatureCreepMC.ITEMS.add(this);

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
