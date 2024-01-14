package featurecreep.api.bg.blocknitem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.CreativeTab;
import game.ItemStack;

public interface BlockOrItem<T> {


	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group) {
	setModId(modid);
	setUnlocName(name);
	setDefaultCreativeTab(group.get());
	setNumberID(id);
			System.out.println("Initalising "+getFCRegistryName());
	}
		public void registerModels();
	public BlocknItemFieldHolder holder();
	
	public default String getModId() {
		return holder().public_modid;
	}
	public default String getUnlocName() {
		return holder().public_name;
	}
	public default int getNumberID() {
		return holder().number_id;
	}
	public default CreativeTab getDefaultCreativeTab() {
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
	public default void setDefaultCreativeTab(CreativeTab group) {
		holder().default_tab = group;
	}
	
	public default String getFCRegistryName()
	{
		return (getModId()+":"+getUnlocName());
	}
	
	
	public Object get();

	public	default ItemStack toStack(int amount) {
	  if (this instanceof FCItemAPI) {
	    FCItemAPI item = (FCItemAPI) this;
	    return new ItemStack(item.get(), amount);
	  } else {
	    FCBlockAPI block = (FCBlockAPI) this;
	    return new ItemStack(block.get(), amount);
	  }

	}
	public void appendOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl);

	public void appendUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl);

	//public boolean appendOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl);

	public boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl);

	public boolean appendAfterHit(AbstractEntity ent, AbstractEntity target,BlockOrItem ic, int holdcount);


	public TextureInfo appendGetTextureInfo();

	public void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side);


	public void appendOnFoodEaten(AbstractEntity e);

	public void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos,FCBlockAPI block, int wasbid);



	public void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl);

	public void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl);

	//public  boolean executeOnLeftClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) ;

	public  boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl);

	public  boolean executeAfterHit(AbstractEntity ent,  AbstractEntity target,BlockOrItem ic, int holdcount);


	public  TextureInfo executeGetTextureInfo();

	public  void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side);


	public  void executeOnFoodEaten(AbstractEntity e);

	public  void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos,FCBlockAPI block, int wasbid);



	
	
	
}

