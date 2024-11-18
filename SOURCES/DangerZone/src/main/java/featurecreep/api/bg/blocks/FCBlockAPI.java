package featurecreep.api.bg.blocks;

import java.util.ArrayList;
import featurecreep.FeatureCreep;

import dangerzone.DangerZone;
import dangerzone.Player;
import dangerzone.blocks.Block;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.io.BasicIO;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;

public interface FCBlockAPI<T> extends BlockOrItem<T> {

	@Override
	public BlockFieldHolder holder();

	public default void initialise(int id, String modid, String name, UnifiedItemGroupGetter group,
			UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
		initialise(id, modid, name, group);
		setDropArrayObjects(drops);
		setStrength(strength);
		setUnifiedBlockMaterial(material);
	}

	public default boolean getSingleSided() {
		return holder().single_sided;
	} // This is for convenience and also could possibly fix the single sided block
		// issue

	public T isSingleSided(boolean answer);

	public default void setDownTextureName(String name) {
		holder().bottomname = name;
	}

	public default void setEastTextureName(String name) {
		holder().rightname = name;
	}

	public default void setNorthTextureName(String name) {
		holder().frontname = name;
	}

	public default void setParticleTextureName(String name) {
		holder().particlename = name;
	}

	public default void setSouthTextureName(String name) {
		holder().backname = name;
	}

	public default void setUpTextureName(String name) {
		holder().topname = name;
	}

	public default void setWestTextureName(String name) {
		holder().leftname = name;
	}

	public default String getDownTextureName() {
		return holder().bottomname;
	}

	public default String getEastTextureName() {
		return holder().rightname;
	}

	public default String getNorthTextureName() {
		return holder().frontname;
	}

	public default String getParticleTextureName() {
		return holder().particlename;
	}

	public default String getSouthTextureName() {
		return holder().backname;
	}

	public default String getUpTextureName() {
		return holder().topname;
	}

	public default String getWestTextureName() {
		return holder().leftname;
	}

	public default featurecreep.api.bg.blocks.drop.BlockDropArrayObject[] getDropArrayObjects() {
		return holder().drop_arrays;
	}

	public default void setDropArrayObjects(featurecreep.api.bg.blocks.drop.BlockDropArrayObject[] array) {
		holder().drop_arrays = array;
	}

	public default int getStrength() {
		return holder().strength;
	}

	public default void setStrength(int strength) {
		holder().strength = strength;
	}

	public default UnifiedBlockMaterial getUnifiedBlockMaterial() {
		return holder().unimat;
	}

	public default void setUnifiedBlockMaterial(UnifiedBlockMaterial mat) {
		holder().unimat = mat;
	}

	@Override
	public default void registerModels() {
		// TODO Auto-generated method stub

		if (getSingleSided()) {
			setUpTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			setDownTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			setWestTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			setEastTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			setNorthTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			setSouthTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + ".png");
			System.out.println(getUpTextureName());

		} else {

			setUpTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_up.png");
			setDownTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_down.png");
			setWestTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_west.png");
			setEastTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_east.png");
			setNorthTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_north.png");
			setSouthTextureName(FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + this.getModId()
					+ "/textures/blocks/" + this.getUnlocName() + "_south.png");

			System.out.println(getUpTextureName());

		}
	}

	public default Block get() {

		if (this instanceof Block) {
			return (Block) this;
		} else {
			System.out.println("Tried to use an internal API referencing a Non-Item when Item was required.");
			return null;
		}

	}

	@Override
	public default Object getObject() {
		return get();
	}

	@Override
	public default void appendOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {
	};

	@Override
	public default void appendUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
	};

	// @Overridepublic default boolean appendOnLeftClick(AbstractEntity holder,
	// BlockOrItem ic, FCWorld worl) {return false;};
	@Override
	public default boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
		return false;
	};

	@Override
	public default boolean appendAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		return false;
	};

	@Override
	public default TextureInfo appendGetTextureInfo() {
		return null;
	}; // Needa Actually implement this

	@Override
	public default void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block,
			int side) {
	};

	@Override
	public default void appendOnFoodEaten(AbstractEntity e) {
	};

	@Override
	public default void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
	};

	@Override
	public default void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {
		get().onCrafted(p.get(), ic.toStack(1));
	};

	@Override
	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
		get().inventoryTick(e.get(), ic.toStack(1), 0);
	}

	// @Overridepublic default boolean executeOnLeftClick(AbstractEntity holder,
	// BlockOrItem ic, FCWorld worl) {get().post}
	@Override
	public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
		get().onRightClick(holder.get(), holder.get(), ic.toStack(1));
		return true;
	}

	@Override
	public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		get().onLeftClick(ent.get(), target.get(), ic.toStack(1));
		return true;
	}

	@Override
	public default TextureInfo executeGetTextureInfo() {
		return appendGetTextureInfo();// On DangerZone actually try to return correct
	}

	@Override
	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block,
			int side) {
		get().leftClickOnBlock(p.get(), p.get().dimension, pos.getXLocation(), pos.getYLocation(), pos.getZLocation(),
				side);
	}

	@Override
	public default void executeOnFoodEaten(AbstractEntity e) {
		get().onFoodEaten(e.get());
	}

	@Override
	public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
		get().onBlockBroken((Player) ent.get(), ent.get().dimension, pos.getXLocation(), pos.getYLocation(),
				pos.getZLocation(), wasbid);
	};

	// We really soon gotta change these for prefered state
	public default FCBlockAPI isSolid(boolean result) {
		holder().solid = result;
		return this;
	}

	public default boolean getSolid() {
		return holder().solid;
	}

	public default void executeBumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
		get().bumpedBlock(e.get(), w.get(), 0, pos.getXLocation(), pos.getYLocation(), pos.getZLocation());
	}

	public default void bumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void executeOnBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
		get().onBlockBroken(player.get(), 0, pos.getXLocation(), pos.getYLocation(), pos.getZLocation(), 0);
	}

	public default void onBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
	}

	public default void executeOnPlaced(FCWorld w, FCBlockPos pos) {
	}

	public default void onPlaced(FCWorld w, FCBlockPos pos) {
		get().onBlockPlaced(w.get(), 0, pos.getXLocation(), pos.getYLocation(), pos.getZLocation());
	}// Gotta update the last 2 args later

	public default ArrayList<BlockDropArrayObject> getDrops(FCItemAPI tool) {

		ArrayList<BlockDropArrayObject> result = new ArrayList<BlockDropArrayObject>();

		for (int i = 0; i < getDropArrayObjects().length; i++) {
			if (getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {

				System.out.println("Right tool used Dropping items");
				result.add(getDropArrayObjects()[i]);
				// getDrops(world, pos, getDropArrayObjects()[i]);

			} else {
				System.out.println(tool.getFCRegistryName()); // Took so damn long to realise i needed stack and not
																// player.ActiveItem

				// System.out.println(stack.getItem().getClass().isAssignableFrom(getDropArrayObjects()[i].getTool.get));
				System.out.println(
						getDropArrayObjects()[i].getTool.get.isAssignableFrom(tool.toStack(1).getItem().getClass()));

				if (getDropArrayObjects()[i].getTool.get.isAssignableFrom(tool.toStack(1).getItem().getClass())) {
					System.out.println("You used the right tool");
					result.add(getDropArrayObjects()[i]);
					// getDrops(world, pos, getDropArrayObjects()[i]);
				} else {
					System.out.print("Wrong Tool Used For This Array, you need an instance of"
							+ getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got"
							+ tool.getClass().getCanonicalName());// player.getActiveItem().getClass().getName());
				}

			}

		}

		return result;

	}

	public default void executeOnStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
		get().doSteppedOn(e.get(), w.get(), 0, pos.getXLocation(), pos.getYLocation(), pos.getZLocation());
	}

	public default void onStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void executeDropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
		worl.get().createEntityByName(DangerZone.blockitemname, 0, (double) pos.getXLocation() + 0.5f,
				(double) pos.getYLocation() + 0.5f, (double) pos.getZLocation() + 0.5f);
	}

	public default void dropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
	}

}
