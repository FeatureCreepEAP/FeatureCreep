package featurecreep.api.blocks;

import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;

public class SingleSidedBlock extends FCBlock {

  public SingleSidedBlock(int id, String modid, String name, CreativeTabs group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    super(id, modid, name, group, material, strength, drops);
   this.single_sided = true;
  }

  public SingleSidedBlock(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, strength, drops);
  }


}