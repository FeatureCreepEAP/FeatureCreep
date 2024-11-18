package featurecreep.api.bg.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.Block;
import game.ItemStack;
import game.LivingEntity;
import game.Player;

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
	public default void registerModels() {

		if (getSingleSided()) {
			this.setDownTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setEastTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setNorthTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setParticleTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setSouthTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setUpTextureName(this.getModId() + ":block/" + this.getUnlocName());
			this.setWestTextureName(this.getModId() + ":block/" + this.getUnlocName());

		} else {
			this.setDownTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_down");
			this.setEastTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_east");
			this.setNorthTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_north");
			this.setParticleTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_particle");
			this.setSouthTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_south");
			this.setUpTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_up");
			this.setWestTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_west");

		}

		// Item Generation

		// I could just do a long string but i will need to use this format for some
		// other things so may as well start
		ModelNode node = new ModelNode();
		node.get("parent").set(this.getModId() + ":block/" + this.getUnlocName());
		// node.get("textures").get("layer0").set(public_modid + ":items/" +
		// public_name); Not needed in Blocks

		if (FeatureCreep.debug_mode) {
			System.out.print(node.toJSONString(false));
		}

		String file_name = "assets/" + this.getModId()
		+ "/models/item/" + this.getUnlocName() + ".json";
		PackLoader.entries.put(file_name, BasicIO.stringToByteArray(node.toJSONString(false)));


		// Block Model Generation

		ModelNode block_node = new ModelNode();
		block_node.get("parent").set("minecraft:block/cube");
		block_node.get("textures").get("down").set(this.getDownTextureName());
		block_node.get("textures").get("east").set(this.getEastTextureName());
		block_node.get("textures").get("north").set(this.getNorthTextureName());
		block_node.get("textures").get("particle").set(this.getParticleTextureName());
		block_node.get("textures").get("south").set(this.getSouthTextureName());
		block_node.get("textures").get("up").set(this.getUpTextureName());
		block_node.get("textures").get("west").set(this.getWestTextureName());



		file_name = "assets/" + this.getModId()
		+ "/models/block/" + this.getUnlocName() + ".json";
		PackLoader.entries.put(file_name, BasicIO.stringToByteArray(block_node.toJSONString(false)));


		// Blockstates

		ModelNode blockstate = new ModelNode();
		blockstate.get("variants").get("").get("model").set(this.getModId() + ":block/" + this.getUnlocName());


		file_name = "assets/" + this.getModId()
		+ "/blockstates/" + this.getUnlocName() + ".json";
		PackLoader.entries.put(file_name, BasicIO.stringToByteArray(blockstate.toJSONString(false)));

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
		
		
		
		
		

	  	@Override
	  	public default void appendOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {};
	  	@Override
		public default void appendUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {};

		//@Overridepublic default boolean appendOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {return false;};
		@Override
		public default boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {return false;};
		@Override
		public default boolean appendAfterHit(AbstractEntity ent,AbstractEntity target, BlockOrItem ic, int holdcount) {return false;};
		@Override
		public default TextureInfo appendGetTextureInfo() {return null;}; //Needa Actually implement this
		@Override
		public default void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {};
		@Override
		public default void appendOnFoodEaten(AbstractEntity e) {};
		@Override
		public default void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {};

		@Override
		public default void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {get().asItem().onCreated(ic.toStack(1), worl.get(), p.get());};
		@Override
		public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {get().asItem().onUpdate(ic.toStack(1), worl.get(), e.get(), 0, false);}

		//@Overridepublic default boolean executeOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {get().post}
		@Override
		public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {Player ent = (Player)holder.get();get().asItem().onItemRightClick(worl.get(), ent, ent.getActiveHand());return true;}
		@Override
		public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
			get().asItem().onHit(toStack(1), (LivingEntity)ent.get(), (LivingEntity)ent.get());
			return true;
		}
		@Override
		public default TextureInfo executeGetTextureInfo() {
			return appendGetTextureInfo();//On DangerZone actually try to return correct
		}
		@Override
		public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
			get().asItem().onBlockLeftClick(toStack(1), worl.get(), block.get().getDefaultState(), pos, p.get());
		}
		
		@Override
		public default void executeOnFoodEaten(AbstractEntity e) {
	LivingEntity ent;
	if (e instanceof LivingEntity)		{
		ent = (LivingEntity)e;
	try {
		Method meth = LivingEntity.class.getDeclaredMethod("method_6098", ItemStack.class, int.class);
			meth.setAccessible(true);
			meth.invoke(ent, toStack(1), 0);//Maybe? not sure about this, use may be better
	} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
			| InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
//		ent.spawnConsumptionEffects(); 
	}
			
		}
		@Override
		public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {get().asItem().onBlockLeftClick(toStack(1), ent.getWorld().get(), block.get().getDefaultState(), pos, (LivingEntity)ent.get());};

		
		
	
}


