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

public class MechKillerRobot1000Entity extends PathAwareEntity{

	protected MechKillerRobot1000Entity(EntityType<? extends PathAwareEntity> entityType, World world) {
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
		
		
        
        
        
	  
        }
	  
	 
	  
	 
}
	  
	  
	   
	   
	  

