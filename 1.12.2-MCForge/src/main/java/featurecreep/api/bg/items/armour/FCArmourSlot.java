package featurecreep.api.bg.items.armour;

import net.minecraft.inventory.EntityEquipmentSlot;

public class FCArmourSlot {

	//Warning on this file, it should not be used by modders in most cases you will get bytecode errors since the methods return different platform and version specfic objects
	
	String location;
	
	public FCArmourSlot(String value)
	{
	location = value;	
	}
	
	
	public EntityEquipmentSlot getSlot()
	{
		if (this.location.equals("HELMET"))
		{
			return EntityEquipmentSlot.HEAD;
		} else if (this.location.equals("TUBIC"))
		{
		return EntityEquipmentSlot.CHEST;	
		}else if (this.location.equals("LEGGINGS"))
		{
			return EntityEquipmentSlot.LEGS;
		}else if (this.location.equals("BOOTS"))
		{
			return EntityEquipmentSlot.FEET;
		}
		else
		{
		return EntityEquipmentSlot.HEAD; // Head is Default. I soon got to find a better way to register custom locations when i get more time	
			
		}
		
		
	}
	
	
	
}
