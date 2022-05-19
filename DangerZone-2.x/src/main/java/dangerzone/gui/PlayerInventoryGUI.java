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
import java.util.StringTokenizer;


import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;


import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.blocks.Blocks;
import dangerzone.items.Items;



public class PlayerInventoryGUI extends GuiInterface {
		
	public InventoryContainer crafter[]; 
	public InventoryContainer crafted;
	public boolean shifted = false;
	public int start_offset = 0;
	public boolean middle_down = false;
	public String isearch = "";
	public boolean isearchinput = false;

	private int filter = InventoryMenus.GENERIC;
	private int dismode = 0;
	
	
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
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, float tr, int j, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			ih = i;
			transp = tr;
			index = j;
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
		
		public void leftclickhandler(){
			/*
			System.out.printf("Left in %d,  %d\n", ih, index);
			*/
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(ih == 9 || ih == 10 || ih == 11 || ih == 12){
				ClickedArmor(index, 0, shifted);
				return;
			}
									
			if(ih == 7){
				start_offset += 100;
				return;
			}
			if(ih == 8){
				start_offset -= 100;
				if(start_offset < 0)start_offset = 0;
				return;
			}
			if(ih == 4){
				dismode = 0;//blocks
				start_offset = 0;
				filter = InventoryMenus.GENERIC;
				return;
			}
			if(ih == 5){
				dismode = 1;//items
				start_offset = 0;
				filter = InventoryMenus.GENERIC;
				return;
			}
			if(ih == 13){
				dismode = 1;//items
				start_offset = 0;
				filter = InventoryMenus.SPAWNEGG;
				return;
			}
			if(ih == 14){
				dismode = 0;//blocks
				start_offset = 0;
				filter = InventoryMenus.SPAWNER;
				return;
			}
			if(ih == 15){
				dismode = 1;//items
				start_offset = 0;
				filter = InventoryMenus.TROPHY;
				return;
			}
			if(ih == 16){
				dismode = 1;//items
				start_offset = 0;
				filter = InventoryMenus.HARDWARE;
				return;
			}
			if(ih == 6){ //delete!
				DeleteMouseBite();
				return;
			}
			if(ih == 2){
				//blocks
				ClickedCreativeInventory(0, index, shifted);
				return;
			}
			if(ih == 3){
				//items
				ClickedCreativeInventory(index, 0, shifted);
				return;
			}
			
			if(ih == -2){
				ImAllDone();
				DangerZone.clearActiveGui();
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
		public void rightclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(ih == 1){
				ClickedInventory(index, 1, shifted);
			}else{
				ClickedHotBar(index, 1, shifted);
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

			if(ih == 9){					
				ClickedCrafted(0, shifted);
			}else{
				ClickedCrafting(index, 0, shifted);
			}
		}
		
		public void rightclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(ih == 9){					
				ClickedCrafted(1, shifted);
			}else{
				ClickedCrafting(index, 1, shifted);
			}
		}
	}
	
	public PlayerInventoryGUI(){
		super();
		
		start_offset = 0;
		buttons = null;
		mousebite = null;
		crafted = null;
		crafter = new InventoryContainer[4];
		isearch = "";
	}
	
	public void process(){
		Texture invtx = null;
		Texture hottx = null;
		Texture buttontexture = null;
		Texture deletetexture = null;
		Texture tosstexture = null;
		Texture craft1texture = null;
		Texture craft4texture = null;
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
		InventoryContainer ic = null;

		invtx = TextureMapper.getTexture("res/menus/playerinventory.png");
		hottx = TextureMapper.getTexture("res/menus/playerhotbar.png");
		buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");
		deletetexture = TextureMapper.getTexture("res/menus/"+"deletebutton.png");
		tosstexture = TextureMapper.getTexture("res/menus/"+"tossbutton.png");
		craft1texture = TextureMapper.getTexture("res/menus/"+"craft1.png");
		craft4texture = TextureMapper.getTexture("res/menus/"+"craft4.png");
		helmettexture = TextureMapper.getTexture("res/menus/"+"invhelmet.png");
		chestplatetexture = TextureMapper.getTexture("res/menus/"+"invchestplate.png");
		leggingstexture = TextureMapper.getTexture("res/menus/"+"invleggings.png");
		bootstexture = TextureMapper.getTexture("res/menus/"+"invboots.png");
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
		starty -= 100;

		//Now add Gamemode 1 and 2 inventory!
		if(DangerZone.player.getGameMode() != GameModes.SURVIVAL){

			
				textAt((int)((startx+375)), (int)((starty+20)), "Search:");	
				GL11.glColor3f(1,1,1); //because text messes this up!
				buttons.add(new MyButtonHandler((int)((startx+375)), (int)((starty-5)), 150, 25, textinputtexture, isearch, 17, isearchinput?1.0f:0.5f, 0, 5000));
			

			starty += 500;
			startx = middle + 50;
			drawRectangleWithTexture(invtx, (startx-6), (starty-205), 750, 375);
			starty -= 375;
			drawRectangleWithTexture(invtx, (startx-6), (starty-205), 750, 375);

			//Now go back and fill in buttons
			starty += 475;
			int found = 0;
			int start_found = 0;

			if(dismode == 0){
				while(found == 0){
					start_found = 0;
					for(i=1;i<Blocks.blocksMAX && found < 100;i++){
						if(Blocks.BlockArray[i] != null){
							if(Blocks.shouldShow(i) && filter == Blocks.getMenu(i) && matches(Blocks.getUniqueName(i), isearch)){ 
								start_found++;
								if(start_found < start_offset+1)continue; //not yet, next page perhaps
								row = found/10;
								col = found%10;
								ic = new InventoryContainer();
								ic.bid = i;
								ic.count = 1;
								buttons.add(new MyButtonHandler((int)((startx+cellsize*col+2)), (int)((starty-row*cellsize)), 60, 60, ic, 2, i, 6000+i)); //2 = blocks
								found++;
							}
						}
					}
					if(found == 0){
						if(start_offset >= 100){
							start_offset -= 100;
						}else{
							break;
						}
					}
				}
			}else{
				while(found == 0){
					start_found = 0;
					for(i=1;i<Items.itemsMAX && found < 100;i++){
						if(Items.ItemArray[i] != null){
							if(Items.shouldShow(i) && filter == Items.getMenu(i) && matches(Items.getUniqueName(i), isearch)){ 
								start_found++;
								if(start_found < start_offset+1)continue; //not yet, next page perhaps
								row = found/10;
								col = found%10;
								ic = new InventoryContainer();
								ic.iid = i;
								ic.count = 1;
								buttons.add(new MyButtonHandler((int)((startx+cellsize*col+2)), (int)((starty-row*cellsize)), 60, 60, ic, 3, i, 7000+i)); //3 = items
								found++;
							}
						}
					}
					if(found == 0){
						if(start_offset >= 100){
							start_offset -= 100;
						}else{
							break;
						}
					}
				}
			}
			
			startx -= 110;
			starty -= 300;

			//Blocks/Items switch
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-100)), (int)(100), (int)(25), buttontexture, "Blocks", 4, 0, 8000)); //4 = blocks mode
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-125)), (int)(100), (int)(25), buttontexture, "Items", 5, 0, 8001)); //5 - items mode
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-150)), (int)(100), (int)(25), buttontexture, "Hardware", 16, 0, 8002)); //
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-175)), (int)(100), (int)(25), buttontexture, "Spawners", 14, 0, 8003)); //
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-200)), (int)(100), (int)(25), buttontexture, "Trophies", 15, 0, 8004)); //
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-225)), (int)(100), (int)(25), buttontexture, "Eggs", 13, 0, 8005)); //

			//prev-next buttons
			buttons.add(new MyButtonHandler((int)((startx+25)), (int)((starty-325)), (int)(75), (int)(25), buttontexture, "Next", 7, 0, 8006));
			buttons.add(new MyButtonHandler((int)((startx+25)), (int)((starty-350)), (int)(75), (int)(25), buttontexture, "Prev", 8, 0, 8007)); 
			
			starty += 300;

		}else{
			//Normal 4-square crafting!
			if(crafter == null)crafter = new InventoryContainer[4];
			crafted = DangerZone.player.getCrafted();
			for(i=0;i<4;i++){
				crafter[i] = DangerZone.player.getCrafting(i);
			}
			starty += 550;
			startx = middle + 150;
			drawRectangleWithTexture(craft4texture, (startx), (starty-115), 200, 200);
			drawRectangleWithTexture(craft1texture, (startx+245), (starty-65), 100, 100);

			//Now go back and fill in buttons

			for(i=0;i<crafter.length;i++){
				row = i/2;
				col = i%2;
				buttons.add(new MyCraftingHandler((int)((startx+(100*col)+10)), (int)((starty-(row*100)-4)), (int)(80), (int)(80), crafter[i], 8, i, 9000+i)); //8 = crafter
			}

			
			buttons.add(new MyCraftingHandler((int)((startx+255)), (int)((starty-54)), (int)(80), (int)(80), crafted, 9, 0, 10000)); //9 = crafted
		
			
			starty += 50;
			
		}

		startx = middle;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 11002)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 11001)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 11000)); //delete button
		



		std_draw();
		
		Iterator<ButtonHandler> bb = buttons.iterator();

		
			//Check for mouse events on our button list!
			while(M_next()){
				float x = M_getEventX();
				float y = M_getEventY();
				x /= scalex;
				y /= scaley;
				clickx = (int)x;
				clicky = (int)y;

				if(M_getEventButtonState()){
					if(M_getEventButton() >= 0){ //clicked!				
						//Find which "button" they clicked on
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
							if(fb != null)fb.leftclickhandler();
							middle_down = false;
						}
						if(M_getEventButton() == 1){
							if(fb != null)fb.rightclickhandler();
							middle_down = false;
						}
						if(M_getEventButton() == 2){
							if(fb != null)fb.middleclickhandler();
							middle_down = true;
						}				
					}
				}else{
					if(M_getEventButton() >= 0){ //released!		
						middle_down = false;
					}
				}
			}

			//enable dragging the middle button! (MASS DELETE!)
			if(middle_down){
				float x = M_getCurX();
				float y = M_getCurY();
				x /= scalex;
				y /= scaley;
				clickx = (int)x;
				clicky = (int)y;

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
				if(fb != null)fb.middleclickhandler();
			}

		

		isearchinput = false;
		
		
		
			//show some descriptive text!
			int cx, cy;
			float x = M_getCurX();
			float y = M_getCurY();
			x /= scalex;
			y /= scaley;
			cx = (int)x;
			cy = (int)y;

			bb = buttons.iterator();
			fb = null;

			while(bb.hasNext()){
				mb = bb.next();
				if(cx >= mb.x && cx <= mb.x+mb.xsize){
					if(cy >= mb.y && cy <= mb.y+mb.ysize){
						ic = mb.ic;
						if(ic != null){
							String hotstring = null;
							String[] hss = null;
							if(ic.bid != 0){
								hotstring = Blocks.getUniqueName(ic.bid);
							}
							if(ic.iid != 0){
								hotstring = Items.getUniqueName(ic.iid);
								int attackstrength = Items.getAttackStrength(ic.iid);
								if(attackstrength > 1){
									hotstring += String.format(" +%d", attackstrength);
								}		
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									guimessagestring = hss[1];
									guimessagetimer = 10;
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
	
	public void ImAllDone(){
		
		ClearTable();
				
		mousebite = null;
		crafted = null;
		crafter = null;
		isearch = "";
		isearchinput = false;
		
		super.ImAllDone();
	}
	
	

	



}
