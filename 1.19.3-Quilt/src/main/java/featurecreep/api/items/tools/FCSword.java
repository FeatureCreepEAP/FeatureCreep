package featurecreep.api.items.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class FCSword extends SwordItem implements FCItemAPI
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public ItemGroup default_tab;
	public FCToolMaterial mat;
	public int damage;
	public int attackspeed;
	
	public FCSword(int id, String modid, String name, ItemGroup group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Settings().group(group));
		public_modid = modid;
		public_name = name;
		registerModels(this);
		this.default_tab = group;
	this.number_id = id;
	this.mat = material;
	this.damage = attackDamage;
	attackspeed = attackSpeed;
		}

	public FCSword(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, attackDamage, attackSpeed);}
		
		
		
	
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




		
		public ItemGroup getDefaultCreativeTab() {
			// TODO Auto-generated method stub
			return default_tab;
		}
		
		
	
		public FCToolMaterial getFCToolMaterial()	{return mat;}
		public int getToolAttackDamage() {return damage;}
		public int getAttackSpeed() {return attackspeed;}
	
}
