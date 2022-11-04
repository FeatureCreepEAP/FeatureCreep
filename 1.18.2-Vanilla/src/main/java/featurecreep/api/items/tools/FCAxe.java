package featurecreep.api.items.tools;

import featurecreep.api.items.FCItemAPI;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FCAxe extends AxeItem implements FCItemAPI
{
	public static String public_modid;
	public static String public_name;
	public static int number_id;
	public static ItemGroup default_tab;
	
	public FCAxe(int id, String modid, String name, ItemGroup group, FCToolMaterial material, int attackDamage, float attackSpeed)
	{
		super(material, attackDamage, attackSpeed, new Item.Settings().group(group));
		public_modid = modid;
		public_name = name;
		registerModels(this);
		this.default_tab = group;
		}

		
		
		
		@Override
		public void registerModels(Item item) {
			// TODO Auto-generated method stub

		}




		@Override
		public String getModId() {
			// TODO Auto-generated method stub
			return public_modid;
		}




		@Override
		public String getUnlocName() {
			// TODO Auto-generated method stub
			return public_name;
		}




		@Override
		public int getNumberID() {
			// TODO Auto-generated method stub
			return number_id;
		}




		@Override
		public ItemGroup getDefaultCreativeTab() {
			// TODO Auto-generated method stub
			return default_tab;
		}
		
		
	
	
	
	
}