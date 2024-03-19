package featurecreep.api.bg.blocks;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.FCItemAPI;
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

	}

	// We really soon gotta change these for prefered state
	public default FCBlockAPI isSolid(boolean result) {
		holder().solid = result;
		return this;
	}

	public default boolean getSolid() {
		return holder().solid;
	}

	public default void executeBumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void bumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void executeOnBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
	}

	public default void onBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
	}

	public default void executeOnPlaced(FCWorld w, FCBlockPos pos) {
	}

	public default void onPlaced(FCWorld w, FCBlockPos pos) {
	}// Gotta update the last 2 args later

	public default ArrayList<BlockDropArrayObject> getDrops(FCItemAPI tool) {
		return null;
	}

	public default void executeOnStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void onStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
	}

	public default void executeDropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
	}

	public default void dropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
	}

//		public default Block get()	{	} Internal API

}
