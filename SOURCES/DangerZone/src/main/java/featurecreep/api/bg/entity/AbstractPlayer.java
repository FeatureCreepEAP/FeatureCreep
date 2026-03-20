package featurecreep.api.bg.entity;

import dangerzone.Player;

@Deprecated(forRemoval = true, since = "13")

public interface AbstractPlayer extends AbstractLivingEntity {

	@Override
	public Player get();

}