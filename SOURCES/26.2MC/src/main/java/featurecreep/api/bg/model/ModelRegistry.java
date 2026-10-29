package featurecreep.api.bg.model;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.resources.Identifier;

public final class ModelRegistry {

	private static final Map<Identifier, ModelSpec> SPECS = new ConcurrentHashMap<>();

	private ModelRegistry() {
	}

	public static void register(Identifier id, ModelSpec spec) {
		SPECS.put(id, spec);
	}

	public static Optional<ModelSpec> get(Identifier id) {
		return Optional.ofNullable(SPECS.get(id));
	}
}
