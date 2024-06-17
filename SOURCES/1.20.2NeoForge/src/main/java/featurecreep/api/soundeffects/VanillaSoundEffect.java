package featurecreep.api.soundeffects;

import game.SoundEffect;
import game.SoundPoolComponent;

public class VanillaSoundEffect implements AbstractSoundEffect{

	public SoundPoolComponent event;
	
	public VanillaSoundEffect(SoundPoolComponent event) {
		this.event = event;
	}
	
	
	@Override
	public SoundPoolComponent get() {
		// TODO Auto-generated method stub
		return event;
	}

}
