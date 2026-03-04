package featurecreep.api.bg.items.armour;

import featurecreep.api.bg.items.FCItemAPI;
import featurecreep.api.bg.items.ItemFieldHolder;
import featurecreep.api.bg.ui.tabs.UnifiedItemGroupGetter;
import net.minecraft.world.item.ArmorItem;

@Deprecated(forRemoval = true, since = "13")

public class FCArmour extends ArmorItem implements FCItemAPI<FCArmour> {
	public ItemFieldHolder holder = new ItemFieldHolder();
	public FCArmourMaterial fcmaterial;
	public FCArmourSlot slot;

	@Override
	public ItemFieldHolder holder() {
		return this.holder;
	}

	public FCArmour(int id, String modid, String name, UnifiedItemGroupGetter group, FCArmourMaterial material,
			FCArmourSlot slot) {
		super(material.get(), slot.getSlot(), new Properties());
		this.initialise(id, modid, name, group);
		this.setSlot(slot);
		this.setFCMaterial(material);
	}

	public void setSlot(FCArmourSlot slot) {
		this.slot = slot;
	}

	public FCArmourSlot getSlot() {
		return this.slot;
	}

	public void setFCMaterial(FCArmourMaterial fcmaterial) {
		this.fcmaterial = fcmaterial;
	}

	public FCArmourMaterial getFCMaterial() {
		return this.fcmaterial;
	}
}