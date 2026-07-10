package asbestosstar.bootstrap.sm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.spongepowered.asm.service.IGlobalPropertyService;
import org.spongepowered.asm.service.IPropertyKey;

public final class FeatureCreepGlobalPropertyService implements IGlobalPropertyService {

	private final Map<String, Object> values = new ConcurrentHashMap<>();

	@Override
	public IPropertyKey resolveKey(String name) {
		return new Key(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProperty(IPropertyKey key) {
		return (T) values.get(key.toString());
	}

	@Override
	public void setProperty(IPropertyKey key, Object value) {
		if (value == null) {
			values.remove(key.toString());
		} else {
			values.put(key.toString(), value);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getProperty(IPropertyKey key, T defaultValue) {
		return (T) values.getOrDefault(key.toString(), defaultValue);
	}

	@Override
	public String getPropertyString(IPropertyKey key, String defaultValue) {
		Object value = values.get(key.toString());
		return value == null ? defaultValue : value.toString();
	}

	private static final class Key implements IPropertyKey {
		private final String name;

		private Key(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
