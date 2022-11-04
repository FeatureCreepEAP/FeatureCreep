package featurecreep.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCAxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCHoeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCPickaxeAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCShovelAsDMR;
import featurecreep.api.items.tools.datafied.dmr.FCSwordAsDMR;

public class DatafiedObjectRegistration {

	
	public static void registerDMRItem(FCItemAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_item");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	 
	  	if (!myObj.exists()) {
	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();
	  	}
			
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCItemAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_item");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	  System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
		  	if (!myObj.exists()) {

	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);
		  	}
	  	    
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	public static void registerDMRItem(FCAxeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_axe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	  System.out.println(myObj.toString());
	  		
		  	if (!myObj.exists()) {

	  	  myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();
		  	}
			
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCAxeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_axe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
		  	if (!myObj.exists()) {

	  		
	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);

		  	}
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	

	
	public static void registerDMRItem(FCHoeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_hoe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
  	  	  myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();
		  	}
			
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCHoeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_hoe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");

		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);

		  	}
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	

	
	public static void registerDMRItem(FCPickaxeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_pickaxe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();

		  	}
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCPickaxeAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_pickaxe");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	  System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  	
	  		
		  	if (!myObj.exists()) {

	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);

		  	}
	  	    
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

	
	public static void registerDMRItem(FCShovelAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_shovel");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	 
	  		
		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();

		  	}
			
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCShovelAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_shovel");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	
		  	if (!myObj.exists()) {

	  		
	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);
		  	}
	  	    
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	

	
	public static void registerDMRItem(FCSwordAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_sword");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());

		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  	  
		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  		
	  		FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(node.toString());
			myWriter.close();

		  	}
			
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public static void registerDMRBinaryItem(FCSwordAsDMR item)
	{
		
		System.out.print(item.getModId() + item.public_name + item.number_id + item.default_tab);

		
		
		item.registerModels(item);
		
		

		ModelNode node = new ModelNode();
		node.get("type").set("generic_sword");
		node.get("modid").set(item.public_modid);
		node.get("item_name").set(item.public_name);
		node.get("id").set(item.number_id);
		node.get("group").set(item.default_tab);
        node.get("attack_damage").set(item.getToolAttackDamage());
        node.get("attack_speed").set(item.getAttackSpeed());
        node.get("material").get("max_uses").set(item.getFCToolMaterial().getToolMaxUses());
        node.get("material").get("efficiency").set(item.getFCToolMaterial().getToolEfficiency());
        node.get("material").get("attack_damage").set(item.getFCToolMaterial().getToolAttackDamage());
        node.get("material").get("harvest_level").set(item.getFCToolMaterial().getToolHarvestLevel());
        node.get("material").get("enchantability").set(item.getFCToolMaterial().getToolEnchantability());


		
		  try {
				 
	  		File myObj = new File(FeatureCreep.gamepath.toString() +  ("/datafiedcontents/items/") + item.public_modid + "/" + item.public_name + ".dmr");
	  
		  	if (!myObj.exists()) {

	  		System.out.println(myObj.toString());
	  		myObj.getParentFile().mkdirs();
	  		
	  	    FileOutputStream fout = new FileOutputStream(myObj);
	  		//FileWriter myWriter = new FileWriter(myObj);
	        //myWriter.write(node.toString());
			//myWriter.close();
		
	  	    node.writeExternal(fout);

		  	}
	  	    
	  	    
	  		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
