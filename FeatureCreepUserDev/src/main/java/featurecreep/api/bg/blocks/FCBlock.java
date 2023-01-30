package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCBlock implements FCBlockAPI<FCBlock> {

public BlockFieldHolder holder = new BlockFieldHolder();
	@Override public BlockFieldHolder holder() {	return holder;	}

  public FCBlock(int id, String modid, String name, UnifiedItemGroupGetter group, UnifiedBlockMaterial material, int strength, BlockDropArrayObject[] drops) {
    initialise(id, modid, name,  group, material, strength, drops);
  }





//Dont use for some internal stuff
  private void getDrops() {

   

  }
  

  @Override
  public FCBlock isSingleSided(boolean answer) {
	   holder(). single_sided = answer;
	return this;  
  }
  
  
  

}
