package featurecreep.api.bg.blocks.drop;

import java.util.ArrayList;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.tooltypes.ToolType;
import featurecreep.api.bg.tooltypes.ToolTypes;

public class BlockDropArrayObject {

	public ArrayList<Object> drop = new ArrayList<Object>();
	public ToolType getTool = ToolTypes.BLANK;

	public BlockDropArrayObject() {

	}

	// The InstanceOfItemandBlock were no longer needed and made portablity harder
	// We gon soon add entities to droppables
	public BlockDropArrayObject addDrop(BlockOrItem added_drop) {
		Object output = null;
		if (added_drop instanceof FCItemAPI) {
			FCItemAPI api = (FCItemAPI) added_drop;
			output = api.get();
		} else if (added_drop instanceof FCBlockAPI) {
			FCBlockAPI api = (FCBlockAPI) added_drop;
			output = api.get();
		}

		if (output.equals(null)) {
			System.out.println("Null, Nil, or Invalid Object not added to Drop");
		} else {
			drop.add(output);
		}

		return this;
	}

	public BlockDropArrayObject removeDrop(BlockOrItem removed_drop) {

		Object output = null;
		if (removed_drop instanceof FCItemAPI) {
			FCItemAPI api = (FCItemAPI) removed_drop;
			output = api.get();
		} else if (removed_drop instanceof FCBlockAPI) {
			FCBlockAPI api = (FCBlockAPI) removed_drop;
			output = api.get();
		}

		if (output.equals(null)) {
			System.out.println("Null, Nil, or Invalid Object not added to Drop");
		} else {
			drop.remove(output);
		}

		return this;

	}

	public BlockDropArrayObject setRequiredToolType(ToolType type) {
		getTool = type;
		return this;
	}

}
