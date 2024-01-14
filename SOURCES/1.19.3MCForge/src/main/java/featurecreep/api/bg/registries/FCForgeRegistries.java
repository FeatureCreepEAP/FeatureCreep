package featurecreep.api.bg.registries;

import com.mojang.serialization.Codec;

import game.Item;
import game.ItemStack;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FCForgeRegistries {

	
	public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "featurecreep");
	
	public static DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
			    DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, "featurecreep");

	//Most credit goes to Randomite mod but i also looked at others for this
	
	public static void onRegisterCreativeTabs(CreativeModeTabEvent.BuildContents event)
	{

		
		
		for (int i = 0; i < FCRegistries.ITEMS.size(); i++) {
		
		if (event.getTab() == FCRegistries.ITEMS.get(i).getDefaultCreativeTab())
		{
		event.add(new ItemStack(FCRegistries.ITEMS.get(i).get()));
		System.out.println("Adding to ItemGroup" + FCRegistries.ITEMS.get(i).getFCRegistryName());
		}

		}
	
	
		for (int b = 0; b < FCRegistries.BLOCKS.size(); b++) {
			
		if (event.getTab() == FCRegistries.BLOCKS.get(b).getDefaultCreativeTab())
		{
			event.add(new ItemStack(FCRegistries.BLOCKS.get(b).get()));
			System.out.println("Adding to ItemGroup" + FCRegistries.BLOCKS.get(b).getFCRegistryName());
		}

		}
		
		
	
	}

	
	
	
	
	
	
}
