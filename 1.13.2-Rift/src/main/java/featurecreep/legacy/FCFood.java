package featurecreep.legacy;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class FCFood extends FoodItem{


	public FCFood(int i, float f, boolean bl, Settings arg) {
		super(i, f, bl, arg);
		// TODO Auto-generated constructor stub
	}











	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, PlayerEntity par3EntityPlayer)
	{
		
		if(this == FeatureCreepMC.RAW_BACON) {
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

		}
		
		if(this == FeatureCreepMC.COOKED_BACON) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 2));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1000, 4));

			}
		
		if(this == FeatureCreepMC.BUTTER_CANDY) {
			       par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 0));
			       par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2000, 0));
			}
		if(this == FeatureCreepMC.CRYSTAL_APPLE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000, 0));
			    par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 0));
			}
		if(this == FeatureCreepMC.LOVE_FOOD) {
			 par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6000, 3));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 2));
			        par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2));
			        par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1));
			        par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5000, 0));
			       par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 5000, 0));
			      } 
		
		if(this == FeatureCreepMC.POPCORN) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4));

			}
	
	
		if(this == FeatureCreepMC.BUTTER_FOOD) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4));

			}
		if(this == FeatureCreepMC.CORN_DOG) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));

			}
		if(this == FeatureCreepMC.COOKED_CORN_DOG) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 4));

			}
		
		if(this == FeatureCreepMC.RAW_CRAB_MEAT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));

			}
		
		if(this == FeatureCreepMC.COOKED_CRAB_MEAT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 3));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 4));

			}
		
		
		if(this == FeatureCreepMC.CHEESE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 4));

			}
		if(this == FeatureCreepMC.SALAD) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 2));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 3));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 5));

			}
		if(this == FeatureCreepMC.BLT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 2));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5000, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1000, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 0));

			
			
			
			
			}
		if(this == FeatureCreepMC.CRAB_PATTY) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 1));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 2));
	      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 2));
	par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));
			}
		if(this == FeatureCreepMC.MAGIC_APPLE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5000, 1));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 1));
	      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 1));
	par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 3));

			}
		if(this == FeatureCreepMC.PEACH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			 

			}
		if(this == FeatureCreepMC.RAW_PEACOCK) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 3));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 5));
			}
	
		if(this == FeatureCreepMC.COOCKED_PEACOCK) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 4));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 6));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 6));

			}
	
		if(this == FeatureCreepMC.BLUE_FISH) {
		
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

			}
		
		if(this == FeatureCreepMC.BUTTERED_POPCORN) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5));

			}
		if(this == FeatureCreepMC.SALTED_POPCORN) {
			
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 1));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 5));
			
			
			}
		if(this == FeatureCreepMC.BUTTERED_AND_SALTED_POPCORN) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 10));

			}
		if(this == FeatureCreepMC.CHERRY) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));

			}
		if(this == FeatureCreepMC.CORN) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));

			}
		
		if(this == FeatureCreepMC.POPCORN_BAG) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 2));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 4000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 10));
			}
		if(this == FeatureCreepMC.QUINOA) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));


			}
		
		
		if(this == FeatureCreepMC.RADISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));

			}
		
		if(this == FeatureCreepMC.RICE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));


			}
		if(this == FeatureCreepMC.ROCK_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));

			}
		if(this == FeatureCreepMC.FIRE_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4));


			}
		
		if(this == FeatureCreepMC.SPARK_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4));

			}
		if(this == FeatureCreepMC.GREEN_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));


			}
		if(this == FeatureCreepMC.GREY_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

			}
		if(this == FeatureCreepMC.PINK_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

			}
		if(this == FeatureCreepMC.SUN_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600, 4));
		//Fly Plans	
		
		}
		
		
		if(this == FeatureCreepMC.STRAWBERRY) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));


			}
		
		if(this == FeatureCreepMC.LETTUCE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1));


			}
		if(this == FeatureCreepMC.TOMATO) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 1));


			}
		if(this == FeatureCreepMC.WOOD_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));

			}
		
		if(this == FeatureCreepMC.RAW_MOOSE_MEAT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 4));

			}
		if(this == FeatureCreepMC.COOKED_MOOSE_MEAT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000, 3));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 3000, 3));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 4));
			}
		if(this == FeatureCreepMC.DEAD_BUG) {
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 10));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));

			}
		
	
		
		if(this == FeatureCreepMC.MAGIC_FROG_OF_STRENGTH) {
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));

			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_WEAKNESS) {
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 2000, 0));

			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_SPEED) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2000, 0));
		

			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_SLOWNESS) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 2000, 0));


			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_REGENERATION) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
		

			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_POISON) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 2000, 0));
	

			}
//		if(this == FeatureCreepMC.MAGIC_FROG_OF_) {
//			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
//
//			}
	
		if(this == FeatureCreepMC.MAGIC_FROG_OF_MORPH) {
//			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 0));
//			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
//plans later but for now just strength
			}
		if(this == FeatureCreepMC.MAGIC_FROG_OF_CONFUSION) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 2000, 0));
	

			}
	
		
		
		
		if(this == FeatureCreepMC.CANDY_CANE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		
		if(this == FeatureCreepMC.GOLDEN_BREAD) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_CHICKEN) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_TROPICAL_FISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		
		if(this == FeatureCreepMC.GOLDEN_COD) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		
		if(this == FeatureCreepMC.GOLDEN_PORKCHOP) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		
		if(this == FeatureCreepMC.WATERMELON_SLICE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}

		if(this == FeatureCreepMC.GOLDEN_MUSHROOM_STEW) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4));

			}
		if(this == FeatureCreepMC.GOLDEN_STEAK) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

			}
		if(this == FeatureCreepMC.GOLDEN_COOKIE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_POTATO) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_PUMPKIN_PIE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_ROTTON_FLESH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_CARROT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));

			}
		if(this == FeatureCreepMC.GOLDEN_PUFFERFISH) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_SALMON) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0));

			}
		if(this == FeatureCreepMC.GOLDEN_CANDYCANE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0));

			}
		if(this == FeatureCreepMC.ULTIMATE_APPLE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 600, 0));

			}
		if(this == FeatureCreepMC.ENCHANTED_GOLDEN_CARROT) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 2));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0));

			}
	
		if(this == FeatureCreepMC.ENCHANTED_GOLDEN_STEAK) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 0));

			}
		if(this == FeatureCreepMC.ENCHANTED_GOLDEN_COD) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 600, 0));

			}
		
		if(this == FeatureCreepMC.ENCHANTED_GOLDEN_COOKIE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0));

			}
		if(this == FeatureCreepMC.ENCHANTED_GOLDEN_CANDYCANE) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
			      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 0));
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0));

			}
		
		if(this == FeatureCreepMC.RADDISH_STEW) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 0));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 0));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 4));

			}
		if(this == FeatureCreepMC.DRINKABLE_GASOLINE_PETROL) {
			par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000, 5));
		      par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000, 2));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 4));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 2));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600, 2));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 2));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2000, 10));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 2000, 10));
		par3EntityPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2000, 10));

		
	
		
			}
		
	}
	
	
	
	
	
	
	
		
				

	
	
	
	}	
	
		
		
		
//	public void registerIcons(IconRegister iconRegister)
//	{
//	itemIcon = iconRegister.registerIcon("featurecreep:raw_bacon");	
//	}
	
	
	
	
	
	