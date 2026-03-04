package featurecreep.api.bg.craftingzone;

import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.FCBlockAPI;
import featurecreep.api.dmr.ModelNode;

public class MeltObject {

	String ingredient;
	String result;
	String ingredient_type;
	String result_type;
	String group;
	int xp;
	int time;

	public MeltObject(BlockOrItem item, BlockOrItem result, int xp, int cooking_time, String group) {

		this.ingredient = CraftingZone.getCorrectNameSpace(item.getFCRegistryName());
		this.result = CraftingZone.getCorrectNameSpace(result.getFCRegistryName());

		if (item instanceof FCBlockAPI) {
			ingredient_type = "item";
		} else {
			ingredient_type = "item";
		}
		if (result instanceof FCBlockAPI) {
			result_type = "item";
		} else {
			result_type = "item";
		}
		this.xp = xp;
		this.group = group;
		this.time = cooking_time;

	}

	public ModelNode get114ModelNode() {
		ModelNode node = new ModelNode();
		node.get("type").set("minecraft:smelting");
		node.get("ingredient").get(ingredient_type).set(ingredient);
		node.get("result").set(result); // For some reason there is no type for this one in vanilla, i need to look
										// deeper
		node.get("experience").set(xp);
		node.get("cookingtime").set(time);
		return node;
	}

	public ModelNode get113ModelNode() {
		ModelNode node = get114ModelNode();
		node.get("type").set("smelting");
		return node;
	}

	public ModelNode get117ModelNode() {
		ModelNode node = new ModelNode();
		node.get("type").set("minecraft:smelting");
		node.get("group").set(group);
		node.get("ingredient").get(ingredient_type).set(ingredient);
		node.get("result").set(result); // For some reason there is no type for this one in vanilla, i need to look
										// deeper
		node.get("experience").set(xp);
		node.get("cookingtime").set(time);
		return node;
	}

	public ModelNode get119ModelNode() {
		ModelNode node = new ModelNode();
		node.get("type").set("minecraft:smelting");
		node.get("cookingtime").set(time);
		node.get("experience").set(xp);
		node.get("group").set(group);
		node.get("ingredient").get(ingredient_type).set(ingredient);
		node.get("result").set(result); // For some reason there is no type for this one in vanilla, i need to look
										// deeper
		return node;
	}

}