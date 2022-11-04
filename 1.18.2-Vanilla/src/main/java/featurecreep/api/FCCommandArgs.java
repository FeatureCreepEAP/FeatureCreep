package featurecreep.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FCCommandArgs {
//From Fabric
	private final Map<String, String> values;
	private final List<String> extraArgs;
	public FCCommandArgs() {
		values = new LinkedHashMap<>();
		extraArgs = new ArrayList<>();
	}
	public void parse(String[] args) {
		parse(Arrays.asList(args));
	}

	public void parse(List<String> args) {
		for (int i = 0; i < args.size(); i++) {
			String arg = args.get(i);

			if (arg.startsWith("--") && i < args.size() - 1) {
				String value = args.get(i + 1);

				if (value.startsWith("--")) {
					// Give arguments that have no value an empty string.
					value = "";
				} else {
					i += 1;
				}

				values.put(arg.substring(2), value);
			} else {
				extraArgs.add(arg);
			}
		}
	}
	public String getOrDefault(String key, String value) {
		return values.getOrDefault(key, value);
	}
}
