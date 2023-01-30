package featurecreep.api.items.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jboss.dmr.ModelNode;

import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class FCPickaxe extends ItemPickaxe implements FCItemAPI<FCPickaxe>
{
	

	public ToolFieldHolder holder = new ToolFieldHolder();
	@Override	public ToolFieldHolder holder() {	return holder;	}
	
	

	
	public FCPickaxe(int id, String modid, String name, CreativeTabs group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(EnumHelper.addToolMaterial(name, material.getToolHarvestLevel(), material.getToolMaxUses(), material.getToolEfficiency(), material.getToolAttackDamage(), material.getToolEnchantability()));
initialise(id,modid,name, group,material,attackDamage,attackSpeed);
		}

	public FCPickaxe(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, attackDamage, attackSpeed);}
		
		
		public FCToolMaterial getFCToolMaterial()	{return mat;}
		public int getToolAttackDamage() {return damage;}
		public int getAttackSpeed() {return attackspeed;}
	
}
