package featurecreep.api.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import org.jetbrains.annotations.Nullable;

import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.drop.BlockDropArrayObjects;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.tooltypes.ToolTypes;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FCSingleSidedOre extends FCOre implements FCBlockAPI {


  public FCSingleSidedOre(int id, String modid, String name, ItemGroup group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    super(id, modid, name, group, material, strength, drops, ore_material);
 this.single_sided = true;
  }

  public FCSingleSidedOre(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, strength, drops, ore_material);
  }


}