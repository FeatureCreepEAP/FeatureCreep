package featurecreep.api.bg.registries;

import game.CreativeTab.Entries;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class FCForgeRegistries {
	//Most credit goes to Randomite mod but i also looked at others for this
	
	public static void onRegisterCreativeTabs(BuildCreativeModeTabContentsEvent event)
	{

		
		
		for (int i = 0; i < FCRegistries.ITEMS.size(); i++) {
		
		if (event.getTab() == FCRegistries.ITEMS.get(i).getDefaultCreativeTab())
		{
			Entries entries = (Entries)(event);
			entries.add(FCRegistries.ITEMS.get(i).get());

			System.out.println("Adding to ItemGroup" + FCRegistries.ITEMS.get(i).getFCRegistryName());
		}

		}
	
	
		for (int b = 0; b < FCRegistries.BLOCKS.size(); b++) {
			
		if (event.getTab() == FCRegistries.BLOCKS.get(b).getDefaultCreativeTab())
		{
			Entries entries = (Entries)(event);
			entries.add(FCRegistries.ITEMS.get(b).get());

			System.out.println("Adding to ItemGroup" + FCRegistries.BLOCKS.get(b).getFCRegistryName());
		}

		}
		
		
	
	}

	
	
	
	
	
	
}
