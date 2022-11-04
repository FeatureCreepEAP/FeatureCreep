package featurecreep.api.items.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.jboss.dmr.ModelNode;

import com.google.common.collect.Sets;

import featurecreep.api.items.FCItemAPI;
import featurecreep.api.ui.tabs.vanilla.VanillaCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class FCAxe extends ItemTool implements FCItemAPI
{
	public String public_modid;
	public String public_name;
	public int number_id;
	public CreativeTabs default_tab;
	public FCToolMaterial mat;
	public int damage;
	public int attackspeed;
	
	public FCAxe(int id, String modid, String name, CreativeTabs group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{
		super(EnumHelper.addToolMaterial(name, material.getToolHarvestLevel(), material.getToolMaxUses(), material.getToolEfficiency(), material.getToolAttackDamage(), material.getToolEnchantability()), EFFECTIVE_ON);
		public_modid = modid;
		public_name = name;
		registerModels(this);
		this.default_tab = group;
	this.number_id = id;
	this.mat = material;
	this.damage = attackDamage;
	attackspeed = attackSpeed;
	this.setCreativeTab(group);
		}

	public FCAxe(int id, String modid, String name, VanillaCreativeTab group, FCToolMaterial material, int attackDamage, int attackSpeed)
	{this(id, modid, name, VanillaCreativeTab.getVanillaGroupFromString(group), material, attackDamage, attackSpeed);}
		
		
		
	
			public void registerModels(Item item) {
		// TODO Auto-generated method stub
		//I could just do a long string but i will need to use this format for some other things so may as well start 
		ModelNode node = new ModelNode();
		node.get("parent").set("item/generated");
		node.get("textures").get("layer0").set(public_modid + ":items/" + public_name);
		
		System.out.print(node.toJSONString(false));
	 

	      try {
	 
	    		File myObj = new File(featurecreep.api.PackLoader.fc_pack_location+ "/assets/" + public_modid + "/models/item/" + public_name + ".json");
	    
	    	  	if (!myObj.exists()) {

	    		System.out.println(myObj.toString());
	    		myObj.getParentFile().mkdirs();
	    		
	    		
	    		FileWriter myWriter = new FileWriter(myObj);
	          myWriter.write(node.toJSONString(true));
			myWriter.close();

	    	  	}
			
			
	    		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	      
	      
	      
	      
	      
	}




		
		public String getModId() {
			// TODO Auto-generated method stub
			return public_modid;
		}




		
		public String getUnlocName() {
			// TODO Auto-generated method stub
			return public_name;
		}




		
		public int getNumberID() {
			// TODO Auto-generated method stub
			return number_id;
		}




		
		public CreativeTabs getDefaultCreativeTab() {
			// TODO Auto-generated method stub
			return default_tab;
		}
		
		
	
		public FCToolMaterial getFCToolMaterial()	{return mat;}
		public int getToolAttackDamage() {return damage;}
		public int getAttackSpeed() {return attackspeed;}
	
		
		
		
		
		
		
		
		


		private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
	    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};



	    public float getStrVsBlock(ItemStack stack, IBlockState state)
	    {
	        Material material = state.getMaterial();
	        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	    }
		
		
		
}
