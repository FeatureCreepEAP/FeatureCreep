package featurecreep.api.bg.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.Item;
import game.LivingEntity;
import game.Player;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;


public interface FCItemAPI<T> extends BlockOrItem<T> {

@Override public ItemFieldHolder holder();


@Override
 public default void registerModels() {

    //I could just do a long string but i will need to use this format for some other things so may as well start 
    ModelNode node = new ModelNode();
    node.get("parent").set("item/generated");
    node.get("textures").get("layer0").set(this.getModId() + ":items/" + this.getUnlocName());
String file_name = "assets/" + this.getModId()
			+ "/models/item/" + this.getUnlocName() + ".json";
			PackLoader.entries.put(file_name, BasicIO.stringToByteArray(node.toJSONString(false)));
  }

  
  
	public default Item get()
	{

		if (this instanceof Item) {
	return (Item)this;	
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
	public default void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {get().onCreated(ic.toStack(1), worl.get(), p.get());};
	@Override
	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {get().onUpdate(ic.toStack(1), worl.get(), e.get(), 0, false);}

	//@Overridepublic default boolean executeOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {get().post}
	@Override
	public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {Player ent = (Player)holder.get();get().onItemRightClick(worl.get(), ent, ent.getActiveHand());return true;}
	@Override
	public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		get().onHit(toStack(1), (Player)ent.get(), (LivingEntity)ent.get());
		return true;
	}
	@Override
	public default TextureInfo executeGetTextureInfo() {
		return appendGetTextureInfo();//On DangerZone actually try to return correct
	}
	@Override
	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
		get().onBlockLeftClick(toStack(1),worl.get(), block.get().getDefaultState(), pos, p.get());
	}
	
	@Override
	public default void executeOnFoodEaten(AbstractEntity e) {
get().onItemUseFinish(toStack(1), e.getWorld().get(), (LivingEntity)e.get());
	}

	@Override
	public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {get().onBlockLeftClick(toStack(1), ent.getWorld().get(), block.get().getDefaultState(), pos, (LivingEntity)ent.get());};

  
  
}
