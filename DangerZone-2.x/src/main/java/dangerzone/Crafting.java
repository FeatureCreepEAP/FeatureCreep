package dangerzone;
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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dangerzone.blocks.Block;
import dangerzone.entities.Entity;
import dangerzone.items.Item;



public class Crafting {
	
	public static  List<Recipe> recipies;
	
	public Crafting(){
		recipies = new ArrayList<Recipe>();
	}
	
	/*
	 * Yes, all recipe registration is done by UNIQUENAME, because itemIDs and blockIDs are indeterminate at registration time!
	 * In other words, itemIDs and blockIDs don't have a stable value until AFTER all registration is complete.
	 * 
	 * "ordered" specifies whether items can be in random places or not.
	 * 
	 */
	
	//add pre-existing recipe for special cases
	public static void registerCraftingRecipe( Recipe r){
		//add recipe to list!
		recipies.add(r);				
	}
	
	//add normal boring recipe
	public static void registerCraftingRecipe( Object i1, Object i2, Object i3,
			Object i4, Object i5, Object i6,
			Object i7, Object i8, Object i9,
			Object outthing, int count, boolean ordered){
		
		Recipe r = new Recipe();
		if(count <= 0 || count > 64)return;
		if(outthing == null)return;
		
		if(i1 instanceof Item)r.ingredients[0] = ((Item) i1).uniquename;
		if(i1 instanceof Block)r.ingredients[0] = ((Block) i1).uniquename;
		
		if(i2 instanceof Item)r.ingredients[1] = ((Item) i2).uniquename;
		if(i2 instanceof Block)r.ingredients[1] = ((Block) i2).uniquename;
		
		if(i3 instanceof Item)r.ingredients[2] = ((Item) i3).uniquename;
		if(i3 instanceof Block)r.ingredients[2] = ((Block) i3).uniquename;
		
		if(i4 instanceof Item)r.ingredients[3] = ((Item) i4).uniquename;
		if(i4 instanceof Block)r.ingredients[3] = ((Block) i4).uniquename;
		
		if(i5 instanceof Item)r.ingredients[4] = ((Item) i5).uniquename;
		if(i5 instanceof Block)r.ingredients[4] = ((Block) i5).uniquename;
		
		if(i6 instanceof Item)r.ingredients[5] = ((Item) i6).uniquename;
		if(i6 instanceof Block)r.ingredients[5] = ((Block) i6).uniquename;
		
		if(i7 instanceof Item)r.ingredients[6] = ((Item) i7).uniquename;
		if(i7 instanceof Block)r.ingredients[6] = ((Block) i7).uniquename;
		
		if(i8 instanceof Item)r.ingredients[7] = ((Item) i8).uniquename;
		if(i8 instanceof Block)r.ingredients[7] = ((Block) i8).uniquename;
		
		if(i9 instanceof Item)r.ingredients[8] = ((Item) i9).uniquename;
		if(i9 instanceof Block)r.ingredients[8] = ((Block) i9).uniquename;
		

		if(outthing instanceof Item)r.outname = ((Item) outthing).uniquename;
		if(outthing instanceof Block)r.outname = ((Block) outthing).uniquename;
		

		r.out_count = count;
		r.ordered = ordered;
		
		//add recipe to list!
		recipies.add(r);		
		
	}
	
	public static boolean mystrcmp(String a, String b){
		if(a == null && b == null)return true;
		if(a == null && b != null)return false;
		if(a != null && b == null)return false;
		if(a.equals(b))return true;
		return false;
	}
	
	public static Recipe find(Entity e){
		String i1 = e.getCrafting(0)==null?null:e.getCrafting(0).getUniqueName();
		String i2 = e.getCrafting(1)==null?null:e.getCrafting(1).getUniqueName();
		String i3 = e.getCrafting(2)==null?null:e.getCrafting(2).getUniqueName();
		String i4 = e.getCrafting(3)==null?null:e.getCrafting(3).getUniqueName();
		String i5 = e.getCrafting(4)==null?null:e.getCrafting(4).getUniqueName();
		String i6 = e.getCrafting(5)==null?null:e.getCrafting(5).getUniqueName();
		String i7 = e.getCrafting(6)==null?null:e.getCrafting(6).getUniqueName();
		String i8 = e.getCrafting(7)==null?null:e.getCrafting(7).getUniqueName();
		String i9 = e.getCrafting(8)==null?null:e.getCrafting(8).getUniqueName();
		Iterator<Recipe> ii = recipies.iterator();
		Recipe r;
		
		while(ii.hasNext()){
			r = ii.next();
			//give recipe itself a chance to find. Default = false!
			if(r.find(e)){
				return r;
			}
			if(r.ordered){
				if(!mystrcmp(i1, r.ingredients[0]))continue;
				if(!mystrcmp(i2, r.ingredients[1]))continue;
				if(!mystrcmp(i3, r.ingredients[2]))continue;
				if(!mystrcmp(i4, r.ingredients[3]))continue;
				if(!mystrcmp(i5, r.ingredients[4]))continue;
				if(!mystrcmp(i6, r.ingredients[5]))continue;
				if(!mystrcmp(i7, r.ingredients[6]))continue;
				if(!mystrcmp(i8, r.ingredients[7]))continue;
				if(!mystrcmp(i9, r.ingredients[8]))continue;
				//match!
				return r;
			}else{
				int i;
				boolean allfound = true;
				boolean used[] = new boolean [9];
				for(i=0;i<9;i++)used[i] = false;
				for(i=0;i<9;i++){					
					if(!used[0] && mystrcmp(r.ingredients[i], i1)){used[0] = true; continue;};
					if(!used[1] && mystrcmp(r.ingredients[i], i2)){used[1] = true; continue;};
					if(!used[2] && mystrcmp(r.ingredients[i], i3)){used[2] = true; continue;};
					if(!used[3] && mystrcmp(r.ingredients[i], i4)){used[3] = true; continue;};
					if(!used[4] && mystrcmp(r.ingredients[i], i5)){used[4] = true; continue;};
					if(!used[5] && mystrcmp(r.ingredients[i], i6)){used[5] = true; continue;};
					if(!used[6] && mystrcmp(r.ingredients[i], i7)){used[6] = true; continue;};
					if(!used[7] && mystrcmp(r.ingredients[i], i8)){used[7] = true; continue;};
					if(!used[8] && mystrcmp(r.ingredients[i], i9)){used[8] = true; continue;};
					
					allfound = false;
				}
				if(allfound)return r;
			}
		}		
		return null;
	}

}
