package featurecreep.api.bg.blocks.drop;

import featurecreep.api.bg.tooltypes.ToolType;

@Deprecated(forRemoval = true, since = "13")

public class SelfBlockDropArrayObject extends BlockDropArrayObject {

	public BlockDropArrayObject setRequiredToolType(ToolType type) {
		if (!this.equals(BlockDropArrayObjects.SELF)) {
			getTool = type;
		}
		return this;

	}

}
