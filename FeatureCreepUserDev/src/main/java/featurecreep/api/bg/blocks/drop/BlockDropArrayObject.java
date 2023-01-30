package featurecreep.api.bg.blocks.drop;

import java.util.ArrayList;

import featurecreep.api.bg.tooltypes.ToolType;
import featurecreep.api.bg.tooltypes.ToolTypes;

public class BlockDropArrayObject {

	public ArrayList<Object> drop = new ArrayList<Object>();
public ToolType getTool = ToolTypes.BLANK;
	
	public BlockDropArrayObject()
	{

	}
	
	public BlockDropArrayObject addDrop(Object added_drop) {
		Object output = null;
		output = added_drop; //No checks will be done on the UserDev
		drop.add(output);
		return this;
	}

	
	public BlockDropArrayObject removeDrop(Object removed_drop) {
		Object output = null;
		output = removed_drop; //No checks will be done on the UserDev
		drop.remove(output);
		return this;

	}
	
	public BlockDropArrayObject setRequiredToolType(ToolType type)
	{
		getTool = type;
		return this;
	}
	
}
