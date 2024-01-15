package featurecreep.api.bg.entity;

import dangerzone.entities.EntityLiving;

public class VanillaLivingEntity extends VanillaEntity implements AbstractLivingEntity{

	EntityLiving entity;	
	
	public VanillaLivingEntity(EntityLiving ent) {
		super(ent);
		// TODO Auto-generated constructor stub
	entity = ent;
	}
	
	public VanillaLivingEntity(AbstractLivingEntity ent)
	{
		this(ent.get());
	}
	
	@Override
	public EntityLiving get()
	{
		return entity;
	}	

}
