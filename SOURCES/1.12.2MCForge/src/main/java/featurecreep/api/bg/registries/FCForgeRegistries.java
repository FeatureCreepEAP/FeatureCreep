package featurecreep.api.bg.registries;

import java.util.ArrayList;
import java.util.List;

import game.Item;
import net.minecraftforge.event.RegistryEvent;

public class FCForgeRegistries {

	
	// THX https://github.com/MelanX/vanilla-hammers/blob/1.14/src/main/java/de/melanx/vanillahammers/common/HammerRegistry.java
//	public static IForgeRegistry<Item> ITEMS = new IForgeRegistry(ForgeRegistries.ITEMS, "featurecreep");

	public static List<Item> ITEMS = new ArrayList<Item>();

public static	RegistryEvent.Register<Item> item_register;
	
	
	
}
