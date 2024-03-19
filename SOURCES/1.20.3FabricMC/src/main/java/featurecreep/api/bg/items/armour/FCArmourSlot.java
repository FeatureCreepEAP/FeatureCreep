package featurecreep.api.bg.items.armour;

import game.Armour;

public class FCArmourSlot {

	// Warning on this file, it should not be used by modders in most cases you will
	// get bytecode errors since the methods return different platform and version
	// specfic objects

	String location;

	public FCArmourSlot(String value) {
		location = value;
	}

	public Armour.ArmourPeice getSlot() {
		if (this.location.equals("HELMET")) {
			return Armour.ArmourPeice.HELMET;
		} else if (this.location.equals("TUBIC")) {
			return Armour.ArmourPeice.TUNIC;
		} else if (this.location.equals("LEGGINGS")) {
			return Armour.ArmourPeice.LEGGINGS;
		} else if (this.location.equals("BOOTS")) {
			return Armour.ArmourPeice.BOOTS;
		} else {
			return Armour.ArmourPeice.HELMET; // Head is Default. I soon got to find a better way to register custom
												// locations when i get more time

		}

	}

}
