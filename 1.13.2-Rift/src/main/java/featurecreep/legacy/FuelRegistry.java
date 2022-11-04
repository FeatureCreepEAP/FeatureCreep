package featurecreep.legacy;

public interface FuelRegistry extends Item2ObjectMap<Integer>{

	final FuelRegistry INSTANCE = FuelRegistryImpl.INSTANCE;
}
