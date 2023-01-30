package featurecreep.api.bg.blocks.drop;

import java.util.ArrayList;

import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.tooltypes.ToolType;
import featurecreep.api.bg.tooltypes.ToolTypes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class BlockDropArrayObject {

	public ArrayList<Object> drop = new ArrayList<Object>();
public ToolType getTool = ToolTypes.BLANK;
	
	public BlockDropArrayObject()
	{

	}
	//We gon soon add entities to droppables
	public BlockDropArrayObject addDrop(Object added_drop) {
		Object output = null;
		if (added_drop instanceof Block) {
		output = added_drop; 
		}else if (added_drop instanceof Item)
		{
			output = added_drop; 
		} else if (added_drop instanceof FCItemAPI)
		{
			FCItemAPI api = (FCItemAPI)added_drop;
			output = api.get();
		}else if (added_drop instanceof FCBlockAPI)
		{
			FCBlockAPI api = (FCBlockAPI)added_drop;
			output = api.get();
		}
		
		if (output.equals(null)) {					
			System.out.println("Null, Nil, or Invalid Object not added to Drop");			
		}else {
			drop.add(output);
		}
		
		
		return this;
	}

	
	public BlockDropArrayObject removeDrop(Object removed_drop) {
		
		Object output = null;
		if (removed_drop instanceof Block) {
		output = removed_drop; 
		}else if (removed_drop instanceof Item)
		{
			output = removed_drop; 
		} else if (removed_drop instanceof FCItemAPI)
		{
			FCItemAPI api = (FCItemAPI)removed_drop;
			output = api.get();
		}else if (removed_drop instanceof FCBlockAPI)
		{
			FCBlockAPI api = (FCBlockAPI)removed_drop;
			output = api.get();
		}
		
		if (output.equals(null)) {					
			System.out.println("Null, Nil, or Invalid Object not added to Drop");			
		}else {
		drop.remove(output);
		}
		
		return this;

	}
	
	public BlockDropArrayObject setRequiredToolType(ToolType type)
	{
		getTool = type;
		return this;
	}
	
}
