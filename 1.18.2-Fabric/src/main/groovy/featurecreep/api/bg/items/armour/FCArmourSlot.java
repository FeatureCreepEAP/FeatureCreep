package featurecreep.api.bg.items.armour;

import net.minecraft.entity.EquipmentSlot;

public class FCArmourSlot {

	//Warning on this file, it should not be used by modders in most cases you will get bytecode errors since the methods return different platform and version specfic objects
	
	String location;
	
	public FCArmourSlot(String value)
	{
	location = value;	
	}
	
	
	public EquipmentSlot getSlot()
	{
		if (this.location.equals("HELMET"))
		{
			return EquipmentSlot.HEAD;
		} else if (this.location.equals("TUBIC"))
		{
		return EquipmentSlot.CHEST;	
		}else if (this.location.equals("LEGGINGS"))
		{
			return EquipmentSlot.LEGS;
		}else if (this.location.equals("BOOTS"))
		{
			return EquipmentSlot.FEET;
		}
		else
		{
		return EquipmentSlot.HEAD; // Head is Default. I soon got to find a better way to register custom locations when i get more time	
			
		}
		
		
	}
	
	
	
}
