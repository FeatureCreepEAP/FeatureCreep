package featurecreep.api.bg.world;

import net.minecraft.world.level.Level;

public class FCWorld {

	Level world;

	// the FC varient in this case is the wrapper, may make interfaces and others
	// soon but for now no, could maybe make it both, but still not now
	public FCWorld(Level worl) {
		this.world = worl;
	}

	public Level get() {
		return world;
	}

}
