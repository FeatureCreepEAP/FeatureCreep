package featurecreep.api.soundeffects;

import game.BuiltInRegistries;
import game.GameRegistries;
import game.RegistryEntry;
import game.SoundEffect;

public class VanillaSoundEffect implements AbstractSoundEffect {

	public SoundEffect event;

	public VanillaSoundEffect(SoundEffect event) {
		this.event = event;
	}
	
	public VanillaSoundEffect(RegistryEntry<SoundEffect> event) {
		this.event = event.comp_349();
	}
	

	@Override
	public SoundEffect get() {
		// TODO Auto-generated method stub
		return event;
	}

	@Override
	public RegistryEntry<SoundEffect> getEntry() {
		// TODO Auto-generated method stub
	//return (Reference<SoundEffect>)	Mirror.of(SoundEffects.class).method(FeatureCreep.mappings.getMappings().reverse.getDefMappedName("game.SoundEffects.def_unknown_25648(Lgame/ResourceLocation;)Lgame/RegistryEntry/Reference;")).invoke(get().getFile());
        return GameRegistries.registerReference(BuiltInRegistries.SOUND_EFFECTS, get().getFile(), get());

	}

}
