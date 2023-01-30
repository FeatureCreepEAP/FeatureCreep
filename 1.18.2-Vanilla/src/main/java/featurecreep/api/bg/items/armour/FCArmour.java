package featurecreep.api.bg.items.armour;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class FCArmour extends ArmorItem implements FCItemAPI<FCArmour>{

	
public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}
	
		public FCArmourMaterial fcmaterial;
	public FCArmourSlot slot;
	
	
	public FCArmour(int id, String modid, String name, UnifiedItemGroupGetter group, FCArmourMaterial material, FCArmourSlot slot) {
		super(material, slot.getSlot(), new Item.Settings().group(group.get()));
		// TODO Auto-generated constructor stub
initialise( id,  modid,  name, group);
		setSlot(slot);
		setFCMaterial(material);

	
	}

	
public void setSlot(FCArmourSlot slot)
{
this.slot = slot;	
}
	
public FCArmourSlot getSlot()
{
	return slot;
}

public void setFCMaterial(FCArmourMaterial fcmaterial)
{
this.fcmaterial = fcmaterial;	
}
	
public FCArmourMaterial getFCMaterial()
{
	return fcmaterial;
}

	
}

