package featurecreep.api.bg.items.armour;

import featurecreep.api.EnumModder;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import game.Armour;
import game.Armour.class_unknown_18329;

public class FCArmour extends Armour implements FCItemAPI<FCArmour>{

	
	
public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();
@Override public featurecreep.api.bg.items.ItemFieldHolder holder() {	return holder;	}
	
	public FCArmourMaterial fcmaterial;
	public FCArmourSlot slot;
	
	public FCArmour(int id, String modid, String name, UnifiedItemGroupGetter group, FCArmourMaterial material, FCArmourSlot slot) {
		super((class_unknown_18329)EnumModder.newEnumInstance(class_unknown_18329.class, name, material.getFCName(), material.getFCDurability(), new int[] {material.getFCProtection(slot), material.getFCProtection(slot),material.getFCProtection(slot) ,material.getFCProtection(slot)}, material.getFCEnchantability(), material.getEquipSound(), material.getFCToughness()), material.getFCTextureNumber(slot), slot.getSlot());
		// TODO Auto-generated constructor stub
		initialise( id,  modid,  name, group);
		setSlot(slot);
		setFCMaterial(material);
	this.setCreativeTab(group.get());
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
