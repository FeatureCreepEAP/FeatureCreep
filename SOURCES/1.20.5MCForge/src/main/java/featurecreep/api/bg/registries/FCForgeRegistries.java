package featurecreep.api.bg.registries;

import game.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCForgeRegistries {

	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "featurecreep");


	// Most credit goes to Randomite mod but i also looked at others for this

	public static void onRegisterCreativeTabs(BuildCreativeModeTabContentsEvent event) {

		for (int i = 0; i < FCRegistries.ITEMS.size(); i++) {

			if (event.getTab() == FCRegistries.ITEMS.get(i).getDefaultCreativeTab()) {
				event.add(FCRegistries.ITEMS.get(i).get());
				System.out.println("Adding to ItemGroup" + FCRegistries.ITEMS.get(i).getFCRegistryName());
			}

		}

		for (int b = 0; b < FCRegistries.BLOCKS.size(); b++) {

			if (event.getTab() == FCRegistries.BLOCKS.get(b).getDefaultCreativeTab()) {
				event.add(FCRegistries.BLOCKS.get(b).get());
				System.out.println("Adding to ItemGroup" + FCRegistries.BLOCKS.get(b).getFCRegistryName());
			}

		}

	}

}
