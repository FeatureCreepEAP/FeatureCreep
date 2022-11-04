package featurecreep.legacy;

//public class RawBacon extends I{

//	  public  int value;
//	  public  float saturation;
//	  public boolean meat;
//	  public  boolean canEatWhenFull;
//	  public  boolean fastToEat;
//	  public  List<Pair<java.util.function.Supplier<EffectInstance>, Float>> effects;
//
//	  public RawBacon(Food.Builder builder) {
//			super(null);
//	 
//	  
//	  
	 // }
//	  
	   // public boolean hasEffect(ItemStack stack) {
//	        return true;
//	    }
//
//	   public int getHealing() {
//	      return this.value;
//	   }
//
//	   public float getSaturation() {
//	      return this.saturation;
//	   }
//
//	   public boolean isMeat() {
//	      return this.meat;
//	   }
//
//	   public boolean canEatWhenFull() {
//	      return this.canEatWhenFull;
//	   }
//
//	   public boolean isFastEating() {
//	      return this.fastToEat;
//	   }
//
//	   public List<Pair<EffectInstance, Float>> getEffects() {
//	      return this.effects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().get() : null, pair.getSecond())).collect(java.util.stream.Collectors.toList());
//	   }
//	
//	   
//	   
//	  
//
//		    
//		   
//	   
//	   
//	   
//	   
//	   
//	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, PlayerEntity par3EntityPlayer)
//	{
//		
//		if(this == FCItems.FOOD_RAW_BACON) {
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//this.value=0;
//		this.saturation=5;
//		this.meat=true;
//		this.canEatWhenFull=true;
//		this.fastToEat=true;
//		
//		}
//		
//		if(this == FCItems.FOOD_COOKED_BACON) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3000, 2));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1000, 4));
//
//			this.value=0;
//			this.saturation=10;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			
//			
//			
//			}
//		
//		if(this == FCItems.FOOD_BUTTER_CANDY) {
//			       par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 2000, 0));
//			       par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 2000, 0));
//			       this.value=0;
//					this.saturation=3;
//					this.meat=true;
//					this.canEatWhenFull=true;
//					this.fastToEat=true;
//						
//		}
//		if(this == FCItems.FOOD_CRYSTAL_APPLE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3000, 0));
//			    par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 0));
//			
//			    this.value=0;
//				this.saturation=10;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//				
//		}
//		if(this == FCItems.FOOD_LOVE_FOOD) {
//			 par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 6000, 3));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 6000, 2));
//			        par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 2));
//			        par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 6000, 1));
//			        par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 5000, 0));
//			       par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 5000, 0));
//			       this.value=0;
//					this.saturation=3;
//					this.meat=true;
//					this.canEatWhenFull=true;
//					this.fastToEat=true;	     
//		} 
//		
//		if(this == FCItems.FOOD_POPCORN) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//	
//	
//		if(this == FCItems.FOOD_BUTTER_FOOD) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_CORN_DOG) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_COOKED_CORN_DOG) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 4000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2000, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 1000, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_RAW_CRAB_MEAT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 2));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_COOKED_CRAB_MEAT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 4000, 3));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2000, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 1000, 4));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		
//		
//		if(this == FCItems.FOOD_CHEESE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_SALAD) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5000, 2));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.GLOWING, 2000, 3));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.LUCK, 2000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 5));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_BLT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5000, 2));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 5000, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1000, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 0));
//
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			
//			
//			
//			}
//		if(this == FCItems.FOOD_CRAB_PATTY) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5000, 1));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.GLOWING, 2000, 2));
//	      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.LUCK, 2000, 2));
//	par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//			
//	   this.value=0;
//		this.saturation=3;
//		this.meat=true;
//		this.canEatWhenFull=true;
//		this.fastToEat=true;
//		}
//		if(this == FCItems.FOOD_MAGIC_APPLE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5000, 1));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.GLOWING, 2000, 1));
//	      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.LUCK, 2000, 1));
//	par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 3));
//	   this.value=0;
//		this.saturation=3;
//		this.meat=true;
//		this.canEatWhenFull=true;
//		this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_PEACH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_RAW_PEACOCK) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 3));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 5));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 5));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//		}
//	
//		if(this == FCItems.FOOD_COOCKED_PEACOCK) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 4000, 4));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2000, 6));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 1000, 6));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//	
//		if(this == FCItems.FOOD_BLUE_FISH) {
//		
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_BUTTERED_POPCORN) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 1));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 5));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_SALTED_POPCORN) {
//			
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 1));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 1));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 5));
//			
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_BUTTERED_AND_SALTED_POPCORN) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 2));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 10));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_CHERRY) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_CORN) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_POPCORN_BAG) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 4000, 2));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 4000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1200, 10));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;	
//		}
//		if(this == FCItems.FOOD_QUINOA) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		
//		
//		if(this == FCItems.FOOD_RADISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_RICE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_ROCK_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_FIRE_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		
//		if(this == FCItems.FOOD_SPARK_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GREEN_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_GREY_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_SUN_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 4));
//		//Fly Plans	
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//		}
//		
//		
//		if(this == FCItems.FOOD_STRAWBERRY) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		
//		if(this == FCItems.FOOD_LETTUCE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 1));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_TOMATO) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 1));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_WOOD_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_RAW_MOOSE_MEAT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 2));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 4));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_COOKED_MOOSE_MEAT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 4000, 3));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 3000, 3));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2000, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 1000, 4));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;	
//		}
//		if(this == FCItems.FOOD_DEAD_BUG) {
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 10));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 1));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//	
//		
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_STRENGTH) {
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			      this.value=0;
//					this.saturation=3;
//					this.meat=true;
//					this.canEatWhenFull=true;
//					this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_WEAKNESS) {
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 2000, 0));
//			      this.value=0;
//					this.saturation=3;
//					this.meat=true;
//					this.canEatWhenFull=true;
//					this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_SPEED) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_SLOWNESS) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_REGENERATION) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_POISON) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.POISON, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
////		if(this == FCItems.MAGIC_FROG_OF_) {
////			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
////			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
////			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
////
////			}
//	
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_MORPH) {
////			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1000, 0));
////			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
////plans later but for now just strength
//			
//		
//		      this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//		}
//		if(this == FCItems.FOOD_MAGIC_FROG_OF_CONFUSION) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.NAUSEA, 2000, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//
//			}
//	
//		
//		
//		
//		if(this == FCItems.FOOD_CANDY_CANE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_GOLDEN_BREAD) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_CHICKEN) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_TROPICAL_FISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_GOLDEN_COD) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_GOLDEN_PORKCHOP) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_WATERMELON_SLICE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//
//		if(this == FCItems.FOOD_GOLDEN_MUSHROOM_STEW) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_STEAK) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_COOKIE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_POTATO) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_PUMPKIN_PIE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_ROTTON_FLESH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SATURATION, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_CARROT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_PUFFERFISH) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.POISON, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_SALMON) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_GOLDEN_CANDYCANE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_ULTIMATE_APPLE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_ENCHANTED_GOLDEN_CARROT) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 2));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//	
//		if(this == FCItems.FOOD_ENCHANTED_GOLDEN_STEAK) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_ENCHANTED_GOLDEN_COD) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_ENCHANTED_GOLDEN_COOKIE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_ENCHANTED_GOLDEN_CANDYCANE) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//			      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 0));
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 0));
//			   this.value=0;
//				this.saturation=3;
//				this.meat=true;
//				this.canEatWhenFull=true;
//				this.fastToEat=true;
//			}
//		
//		if(this == FCItems.FOOD_RADDISH_STEW) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 0));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 4));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		if(this == FCItems.FOOD_DRINKABLE_GASOLINE_PETROL) {
//			par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 2000, 0));
//		      par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.STRENGTH, 2000, 2));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 4));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 2));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 2));
//		par3EntityPlayer.addPotionEffect(new EffectInstance(Effects.HASTE, 600, 2));
//		   this.value=0;
//			this.saturation=3;
//			this.meat=true;
//			this.canEatWhenFull=true;
//			this.fastToEat=true;
//			}
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//		
//				
//		
//	
//
//	
//	
//	
	
	//}	
	
		
		
		
//	public void registerIcons(IconRegister iconRegister)
//	{
//	itemIcon = iconRegister.registerIcon("featurecreep:raw_bacon");	
//	}
	
	
	
	
	
	