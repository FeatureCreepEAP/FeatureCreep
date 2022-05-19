package featurecreep.content;

import featurecreep.FeatureCreep;
import featurecreep.api.FCRegistries;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import featurecreep.api.items.tools.FCToolMaterial;
import featurecreep.api.ui.FCCreativeTabs;

public class FCItems {

public static FCItem AMETHYST = new FCItem(3000, FeatureCreep.modid, "amethyst", FCCreativeTabs.MATERIALS);
    
	
	
	
//Tools		
public static FCToolMaterial AMETHYST_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20.0F, 18.0F, 20, AMETHYST);
public static FCPickaxe AMETHYST_PICKAXE = new FCPickaxe(3001, FeatureCreep.modid, "amethyst_pickaxe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0); 
public static FCShovel AMETHYST_SHOVEL = new FCShovel(3001, FeatureCreep.modid, "amethyst_shovel", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCHoe AMETHYST_HOE = new FCHoe(3001, FeatureCreep.modid, "amethyst_hoe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCSword AMETHYST_SWORD = new FCSword(3001, FeatureCreep.modid, "amethyst_sword", FCCreativeTabs.COMBAT, AMETHYST_TOOL_MATERIAL, 0, 0);
public static FCAxe AMETHYST_AXE = new FCAxe(3001, FeatureCreep.modid, "amethyst_axe", FCCreativeTabs.TOOLS, AMETHYST_TOOL_MATERIAL, 0, 0);

	
	
	public static void onInitialise() {
	// TODO Auto-generated method stub
	
		FCRegistries.registerItem(AMETHYST);
		FCRegistries.registerItem(AMETHYST_PICKAXE);
		FCRegistries.registerItem(AMETHYST_HOE);
		FCRegistries.registerItem(AMETHYST_SWORD);
		FCRegistries.registerItem(AMETHYST_AXE);
		FCRegistries.registerItem(AMETHYST_SHOVEL);
		
}



	
}
