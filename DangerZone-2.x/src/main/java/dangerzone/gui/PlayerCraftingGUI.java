package dangerzone.gui;
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
import java.util.StringTokenizer;


import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;



import dangerzone.Crafting;
import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.Recipe;
import dangerzone.TextureMapper;
import dangerzone.blocks.Blocks;

import dangerzone.items.Items;


public class PlayerCraftingGUI extends GuiInterface {
	

	List<MyCraftingHandler> crafting_buttons;
	
	public InventoryContainer crafted;
	public InventoryContainer last_crafted;
	public boolean shifted = false;
	public String isearch = "";
	public boolean isearchinput = false;
	public int curidx = 0;
	public int crafting;

	
	
	private class MyButtonHandler extends ButtonHandler {
		
		public int ih; //inventory or hotbar
		public int index; //index into above
		public float transp;
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int j, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			ih = i;
			index = j;
		}
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, InventoryContainer ic, int i, int j, int bid){
			super(xpos, ypos, bxsize, bysize, ic, bid);
			ih = i;
			index = j;
		}
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, float tr, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			ih = i;
			transp = tr;
		}
		
		public void leftclickhandler(){

			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		
			
			if(ih >= 20 && ih <= 29){
				curidx = ih-20;
				return;
			}
			
			//System.out.printf("Left in %d,  %d\n", ih, index);
			if(ih == 9 || ih == 10 || ih == 11 || ih == 12){
				ClickedArmor(index, 0, shifted);
				return;
			}
						
			if(ih == 6){ //delete!
				DeleteMouseBite();
				return;
			}
			
			if(ih == -2){ //delete!
				ImAllDone();
				return;
			}
			
			if(ih < 0 || index < 0){
				SpitMouseBite();
				return;
			}
			
			if(ih == 1){
				ClickedInventory(index, 0, shifted);
			}
			if(ih == 0){
				ClickedHotBar(index, 0, shifted);
			}
		}
		public void middleclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			//just for quick delete for now...
			if(ih == 1){				
				ClickedInventory(index, 2, false);
			}
			if(ih == 9 || ih == 10 || ih == 11 || ih == 12){
				ClickedArmor(index, 2, false);
			}
			if(ih == 0){
				ClickedHotBar(index, 2, false);
			}

		}
		public void rightclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(ih == 1){
				ClickedInventory(index, 1, shifted);
			}
			if(ih == 0){
				ClickedHotBar(index, 1, shifted);
			}
		}
		
		public void draw(){
			
			if(ic != null){
				super.draw();
			}else{
				if(t != null){
					if(transp != 0)GL11.glColor4f(1,1,1, transp);
					else GL11.glColor4f(1,1,1,1);
					drawRectangleWithTexture(t, x, y, xsize, ysize);

				}
				if(s != null && !s.equals("")){
					textAt(x+6, y, s);
					GL11.glColor4f(1,1,1,1); //because text messes this up!
				}
			}
		}
		
	}
	
	private class MyCraftingHandler extends ButtonHandler {
		
		public int ih; //crafter or crafted
		public int index; //index into above
		
		MyCraftingHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int j, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			ih = i;
			index = j;
		}
		
		MyCraftingHandler(int xpos, int ypos, int bxsize, int bysize, InventoryContainer ic, int i, int j, int bid){
			super(xpos, ypos, bxsize, bysize, ic, bid);
			ih = i;
			index = j;
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			//System.out.printf("Left in %d,  %d\n", ih, index);
			if(ih == 1){
				if(crafting < 60)return;
				ClickedCrafted(0, shifted);	
			}else{
				ClickedCrafting(index, 0, shifted);
			}	
		}
		public void rightclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			//System.out.printf("Right in %d,  %d\n", ih, index);
			//try picking up half
			if(ih == 1){	
				if(crafting < 60)return;
				ClickedCrafted(1, shifted);
			}else{
				ClickedCrafting(index, 1, shifted);
			}
		}
	}
	
	public PlayerCraftingGUI(){
		super();
		buttons = null;
		mousebite = null;
		crafted = null;
		last_crafted = null;
		crafting = 0;
		isearch = "";

	}

	/*
	 * This is the callback from main to process the GUI.
	 * 
	 */
	public void process(){
		Texture woodtexture = null;
		Texture axetexture = null;
		Texture invtx = null;
		Texture hottx = null;
		Texture deletetexture = null;
		Texture tosstexture = null;
		Texture craft1texture = null;
		Texture craft9texture = null;
		Texture helmettexture = null;
		Texture leggingstexture = null;
		Texture chestplatetexture = null;
		Texture bootstexture = null;
		Texture textinputtexture = null;
		Texture backtexture = null;
		int i, row, col;
	
		int cellsize = 75;
		ButtonHandler mb = null;
		ButtonHandler fb = null;
		
		int mod = 10;

		
		invtx = TextureMapper.getTexture("res/menus/playerinventory.png");
		hottx = TextureMapper.getTexture("res/menus/playerhotbar.png");
		//buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");
		deletetexture = TextureMapper.getTexture("res/menus/"+"deletebutton.png");
		tosstexture = TextureMapper.getTexture("res/menus/"+"tossbutton.png");
		craft1texture = TextureMapper.getTexture("res/menus/"+"craft1.png");
		craft9texture = TextureMapper.getTexture("res/menus/"+"craft9.png");
		helmettexture = TextureMapper.getTexture("res/menus/"+"invhelmet.png");
		chestplatetexture = TextureMapper.getTexture("res/menus/"+"invchestplate.png");
		leggingstexture = TextureMapper.getTexture("res/menus/"+"invleggings.png");
		bootstexture = TextureMapper.getTexture("res/menus/"+"invboots.png");
		woodtexture = TextureMapper.getTexture("res/blocks/plywood.png");
		axetexture = TextureMapper.getTexture("res/items/copperaxe.png");
		textinputtexture = TextureMapper.getTexture("res/menus/"+"textinput.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		
		
		if(K_isKeyDown(Keyboard.KEY_LSHIFT) || K_isKeyDown(Keyboard.KEY_RSHIFT)){
			shifted = true;
		}else{
			shifted = false;
		}
		
		startx -= 25;
		starty = top_of_display - 300;
		starty += 50;
		
		GL11.glColor3f(1,1,1); //because text messes this up!
		
		//OpenGL has some quirks...
		drawRectangleWithTexture(invtx, (startx-5), (starty-205), 750, 375);

		//Build the button list... yeah... i know... would be nice to not recreate it every time...
		//but they do have a tendency to change dynamically, so we don't...
		buttons = new ArrayList<ButtonHandler>();
		crafting_buttons = new ArrayList<MyCraftingHandler>();

		for(i=0;i<50;i++){
			row = i/mod;
			col = i%mod;
			buttons.add(new MyButtonHandler((int)((startx+cellsize*col)), (int)((starty-row*cellsize+100)), (int)(60), (int)(60), DangerZone.player.getInventory(i), 1, i, 1000+i)); //1 = inventory
		}

		//add hotbar buttons...
		starty -= 300;
		drawRectangleWithTexture(hottx, (startx-5), (starty-10), 750, 75);
		for(i=0;i<10;i++){
			row = 0;
			col = i;
			buttons.add(new MyButtonHandler((int)((startx+cellsize*col)), (int)((starty-row*cellsize)), (int)(60), (int)(60), DangerZone.player.getHotbar(i), 0, i, 2000+i)); //0 = hotbar
		}
		

		//add armor buttons!
		starty -= 100;
		drawRectangleWithTexture(helmettexture, (startx-5), (starty-5), 75, 75);
		buttons.add(new MyButtonHandler((int)((startx)), (int)((starty)), (int)(60), (int)(60), DangerZone.player.getArmor(0), 9, 0, 3000));
		showArmorValue(0, startx+5, starty-27);

		drawRectangleWithTexture(chestplatetexture, (startx+70), (starty-5), 75, 75);
		buttons.add(new MyButtonHandler((int)((startx+75)), (int)((starty)), (int)(60), (int)(60), DangerZone.player.getArmor(1), 10, 1, 3001));
		showArmorValue(1, startx+80, starty-27);

		drawRectangleWithTexture(leggingstexture, (startx+145), (starty-5), 75, 75);
		buttons.add(new MyButtonHandler((int)((startx+150)), (int)((starty)), (int)(60), (int)(60), DangerZone.player.getArmor(2), 11, 2, 3002));
		showArmorValue(2, startx+155, starty-27);

		drawRectangleWithTexture(bootstexture, (startx+220), (starty-5), 75, 75);
		buttons.add(new MyButtonHandler((int)((startx+225)), (int)((starty)), (int)(60), (int)(60), DangerZone.player.getArmor(3), 12, 3, 3003));
		showArmorValue(3, startx+230, starty-27);
						
		
		//Normal 9-square crafting!
		starty = top_of_display - 300;
		starty += 200;
		startx = middle + 75;
		
		
		drawRectangleWithTexture(craft9texture, (startx-2), (starty-225), 225, 225);
		drawRectangleWithTexture(craft1texture, (startx+260), (starty-75), 75, 75);
	

		//Now go back and fill in buttons
		for(i=0;i<9;i++){
			row = i/3;
			col = i%3;
			crafting_buttons.add(new MyCraftingHandler((int)((startx+cellsize*col+5)), (int)((starty-row*cellsize-64)), (int)(60), (int)(60), DangerZone.player.getCrafting(i), 0, i, 4000+i)); //0 = crafter
		}
		
		crafted = DangerZone.player.getCrafted();
		if(last_crafted != crafted){
			crafting = 1;
			last_crafted = crafted;
		}
		if(last_crafted == null)crafting = 0;
		
		if(crafting > 0){			
			crafting++;
		}
		
		if(!DangerZone.crafting_animation)crafting = 61;
		
		if(crafting >= 60){
			if(crafted != null)buttons.add(new MyCraftingHandler((int)((startx+265)), (int)((starty-65)), (int)(60), (int)(60), crafted, 1, 0, 5000)); //1 = crafted
			crafting = 61;
		}else{
			if(crafting > 0){
				drawRectangleWithTexture(woodtexture, (startx+265), (starty-65), 60, 60);
				drawRectangleWithTextureTwo(axetexture, (startx+270+(crafting%40)), (starty-20), 60, 60, (crafting%20)-10, 0.5f);
			}
		}
		
		
		
		starty -= 50;		
		startx = middle;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 5003)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 5002)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 5001)); //delete button
		
	


		starty -= 300;		
		
		
		textAt((int)((startx+10)), (int)((starty-235)), "Search:");	
		GL11.glColor3f(1,1,1); //because text messes this up!

		buttons.add(new MyButtonHandler((int)((startx+10)), (int)((starty-260)), 150, 25, textinputtexture, isearch, 17, isearchinput?1.0f:0.5f, 5004));
		
		//add search items list...
		drawRectangleWithTexture(hottx, (startx-5), (starty-340), 750, 75);
		
		InventoryContainer icr;		
		Iterator<Recipe> ii = Crafting.recipies.iterator();
		Recipe r = null;
		Recipe rsave = null;
		Recipe rprev = null;
		i = 0;
		while(i < 10){
			
			if(!ii.hasNext())break;
			r = ii.next();
			if(r.outname == null)continue;
			if(!matches(r.outname, isearch))continue;
			if(is_same(rprev, r)){
				continue;
			}
			
			icr = new InventoryContainer(r.outname, 1);			
			row = 0;
			col = i;
			buttons.add(new MyButtonHandler((int)((startx+cellsize*col)), (int)((starty-row*cellsize-335)), 60, 60, icr, 20+i, 0, 6000+i)); 
			if(i == curidx){
				rsave = r;
				drawRectangleWithTexture(textinputtexture, (int)(((startx)+cellsize*col+2)), (int)(((starty)-row*cellsize-335)), 60, 60);
			}
			i++;
			rprev = r;
		}
		

		startx += 495;
		drawRectangleWithTexture(craft9texture, (startx-2), (starty-225), 225, 225);

		if(rsave != null){
			//Now go back and fill in buttons
			for(i=0;i<9;i++){
				if(rsave.ingredients[i] == null)continue;
				icr = new InventoryContainer(rsave.ingredients[i], 1);
				row = i/3;
				col = i%3;
				buttons.add(new MyButtonHandler((int)((startx+cellsize*col+5)), (int)((starty-row*cellsize-65)), 60, 60, icr, -99, 0, 7000+i)); 
			}
		}

				
		non_std_draw( crafting_buttons);		//Draw buttons!
		
		Iterator<MyCraftingHandler> bc = crafting_buttons.iterator();
		
		

		Iterator<ButtonHandler> bb = buttons.iterator();		
		
		
			//Check for mouse EVENTS on our button list!
			while(M_next()){
				
				float x = M_getEventX();
				float y = M_getEventY();
				x /= scalex;
				y /= scaley;
				clickx = (int)x;
				clicky = (int)y;
				
				if(!M_getEventButtonState()){ //Mouse button UP!				
					// 0 = left, 1 = right, 2 = middle
					if(M_getEventButton() == 0){	
						SpreadListClear();
					}
				}else{ //mouse button down
					if(M_getEventButton() >= 0){ //clicked!				
						//Find which "button" they clicked on
						//System.out.printf("down\n");
						bb = buttons.iterator();
						fb = null;
						while(bb.hasNext()){
							mb = bb.next();
							if(clickx >= mb.x && clickx <= mb.x+mb.xsize){
								if(clicky >= mb.y && clicky <= mb.y+mb.ysize){
									fb = mb;
									break;
								}
							}
						}
						// 0 = left, 1 = right, 2 = middle
						if(M_getEventButton() == 0){												
							if(fb != null){
								fb.leftclickhandler();
								SpreadListClear();
							}
						}
						if(M_getEventButton() == 1){
							if(fb != null){
								fb.rightclickhandler();
								SpreadListClear();
							}						
						}
						if(M_getEventButton() == 2){
							if(fb != null){
								fb.middleclickhandler();
								SpreadListClear();
							}						
						}

						/* crafting buttons */					

						bc = crafting_buttons.iterator();
						fb = null;
						while(bc.hasNext()){
							mb = bc.next();
							if(clickx >= mb.x && clickx <= mb.x+mb.xsize){
								if(clicky >= mb.y && clicky <= mb.y+mb.ysize){
									fb = mb;
									break;
								}
							}
						}
						if(fb != null){	
							// 0 = left, 1 = right, 2 = middle
							if(M_getEventButton() == 0){						
								MyCraftingHandler mch = (MyCraftingHandler)fb;
								SpreadListAdd(mch.index, shifted?1:0, 1); //down
								DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

							}
							if(M_getEventButton() == 1){
								if(fb != null){
									fb.rightclickhandler();
								}
								SpreadListClear();
							}
						}else{
							SpreadListClear();
						}

					}

				}
			}



		
		
		//handle mouse CURRENT position.
		
		//show some descriptive text and handle spreading!
		
		
			int cx, cy;
			float x = M_getCurX();
			float y = M_getCurY();
			x /= scalex;
			y /= scaley;
			cx = (int)x;
			cy = (int)y;
			
			bb = buttons.iterator();
			fb = null;
			isearchinput = false;
			while(bb.hasNext()){
				mb = bb.next();
				if(cx >= mb.x && cx <= mb.x+mb.xsize){
					if(cy >= mb.y && cy <= mb.y+mb.ysize){
						InventoryContainer ic = mb.ic;
						if(ic != null){
							String hotstring = null;
							String[] hss = null;
							if(ic.bid != 0){
								hotstring = Blocks.getUniqueName(ic.bid);
							}
							if(ic.iid != 0){
								hotstring = Items.getUniqueName(ic.iid);
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									DangerZone.hotmessagestring = hss[1];
									DangerZone.hotmessagetimer = 10;
								}
							}
						}else{
							if(mb instanceof MyButtonHandler){
								MyButtonHandler mbh = (MyButtonHandler)mb;
								if(mbh.ih == 17){
									isearchinput = true;
								}							
							}
						}
					}
				}
			}


			
			x = M_getCurX();
			y = M_getCurY();
			x /= scalex;
			y /= scaley;
			cx = (int)x;
			cy = (int)y;
			
			bc = crafting_buttons.iterator();
			MyCraftingHandler mch = null;
			while(bc.hasNext()){
				mch = bc.next();
				if(cx >= mch.x && cx <= mch.x+mch.xsize){
					if(cy >= mch.y && cy <= mch.y+mch.ysize){
						InventoryContainer ic = mch.ic;
						if(ic != null){
							String hotstring = null;
							String[] hss = null;
							if(ic.bid != 0){
								hotstring = Blocks.getUniqueName(ic.bid);
							}
							if(ic.iid != 0){
								hotstring = Items.getUniqueName(ic.iid);
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									DangerZone.hotmessagestring = hss[1];
									DangerZone.hotmessagetimer = 10;
								}
							}
						}else{
							SpreadListAdd(mch.index, shifted?1:0, 2); //drag
						}
					}
				}
			}
	

		
		

			if(isearchinput){
				String si = getTextChar();
				if(escaped){
					ImAllDone();
					return;
				}
				if(si != null){
					if(!si.equals("delete")){
						//add a new char
						if(isearch.length() < 16){
							isearch += si;
						}
					}else{
						//delete the last char
						if(isearch.length() > 0){
							String newstring = new String();
							for(i=0;i<isearch.length()-1;i++){
								newstring += isearch.charAt(i);
							}
							isearch = newstring;
						}
					}
				}

			}else{
				//Check for exit
				while(K_next()){
					if (K_getEventKey() == Keyboard.KEY_E && K_isKeyDown(Keyboard.KEY_E)){
						ImAllDone();
						return;
					}
					if (K_getEventKey() == Keyboard.KEY_ESCAPE && K_isKeyDown(Keyboard.KEY_ESCAPE)){
						ImAllDone();
						return;
					}
				}
			}
	}
		
	
	
	public boolean matches(String checkthis, String containsthis){
		if(containsthis == null || containsthis.length() == 0)return true;
		if(checkthis == null || checkthis.length() == 0)return true;
		
		StringTokenizer st = new StringTokenizer(containsthis);
		while(st.hasMoreTokens()){
			String thisone = st.nextToken();
			if(!checkthis.toLowerCase().contains(thisone.toLowerCase()))return false;
		}		
		return true;
	}
	
	//is PROBABLY same or equivalent. Not perfect.
	public boolean is_same(Recipe first, Recipe second){
		if(first == null || second == null)return false;
		if(first.outname == null || second.outname == null)return false;
		if(first.ordered != second.ordered)return false;
		if(first.out_count != second.out_count)return false;
		if(!first.outname.equals(second.outname))return false;
		int firstcount = 0;
		int secondcount = 0;
		int i, j;
		boolean found;
		
		//compare counts
		for(i=0;i<9;i++){
			if(first.ingredients[i] != null)firstcount++;
			if(second.ingredients[i] != null)secondcount++;
		}
		if(firstcount != secondcount)return false;
		
		//search that each at least has the others ingredients
		for(i=0;i<9;i++){
			found = false;
			if(first.ingredients[i] == null)continue;
			for(j=0;j<9;j++){
				if(second.ingredients[j] == null)continue;
				if(first.ingredients[i].equals(second.ingredients[j])){
					found=true;
					break;
				}
			}			
			if(!found)return false;
		}
		return true;
	}
	
	
	public void ImAllDone(){
		
		ClearTable();
		
		mousebite = null;
		crafted = null;
		last_crafted = null;
		isearch = "";
		isearchinput = false;
		
		super.ImAllDone();
	}
	
	
	public int non_std_draw(List<MyCraftingHandler>  cb){
		Texture tx = null;
		String s = null;
		//Draw buttons!
		clickx = 0;
		clicky = 0;
		int high_button = findHighlightedButton( cb);

		//Draw buttons!
		Iterator<ButtonHandler> bb = buttons.iterator();
		ButtonHandler thisbutton = null;
		float diff = 0.55f;
		diff = 0.75f;
		while(bb.hasNext()){
			thisbutton = bb.next();
			thisbutton.draw(thisbutton.buttonid==high_button?1.0f:diff);
		}
		Iterator<MyCraftingHandler> bbc = cb.iterator();
		while(bbc.hasNext()){
			thisbutton = bbc.next();
			thisbutton.draw(thisbutton.buttonid==high_button?1.0f:diff);
		}

		//Draw the things under the mouse
		mousebite = DangerZone.player.getMouseBite();
		
		
			if(mousebite != null){	
				float x = M_getCurX();
				float y = M_getCurY();
				x /= scalex;
				y /= scaley;
				
				tx = mousebite.getTexture();
				s = null;
				if(mousebite.count > 1){
					s = String.format("%d", mousebite.count);
				}
				if(tx != null){
					GL11.glColor3f(1,1,1);
					drawRectangleWithTexture(tx, x-24, y-24, 48, 48);
				}
				if(s != null && !s.equals("")){
					textAt( x-18, y-24, s);
					GL11.glColor3f(1,1,1); //because text messes this up!
				}
			}

		
		return high_button;
	}
	
	public int findHighlightedButton( List<MyCraftingHandler>  cb) {

	
			//Highlight only when cursor on top of button
			float x = M_getCurX();
			float y = M_getCurY();
			x /= scalex;
			y /= scaley;
			clickx = (int)x;
			clicky = (int)y;
			
			Iterator<ButtonHandler> bb = buttons.iterator();
			ButtonHandler thisbutton = null;

			while(bb.hasNext()){
				thisbutton = bb.next();
				if(clickx >= thisbutton.x && clickx <= thisbutton.x+thisbutton.xsize){
					if(clicky >= thisbutton.y && clicky <= thisbutton.y+thisbutton.ysize){
						return thisbutton.buttonid;
					}
				}
			}

		return -1;
	}

}
