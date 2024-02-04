package featurecreep.api.bg.blocknitem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;

public interface BlockOrItem<T> {


	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group) {
	setModId(modid);
	setUnlocName(name);
//	setDefaultCreativeTab(group.get());
	setNumberID(id);
			System.out.println("Initalising "+getFCRegistryName());
	}
	
	public BlocknItemFieldHolder holder();
		public void registerModels();
	public default String getModId() {
		return holder().public_modid;
	}
	public default String getUnlocName() {
		return holder().public_name;
	}
	public default int getNumberID() {
		return holder().number_id;
	}
	public default FCCreativeTab getDefaultCreativeTab() {
		return holder().default_tab;
	}
	
	
	public default void setModId(String modid) {
		holder().public_modid = modid;
	}
	public default void setUnlocName(String name) {
		holder().public_name = name;
	}
	public default void setNumberID(int id) {
		holder().number_id = id;
	}
	public default void setDefaultCreativeTab(FCCreativeTab group) {
		holder().default_tab = group;
	}
	
	public default String getFCRegistryName()
	{
		return (getModId()+":"+getUnlocName());
	}
	
	
//	public Object get(); May enable later, use with Caution

//	public	default ItemStack toStack(int amount) {
//	  if (this instanceof FCItemAPI) {
//	    FCItemAPI item = (FCItemAPI) this;
//	    return new ItemStack(item.get(), amount);
//	  } else {
//	    FCBlockAPI block = (FCBlockAPI) this;
//	    return new ItemStack(block.get(), amount);
//	  }

//	}
	public default void appendOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {};

	public default void appendUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl){};

	//public default boolean appendOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl){};

	public default boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl){return true;};

	public default boolean appendAfterHit(AbstractEntity ent, AbstractEntity target,BlockOrItem ic, int holdcount){return true;};


	public default TextureInfo appendGetTextureInfo(){return null;};

	public default void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side){};


	public default void appendOnFoodEaten(AbstractEntity e){};

	public default void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos,FCBlockAPI block, int wasbid){};



	public default void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl){};

	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl){};

	//public  default boolean executeOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) ;

	public default  boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl){return true;};

	public default boolean executeAfterHit(AbstractEntity ent,  AbstractEntity target,BlockOrItem ic, int holdcount){return true;};


	public default TextureInfo executeGetTextureInfo(){return null;};

	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side){};


	public default void executeOnFoodEaten(AbstractEntity e){};

	public  default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos,FCBlockAPI block, int wasbid){};



	
}
