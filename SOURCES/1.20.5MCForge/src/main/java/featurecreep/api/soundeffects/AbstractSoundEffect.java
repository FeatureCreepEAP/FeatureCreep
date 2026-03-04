package featurecreep.api.soundeffects;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;

@Deprecated(forRemoval = true, since = "13")

public interface AbstractSoundEffect {

	public SoundEvent get();

	public Holder<SoundEvent> getEntry();

}
