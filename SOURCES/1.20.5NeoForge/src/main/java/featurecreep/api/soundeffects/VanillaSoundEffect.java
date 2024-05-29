package featurecreep.api.soundeffects;

import game.BuiltInRegistries;
import game.GameRegistries;
import game.RegistryEntry;
import game.SoundEffect;

public class VanillaSoundEffect implements AbstractSoundEffect {

	public obf.class_unknown_586_ event;

	public VanillaSoundEffect(obf.class_unknown_586_ event) {
		this.event = event;
	}
	
	public VanillaSoundEffect(RegistryEntry<obf.class_unknown_586_> event) {
		this.event = event.comp_349();
	}
	

	@Override
	public obf.class_unknown_586_ get() {
		// TODO Auto-generated method stub
		return event;
	}

	@Override
	public RegistryEntry<obf.class_unknown_586_> getEntry() {
		// TODO Auto-generated method stub
	//return (Reference<SoundEffect>)	Mirror.of(SoundEffects.class).method(FeatureCreep.mappings.getMappings().reverse.getDefMappedName("game.SoundEffects.def_unknown_25648(Lgame/ResourceLocation;)Lgame/RegistryEntry/Reference;")).invoke(get().getFile());
        return GameRegistries.registerReference(BuiltInRegistries.SOUND_EFFECTS, get().getFile(), get());

	}

}
