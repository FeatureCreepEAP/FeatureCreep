package featurecreep.api.soundeffects;

import game.RegistryEntry;
import game.SoundEffect;

public interface AbstractSoundEffect {

	public SoundEffect get();
	
	public RegistryEntry<SoundEffect> getEntry();

}
