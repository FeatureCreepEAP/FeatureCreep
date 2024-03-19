package featurecreep.api.bg.items.armour;

public class FCArmourSlot {

	// Warning on this file, it should not be used by modders in most cases you will
	// get bytecode errors since the methods return different platform and version
	// specfic objects

	String location;

	public FCArmourSlot(String value) {
		location = value;
	}

	public int getSlot() {
		if (this.location.equals("HELMET")) {
			return 0;
		} else if (this.location.equals("TUBIC")) {
			return 1;
		} else if (this.location.equals("LEGGINGS")) {
			return 2;
		} else if (this.location.equals("BOOTS")) {
			return 3;
		} else {
			return 0; // Head is Default. I soon got to find a better way to register custom locations
						// when i get more time

		}

	}

}
