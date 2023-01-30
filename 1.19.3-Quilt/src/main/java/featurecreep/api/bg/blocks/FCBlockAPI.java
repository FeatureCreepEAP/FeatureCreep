package featurecreep.api.bg.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.minecraft.block.Block;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public interface FCBlockAPI<T> extends BlockOrItem<T> {

@Override
	public BlockFieldHolder holder();



	  
	  
	 
	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
		initialise(id, modid, name, group);
		setDropArrayObjects(drops);
		setStrength(strength);
		setUnifiedBlockMaterial(material);
	}
	
	
	
 public default boolean getSingleSided() {return holder().single_sided;} //This is for convenience and also could possibly fix the single sided block issue

  public T isSingleSided(boolean answer);
  
  public  default void setDownTextureName(String name) {holder().bottomname=name;}
  public  default void setEastTextureName(String name){holder().rightname=name;}
  public default void setNorthTextureName(String name){holder().frontname=name;}
  public default void setParticleTextureName(String name){holder().particlename=name;}
  public default void setSouthTextureName(String name){holder().backname=name;}
  public default void setUpTextureName(String name){holder().topname=name;}
  public default void setWestTextureName(String name){holder().leftname=name;}

  public default String getDownTextureName() {return holder().bottomname;}
  public default String getEastTextureName(){return holder().rightname;}
  public default String getNorthTextureName(){return holder().frontname;}
  public default String getParticleTextureName(){return holder().particlename;}
  public default String getSouthTextureName(){return holder().backname;}
  public default String getUpTextureName(){return holder().topname;}
  public default String getWestTextureName(){return holder().leftname;}
  
  
  
  
	public default featurecreep.api.bg.blocks.drop.BlockDropArrayObject[] getDropArrayObjects() {
		return holder().drop_arrays;
	}
	
	public default void setDropArrayObjects(featurecreep.api.bg.blocks.drop.BlockDropArrayObject[] array) {
		holder().drop_arrays = array;
	}
	
	public default int getStrength()
	{
		return holder().strength;
	}
	
	public default void setStrength(int strength)
	{
		holder().strength = strength;
	}
	
	public default UnifiedBlockMaterial getUnifiedBlockMaterial()
	{
		return holder().unimat;
	}
	
	public default void setUnifiedBlockMaterial(UnifiedBlockMaterial mat)
	{
		holder().unimat = mat;
	}
  
	  
	  @Override
	public  default void registerModels() {


		  if (getSingleSided()) {
		      this.setDownTextureName    (this.getModId() + ":block/" + this.getUnlocName());
		      this.setEastTextureName (this.getModId() + ":block/" + this.getUnlocName());
		      this.setNorthTextureName  (this.getModId() + ":block/" + this.getUnlocName());
		      this.setParticleTextureName  (this.getModId() + ":block/" + this.getUnlocName());
		      this.setSouthTextureName  (this.getModId() + ":block/" + this.getUnlocName());
		      this.setUpTextureName  (this.getModId() + ":block/" + this.getUnlocName());
		      this.setWestTextureName  (this.getModId() + ":block/" + this.getUnlocName());

		    } else {
		    	this.setDownTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_down");
		    	this.setEastTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_east");
		    	this.setNorthTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_north");
		    	this.setParticleTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_particle");
		    	this.setSouthTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_south");
		    	this.setUpTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_up");
		    	this.setWestTextureName( this.getModId() + ":block/" + this.getUnlocName() + "_west");

		    }

		    //Item Generation

		    //I could just do a long string but i will need to use this format for some other things so may as well start 
		    ModelNode node = new ModelNode();
		    node.get("parent").set(this.getModId() + ":block/" + this.getUnlocName());
		    //node.get("textures").get("layer0").set(public_modid + ":items/" + public_name); Not needed in Blocks

		    System.out.print(node.toJSONString(false));

		    try {

		      File myObj = new File(featurecreep.api.bg.PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/models/item/" + this.getUnlocName() + ".json");

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

		    //Block Model Generation	      

		    ModelNode block_node = new ModelNode();
		    block_node.get("parent").set("minecraft:block/cube");
		    block_node.get("textures").get("down").set(this.getDownTextureName());
		    block_node.get("textures").get("east").set(this.getEastTextureName());
		    block_node.get("textures").get("north").set(this.getNorthTextureName());
		    block_node.get("textures").get("particle").set(this.getParticleTextureName());
		    block_node.get("textures").get("south").set(this.getSouthTextureName());
		    block_node.get("textures").get("up").set(this.getUpTextureName());
		    block_node.get("textures").get("west").set(this.getWestTextureName());

		    System.out.print(block_node.toJSONString(false));

		    try {

		      File myObj = new File(featurecreep.api.bg.PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/models/block/" + this.getUnlocName() + ".json");

		      if (!myObj.exists()) {

		        System.out.println(myObj.toString());
		        myObj.getParentFile().mkdirs();

		        FileWriter myWriter = new FileWriter(myObj);
		        myWriter.write(block_node.toJSONString(true));
		        myWriter.close();

		      }

		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }

		    //Blockstates

		    ModelNode blockstate = new ModelNode();
		    blockstate.get("variants").get("").get("model").set(this.getModId() + ":block/" + this.getUnlocName());

		    System.out.print(blockstate.toJSONString(false));

		    try {

		      File myObj = new File(featurecreep.api.bg.PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/blockstates/" + this.getUnlocName() + ".json");

		      if (!myObj.exists()) {

		        System.out.println(myObj.toString());
		        myObj.getParentFile().mkdirs();

		        FileWriter myWriter = new FileWriter(myObj);
		        myWriter.write(blockstate.toJSONString(true));
		        myWriter.close();

		      }

		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
	  }
	
	
	
		public default Block get()
	{

		if (this instanceof Block) {
	return (Block)this;	
		}else
		{
	System.out.println("Tried to use an internal API referencing a Non-Item when Item was required.");		
	return null;
		}
	
	}
	
	
}


