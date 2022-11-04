package featurecreep.api.blocks;

import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;

public class FCSingleSidedOre extends FCOre implements FCBlockAPI {


  public FCSingleSidedOre(int id, String modid, String name, CreativeTabs group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    super(id, modid, name, group, material, strength, drops, ore_material);
 this.single_sided = true;
  }

  public FCSingleSidedOre(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops, Object ore_material) {
    this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, strength, drops, ore_material);
  }


}