package featurecreep.api.bg.craftingzone;

import org.jboss.dmr.ModelNode;

import featurecreep.FeatureCreep;
import featurecreep.api.bg.blocknitem.BlockOrItem;
import featurecreep.api.bg.blocks.FCBlockAPI;

public class CraftObject {

	String ing0letter;
	String ing0key;
	String ing0type;
	String ing1letter;
	String ing1key;
	String ing1type;
	String ing2letter;
	String ing2key;
	String ing2type;
	String ing3letter;
	String ing3key;
	String ing3type;
	String ing4letter;
	String ing4key;
	String ing4type;
	String ing5letter;
	String ing5key;
	String ing5type;
	String ing6letter;
	String ing6key;
	String ing6type;
	String ing7letter;
	String ing7key;
	String ing7type;
	String ing8letter;
	String ing8key;
	String ing8type;
	String result;
	int quantity;
	String resulttype;

	public CraftObject(BlockOrItem result, int quantity, BlockOrItem ingredient0, BlockOrItem ingredient1,
			BlockOrItem ingredient2, BlockOrItem ingredient3, BlockOrItem ingredient4, BlockOrItem ingredient5,
			BlockOrItem ingredient6, BlockOrItem ingredient7, BlockOrItem ingredient8) {

		String ing0letter;
		String ing0key;
		String ing0type;
		String ing1letter;
		String ing1key;
		String ing1type;
		String ing2letter;
		String ing2key;
		String ing2type;
		String ing3letter;
		String ing3key;
		String ing3type;
		String ing4letter;
		String ing4key;
		String ing4type;
		String ing5letter;
		String ing5key;
		String ing5type;
		String ing6letter;
		String ing6key;
		String ing6type;
		String ing7letter;
		String ing7key;
		String ing7type;
		String ing8letter;
		String ing8key;
		String ing8type;

		if (result != null) {

			if (ingredient0 != null) {
				ing0letter = "A";
				ing0key = CraftingZone.getCorrectNameSpace(ingredient0.getFCRegistryName());
				if (ingredient0 instanceof FCBlockAPI) {
					ing0type = "item";
				} else {
					ing0type = "item";
				}

			} else {
				ing0letter = " ";
				ing0key = " ";
				ing0type = " ";
			}

			if (ingredient1 != null) {
				ing1letter = "B";
				ing1key = CraftingZone.getCorrectNameSpace(ingredient1.getFCRegistryName());
				if (ingredient1 instanceof FCBlockAPI) {
					ing1type = "item";
				} else {
					ing1type = "item";
				}

			} else {
				ing1letter = " ";
				ing1key = " ";
				ing1type = " ";
			}

			if (ingredient2 != null) {
				ing2letter = "C";
				ing2key = CraftingZone.getCorrectNameSpace(ingredient2.getFCRegistryName());
				if (ingredient2 instanceof FCBlockAPI) {
					ing2type = "item";
				} else {
					ing2type = "item";
				}

			} else {
				ing2letter = " ";
				ing2key = " ";
				ing2type = " ";
			}

			if (ingredient3 != null) {
				ing3letter = "D";
				ing3key = CraftingZone.getCorrectNameSpace(ingredient3.getFCRegistryName());
				if (ingredient3 instanceof FCBlockAPI) {
					ing3type = "item";
				} else {
					ing3type = "item";
				}

			} else {
				ing3letter = " ";
				ing3key = " ";
				ing3type = " ";

			}

			if (ingredient4 != null) {
				ing4letter = "E";
				ing4key = CraftingZone.getCorrectNameSpace(ingredient4.getFCRegistryName());
				if (ingredient4 instanceof FCBlockAPI) {
					ing4type = "item";
				} else {
					ing4type = "item";
				}

			} else {
				ing4letter = " ";
				ing4key = " ";
				ing4type = " ";

			}

			if (ingredient5 != null) {
				ing5letter = "F";
				ing5key = CraftingZone.getCorrectNameSpace(ingredient5.getFCRegistryName());
				if (ingredient5 instanceof FCBlockAPI) {
					ing5type = "item";
				} else {
					ing5type = "item";
				}

			} else {
				ing5letter = " ";
				ing5key = " ";
				ing5type = " ";

			}

			if (ingredient6 != null) {
				ing6letter = "G";
				ing6key = CraftingZone.getCorrectNameSpace(ingredient6.getFCRegistryName());
				if (ingredient6 instanceof FCBlockAPI) {
					ing6type = "item";
				} else {
					ing6type = "item";
				}

			} else {
				ing6letter = " ";
				ing6key = " ";
				ing6type = " ";

			}

			if (ingredient7 != null) {
				ing7letter = "H";
				ing7key = CraftingZone.getCorrectNameSpace(ingredient7.getFCRegistryName());
				if (ingredient7 instanceof FCBlockAPI) {
					ing7type = "block";
				} else {
					ing7type = "item";
				}

			} else {
				ing7letter = " ";
				ing7key = " ";
				ing7type = " ";

			}

			if (ingredient8 != null) {
				ing8letter = "I";
				ing8key = CraftingZone.getCorrectNameSpace(ingredient8.getFCRegistryName());
				if (ingredient8 instanceof FCBlockAPI) {
					ing8type = "block";
				} else {
					ing8type = "item";
				}

			} else {
				ing8letter = " ";
				ing8key = " ";
				ing8type = " ";

			}

			this.ing0letter = ing0letter;
			this.ing0key = ing0key;
			this.ing0type = ing0type;
			this.ing1letter = ing1letter;
			this.ing1key = ing1key;
			this.ing1type = ing1type;
			this.ing2letter = ing2letter;
			this.ing2key = ing2key;
			this.ing2type = ing2type;
			this.ing3letter = ing3letter;
			this.ing3key = ing3key;
			this.ing3type = ing3type;
			this.ing4letter = ing4letter;
			this.ing4key = ing4key;
			this.ing4type = ing4type;
			this.ing5letter = ing5letter;
			this.ing5key = ing5key;
			this.ing5type = ing5type;
			this.ing6letter = ing6letter;
			this.ing6key = ing6key;
			this.ing6type = ing6type;
			this.ing7letter = ing7letter;
			this.ing7key = ing7key;
			this.ing7type = ing7type;
			this.ing8letter = ing8letter;
			this.ing8key = ing8key;
			this.ing8type = ing8type;
			this.result = CraftingZone.getCorrectNameSpace(result.getFCRegistryName());
			this.quantity = quantity;
			if (result instanceof FCBlockAPI) {
				resulttype = "block";
			} else {
				resulttype = "item";
			}

		} else {
			System.out.println("Attempted to register a recipe with a null output, it will be discarded");
		}

	}

	public ModelNode get119ModelNode() {
		ModelNode node = new ModelNode();
		node.get("type").set("minecraft:crafting_shaped");

		if (!ing0letter.equals(" ")) {
			node.get("key").get(ing0letter).get(ing0type).set(ing0key); // This was easier than i though
		}
		if (!ing1letter.equals(" ")) {
			node.get("key").get(ing1letter).get(ing1type).set(ing1key); // This was easier than i though
		}
		if (!ing2letter.equals(" ")) {
			node.get("key").get(ing2letter).get(ing2type).set(ing2key); // This was easier than i though
		}
		if (!ing3letter.equals(" ")) {
			node.get("key").get(ing3letter).get(ing3type).set(ing3key); // This was easier than i though
		}
		if (!ing4letter.equals(" ")) {
			node.get("key").get(ing4letter).get(ing4type).set(ing4key); // This was easier than i though
		}
		if (!ing5letter.equals(" ")) {
			node.get("key").get(ing5letter).get(ing5type).set(ing5key); // This was easier than i though
		}
		if (!ing6letter.equals(" ")) {
			node.get("key").get(ing6letter).get(ing6type).set(ing6key); // This was easier than i though
		}
		if (!ing7letter.equals(" ")) {
			node.get("key").get(ing7letter).get(ing7type).set(ing7key); // This was easier than i though
		}
		if (!ing8letter.equals(" ")) {
			node.get("key").get(ing8letter).get(ing8type).set(ing8key); // This was easier than i though
		}

		node.get("pattern").add(ing0letter + ing1letter + ing2letter);
		node.get("pattern").add(ing3letter + ing4letter + ing5letter);
		node.get("pattern").add(ing6letter + ing7letter + ing8letter);

		node.get("result").get("count").set(quantity);
		node.get("result").get(resulttype).set(result);

		if (FeatureCreep.debug_mode) {
			System.out.println(node.toString());
		}

		return node;
	}

	public ModelNode get114ModelNode() {
		ModelNode node = new ModelNode();
		node.get("type").set("minecraft:crafting_shaped");

		node.get("pattern").add(ing0letter + ing1letter + ing2letter);
		node.get("pattern").add(ing3letter + ing4letter + ing5letter);
		node.get("pattern").add(ing6letter + ing7letter + ing8letter);

		if (!ing0letter.equals(" ")) {
			node.get("key").get(ing0letter).get(ing0type).set(ing0key); // This was easier than i though
		}
		if (!ing1letter.equals(" ")) {
			node.get("key").get(ing1letter).get(ing1type).set(ing1key); // This was easier than i though
		}
		if (!ing2letter.equals(" ")) {
			node.get("key").get(ing2letter).get(ing2type).set(ing2key); // This was easier than i though
		}
		if (!ing3letter.equals(" ")) {
			node.get("key").get(ing3letter).get(ing3type).set(ing3key); // This was easier than i though
		}
		if (!ing4letter.equals(" ")) {
			node.get("key").get(ing4letter).get(ing4type).set(ing4key); // This was easier than i though
		}
		if (!ing5letter.equals(" ")) {
			node.get("key").get(ing5letter).get(ing5type).set(ing5key); // This was easier than i though
		}
		if (!ing6letter.equals(" ")) {
			node.get("key").get(ing6letter).get(ing6type).set(ing6key); // This was easier than i though
		}
		if (!ing7letter.equals(" ")) {
			node.get("key").get(ing7letter).get(ing7type).set(ing7key); // This was easier than i though
		}
		if (!ing8letter.equals(" ")) {
			node.get("key").get(ing8letter).get(ing8type).set(ing8key); // This was easier than i though
		}

		node.get("result").get("count").set(quantity);
		node.get("result").get(resulttype).set(result);

		if (FeatureCreep.debug_mode) {
			System.out.println(node.toString());
		}

		return node;
	}

	public ModelNode get112ModelNode() {
		ModelNode node = get114ModelNode();
		node.get("type").set("crafting_shaped");
		return node;
	}

}
