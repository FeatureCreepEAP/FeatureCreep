package featurecreep.api.bg.registries;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCForgeRegistries {

	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "featurecreep");


	// Most credit goes to Randomite mod but i also looked at others for this

	public static void onRegisterCreativeTabs(BuildCreativeModeTabContentsEvent event) {

		for (int i = 0; i < FCRegistries.ITEMS.size(); i++) {

			if (event.getTab() == ((FCItemAPI)FCRegistries.ITEMS.get(i)).getDefaultCreativeTab()) {
				event.accept(((FCItemAPI)FCRegistries.ITEMS.get(i)).get());
				System.out.println("Adding to ItemGroup" + ((FCItemAPI)FCRegistries.ITEMS.get(i)).getFCRegistryName());
			}

		}

		for (int b = 0; b < FCRegistries.BLOCKS.size(); b++) {

			if (event.getTab() == ((FCBlockAPI)FCRegistries.BLOCKS.get(b)).getDefaultCreativeTab()) {
				event.accept(((FCBlockAPI)FCRegistries.BLOCKS.get(b)).get());
				System.out.println("Adding to ItemGroup" + ((FCBlockAPI)FCRegistries.BLOCKS.get(b)).getFCRegistryName());
			}

		}

	}

}