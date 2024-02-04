package featurecreep.api.bg.entity;

public class VanillaPlayer extends VanillaLivingEntity implements AbstractPlayer{
	
//	PlayerEntity entity;
//	
//	public VanillaPlayer(PlayerEntity ent) {
//		super(ent);
//		// TODO Auto-generated constructor stub
//	entity = ent;
//	}

	public VanillaPlayer(AbstractPlayer ent)
	{
		super(ent);
	}
//
//	@Override
//	public PlayerEntity get() {
//		return entity;
//	}

}
