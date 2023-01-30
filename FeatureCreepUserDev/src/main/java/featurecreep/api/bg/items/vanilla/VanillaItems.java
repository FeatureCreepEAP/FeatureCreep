package featurecreep.api.bg.items.vanilla;

import featurecreep.api.bg.registries.VanillaRegistries;

public class VanillaItems {

public static VanillaItem STICK = new VanillaItem(null, "");
public static VanillaItem CRYSTAL_STICK = new VanillaItem(null, "");

	
	public static void onInitialise()
	{
		VanillaRegistries.registerItem(STICK);
		VanillaRegistries.registerItem(CRYSTAL_STICK);
	}
	
}
