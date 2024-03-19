package featurecreep.api.bg.entity;

import featurecreep.api.bg.world.FCWorld;

public interface AbstractEntity {

//	public Entity get();

	public default FCWorld getWorld() {
		return null;// new FCWorld(get().getWorld());

	}

}
