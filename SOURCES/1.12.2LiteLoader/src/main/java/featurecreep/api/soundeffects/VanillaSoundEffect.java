package featurecreep.api.soundeffects;

import game.SoundPoolComponent;

public class VanillaSoundEffect implements AbstractSoundEffect{

	public SoundPoolComponent event;
	
	public VanillaSoundEffect(SoundPoolComponent itemArmourEquipGeneric) {
		this.event = itemArmourEquipGeneric;
	}
	
	
	@Override
	public SoundPoolComponent get() {
		// TODO Auto-generated method stub
		return event;
	}

}
