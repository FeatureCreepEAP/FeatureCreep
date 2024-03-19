package featurecreep.api.bg.items.tools.datafied.dmr;

import org.jboss.dmr.ModelNode;

import dangerzone.items.Item;
import featurecreep.api.bg.items.datafied.dmr.FCItemAsDMR;
import featurecreep.api.bg.items.tools.FCHoe;
import featurecreep.api.bg.items.tools.FCToolMaterial;
import featurecreep.api.bg.items.tools.ToolFieldHolder;
import featurecreep.api.bg.items.tools.ToolsAPI;
import featurecreep.api.bg.registries.UniversalRegistryGettersAndSetters;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.parsers.ParseDMRItem;
import featurecreep.content.FCItems;

public class FCHoeAsDMR extends FCItemAsDMR<FCHoeAsDMR> implements ToolsAPI<FCHoeAsDMR> {

	public ToolFieldHolder holder = new ToolFieldHolder();

	@Override
	public ToolFieldHolder holder() {
		return holder;
	}

	public FCHoeAsDMR(int id, String modid, String name, UnifiedItemGroupGetter group, FCToolMaterial material,
			int attackDamage, int attackSpeed) {
		super(id, modid, name, group);
		initialise(id, modid, name, group, material, attackDamage, attackSpeed);

	}

	@Override
	public ModelNode toModelNode() {
		ModelNode node = super.toModelNode();
		node.get("attack_damage").set(getToolAttackDamage());
		node.get("attack_speed").set(getAttackSpeed());
		node.get("material").get("max_uses").set(getFCToolMaterial().getToolMaxUses());
		node.get("material").get("efficiency").set(getFCToolMaterial().getToolEfficiency());
		node.get("material").get("attack_damage").set(getFCToolMaterial().getToolAttackDamage());
		node.get("material").get("harvest_level").set(getFCToolMaterial().getToolHarvestLevel());
		node.get("material").get("enchantability").set(getFCToolMaterial().getToolEnchantability());
		return node;
	}

	@Override
	public Item get() {
		ModelNode node = ParseDMRItem.getModelNodeFromDMRItem(this);
		return new FCHoe(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(),
				UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()),
				new FCToolMaterial(node.get("material").get("harvest_level").asInt(),
						node.get("material").get("max_uses").asInt(), node.get("material").get("efficiency").asInt(),
						node.get("material").get("attack_damage").asInt(),
						node.get("material").get("enchantability").asInt(), FCItems.AMETHYST),
				node.get("attack_damage").asInt(), node.get("attack_speed").asInt());
	}

}
