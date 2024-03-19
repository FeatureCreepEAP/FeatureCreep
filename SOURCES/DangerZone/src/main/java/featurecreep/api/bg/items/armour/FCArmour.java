package featurecreep.api.bg.items.armour;

import dangerzone.items.ItemArmor;
import featurecreep.FeatureCreep;
import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.ui.FCCreativeTab;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;

public class FCArmour extends ItemArmor implements FCItemAPI<FCArmour> {

	public featurecreep.api.bg.items.ItemFieldHolder holder = new featurecreep.api.bg.items.ItemFieldHolder();

	@Override
	public featurecreep.api.bg.items.ItemFieldHolder holder() {
		return holder;
	}

	public FCArmourMaterial fcmaterial;
	public FCArmourSlot slot;

	public FCArmour(int id, String modid, String name, UnifiedItemGroupGetter group, FCArmourMaterial material,
			FCArmourSlot slot) {
		super(modid + ":" + name,
				FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/" + modid + "/textures/items/" + name + ".png",
				FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/minecraft/textures/models/armor/" + name
						+ "_layer_1.png",
				FeatureCreep.gamepath + "/resourcepacks/fcpack_8/assets/minecraft/textures/models/armor/" + name
						+ "_layer_2.png",
				material.getFCProtection(slot), material.getFCDurability(), slot.getSlot());
		// TODO Auto-generated constructor stub
		initialise(id, modid, name, group);
		setSlot(slot);
		setFCMaterial(material);

	}

	public void setSlot(FCArmourSlot slot) {
		this.slot = slot;
	}

	public FCArmourSlot getSlot() {
		return slot;
	}

	public void setFCMaterial(FCArmourMaterial fcmaterial) {
		this.fcmaterial = fcmaterial;
	}

	public FCArmourMaterial getFCMaterial() {
		return fcmaterial;
	}

}
