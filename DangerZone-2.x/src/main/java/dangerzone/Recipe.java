package dangerzone;

import dangerzone.blocks.Block;
import dangerzone.entities.Entity;
import dangerzone.items.Item;

/*
 * This code is copyright Richard H. Clark, TheyCallMeDanger, OreSpawn, 2015-2021.
 * You may use this code for reference for modding the DangerZone game program,
 * and are perfectly welcome to cut'n'paste portions for your mod as well.
 * DO NOT USE THIS CODE FOR ANY PURPOSE OTHER THAN MODDING FOR THE DANGERZONE GAME.
 * DO NOT REDISTRIBUTE THIS CODE. 
 * 
 *
 * 
 * WARNING: There are bugs. Big bugs. Little bugs. Every size in-between bugs.
 * This code is NOT suitable for use in anything other than this particular game. 
 * NO GUARANTEES of any sort are given, either express or implied, and Richard H. Clark, 
 * TheyCallMeDanger, OreSpawn are not responsible for any damages, direct, indirect, or otherwise. 
 * You should have made backups. It's your own fault for not making them.
 * 
 * NO ATTEMPT AT SECURITY IS MADE. This code is USE AT YOUR OWN RISK.
 * Regardless of what you may think, the reality is, that the moment you 
 * connected your computer to the Internet, Uncle Sam, among many others, hacked it.
 * DO NOT KEEP VALUABLE INFORMATION ON INTERNET-CONNECTED COMPUTERS.
 * Or your phone...
 * 
 */
	public class Recipe {
		public String ingredients[];
		public boolean ordered;
		public String outname;
		public int out_count;
		
		public Recipe(){
			ingredients = new String[9];
			for(int i=0;i<9;i++){
				ingredients[i] = null;
			}
			outname = null;
			out_count = 0;
			ordered = false;
		}
		
		//add custom recipe
		public  Recipe( Object i1, Object i2, Object i3,
				Object i4, Object i5, Object i6,
				Object i7, Object i8, Object i9,
				Object outthing, int count, boolean ordrd){
			
			ingredients = new String[9];
			for(int i=0;i<9;i++){
				ingredients[i] = null;
			}
			outname = null;
			out_count = 0;
			ordered = false;
			
			if(i1 instanceof Item)ingredients[0] = ((Item) i1).uniquename;
			if(i1 instanceof Block)ingredients[0] = ((Block) i1).uniquename;
			
			if(i2 instanceof Item)ingredients[1] = ((Item) i2).uniquename;
			if(i2 instanceof Block)ingredients[1] = ((Block) i2).uniquename;
			
			if(i3 instanceof Item)ingredients[2] = ((Item) i3).uniquename;
			if(i3 instanceof Block)ingredients[2] = ((Block) i3).uniquename;
			
			if(i4 instanceof Item)ingredients[3] = ((Item) i4).uniquename;
			if(i4 instanceof Block)ingredients[3] = ((Block) i4).uniquename;
			
			if(i5 instanceof Item)ingredients[4] = ((Item) i5).uniquename;
			if(i5 instanceof Block)ingredients[4] = ((Block) i5).uniquename;
			
			if(i6 instanceof Item)ingredients[5] = ((Item) i6).uniquename;
			if(i6 instanceof Block)ingredients[5] = ((Block) i6).uniquename;
			
			if(i7 instanceof Item)ingredients[6] = ((Item) i7).uniquename;
			if(i7 instanceof Block)ingredients[6] = ((Block) i7).uniquename;
			
			if(i8 instanceof Item)ingredients[7] = ((Item) i8).uniquename;
			if(i8 instanceof Block)ingredients[7] = ((Block) i8).uniquename;
			
			if(i9 instanceof Item)ingredients[8] = ((Item) i9).uniquename;
			if(i9 instanceof Block)ingredients[8] = ((Block) i9).uniquename;
			

			if(outthing instanceof Item)outname = ((Item) outthing).uniquename;
			if(outthing instanceof Block)outname = ((Block) outthing).uniquename;
			
			out_count = count;
			ordered = ordrd;
			
		}
		
		//override to make your own finish routine
		public void finishCrafting(Entity e){
			for(int i=0;i<9;i++){
				InventoryContainer ic = e.getCrafting(i);
				if(ic != null){						 
					ic.count--;
					if(ic.count <= 0){
						e.setCrafting(i, null);
					}else{
						e.setCraftingChanged(i);
					}

				}
			}
		}
		
		public boolean mystrcmp(String a, String b){
			if(a == null && b == null)return true;
			if(a == null && b != null)return false;
			if(a != null && b == null)return false;
			if(a.equals(b))return true;
			return false;
		}
		
		//override to make your own finder in your own recipe
		public boolean find(Entity e){
			return false;
		}
	
	}