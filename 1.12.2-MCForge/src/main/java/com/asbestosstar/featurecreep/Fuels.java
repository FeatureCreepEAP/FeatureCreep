package com.asbestosstar.featurecreep;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class Fuels implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		// TODO Auto-generated method stub
		if(fuel.getItem() == FeatureCreepMC.Oil) //Item.appleRed.itemID being the item you are adding a fuel value to
			return 10 * 40; //10 * 20 because it is 20 ticks in a second * 10 for 10 seconds of fuel per apple
		if(fuel.getItem() == FeatureCreepMC.GASOLINE_PETROL) //Item.appleRed.itemID being the item you are adding a fuel value to
			return 10 * 40; //10 * 20 because it is 20 ticks in a second * 10 for 10 seconds of fuel per apple

		
		
		
		
		
		
		
		return 0;
	}

}
