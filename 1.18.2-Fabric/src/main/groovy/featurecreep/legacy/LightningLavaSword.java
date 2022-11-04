package featurecreep.legacy;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningLavaSword extends SwordItem {

	public LightningLavaSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		// TODO Auto-generated constructor stub


	}



	
 @Override
 public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity entity) {
		boolean hit = super.postHit(stack, target, entity);
		double x = target.getX();
		double y = target.getY();
		double z = target.getZ();
		World world = target.world;
		if (world instanceof ServerWorld) {
				Entity entityToSpawn = new LightningEntity(EntityType.LIGHTNING_BOLT, (World) world);
				entityToSpawn.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).initialize((ServerWorld) world, world.getLocalDifficulty(entityToSpawn.getBlockPos()),
							SpawnReason.MOB_SUMMONED, (EntityData) null, (NbtCompound) null);
				world.spawnEntity(entityToSpawn);
			}
			if (world instanceof ServerWorld) {
				Entity entityToSpawn = new LightningEntity(EntityType.LIGHTNING_BOLT, (World) world);
				entityToSpawn.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).initialize((ServerWorld) world, world.getLocalDifficulty(entityToSpawn.getBlockPos()),
							SpawnReason.MOB_SUMMONED, (EntityData) null, (NbtCompound) null);
				world.spawnEntity(entityToSpawn);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
			
			
			
			
		return hit;
	}
 
 
 
	
}
