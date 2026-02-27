package featurecreep.api.bg.items.armour;

// En FCArmourSlot.java
import net.minecraft.world.item.equipment.ArmorType;

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