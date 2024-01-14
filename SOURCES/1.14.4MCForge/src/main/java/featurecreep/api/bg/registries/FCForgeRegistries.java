package featurecreep.api.bg.registries;

import game.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCForgeRegistries {

	
	// THX https://github.com/MelanX/vanilla-hammers/blob/1.14/src/main/java/de/melanx/vanillahammers/common/HammerRegistry.java
	public static DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, "featurecreep");

	
	
}
