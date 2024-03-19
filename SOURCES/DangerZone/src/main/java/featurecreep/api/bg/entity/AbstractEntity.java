package featurecreep.api.bg.entity;

import dangerzone.entities.Entity;
import featurecreep.api.bg.world.FCWorld;

public interface AbstractEntity {

	public Entity get();

	public default FCWorld getWorld() {
		return new FCWorld(get().world);
	}

}
