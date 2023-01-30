package featurecreep.api.bg.items.datafied.dmr;

import org.jboss.dmr.ModelNode;

import dangerzone.items.Item;
import featurecreep.api.bg.items.FCItem;
import featurecreep.api.bg.registries.UniversalRegistryGettersAndSetters;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import featurecreep.api.bg.ui.tabs.vanilla.VanillaCreativeTab;
import featurecreep.api.parsers.ParseDMRItem;

public class FCItemAsDMR<FCItemAsDMR> implements DMRItem<FCItemAsDMR>

{
	
public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}
	UnifiedItemGroupGetter gettab;// Gotta add this to BlockOrItemAPI Holder
	
	public FCItemAsDMR(int id, String modid, String name, UnifiedItemGroupGetter group)
	{
	if (this instanceof ToolsAPI) {
		
	}else {
		initialise(id, modid, name,group);
	}
	
	gettab = group;
	
	}

	@Override
	public ModelNode toModelNode() {
		// TODO Auto-generated method stub
		ModelNode node = new ModelNode();
		node.get("type").set("generic_item");
		node.get("modid").set(getModId());
		node.get("item_name").set(getUnlocName());
		node.get("id").set(getNumberID());
        if(gettab instanceof VanillaCreativeTab) {
        	VanillaCreativeTab van = (VanillaCreativeTab)gettab;
        node.get("group").set(van.tabname);
        }else {
        	node.get("group").set(gettab.getTabName());	
        }
        
        return node;
        
	}

	
	@Override
	public Item get()
	{
ModelNode node = ParseDMRItem.getModelNodeFromDMRItem(this);
return new FCItem(node.get("id").asInt(), node.get("modid").asString(), node.get("item_name").asString(), UniversalRegistryGettersAndSetters.getFCItemGroupbyName(node.get("group").asString()));	
	}
	
	
	
	
}
