package featurecreep.api.bg.blocks;

import featurecreep.api.bg.blocknitem.BlocknItemFieldHolder;
import featurecreep.api.bg.blocks.drop.BlockDropArrayObject;
import featurecreep.api.bg.blocks.materials.UnifiedBlockMaterial;

public class BlockFieldHolder extends BlocknItemFieldHolder{

	public boolean single_sided = false;
	  public BlockDropArrayObject[] drop_arrays;
	 public String topname;
	  public String bottomname;
	  public String leftname;
	  public String rightname;
	  public String frontname;
	  public String backname;
	  public String particlename;
	public int strength;
	public UnifiedBlockMaterial unimat;
	
	
}
