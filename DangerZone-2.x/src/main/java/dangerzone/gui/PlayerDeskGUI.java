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



import dangerzone.DangerZone;
import dangerzone.DeskCrafting;
import dangerzone.DeskRecipe;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.blocks.Blocks;
import dangerzone.entities.EntityDesk;
import dangerzone.items.Items;




public class PlayerDeskGUI extends GuiInterface {
	
	List<MyCraftingHandler> crafting_buttons;
	public InventoryContainer crafted;
	public EntityDesk ec = null;
	public boolean shifted = false;
	public InventoryContainer last_crafted;
	public int crafting;
	public DeskRecipe last_recipe = null;
	public String isearch = "";
	public boolean isearchinput = false;
	public int curidx = 0;

	
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
			
			if(ih == 9 || ih == 10 || ih == 11 || ih == 12){
				ClickedArmor(index, 0, shifted);
				return;
			}
			if(ih == 6){ //delete!
				DeleteMouseBite();
				return;
			}						
			if(ih == 3){
				ClickedEntityInventory(ec.entityID, index, 0, shifted);
				return;
			}
			
			if(ih == -2) {
				ImAllDone();
				return;
			}
			if(ih < 0 || index < 0){
				SpitMouseBite();
				return;
			}			
			if(ih == 1){
				ClickedInventoryWithEntity(index, 0, shifted, ec.entityID);
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
			if(ih == 3){			
				ClickedEntityInventory(ec.entityID, index, 2, shifted);
			}
		}
		public void rightclickhandler(){

			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			//System.out.printf("Right in %d,  %d\n", ih, index);
			if(ih == 3){					
				ClickedEntityInventory(ec.entityID, index, 1, shifted);
				return;
			}
			if(ih < 0 || ih > 1){
				return;
			}			
			if(ih == 1){
				ClickedInventory(index, 1, shifted);
			}else{
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
			if(crafting < 1)crafting = 1;
			if(ih == 1){
				if(crafting < 60)return;
				ClickedDeskCrafted(0, shifted);	
			}else{
				ClickedDeskCrafting(index, 0, shifted);
			}
			crafting = 1;
		}
		
		public void rightclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			//System.out.printf("Right in %d,  %d\n", ih, index);
			//try picking up half
			if(crafting < 1)crafting = 1;
			if(ih == 1){	
				if(crafting < 60)return;
				ClickedDeskCrafted(1, shifted);
			}else{
				ClickedDeskCrafting(index, 1, shifted);
			}
			crafting = 1;
		}
	}
	
	public PlayerDeskGUI(){
		super();
		buttons = null;
		mousebite = null;
		crafted = null;
		last_crafted = null;
		crafting = 0;
		isearch = "";

	}
	
	public void process(){
		Texture invtx = null;
		Texture hottx = null;
		Texture deletetexture = null;
		Texture tosstexture = null;
		Texture helmettexture = null;
		Texture leggingstexture = null;
		Texture chestplatetexture = null;
		Texture bootstexture = null;
		Texture craft1texture = null;
		Texture craft4texture = null;
		Texture papertexture = null;
		Texture penciltexture = null;
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
		deletetexture = TextureMapper.getTexture("res/menus/"+"deletebutton.png");
		tosstexture = TextureMapper.getTexture("res/menus/"+"tossbutton.png");
		helmettexture = TextureMapper.getTexture("res/menus/"+"invhelmet.png");
		chestplatetexture = TextureMapper.getTexture("res/menus/"+"invchestplate.png");
		leggingstexture = TextureMapper.getTexture("res/menus/"+"invleggings.png");
		bootstexture = TextureMapper.getTexture("res/menus/"+"invboots.png");
		craft1texture = TextureMapper.getTexture("res/menus/"+"craft1.png");
		craft4texture = TextureMapper.getTexture("res/menus/"+"craft4.png");
		papertexture = TextureMapper.getTexture("res/items/paper.png");
		penciltexture = TextureMapper.getTexture("res/items/charcoal stick.png");
		textinputtexture = TextureMapper.getTexture("res/menus/"+"textinput.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		
		if(ec == null){
			ImAllDone();
			return;
		}
		
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
		
		DeskRecipe r = DeskCrafting.find(nameof(DangerZone.player.getCrafting(0)),
				nameof(DangerZone.player.getCrafting(1)),
				nameof(DangerZone.player.getCrafting(2)),
				nameof(DangerZone.player.getCrafting(3)));
		
		int cost = 0;
		if(r != null) {
			cost = r.exp_cost;
		}
		
		textAt(startx, starty, String.format("Experience Cost: %d", cost));
		GL11.glColor4f(1,1,1,1); //because text messes this up!
		
		textAt(startx, starty-25, String.format("Your Experience: %d", DangerZone.player.getExperience()));
		GL11.glColor4f(1,1,1,1); //because text messes this up!
			
		
		startx = middle + 50;
		starty = top_of_display - 300;
		starty += 50;
		
		GL11.glColor3f(1,1,1); //because text messes this up!
		
		//OpenGL has some quirks...
		drawRectangleWithTexture(invtx, (startx-5), (starty-205), 750, 375);

		//Build the button list... yeah... i know... would be nice to not recreate it every time...
		//but they do have a tendency to change dynamically, so we don't...
		
		for(i=0;i<50;i++){
			row = i/mod;
			col = i%mod;
			buttons.add(new MyButtonHandler((int)((startx+cellsize*col)), (int)((starty-row*cellsize+100)), 60, 60, 
					ec.getInventory(i), 3, i, 4000+i)); //3 = chest
		}
				
		startx = middle;
		starty += 100;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 6003)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 6002)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 6001)); //delete button
		

		
		
		
		//Now go back and fill in buttons
		starty -= 450;
		startx = middle + 400;
		cellsize = 50;
		
		drawRectangleWithTexture(craft4texture, (startx-2), (starty-115), 100, 100);
		drawRectangleWithTexture(craft1texture, (startx+150), (starty-70), 50, 50);
		
		crafting_buttons = new ArrayList<MyCraftingHandler>();
		for(i=0;i<4;i++){
			row = i/2;
			col = i%2;
			crafting_buttons.add(new MyCraftingHandler((int)((startx+cellsize*col+5)), (int)((starty-row*cellsize-59)), (int)(40), (int)(40), DangerZone.player.getCrafting(i), 0, i, 5000+i)); //0 = crafter
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
			if(crafted != null)buttons.add(new MyCraftingHandler((int)((startx+155)), (int)((starty-65)), (int)(40), (int)(40), crafted, 1, 0, 6000)); //1 = crafted
			crafting = 61;
		}else{
			if(crafting > 0){
				drawRectangleWithTexture(papertexture, (startx+155), (starty-65), 40, 40);
				drawRectangleWithTextureTwo(penciltexture, (startx+160+(crafting%40)), (starty-35), 40, 40, (crafting%20)-10, 0.5f);
			}
		}

		


		
		startx = middle - 200;
		starty += 150;
		textAt((int)((startx+10)), (int)((starty-270)), "Search:");	
		GL11.glColor3f(1,1,1); //because text messes this up!

		buttons.add(new MyButtonHandler((int)((startx+10)), (int)((starty-290)), 150, 25, textinputtexture, isearch, 17, isearchinput?1.0f:0.5f, 6003));
		
		//add search items list...
		drawRectangleWithTexture(hottx, (startx-5), (starty-355), 500, 50);
		
		InventoryContainer icr;		
		Iterator<DeskRecipe> ii = DeskCrafting.recipies.iterator();
		//DeskRecipe r = null;
		DeskRecipe rsave = null;
		DeskRecipe rprev = null;
		i = 0;
		while(i < 10){
			
			if(!ii.hasNext())break;
			r = ii.next();
			if(!matches(r.outname, isearch))continue;
			if(is_same(rprev, r)){
				continue;
			}
			
			icr = new InventoryContainer(r.outname, 1);			
			row = 0;
			col = i;
			buttons.add(new MyButtonHandler((int)((startx+cellsize*col)), (int)((starty-row*cellsize-350)), (int)(40), (int)(40), icr, 20+i, 0, 7000+i)); 
			if(i == curidx){
				rsave = r;
				drawRectangleWithTexture(textinputtexture, (int)(((startx-10)+cellsize*col)), (int)(((starty-10)-row*cellsize-350)), (int)(60), (int)(60));
			}
			i++;
			rprev = r;
		}
		
		starty -= 100;
		startx = middle + 100;
		drawRectangleWithTexture(craft4texture, (startx-2), (starty-165), 100, 100);

		if(rsave != null){
			//Now go back and fill in buttons
			for(i=0;i<4;i++){
				if(rsave.ingredients[i] == null)continue;
				icr = new InventoryContainer(rsave.ingredients[i], 1);
				row = i/2;
				col = i%2;
				buttons.add(new MyButtonHandler((int)((startx+cellsize*col+5)), (int)((starty-(row*cellsize)-109)), (int)(40), (int)(40), icr, -99, 0, 8000+i)); 
			}
		}
	
		
		non_std_draw(crafting_buttons);		//Draw buttons!
		

		Iterator<ButtonHandler> bb = buttons.iterator();	
		Iterator<MyCraftingHandler> bc = crafting_buttons.iterator();	
		
		

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
					}
					if(M_getEventButton() == 1){
						if(fb != null)fb.rightclickhandler();
					}
					if(M_getEventButton() == 2){
						if(fb != null)fb.middleclickhandler();
					}
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
					// 0 = left, 1 = right, 2 = middle
					if(M_getEventButton() == 0){
						if(fb != null)fb.leftclickhandler();
					}
					if(M_getEventButton() == 1){
						if(fb != null)fb.rightclickhandler();
					}
				}
			}
		
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
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									DangerZone.hotmessagestring = hss[1];
									DangerZone.hotmessagetimer = 10;
									//int xoff = 0;
									//if(cx > DangerZone.screen_width/2)xoff = -(hss[1].length());
									//WorldRendererUtils.textAt(font, cx + xoff, cy - 5, hss[1]);
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
			fb = null;
			while(bc.hasNext()){
				mb = bc.next();
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
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									DangerZone.hotmessagestring = hss[1];
									DangerZone.hotmessagetimer = 10;
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
	
	//is PROBABLY same or equivalent. Not perfect.
	public boolean is_same(DeskRecipe first, DeskRecipe second){
		if(first == null || second == null)return false;
		if(first.exp_cost != second.exp_cost)return false;
		if(first.out_count != second.out_count)return false;
		if(!first.outname.equals(second.outname))return false;
		int firstcount = 0;
		int secondcount = 0;
		int i, j;
		boolean found;
		
		//compare counts
		for(i=0;i<4;i++){
			if(first.ingredients[i] != null)firstcount++;
			if(second.ingredients[i] != null)secondcount++;
		}
		if(firstcount != secondcount)return false;
		
		//search that each at least has the others ingredients
		for(i=0;i<4;i++){
			found = false;
			if(first.ingredients[i] == null)continue;
			for(j=0;j<4;j++){
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
		
		DangerZone.world.playSound("DangerZone:drawer_close", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.75f, 1.0f);
		
		ClearTable();
		
		crafted = null;
		mousebite = null;				
		ec = null;
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
				int cx, cy;
				float x = M_getCurX();
				float y = M_getCurY();
				x /= scalex;
				y /= scaley;
				cx = (int)x;
				cy = (int)y;
				
				tx = mousebite.getTexture();
				s = null;
				if(mousebite.count > 1){
					s = String.format("%d", mousebite.count);
				}
				if(tx != null){
					GL11.glColor3f(1,1,1);
					drawRectangleWithTexture(tx, cx-16, cy-16, 32, 32);
				}
				if(s != null && !s.equals("")){
					textAt( cx-10,cy-16, s);
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
	
	public String nameof(InventoryContainer ic) {
		if(ic == null)return null;
		if(ic.getItem() != null)return ic.getItem().uniquename;
		if(ic.getBlock() != null)return ic.getBlock().uniquename;
		return null;
	}

	

}
