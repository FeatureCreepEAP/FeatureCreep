package featurecreep.api.bg.entity;

import dangerzone.Player;

public class VanillaPlayer extends Player implements AbstractPlayer{
	
	Player entity;
	
	public VanillaPlayer(Player ent) {
		super(ent.world);
		// TODO Auto-generated constructor stub
	entity = ent;
	}
	
		public VanillaPlayer(AbstractPlayer ent)
	{
		this(ent.get());
	}

	@Override
	public Player get() {
		return entity;
	}

}
