package featurecreep.api.blocks.drop;

import java.util.ArrayList;

import featurecreep.api.tooltypes.ToolType;
import featurecreep.api.tooltypes.ToolTypes;

public class BlockDropArrayObject {

	public ArrayList<Object> drop = new ArrayList<Object>();
public ToolType getTool = ToolTypes.BLANK;
	
	public BlockDropArrayObject()
	{

	}
	
	public BlockDropArrayObject addDrop(Object added_drop) {
		drop.add(added_drop);
		return this;
	}

	
	public BlockDropArrayObject removeDrop(Object added_drop) {
		drop.remove(added_drop);
		return this;

	}
	
	public BlockDropArrayObject setRequiredToolType(ToolType type)
	{
		getTool = type;
		return this;
	}
	
}
