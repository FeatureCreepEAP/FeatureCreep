package featurecreep.api.bg.entity;

import net.minecraft.entity.player.PlayerEntity;

public class VanillaPlayer extends VanillaLivingEntity implements AbstractPlayer{
	
	PlayerEntity entity;
	
	public VanillaPlayer(PlayerEntity ent) {
		super(ent);
		// TODO Auto-generated constructor stub
	entity = ent;
	}
	
		public VanillaPlayer(AbstractPlayer ent)
	{
		this(ent.get());
	}

	@Override
	public PlayerEntity get() {
		return entity;
	}

}
