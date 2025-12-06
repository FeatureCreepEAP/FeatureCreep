package featurecreep.api.bg.entity;

import featurecreep.api.bg.world.FCWorld;
import net.minecraft.world.entity.Entity;

public interface AbstractEntity {

	public Entity get();

	public default FCWorld getWorld() {
		return new FCWorld(get().level());
	}

}
