package featurecreep.api.bg.entity;

import game.Player;

public class VanillaPlayer extends VanillaLivingEntity implements AbstractPlayer {

	Player entity;

	public VanillaPlayer(Player ent) {
		super(ent);
		// TODO Auto-generated constructor stub
		entity = ent;
	}

	public VanillaPlayer(AbstractPlayer ent) {
		this(ent.get());
	}

	@Override
	public Player get() {
		return entity;
	}

}
