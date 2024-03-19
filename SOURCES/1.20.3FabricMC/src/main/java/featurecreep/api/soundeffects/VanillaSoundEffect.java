package featurecreep.api.soundeffects;

import game.SoundEffect;

public class VanillaSoundEffect implements AbstractSoundEffect {

	public SoundEffect event;

	public VanillaSoundEffect(SoundEffect event) {
		this.event = event;
	}

	@Override
	public SoundEffect get() {
		// TODO Auto-generated method stub
		return event;
	}

}
