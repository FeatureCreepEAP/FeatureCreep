package featurecreep.legacy;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.IronGolemWanderAroundGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundPointOfInterestGoal;
import net.minecraft.entity.ai.goal.WanderNearTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class YellowWindigoEntity extends PathAwareEntity{

	protected YellowWindigoEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub

	
	}


	  public static DefaultAttributeContainer.Builder  setCustomAttributes() {
	        return MobEntity.createMobAttributes()
	        		.add(EntityAttributes.GENERIC_MAX_HEALTH, 12000.0)
	        		.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
	        		.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
	        		.add(EntityAttributes.GENERIC_FLYING_SPEED, 1.0)
	        		.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 25.0)
	        		.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 900.0);
	    
	  
	  }
	
	

	   @Override
	    protected void initGoals() {
	        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true));
	        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
	        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal((PathAwareEntity)this, 0.6, false));
	        this.goalSelector.add(4, new IronGolemWanderAroundGoal(this, 0.6));
	        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
	        this.goalSelector.add(8, new LookAroundGoal(this));
	        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
	        this.targetSelector.add(3, new ActiveTargetGoal<MobEntity>(this, MobEntity.class, 5, false, false, entity -> entity instanceof MobEntity));


	   }
	  
       public float targetSpeed;
       Vec3d targetPosition = Vec3d.ZERO;

    
	  @Override
	  public void mobTick()
	  {
		
		  
		  if (this.getHealth() < 750 && this.getHealth() > 0)
		  {
			  this.setHealth(750.0F);
		  }
			  
		  
  
		  snowGeneration();
	
	  
		  
		    if (this.horizontalCollision) {
                this.setYaw(this.getYaw() + 180.0f);
                this.targetSpeed = 0.1f;
            }
            float f = (float)(this.targetPosition.x - this.getX());
            float g = (float)(this.targetPosition.y - this.getY());
            float h = (float)(this.targetPosition.z - this.getZ());
            double d = MathHelper.sqrt(f * f + h * h);
            if (Math.abs(d) > (double)1.0E-5f) {
                double e = 1.0 - (double)MathHelper.abs(g * 0.7f) / d;
                f = (float)((double)f * e);
                h = (float)((double)h * e);
                d = MathHelper.sqrt(f * f + h * h);
                double i = MathHelper.sqrt(f * f + h * h + g * g);
                float j = this.getYaw();
                float k = (float)MathHelper.atan2(h, f);
                float l = MathHelper.wrapDegrees(this.getYaw() + 90.0f);
                float m = MathHelper.wrapDegrees(k * 57.295776f);
                this.setYaw(MathHelper.stepUnwrappedAngleTowards(l, m, 4.0f) - 90.0f);
                this.bodyYaw = this.getYaw();
                this.targetSpeed = MathHelper.angleBetween(j, this.getYaw()) < 3.0f ? MathHelper.stepTowards(this.targetSpeed, 1.8f, 0.005f * (1.8f / this.targetSpeed)) : MathHelper.stepTowards(this.targetSpeed, 0.2f, 0.025f);
                float n = (float)(-(MathHelper.atan2(-g, d) * 57.2957763671875));
                this.setPitch(n);
                float o = this.getYaw() + 90.0f;
                double p = (double)(this.targetSpeed * MathHelper.cos(o * ((float)Math.PI / 180))) * Math.abs((double)f / i);
                double q = (double)(this.targetSpeed * MathHelper.sin(o * ((float)Math.PI / 180))) * Math.abs((double)h / i);
                double r = (double)(this.targetSpeed * MathHelper.sin(n * ((float)Math.PI / 180))) * Math.abs((double)g / i);
                Vec3d vec3d = this.getVelocity();
                this.setVelocity(vec3d.add(new Vec3d(p, r, q).subtract(vec3d).multiply(0.2)));
            }
        
        
        
	  
        }
	  
	 
	  
	  
	  
	  
	  

	  
	  
	  
public void snowGeneration()
{
	
	BlockPos blockPosition = this.getBlockPos();
	Direction direction = this.getMovementDirection();

	if(!world.isClient) {
		for(int x = -50; x <= 50; x++) {
			for(int y = -30; y <= 30; y++) {
				for(int z = -50; z <= 50; z++) {
					BlockPos blockPosition0 = new BlockPos(blockPosition);
					if(direction == Direction.SOUTH) {
						blockPosition0 = new BlockPos(blockPosition.getX() + x, blockPosition.getY() + y, blockPosition.getZ() + z);
					}else if(direction == Direction.NORTH) {
						blockPosition0 = new BlockPos(blockPosition.getX() - x, blockPosition.getY() + y, blockPosition.getZ() - z);
					}else if(direction == Direction.EAST) {
						blockPosition0 = new BlockPos(blockPosition.getX() + z, blockPosition.getY() + y, blockPosition.getZ() + x);
					}else if(direction == Direction.WEST) {
						blockPosition0 = new BlockPos(blockPosition.getX() - z, blockPosition.getY() + y, blockPosition.getZ() - x);
					}

					BlockPos RemovePos = new BlockPos(blockPosition0.getX(), blockPosition0.getY() + 1, blockPosition0.getZ());
				
					if(		world.getBlockState(blockPosition0) != Blocks.AIR.getDefaultState()) {
						world.setBlockState(blockPosition0, Blocks.SNOW_BLOCK.getDefaultState());
					}
				}
			}
		}
	//	return new TypedActionResult<>(ActionResult.SUCCESS, stack);
		}
//	return new TypedActionResult<>(ActionResult.FAIL, stack);
	}
}
	  
	  
	   
	   
	  

