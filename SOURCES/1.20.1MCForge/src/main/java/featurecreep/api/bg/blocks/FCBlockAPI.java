package featurecreep.api.bg.blocks;

import featurecreep.api.bg.PackLoader;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocknitem.TextureInfo;
import featurecreep.api.bg.blocks.BlockFieldHolder;
import featurecreep.api.bg.blocks.FCBlockPos;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.entity.AbstractEntity;
import featurecreep.api.bg.entity.AbstractPlayer;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.tooltypes.ToolTypes;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.world.FCWorld;
import game.Block;
import game.ItemStack;
import game.LivingEntity;
import game.Player;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.jboss.dmr.ModelNode;

public interface FCBlockAPI<T>
extends BlockOrItem<T> {
    @Override
    public BlockFieldHolder holder();

    default public void initialise(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
        this.initialise(id, modid, name, group);
        this.setDropArrayObjects(drops);
        this.setStrength(strength);
        this.setUnifiedBlockMaterial(material);
    }

    default public boolean getSingleSided() {
        return this.holder().single_sided;
    }

    public T isSingleSided(boolean var1);

    default public void setDownTextureName(String name) {
        this.holder().bottomname = name;
    }

    default public void setEastTextureName(String name) {
        this.holder().rightname = name;
    }

    default public void setNorthTextureName(String name) {
        this.holder().frontname = name;
    }

    default public void setParticleTextureName(String name) {
        this.holder().particlename = name;
    }

    default public void setSouthTextureName(String name) {
        this.holder().backname = name;
    }

    default public void setUpTextureName(String name) {
        this.holder().topname = name;
    }

    default public void setWestTextureName(String name) {
        this.holder().leftname = name;
    }

    default public String getDownTextureName() {
        return this.holder().bottomname;
    }

    default public String getEastTextureName() {
        return this.holder().rightname;
    }

    default public String getNorthTextureName() {
        return this.holder().frontname;
    }

    default public String getParticleTextureName() {
        return this.holder().particlename;
    }

    default public String getSouthTextureName() {
        return this.holder().backname;
    }

    default public String getUpTextureName() {
        return this.holder().topname;
    }

    default public String getWestTextureName() {
        return this.holder().leftname;
    }

    default public BlockDropArrayObject[] getDropArrayObjects() {
        return this.holder().drop_arrays;
    }

    default public void setDropArrayObjects(BlockDropArrayObject[] array) {
        this.holder().drop_arrays = array;
    }

    default public int getStrength() {
        return this.holder().strength;
    }

    default public void setStrength(int strength) {
        this.holder().strength = strength;
    }

    default public UnifiedBlockMaterial getUnifiedBlockMaterial() {
        return this.holder().unimat;
    }

    default public void setUnifiedBlockMaterial(UnifiedBlockMaterial mat) {
        this.holder().unimat = mat;
    }

    @Override
    default public void registerModels() {
        if (this.getSingleSided()) {
            this.setDownTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setEastTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setNorthTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setParticleTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setSouthTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setUpTextureName(this.getModId() + ":block/" + this.getUnlocName());
            this.setWestTextureName(this.getModId() + ":block/" + this.getUnlocName());
        } else {
            this.setDownTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_down");
            this.setEastTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_east");
            this.setNorthTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_north");
            this.setParticleTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_particle");
            this.setSouthTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_south");
            this.setUpTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_up");
            this.setWestTextureName(this.getModId() + ":block/" + this.getUnlocName() + "_west");
        }
        ModelNode node = new ModelNode();
        node.get("parent").set(this.getModId() + ":block/" + this.getUnlocName());
        System.out.print(node.toJSONString(false));
        try {
            File myObj = new File(PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/models/item/" + this.getUnlocName() + ".json");
            if (!myObj.exists()) {
                System.out.println(myObj.toString());
                myObj.getParentFile().mkdirs();
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write(node.toJSONString(true));
                myWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ModelNode block_node = new ModelNode();
        block_node.get("parent").set("minecraft:block/cube");
        block_node.get("textures").get("down").set(this.getDownTextureName());
        block_node.get("textures").get("east").set(this.getEastTextureName());
        block_node.get("textures").get("north").set(this.getNorthTextureName());
        block_node.get("textures").get("particle").set(this.getParticleTextureName());
        block_node.get("textures").get("south").set(this.getSouthTextureName());
        block_node.get("textures").get("up").set(this.getUpTextureName());
        block_node.get("textures").get("west").set(this.getWestTextureName());
        System.out.print(block_node.toJSONString(false));
        try {
            File myObj = new File(PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/models/block/" + this.getUnlocName() + ".json");
            if (!myObj.exists()) {
                System.out.println(myObj.toString());
                myObj.getParentFile().mkdirs();
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write(block_node.toJSONString(true));
                myWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ModelNode blockstate = new ModelNode();
        blockstate.get("variants").get("").get("model").set(this.getModId() + ":block/" + this.getUnlocName());
        System.out.print(blockstate.toJSONString(false));
        try {
            File myObj = new File(PackLoader.fc_pack_location + "/assets/" + this.getModId() + "/blockstates/" + this.getUnlocName() + ".json");
            if (!myObj.exists()) {
                System.out.println(myObj.toString());
                myObj.getParentFile().mkdirs();
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write(blockstate.toJSONString(true));
                myWriter.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    default public Block get() {
        if (this instanceof Block) {
            return (Block)((Object)this);
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
        this.get().asItem().onCreated(ic.toStack(1), worl.get(), p.get());
    }

    @Override
    default public void executeUpdate(AbstractEntity e, BlockOrItem ic, FCWorld worl) {
        this.get().asItem().onUpdate(ic.toStack(1), worl.get(), e.get(), 0, false);
    }

    @Override
    default public boolean executeOnRightClick(AbstractEntity holder, BlockOrItem ic, FCWorld worl) {
        Player ent = (Player)holder.get();
        this.get().asItem().onItemRightClick(worl.get(), ent, ent.getActiveHand());
        return true;
    }

    @Override
    default public boolean executeAfterHit(AbstractEntity ent, AbstractEntity target, BlockOrItem ic, int holdcount) {
        this.get().asItem().onHit(this.toStack(1), (LivingEntity)ent.get(), (LivingEntity)ent.get());
        return true;
    }

    @Override
    default public TextureInfo executeGetTextureInfo() {
        return this.appendGetTextureInfo();
    }

    @Override
    default public void executeLeftClickOnBlock(AbstractPlayer p, FCWorld worl, FCBlockPos pos, FCBlockAPI block, int side) {
        this.get().asItem().onBlockLeftClick(this.toStack(1), worl.get(), block.get().getDefaultState(), pos, p.get());
    }

    @Override
    default public void executeOnFoodEaten(AbstractEntity e) {
        if (e instanceof LivingEntity) {
            LivingEntity ent = (LivingEntity)((Object)e);
            try {
                Method meth = LivingEntity.class.getDeclaredMethod("func_184584_a", new Class[]{ItemStack.class, Integer.TYPE});
                meth.setAccessible(true);
                meth.invoke((Object)ent, new Object[]{this.toStack(1), 0});
            }
            catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    default public void executeOnBlockBroken(AbstractEntity ent, FCBlockPos pos, FCBlockAPI block, int wasbid) {
        this.get().asItem().onBlockLeftClick(this.toStack(1), ent.getWorld().get(), block.get().getDefaultState(), pos, (LivingEntity)ent.get());
    }

    default public FCBlockAPI isSolid(boolean result) {
        this.holder().solid = result;
        return this;
    }

    default public boolean getSolid() {
        return this.holder().solid;
    }

    default public void executeBumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
        this.get().onEntityCollision(this.get().getDefaultState(), w.get(), pos, e.get());
    }

    default public void bumpedBlock(AbstractEntity e, FCWorld w, FCBlockPos pos) {
    }

    default public void executeOnBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
        this.get().onBroke(player.getWorld().get(), pos, this.get().getDefaultState());
    }

    default public void onBroken(AbstractPlayer player, int dimension, FCBlockPos pos) {
    }

    default public void executeOnPlaced(FCWorld w, FCBlockPos pos) {
    }

    default public void onPlaced(FCWorld w, FCBlockPos pos) {
        this.get().onPlaced(w.get(), pos, this.get().getDefaultState(), (LivingEntity)w.get().getEntityById(0), this.toStack(1));
    }

    default public ArrayList<BlockDropArrayObject> getDrops(FCItemAPI tool) {
        ArrayList result = new ArrayList();
        for (int i = 0; i < this.getDropArrayObjects().length; ++i) {
            if (this.getDropArrayObjects()[i].getTool.equals(ToolTypes.BLANK)) {
                System.out.println("Right tool used Dropping items");
                result.add((Object)this.getDropArrayObjects()[i]);
                continue;
            }
            System.out.println(tool.getFCRegistryName());
            System.out.println(this.getDropArrayObjects()[i].getTool.get.isAssignableFrom(tool.toStack(1).getItem().getClass()));
            if (this.getDropArrayObjects()[i].getTool.get.isAssignableFrom(tool.toStack(1).getItem().getClass())) {
                System.out.println("You used the right tool");
                result.add((Object)this.getDropArrayObjects()[i]);
                continue;
            }
            System.out.print("Wrong Tool Used For This Array, you need an instance of" + this.getDropArrayObjects()[i].getTool.get.getCanonicalName() + "But instead got" + tool.getClass().getCanonicalName());
        }
        return result;
    }

    default public void executeOnStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
        this.get().onSteppedOn(w.get(), pos, this.get().getDefaultState(), e.get());
    }

    default public void onStepped(AbstractEntity e, FCWorld w, FCBlockPos pos) {
    }

    default public void executeDropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
        Block.drop(worl.get(), pos, drop.toStack(1));
    }

    default public void dropItem(FCWorld worl, FCBlockPos pos, BlockOrItem drop) {
    }
}
