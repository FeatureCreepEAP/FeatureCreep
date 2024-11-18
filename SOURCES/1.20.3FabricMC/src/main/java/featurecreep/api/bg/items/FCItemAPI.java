package featurecreep.api.bg.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.world.FCWorld;
import game.Item;
import game.ItemStack;
import game.LivingEntity;
import game.Player;
import featurecreep.api.io.BasicIO;
import featurecreep.api.bg.PackLoader;

public interface FCItemAPI<T> extends BlockOrItem<T> {

	@Override
	public ItemFieldHolder holder();

	@Override
	public default void registerModels() {

		// I could just do a long string but i will need to use this format for some
		// other things so may as well start
		ModelNode node = new ModelNode();
		node.get("parent").set("minecraft:item/generated");
		node.get("textures").get("layer0").set(this.getModId() + ":item/" + this.getUnlocName());

String file_name = "assets/" + this.getModId()
			+ "/models/item/" + this.getUnlocName() + ".json";
			PackLoader.entries.put(file_name, BasicIO.stringToByteArray(node.toJSONString(false)));

	}

	public default Item get() {

		if (this instanceof Item) {
			return (Item) this;
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
		get().onCreated(ic.toStack(1), worl.get());
	};

	@Override
	public default void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
		get().onUpdate(ic.toStack(1), worl.get(), e.get(), 0, false);
	}

	// @Overridepublic default boolean executeOnLeftClick(AbstractEntity holder,
	// BlockOrItem ic, FCWorld worl) {get().post}
	@Override
	public default boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
		Player ent = (Player) holder.get();
		get().onItemRightClick(worl.get(), ent, ent.getActiveHand());
		return true;
	}

	@Override
	public default boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
		get().onHit(get().getStackForRender(), (LivingEntity) ent.get(), (LivingEntity) ent.get());
		return true;
	}

	@Override
	public default TextureInfo executeGetTextureInfo() {
		return appendGetTextureInfo();// On DangerZone actually try to return correct
	}

	@Override
	public default void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block,
			int side) {
		get().getStackForRender().mineBlock(worl.get(), block.get().getDefaultState(), pos, p.get());
	}

	@Override
	public default void executeOnFoodEaten(AbstractEntity e) {
		LivingEntity ent;
		if (e instanceof LivingEntity) {
			ent = (LivingEntity) e;
			try {
				Method meth = LivingEntity.class.getDeclaredMethod("method_6098", ItemStack.class, int.class);
				meth.setAccessible(true);
				meth.invoke(ent, get().getStackForRender(), 0);// Maybe? not sure about this, use may be better
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//	ent.spawnConsumptionEffects(); 
		}

	}

	@Override
	public default void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
		get().onBlockLeftClick(toStack(1), ent.getWorld().get(), block.get().getDefaultState(), pos,
				(LivingEntity) ent.get());
	};

}
