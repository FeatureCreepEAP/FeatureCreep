package featurecreep.api.bg.items.armour;

import net.minecraft.world.item.ArmorItem.Type;

public class FCArmourSlot {

	// Warning on this file, it should not be used by modders in most cases you will
	// get bytecode errors since the methods return different platform and version
	// specfic objects

	String location;

	public FCArmourSlot(String value) {
		location = value;
	}

	public Type getSlot() {
		if (this.location.equals("HELMET")) {
			return Type.HELMET;
		} else if (this.location.equals("TUBIC")) {
			return Type.CHESTPLATE;
		} else if (this.location.equals("LEGGINGS")) {
			return Type.LEGGINGS;
		} else if (this.location.equals("BOOTS")) {
			return Type.BOOTS;
		} else {
			return Type.HELMET; // Head is Default. I soon got to find a better way to register custom
												// locations when i get more time

		}

	}

}