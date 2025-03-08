
package featurecreep.api.bg.items;

import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.ItemFieldHolder;
import featurecreep.api.bg.world.FCWorld;
import game.Item;
import game.ItemStack;
import game.LivingEntity;
import game.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.jboss.dmr.ModelNode;
import featurecreep.api.io.BasicIO;
import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.VersionDependentContstants;


public interface FCItemAPI<T>
extends BlockOrItem<T> {
    @Override
    public ItemFieldHolder holder();

    @Override
    default public void registerModels() {
        ModelNode node = new ModelNode();
        node.get("parent").set("minecraft:item/generated");
		node.get("textures").get("layer0").set(this.getModId() + ":"+VersionDependentContstants.ITEM_TEXTURE_LOCATION+"/" + this.getUnlocName());
String file_name = "assets/" + this.getModId()
			+ "/models/item/" + this.getUnlocName() + ".json";
			PackLoader.entries.put(file_name, BasicIO.stringToByteArray(node.toJSONString(false)));
    }

    @Override
    default public Item get() {
        if (this instanceof Item) {
            return (Item)((Object)this);
        }
        System.out.println("Tried to use an internal API referencing a Non-Item when Item was required.");
        return null;
    }

    @Override
    default public void appendOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {
    }

    @Override
    default public void appendUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
    }

    @Override
    default public boolean appendOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
        return false;
    }

    @Override
    default public boolean appendAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
        return false;
    }

    @Override
    default public TextureInfo appendGetTextureInfo() {
        return null;
    }

    @Override
    default public void appendLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
    }

    @Override
    default public void appendOnFoodEaten(AbstractEntity e) {
    }

    @Override
    default public void appendOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
    }

    @Override
    default public void executeOnCrafted(AbstractPlayer p, BlockOrItem ic, FCWorld worl) {
        this.get().onCreated(ic.toStack(1), worl.get(), p.get());
    }

    @Override
    default public void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
        this.get().onUpdate(ic.toStack(1), worl.get(), e.get(), 0, false);
    }

    @Override
    default public boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
        Player ent = (Player)holder.get();
        this.get().onItemRightClick(worl.get(), ent, ent.getActiveHand());
        return true;
    }

    @Override
    default public boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
        this.get().onHit(this.get().getStackForRender(), (LivingEntity)ent.get(), (LivingEntity)ent.get());
        return true;
    }

    @Override
    default public TextureInfo executeGetTextureInfo() {
        return this.appendGetTextureInfo();
    }

    @Override
    default public void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
        this.get().getStackForRender().mineBlock(worl.get(), block.get().getDefaultState(), pos, p.get());
    }

    @Override
    default public void executeOnFoodEaten(AbstractEntity e) {
        if (e instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity)((Object)e);
            try {
                Method meth = LivingEntity.class.getDeclaredMethod("method_6098", new Class[]{ItemStack.class, Integer.TYPE});
                meth.setAccessible(true);
                meth.invoke((Object)ent, new Object[]{this.get().getStackForRender(), 0});
            }
            catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    default public void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
        this.get().onBlockLeftClick(this.toStack(1), ent.getWorld().get(), block.get().getDefaultState(), pos, (LivingEntity)ent.get());
    }
}
