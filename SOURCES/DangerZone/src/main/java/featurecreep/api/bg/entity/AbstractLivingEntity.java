package featurecreep.api.bg.entity;

import dangerzone.entities.EntityLiving;

@Deprecated(forRemoval = true, since = "13")

public interface AbstractLivingEntity extends AbstractEntity {

	@Override
	public EntityLiving get();

}