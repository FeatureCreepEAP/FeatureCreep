package featurecreep.api.bg.entity;

import net.minecraft.world.entity.Entity;

@Deprecated(forRemoval = true, since = "13")

public class VanillaEntity implements AbstractEntity {

	Entity entity;

	public VanillaEntity(Entity ent) {
		entity = ent;
	}

	public VanillaEntity(AbstractEntity ent) {
		this(ent.get());
	}

	@Override
	public Entity get() {
		// TODO Auto-generated method stub
		return entity;
	}

}
