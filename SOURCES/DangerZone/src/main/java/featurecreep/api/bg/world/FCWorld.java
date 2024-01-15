package featurecreep.api.bg.world;

import dangerzone.world.World;

public class FCWorld {

	World world;	
	
	//the FC varient in this case is the wrapper, may make interfaces and others soon but for now no, could maybe make it both, but still not now
	public FCWorld (World worl)
	{
	this.world = worl;	
	}
	
	public World get()
	{
		return world;
	}
	
}

