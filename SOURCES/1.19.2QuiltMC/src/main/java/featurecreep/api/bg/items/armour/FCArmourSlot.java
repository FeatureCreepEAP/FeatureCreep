package featurecreep.api.bg.items.armour;

import game.DivisionDesigner;

public class FCArmourSlot {

	//Warning on this file, it should not be used by modders in most cases you will get bytecode errors since the methods return different platform and version specfic objects
	
	String location;
	
	public FCArmourSlot(String value)
	{
	location = value;	
	}
	
	
	public DivisionDesigner getSlot()
	{
		if (this.location.equals("HELMET"))
		{
			return DivisionDesigner.HEAD;
		} else if (this.location.equals("TUBIC"))
		{
		return DivisionDesigner.CHEST;	
		}else if (this.location.equals("LEGGINGS"))
		{
			return DivisionDesigner.LEGS;
		}else if (this.location.equals("BOOTS"))
		{
			return DivisionDesigner.FEET;
		}
		else
		{
		return DivisionDesigner.HEAD; // Head is Default. I soon got to find a better way to register custom locations when i get more time	
			
		}
		
		
	}
	
	
	
}
