package featurecreep.api.bg.entity;

import net.minecraft.world.entity.LivingEntity;

public class VanillaLivingEntity extends VanillaEntity implements AbstractLivingEntity {

	LivingEntity entity;

	public VanillaLivingEntity(LivingEntity ent) {
		super(ent);
		// TODO Auto-generated constructor stub
		entity = ent;
	}

	public VanillaLivingEntity(AbstractLivingEntity ent) {
		this(ent.get());
	}

	@Override
	public LivingEntity get() {
		return entity;
	}

}
