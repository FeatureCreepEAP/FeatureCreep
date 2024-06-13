package featurecreep.api.soundeffects;

import game.RegistryEntry;
import game.SoundPoolComponent;

public interface AbstractSoundEffect {

	public SoundPoolComponent get();
	
	public RegistryEntry<SoundPoolComponent> getEntry();

}
