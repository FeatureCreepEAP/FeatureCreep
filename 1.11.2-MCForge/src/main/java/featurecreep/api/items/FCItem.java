package featurecreep.api.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FCItem extends Item implements FCItemAPI
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public CreativeTabs default_tab;
	
	public FCItem(int id, String modid, String name, CreativeTabs group)
	{
		super();
	public_modid = modid;
	public_name = name;
	registerModels(this);
	this.default_tab = group;
	this.number_id = id;
	this.setCreativeTab(group);
	}

	
	public FCItem(int id, String modid, String name, VanillaCreativeTab group)
	{this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group));}
	
	
	

	
	
	
	
	
	
	
	public void registerModels(Item item) {
		// TODO Auto-generated method stub
		//I could just do a long string but i will need to use this format for some other things so may as well start 
		ModelNode node = new ModelNode();
		node.get("parent").set("item/generated");
		node.get("textures").get("layer0").set(public_modid + ":items/" + public_name);
		
		System.out.print(node.toJSONString(false));
	 

	      try {
	 
	    		File myObj = new File(featurecreep.api.PackLoader.fc_pack_location+ "/assets/" + public_modid + "/models/item/" + public_name + ".json");
	    
	    	  	if (!myObj.exists()) {

	    		System.out.println(myObj.toString());
	    		myObj.getParentFile().mkdirs();
	    		
	    		
	    		FileWriter myWriter = new FileWriter(myObj);
	          myWriter.write(node.toJSONString(true));
			myWriter.close();

	    	  	}
			
			
	    		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	      
	      
	      
	      
	      
	}




	public String getModId() {
		// TODO Auto-generated method stub
		return public_modid;
	}




	public String getUnlocName() {
		// TODO Auto-generated method stub
		return public_name;
	}




	public int getNumberID() {
		// TODO Auto-generated method stub
		return number_id;
	}




	public CreativeTabs getDefaultCreativeTab() {
		// TODO Auto-generated method stub
		return default_tab;
	}
	
	
	
	
	
	
	
	
	
}
