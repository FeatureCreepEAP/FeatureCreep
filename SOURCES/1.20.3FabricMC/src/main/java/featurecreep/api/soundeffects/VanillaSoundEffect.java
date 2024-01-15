package featurecreep.api.soundeffects;

import net.minecraft.sound.SoundEvent;

public class VanillaSoundEffect implements AbstractSoundEffect{

	public SoundEvent event;
	
	public VanillaSoundEffect(SoundEvent event) {
		this.event = event;
	}
	
	
	@Override
	public SoundEvent get() {
		// TODO Auto-generated method stub
		return event;
	}

}
