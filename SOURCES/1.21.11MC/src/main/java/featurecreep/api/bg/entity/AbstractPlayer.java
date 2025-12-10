package featurecreep.api.bg.entity;

import net.minecraft.world.entity.player.Player;

public interface AbstractPlayer extends AbstractLivingEntity {

	@Override
	public Player get();

}
