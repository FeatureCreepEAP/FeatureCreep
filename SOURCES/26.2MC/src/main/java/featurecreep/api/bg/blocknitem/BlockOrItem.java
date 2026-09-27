package featurecreep.api.bg.blocknitem;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@Deprecated(forRemoval = true, since = "13")
public interface BlockOrItem<T> {

	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group) {
		setModId(modid);
		setUnlocName(name);
		// 26.1+ creative tabs are bootstrapped after built-in item/block registries.
		// Preserve the logical tab reference instead of resolving it during static init.
		setDefaultCreativeTab(group);
		setNumberID(id);
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

	/**
	 * Resolves the legacy CreativeModeTab lazily. During early bootstrap this may
	 * legitimately return null because vanilla creative tabs are not bound yet.
	 */
	public default CreativeModeTab getDefaultCreativeTab() {
		if (holder().default_tab != null) {
			return holder().default_tab;
		}
		UnifiedItemGroupGetter getter = holder().default_tab_getter;
		return getter == null ? null : getter.get();
	}

	public default UnifiedItemGroupGetter getDefaultCreativeTabGetter() {
		return holder().default_tab_getter;
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

	/** Legacy direct-tab setter retained for callers that already have a bound tab. */
	public default void setDefaultCreativeTab(CreativeModeTab group) {
		holder().default_tab = group;
		holder().default_tab_getter = null;
	}

	/** Preferred 26.1+ form: retain the logical tab and resolve only when needed. */
	public default void setDefaultCreativeTab(UnifiedItemGroupGetter group) {
		holder().default_tab_getter = group;
		holder().default_tab = null;
	}

	public default String getFCRegistryName() {
		return (getModId() + ":" + getUnlocName());
	}

	public Object get();

	public default ItemStack toStack(int amount) {
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
	public boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl);
	public boolean appendAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount);
	public void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side);
	public void appendOnFoodEaten(AbstractEntity e);
	public void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid);
	public void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl);
	public void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl);
	public boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl);
	public boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount);
	public void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side);
	public void executeOnFoodEaten(AbstractEntity e);
	public void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid);
}
