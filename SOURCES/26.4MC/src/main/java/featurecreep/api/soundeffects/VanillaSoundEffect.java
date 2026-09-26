package featurecreep.api.soundeffects;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;

@Deprecated(forRemoval = true, since = "13")

public class VanillaSoundEffect implements AbstractSoundEffect {

	public SoundEvent event;

	public VanillaSoundEffect(SoundEvent event) {
		this.event = event;
	}

	public VanillaSoundEffect(Holder<SoundEvent> event) {
		this.event = event.value();
	}

	@Override
	public SoundEvent get() {
		// TODO Auto-generated method stub
		return event;
	}

	@Override
	public Holder<SoundEvent> getEntry() {
		// TODO Auto-generated method stub
		// return (Reference<SoundEffect>)
		// Mirror.of(SoundEffects.class).method(FeatureCreep.mappings.getMappings().reverse.getDefMappedName("game.SoundEffects.def_unknown_25648(Lgame/ResourceLocation;)Lgame/RegistryEntry/Reference;")).invoke(get().getFile());

		// return
		// GameRegistriesInterface.registerReference(BuiltInRegistries.SOUND_EFFECTS,
		// get().getFile(), get());
		// return ForgeRegistries.SOUND_EVENTS.register(get().getFile(), );

		return Holder.direct(get());

	}

}
