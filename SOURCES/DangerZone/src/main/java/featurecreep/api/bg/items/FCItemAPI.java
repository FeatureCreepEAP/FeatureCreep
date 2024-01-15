package featurecreep.api.bg.items;

import dangerzone.items.Item;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.world.FCWorld;

public interface FCItemAPI<T> extends BlockOrItem<T> {

@Override public ItemFieldHolder holder();


@Override
public  default void registerModels() {

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
	public default Object getObject() {
return get();		
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
	public default void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {get().onCrafted(p.get(), ic.toStack(1));};
	@Override
	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {get().inventoryTick(e.get(), ic.toStack(1), 0); }

	//@Overridepublic default boolean executeOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {get().post}
	@Override
	public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {get().onRightClick(holder.get(), holder.get(), ic.toStack(1)); return true;}
	@Override
	public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		get().onLeftClick(ent.get(), target.get(), ic.toStack(1));
		return true;
	}
	@Override
	public default TextureInfo executeGetTextureInfo() {
		return appendGetTextureInfo();//On DangerZone actually try to return correct
	}
	@Override
	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
		get().leftClickOnBlock(p.get(), p.get().dimension, pos.getXLocation(), pos.getYLocation(), pos.getZLocation(), side);
	}
	
	@Override
	public default void executeOnFoodEaten(AbstractEntity e) {
get().onFoodEaten(e.get());
	}

	@Override
	public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {get().onBlockBroken(ent.get(), ent.get().dimension, pos.getXLocation(), pos.getYLocation(), pos.getZLocation(), wasbid);};

	
	
	
	
	
}
