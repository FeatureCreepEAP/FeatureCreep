package featurecreep.legacy;


import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Fuels {
	  public static final Fuels instance = new Fuels();
	
   private Fuels() {
   }
//Thanks to the More Fuels Mod for Influence on how to do this.
   @SubscribeEvent
   public void onFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
      ItemStack fuelStack = event.getItemStack();
      Item fuel = fuelStack.getItem();
      int oil = 10*40;
      int gasonline_petrol = 10*40;
      
      if (fuel == FCItems.Oil.get()) {
         event.setBurnTime(oil);
      } 
      else if (fuel == FCItems.GASOLINE_PETROL.get()) {
          event.setBurnTime(gasonline_petrol);
       }

   }
}