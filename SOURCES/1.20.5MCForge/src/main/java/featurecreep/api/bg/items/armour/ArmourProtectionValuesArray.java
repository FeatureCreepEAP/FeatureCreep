package featurecreep.api.bg.items.armour;

@Deprecated(forRemoval = true, since = "13")

public class ArmourProtectionValuesArray {

	int helmet_protection;
	int chestplat_protection;
	int leggins_protection;
	int boots_protection;

	public ArmourProtectionValuesArray(int helmet, int chestplate, int leggings, int boots) {
		helmet_protection = helmet;
		chestplat_protection = chestplate;
		leggins_protection = leggings;
		boots_protection = boots;

	}

	public int getHelmetProtectionValue() {
		return helmet_protection;
	}

	public int getChestplateProtectionValue() {
		return chestplat_protection;
	}

	public int getLeggingsProtectionValue() {
		return leggins_protection;
	}

	public int getBootsProtectionValue() {
		return leggins_protection;
	}

}
