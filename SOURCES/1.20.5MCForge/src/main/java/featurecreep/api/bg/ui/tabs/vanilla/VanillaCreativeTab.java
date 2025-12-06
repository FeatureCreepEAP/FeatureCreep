package featurecreep.api.bg.ui.tabs.vanilla;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import mx.kenzie.mirror.Mirror;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public class VanillaCreativeTab implements UnifiedItemGroupGetter {
	public String tabname;

	public VanillaCreativeTab(String name) {
		tabname = name;
		setTabName(getVanillaGroupFromString(this).getDisplayName().getString()); // May not work, ideally tabname
																						// will be used for most //In
																						// Yarn the ID is the String
																						// name, kinda throws off
																						// considering here its the
																						// number
		setID(0);// TODO
	}

//Minecraft Only gotta change the mappings for these once i figure them out
	public static CreativeModeTab getVanillaGroupFromString(VanillaCreativeTab groupname) {
		if (groupname.tabname.equals("BUILDING_BLOCKS")) {
			String tab_string = FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.BUILDING_BLOCKS:Lgame/RegistryKey;");
			System.out.println(tab_string);
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(tab_string).get());
		} else if (groupname.tabname.equals("BREWING")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.MISULANIOUS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("COMBAT")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.COMBAT:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("DECORATIONS")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.BUILDING_BLOCKS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("FOOD")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.FOODSTUFFS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("MATERIALS")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.PLANTS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("MISC")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.MISULANIOUS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("REDSTONE")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.REDSTONE:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("TOOLS")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.TOOLS:Lgame/RegistryKey;")).get());
		} else if (groupname.tabname.equals("TRANSPORTATION")) {
			return BuiltInRegistries.CREATIVE_MODE_TAB.get((ResourceKey<CreativeModeTab>)Mirror.of(CreativeModeTabs.class).field(FeatureCreep.mappings.getMappings().getReverse().getVarMappedName("game.CreativeTabs.FUNCTION:Lgame/RegistryKey;")).get());
		} else {
			return null;
		}

	}

	@Override
	public CreativeModeTab get() {
		// TODO Auto-generated method stub
		return getVanillaGroupFromString(this);
	}

}