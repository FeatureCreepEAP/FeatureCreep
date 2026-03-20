package featurecreep.api.bg.entity;

import net.minecraft.world.entity.LivingEntity;

@Deprecated(forRemoval = true, since = "13")

public interface AbstractLivingEntity extends AbstractEntity {

	@Override
	public LivingEntity get();

}
