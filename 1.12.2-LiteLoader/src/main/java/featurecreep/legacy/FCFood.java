package featurecreep.legacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FCFood extends ItemFood{


	public FCFood(int i, float f, boolean bl, CreativeTabs arg) {
		super(i, f, bl);
		// TODO Auto-generated constructor stub
	this.setCreativeTab(arg);
	}









	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		
		if(this == LiteModFeatureCreepMC.RAW_BACON) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

		}
		
		if(this == LiteModFeatureCreepMC.COOKED_BACON) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 2));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1000, 4));

			}
		
		if(this == LiteModFeatureCreepMC.BUTTER_CANDY) {
			       par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2000, 0));
			       par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 2000, 0));
			}
		if(this == LiteModFeatureCreepMC.CRYSTAL_APPLE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0));
			    par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 0));
			}
		if(this == LiteModFeatureCreepMC.LOVE_FOOD) {
			 par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 3));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 6000, 2));
			        par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 2));
			        par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 1));
			        par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5000, 0));
			       par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 5000, 0));
			      } 
		
		if(this == LiteModFeatureCreepMC.POPCORN) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 4));

			}
	
	
		if(this == LiteModFeatureCreepMC.BUTTER_FOOD) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.CORN_DOG) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.COOKED_CORN_DOG) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1000, 4));

			}
		
		if(this == LiteModFeatureCreepMC.RAW_CRAB_MEAT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 2));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));

			}
		
		if(this == LiteModFeatureCreepMC.COOKED_CRAB_MEAT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 3));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1000, 4));

			}
		
		
		if(this == LiteModFeatureCreepMC.CHEESE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.SALAD) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5000, 2));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2000, 3));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.LUCK, 2000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 5));

			}
		if(this == LiteModFeatureCreepMC.BLT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5000, 2));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 5000, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1000, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20, 0));

			
			
			
			
			}
		if(this == LiteModFeatureCreepMC.CRAB_PATTY) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5000, 1));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2000, 2));
	      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.LUCK, 2000, 2));
	par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));
			}
		if(this == LiteModFeatureCreepMC.MAGIC_APPLE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5000, 1));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2000, 1));
	      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.LUCK, 2000, 1));
	par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 3));

			}
		if(this == LiteModFeatureCreepMC.PEACH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			 

			}
		if(this == LiteModFeatureCreepMC.RAW_PEACOCK) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 3));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 5));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 5));
			}
	
		if(this == LiteModFeatureCreepMC.COOCKED_PEACOCK) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 4));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 6));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1000, 6));

			}
	
		if(this == LiteModFeatureCreepMC.BLUE_FISH) {
		
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

			}
		
		if(this == LiteModFeatureCreepMC.BUTTERED_POPCORN) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 5));

			}
		if(this == LiteModFeatureCreepMC.SALTED_POPCORN) {
			
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 5));
			
			
			}
		if(this == LiteModFeatureCreepMC.BUTTERED_AND_SALTED_POPCORN) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 2));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 10));

			}
		if(this == LiteModFeatureCreepMC.CHERRY) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));

			}
		if(this == LiteModFeatureCreepMC.CORN) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));

			}
		
		if(this == LiteModFeatureCreepMC.POPCORN_BAG) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 2));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 4000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 10));
			}
		if(this == LiteModFeatureCreepMC.QUINOA) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));


			}
		
		
		if(this == LiteModFeatureCreepMC.RADISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));

			}
		
		if(this == LiteModFeatureCreepMC.RICE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));


			}
		if(this == LiteModFeatureCreepMC.ROCK_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.FIRE_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 600, 4));


			}
		
		if(this == LiteModFeatureCreepMC.SPARK_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.GREEN_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));


			}
		if(this == LiteModFeatureCreepMC.GREY_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.PINK_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.SUN_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 600, 4));
		//Fly Plans	
		
		}
		
		
		if(this == LiteModFeatureCreepMC.STRAWBERRY) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));


			}
		
		if(this == LiteModFeatureCreepMC.LETTUCE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));


			}
		if(this == LiteModFeatureCreepMC.TOMATO) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 1));


			}
		if(this == LiteModFeatureCreepMC.WOOD_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));

			}
		
		if(this == LiteModFeatureCreepMC.RAW_MOOSE_MEAT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 2));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 2));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.COOKED_MOOSE_MEAT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4000, 3));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3000, 3));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2000, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1000, 4));
			}
		if(this == LiteModFeatureCreepMC.DEAD_BUG) {
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 10));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 1));

			}
		
	
		
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_STRENGTH) {
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));

			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_WEAKNESS) {
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2000, 0));

			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_SPEED) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2000, 0));
		

			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_SLOWNESS) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2000, 0));


			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_REGENERATION) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
		

			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_POISON) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.POISON, 2000, 0));
	

			}
//		if(this == FeatureCreepMC.MAGIC_FROG_OF_) {
//			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
//
//			}
	
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_MORPH) {
//			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1000, 0));
//			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
//plans later but for now just strength
			}
		if(this == LiteModFeatureCreepMC.MAGIC_FROG_OF_CONFUSION) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2000, 0));
	

			}
	
		
		
		
		if(this == LiteModFeatureCreepMC.CANDY_CANE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.GOLDEN_BREAD) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_CHICKEN) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_TROPICAL_FISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.GOLDEN_COD) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.GOLDEN_PORKCHOP) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.WATERMELON_SLICE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}

		if(this == LiteModFeatureCreepMC.GOLDEN_MUSHROOM_STEW) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_STEAK) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_COOKIE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_POTATO) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_PUMPKIN_PIE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_ROTTON_FLESH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_CARROT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_PUFFERFISH) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.POISON, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_SALMON) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.GOLDEN_CANDYCANE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.ULTIMATE_APPLE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.ENCHANTED_GOLDEN_CARROT) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 2));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, 0));

			}
	
		if(this == LiteModFeatureCreepMC.ENCHANTED_GOLDEN_STEAK) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.ENCHANTED_GOLDEN_COD) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.ENCHANTED_GOLDEN_COOKIE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 0));

			}
		if(this == LiteModFeatureCreepMC.ENCHANTED_GOLDEN_CANDYCANE) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 0));
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 0));

			}
		
		if(this == LiteModFeatureCreepMC.RADDISH_STEW) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 0));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 4));

			}
		if(this == LiteModFeatureCreepMC.DRINKABLE_GASOLINE_PETROL) {
			par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2000, 5));
		      par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2000, 2));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 2));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 2));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.HASTE, 600, 2));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2000, 10));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.LUCK, 2000, 10));
		par3EntityPlayer.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2000, 10));

		
	
		
			}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
				

	
	
	
	}	
	
		
		
		
//	public void registerIcons(IconRegister iconRegister)
//	{
//	itemIcon = iconRegister.registerIcon("featurecreep:raw_bacon");	
//	}
	
	
	
	
	
	