package examplemod;
import org.jboss.logging.Logger.Level;

import featurecreep.FeatureCreep;
import featurecreep.api.DatafiedObjectRegistration;
import featurecreep.api.FCRegistries;
import featurecreep.api.items.FCItem;
import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.FCAxe;
import featurecreep.api.items.tools.FCHoe;
import featurecreep.api.items.tools.FCPickaxe;
import featurecreep.api.items.tools.FCShovel;
import featurecreep.api.items.tools.FCSword;
import featurecreep.api.items.tools.FCToolMaterial;
import featurecreep.api.items.tools.datafied.dmr.FCAxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCHoeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCPickaxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCShovelAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCSwordAsDMR;
import featurecreep.api.ui.FCCreativeTabs;


public class ExampleMod {



public static FCItem EXAMPLE_ITEM = new FCItem(4000, "example", "example_item", FCCreativeTabs.MISC);
	public static FCItem EXAMPLE_ITEM_2 = new FCItem(4001, "example", "example_item_2", FCCreativeTabs.COMBAT);

	
	
	
	
	public static FCItemAsDMR DMR_EXAMPLE = new FCItemAsDMR(4002, "example", "dmr_example", FCCreativeTabs.MATERIALS);
	public static FCItemAsDMR DMR_BINARY_EXAMPLE = new FCItemAsDMR(4003, "example", "dmr_binary_example", FCCreativeTabs.MATERIALS);


		
		
	//Tools		
	public static FCToolMaterial EXAMPLE_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, EXAMPLE_ITEM);
	public static FCPickaxe EXAMPLE_PICKAXE = new FCPickaxe(4004, "example", "example_pickaxe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0); 
	public static FCShovel EXAMPLE_SHOVEL = new FCShovel(4005, "example", "example_shovel", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCHoe EXAMPLE_HOE = new FCHoe(4006, "example", "example_hoe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCSword EXAMPLE_SWORD = new FCSword(4007, "example", "example_sword", FCCreativeTabs.COMBAT, EXAMPLE_TOOL_MATERIAL, 0, 0);
	public static FCAxe EXAMPLE_AXE = new FCAxe(4008, "example", "example_axe", FCCreativeTabs.TOOLS, EXAMPLE_TOOL_MATERIAL, 0, 0);

		


	//DMR Items do not currently work as repair materials
	public static FCToolMaterial EXAPLE_DMR_TOOL_MATERIAL = new FCToolMaterial(8, 5000, 20, 18, 20, EXAMPLE_ITEM_2);
	public static FCPickaxeAsDMR EXAPLE_DMR_PICKAXE = new FCPickaxeAsDMR(4009, "example", "example_dmr_pickaxe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0); 
	public static FCShovelAsDMR EXAPLE_DMR_SHOVEL = new FCShovelAsDMR(4010, "example", "example_dmr_shovel", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCHoeAsDMR EXAPLE_DMR_HOE = new FCHoeAsDMR(4011, "example", "example_dmr_hoe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCSwordAsDMR EXAPLE_DMR_SWORD = new FCSwordAsDMR(4012, "example", "example_dmr_sword", FCCreativeTabs.COMBAT, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);
	public static FCAxeAsDMR EXAPLE_DMR_AXE = new FCAxeAsDMR(4013, "example", "example_dmr_axe", FCCreativeTabs.TOOLS, EXAPLE_DMR_TOOL_MATERIAL, 0, 0);

	
	
	
	/**
	 * @param args
	 */

	public static void main(String[] main) {
		// TODO Auto-generated method stub

//		 String classpath = System.getProperty("java.class.path");
//	        String[] classPathValues = classpath.split(File.pathSeparator);
//	        for (String classPath: classPathValues) {
//	            System.out.println(classPath);
//	        }
	
		System.out.println("EXAMPLE MOD");
		
		
FCRegistries.registerItem(EXAMPLE_ITEM);
FCRegistries.registerItem(EXAMPLE_ITEM_2);



FCRegistries.registerItem(EXAMPLE_PICKAXE);
FCRegistries.registerItem(EXAMPLE_SHOVEL);
FCRegistries.registerItem(EXAMPLE_HOE);
FCRegistries.registerItem(EXAMPLE_SWORD);
FCRegistries.registerItem(EXAMPLE_AXE);



DatafiedObjectRegistration.registerDMRItem(DMR_EXAMPLE);
DatafiedObjectRegistration.registerDMRBinaryItem(DMR_BINARY_EXAMPLE);

DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_PICKAXE);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_SHOVEL);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_HOE);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_SWORD);
DatafiedObjectRegistration.registerDMRItem(EXAPLE_DMR_AXE);




	//	 String oldclasspath = System.getProperty("java.class.path");

//		System.out.println(oldclasspath);





FeatureCreep.LOGGER.log(Level.INFO, "FPMBuild will be made soon");



	}
	
	
	
	
	
	}