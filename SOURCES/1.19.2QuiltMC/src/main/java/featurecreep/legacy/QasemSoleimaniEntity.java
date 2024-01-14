package featurecreep.legacy;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.IronGolemLookGoal;
import net.minecraft.entity.ai.goal.IronGolemWanderAroundGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackIronGolemTargetGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundPointOfInterestGoal;
import net.minecraft.entity.ai.goal.WanderNearTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class QasemSoleimaniEntity extends PathAwareEntity{

	protected QasemSoleimaniEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub

	}

	
	  public static DefaultAttributeContainer.Builder  setCustomAttributes() {
	        return MobEntity.createMobAttributes()
	        		.add(EntityAttributes.GENERIC_MAX_HEALTH, 2000.0)
	        		.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
	        		    		.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 3.5)
	        		.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 45.0);
	    
	  
	  }
	
	
	  
	  
	   @Override
	    protected void initGoals() {
	       this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
	      this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
	      this.goalSelector.add(2, new WanderAroundPointOfInterestGoal((PathAwareEntity)this, 0.6, false));
	        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
	        this.goalSelector.add(8, new LookAroundGoal(this));
	        this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
	        this.targetSelector.add(3, new ActiveTargetGoal<MobEntity>(this, MobEntity.class, 5, false, false, entity -> entity instanceof Monster));
	  
	   
	   
	   }
	  
	  
	
}
