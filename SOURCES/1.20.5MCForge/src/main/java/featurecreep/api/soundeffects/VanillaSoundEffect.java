package featurecreep.api.soundeffects;

import game.BuiltInRegistries;
import game.RegistryEntry;
import game.GameRegistriesInterface;
import game.SoundPoolComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class VanillaSoundEffect implements AbstractSoundEffect {

	public SoundPoolComponent event;

	public VanillaSoundEffect(SoundPoolComponent event) {
		this.event = event;
	}
	
	public VanillaSoundEffect(RegistryEntry<SoundPoolComponent> event) {
		this.event = event.comp_349();
	}
	

	@Override
	public SoundPoolComponent get() {
		// TODO Auto-generated method stub
		return event;
	}

	@Override
	public RegistryEntry<SoundPoolComponent> getEntry() {
		// TODO Auto-generated method stub
	//return (Reference<SoundEffect>)	Mirror.of(SoundEffects.class).method(FeatureCreep.mappings.getMappings().reverse.getDefMappedName("game.SoundEffects.def_unknown_25648(Lgame/ResourceLocation;)Lgame/RegistryEntry/Reference;")).invoke(get().getFile());		
		
		//return GameRegistriesInterface.registerReference(BuiltInRegistries.SOUND_EFFECTS, get().getFile(), get());
		//return ForgeRegistries.SOUND_EVENTS.register(get().getFile(), );

return RegistryEntry.of(get());
	
	}

}
