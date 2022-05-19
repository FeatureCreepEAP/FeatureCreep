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

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.entities.EntityLiving;

import dangerzone.items.Items;


public class PlayerTradeGUI extends GuiInterface {
	

	public EntityLiving ec = null;
	public boolean shifted = false;
	public boolean buy_mode = true;
	public int buy_index = 0;

	
	private class MyButtonHandler extends ButtonHandler {
		
		public int ih; //inventory or hotbar
		public int index; //index into above
		
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
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 0){
				ImAllDone();
				return;
			}
			
			if(ih == 6){ //delete!
				DeleteMouseBite();
				return;
			}
			
			if(ih == 7){
				buy_index++;
				if(buy_index > 7)buy_index = 0;
				return;
			}
			if(ih == 8){
				buy_index--;
				for(int i=0;i<8;i++){
					ic = ec.getInventory(buy_index);
					if(ic != null)break;
					buy_index--;
					if(buy_index < 0)buy_index = 7;
				}
				return;
			}
			if(ih == 9){
				buy_mode = !buy_mode;
				return;
			}
			if(ih == 10){
				//buy it!
				if(!buy_mode)return;
				BuyFromEntity(buy_index, ec.entityID);
				return;
			}
			if(ih == 11){
				//sell it!
				if(buy_mode)return;
				SellToEntity(ec.entityID);
				return;
			}
			if(ih == 12){
				//pick up or put down
				if(buy_mode)return;
				MouseBiteToEntity(ec.entityID);
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
				ClickedInventory(index, 0, false);
			}
			if(ih == 0) {
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

			//System.out.printf("Right in %d,  %d\n", ih, index);
			if(ih < 0 || ih > 1){
				return;
			}			
			if(ih == 1){
				ClickedInventory(index, 1, false);
			}else{
				ClickedHotBar(index, 1, shifted);
			}
		}
	}
	

	
	public PlayerTradeGUI(){
		super();
		buttons = null;
		mousebite = null;
	}
	
	public void process(){
		Texture invtx = null;
		Texture hottx = null;
		Texture buttontexture = null;
		Texture selltexture = null;
		Texture backtexture = null;
		Texture helmettexture = null;
		Texture leggingstexture = null;
		Texture chestplatetexture = null;
		Texture bootstexture = null;
		Texture deletetexture = null;
		Texture tosstexture = null;


		int i, row, col;
		int cellsize = 75;
		int mod = 10;
		InventoryContainer ic = null;
		int totalcoins = 0;
		
		invtx = TextureMapper.getTexture("res/menus/playerinventory.png");
		hottx = TextureMapper.getTexture("res/menus/playerhotbar.png");
		buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");
		selltexture = TextureMapper.getTexture("res/menus/"+"shredder.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		helmettexture = TextureMapper.getTexture("res/menus/"+"invhelmet.png");
		chestplatetexture = TextureMapper.getTexture("res/menus/"+"invchestplate.png");
		leggingstexture = TextureMapper.getTexture("res/menus/"+"invleggings.png");
		bootstexture = TextureMapper.getTexture("res/menus/"+"invboots.png");
		deletetexture = TextureMapper.getTexture("res/menus/"+"deletebutton.png");
		tosstexture = TextureMapper.getTexture("res/menus/"+"tossbutton.png");


		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!
		starty -= 400;
		
		if(ec == null){
			ImAllDone();
			return;
		}
		if(ec.deadflag){
			ImAllDone();
			return;
		}
		
		for(i=0;i<8;i++){
			ic = ec.getInventory(buy_index);
			if(ic != null)break;
			buy_index++;
			if(buy_index > 7)buy_index = 0;
		}

		
		if(K_isKeyDown(Keyboard.KEY_LSHIFT) || K_isKeyDown(Keyboard.KEY_RSHIFT)){
			shifted = true;
		}else{
			shifted = false;
		}
		
		//Draw full player inventory
		
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
			
		for(i=0;i<50;i++){
				ic = DangerZone.player.getInventory(i);
				if(ic != null){
					if(ic.iid == Items.coinplatinum.itemID)totalcoins += 1000 * ic.count;
					if(ic.iid == Items.coingold.itemID)totalcoins += 100 * ic.count;
					if(ic.iid == Items.coinsilver.itemID)totalcoins += 10 * ic.count;
				}
		}

		for(i=0;i<10;i++){
				ic = DangerZone.player.getHotbar(i);
				if(ic != null){
					if(ic.iid == Items.coinplatinum.itemID)totalcoins += 1000 * ic.count;
					if(ic.iid == Items.coingold.itemID)totalcoins += 100 * ic.count;
					if(ic.iid == Items.coinsilver.itemID)totalcoins += 10 * ic.count;
				}
		}
		
		starty -= 100;		
		textAt((startx + 100), (starty), String.format("You have: $%d", totalcoins));

		//----------------------------------------------------------------- Trading


		
		starty = top_of_display - 200;
		startx = middle+100;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 11002)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 11001)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 11000)); //delete button

		startx += 100;
		
		// buy-sell toggle
		buttons.add(new MyButtonHandler((int)(startx), (int)(starty), (int)(85), (int)(25), buttontexture, "Buy/Sell", 9, 0, 9000)); 
		

		
		if(buy_mode){
			//prev-next buttons
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-50)), (int)(75), (int)(25), buttontexture, "Next", 7, 0, 4000));
			buttons.add(new MyButtonHandler((int)(startx), (int)((starty-75)), (int)(75), (int)(25), buttontexture, "Prev", 8, 0, 4001)); 
			
			buttons.add(new MyButtonHandler((int)((startx+165)), (int)((starty-125)), (int)(75), (int)(25), buttontexture, "Buy It!", 10, 0, 4002)); 
			ic = ec.getInventory(buy_index);
			if(ic != null){
				int cost = ec.getVarInt(buy_index+16);
				int subcost = cost/1000;
				int off = 125;
				
				textAt(((startx+175)+off), (starty + 50), String.format("Price: $%d", cost));
				
				if(subcost > 0){
					drawRectangleWithTexture(Items.coinplatinum.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*1000;
					off += 55;
				}
				subcost = cost/100;
				if(subcost > 0){
					drawRectangleWithTexture(Items.coingold.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*100;
					off += 55;
				}
				subcost = cost/10;
				if(subcost > 0){
					drawRectangleWithTexture(Items.coinsilver.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*10;
				}
				drawRectangleWithTexture(selltexture, ((startx+163)), (starty-22), 75, 75);
				//dumy button just so we get text description
				buttons.add(new MyButtonHandler((int)((startx+175)), (int)((starty-10)), (int)(50), (int)(50), ic, 999, 999, 4003)); 

			}

		}else{
			buttons.add(new MyButtonHandler((int)((startx+165)), (int)((starty-75)), (int)(75), (int)(25), buttontexture, "Sell It!", 11, 0, 5000)); 			
			buttons.add(new MyButtonHandler((int)((startx+163)), (int)((starty-22)), (int)(75), (int)(75), selltexture, null, 12, 0, 5001)); 
			
			ic = ec.getHotbar(0);
			if(ic != null){
				int cost = ec.getVarInt(30);
				int subcost = cost/1000;
				int off = 125;
				
				
				textAt(((startx+175)+off), (starty+50), String.format("Offer: $%d", cost));
				
				if(subcost > 0){
					drawRectangleWithTexture(Items.coinplatinum.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*1000;
					off += 55;
				}
				subcost = cost/100;
				if(subcost > 0){
					drawRectangleWithTexture(Items.coingold.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*100;
					off += 55;
				}
				subcost = cost/10;
				if(subcost > 0){
					drawRectangleWithTexture(Items.coinsilver.getTexture(), ((startx+175)+off), (starty), 50, 50);
					textAt(((startx+175)+off), (starty-10), String.format("%d", subcost));
					cost -= subcost*10;
				}
				
				drawRectangleWithTexture(selltexture, ((startx+163)), (starty-22), 75, 75);
				buttons.add(new MyButtonHandler((int)((startx+175)), (int)((starty-10)), (int)(50), (int)(50), ic, 999, 999, 5002)); 

			}
		}
		
		//----------------------------------------------------------------- normal stuff
		
		int high_button = std_draw();
		std_clicker(high_button);
		std_text(high_button);
		
	}
	
	public void ImAllDone(){
		ClearTable();
		mousebite = null;
		shifted = false;
		buy_mode = true;
		buy_index = 0;
		UnStayEntity(ec.entityID); 
		ec = null;
		super.ImAllDone();
	}
	

	


}