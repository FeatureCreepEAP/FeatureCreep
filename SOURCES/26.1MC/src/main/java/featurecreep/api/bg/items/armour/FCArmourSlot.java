package featurecreep.api.bg.items.armour;

import net.minecraft.world.item.equipment.ArmorType;

@Deprecated(forRemoval = true, since = "13")

public class FCArmourSlot {
	String location;

	public FCArmourSlot(String value) {
		location = value;
	}

	public ArmorType getSlot() {
		if (this.location.equals("HELMET")) {
			return ArmorType.HELMET;
		} else if (this.location.equals("TUBIC")) {
			return ArmorType.CHESTPLATE;
		} else if (this.location.equals("LEGGINGS")) {
			return ArmorType.LEGGINGS;
		} else if (this.location.equals("BOOTS")) {
			return ArmorType.BOOTS;
		} else {
			return ArmorType.HELMET;
		}
	}
}