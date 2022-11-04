package featurecreep.api.blocks;

import featurecreep.api.blocks.drop.BlockDropArrayObject;
import featurecreep.api.blocks.materials.VanillaBlockMaterial;
import featurecreep.api.ui.FCCreativeTab;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;

public class FCBlock implements FCBlockAPI {

  public String public_modid;
  public String public_name;
  public int number_id;
  public FCCreativeTab default_tab;
  public boolean single_sided = false;
  public BlockDropArrayObject[] drop_arrays;

  public FCBlock(int id, String modid, String name, FCCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    public_modid = modid;
    public_name = name;
    this.default_tab = group;
    this.number_id = id;
    drop_arrays = drops;
  }

  public FCBlock(int id, String modid, String name, VanillaCreativeTab group, VanillaBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    this(id, modid, name, (FCCreativeTab)null, material, strength, drops);
  }

  public void registerModels() {
    // TODO Auto-generated method stub

  
  }

  public String getModId() {
    // TODO Auto-generated method stub
    return public_modid;
  }

  public String getUnlocName() {
    // TODO Auto-generated method stub
    return public_name;
  }

  public int getNumberID() {
    // TODO Auto-generated method stub
    return number_id;
  }

  public FCCreativeTab getDefaultCreativeTab() {
    // TODO Auto-generated method stub
    return default_tab;
  }

  public void isSingleSided(boolean answer) {
    single_sided = answer;
  }

//Dont use for some internal stuff
  private void getDrops() {

   

  }

}