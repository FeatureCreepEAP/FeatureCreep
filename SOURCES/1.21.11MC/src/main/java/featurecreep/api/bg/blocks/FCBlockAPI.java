package featurecreep.api.bg.blocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.dmr.ModelNode;

import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.VersionDependentContstants;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import featurecreep.api.io.BasicIO;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

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

	public default BlockDropArrayObject[] getDropArrayObjects() {
		return holder().drop_arrays;
	}

	public default void setDropArrayObjects(BlockDropArrayObject[] array) {
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

		if (getSingleSided()) {
			this.setDownTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setEastTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setNorthTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setParticleTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setSouthTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setUpTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());
			this.setWestTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName());

		} else {
			this.setDownTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_down");
			this.setEastTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_east");
			this.setNorthTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_north");
			this.setParticleTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_particle");
			this.setSouthTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_south");
			this.setUpTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_up");
			this.setWestTextureName(this.getModId() + ":" + VersionDependentContstants.BLOCK_TEXTURE_LOCATION + "/"
					+ this.getUnlocName() + "_west");

		}

		// Item Generation

		// I could just do a long string but i will need to use this format for some
		// other things so may as well start
		ModelNode node = new ModelNode();
		node.get("parent").set(this.getModId() + ":block/" + this.getUnlocName());
		// node.get("textures").get("layer0").set(public_modid + ":items/" +
		// public_name); Not needed in Blocks

		String file_name = "assets/" + this.getModId() + "/models/item/" + this.getUnlocName() + ".json";
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

		file_name = "assets/" + this.getModId() + "/models/block/" + this.getUnlocName() + ".json";
		PackLoader.entries.put(file_name, BasicIO.stringToByteArray(block_node.toJSONString(false)));

		// Blockstates

		ModelNode blockstate = new ModelNode();
		blockstate.get("variants").get("").get("model").set(this.getModId() + ":block/" + this.getUnlocName());

		file_name = "assets/" + this.getModId() + "/blockstates/" + this.getUnlocName() + ".json";
		PackLoader.entries.put(file_name, BasicIO.stringToByteArray(blockstate.toJSONString(false)));

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
		get().asItem().onCraftedPostProcess(ic.toStack(1), worl.get());
	};

	@Override
	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
		//get().asItem().inventoryTick(ic.toStack(1), worl.get(), e.get(), 0, false);
	}

	// @Overridepublic default boolean executeOnLeftClick(AbstractEntity holder,
	// BlockOrItem ic, FCWorld worl) {get().post}
	@Override
	public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
		Player ent = (Player) holder.get();
		get().asItem().use(worl.get(), ent, ent.getUsedItemHand());
		return true;
	}

	@Override
	public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		get().asItem().hurtEnemy(toStack(1), (LivingEntity) ent.get(), (LivingEntity) ent.get());
		return true;
	}

	@Override
	public default TextureInfo executeGetTextureInfo() {
		return appendGetTextureInfo();// On DangerZone actually try to return correct
	}

	@Override
	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block,
			int side) {
		get().asItem().mineBlock(toStack(1), worl.get(), block.get().defaultBlockState(), pos, p.get());
	}

	@Override
	public default void executeOnFoodEaten(AbstractEntity e) {//TODO fix this method
		LivingEntity ent;
		if (e instanceof LivingEntity) {
			ent = (LivingEntity) e;
			try {
				Method meth = LivingEntity.class.getDeclaredMethod("method_6098", ItemStack.class, int.class);
				meth.setAccessible(true);
				meth.invoke(ent, toStack(1), 0);// Maybe? not sure about this, use may be better
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//		ent.spawnConsumptionEffects(); 
		}

	}

	@Override
	public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
		get().asItem().mineBlock(toStack(1), ent.getWorld().get(), block.get().defaultBlockState(), pos,
				(LivingEntity) ent.get());
	};

}