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
import dangerzone.entities.EntityFurnace;





public class PlayerFurnaceGUI extends GuiInterface {
	

	public EntityFurnace ec = null;
	public boolean shifted = false;

	
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
			if(ih == -2){
				ImAllDone();
				return;
			}	
			if(ih < 0 || index < 0){
				SpitMouseBite();
				return;
			}			
			if(ih == 1){
				ClickedInventoryWithEntity(index, 0, false, ec.entityID); //no shift-clicking into furnace!
			}else{
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
			//try picking up half
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
	}
	

	
	public PlayerFurnaceGUI(){
		super();
		buttons = null;
		mousebite = null;
	}
	
	public void process(){
		Texture craft1texture = null;
		Texture timertexture = null;
		Texture invtx = null;
		Texture hottx = null;
		Texture deletetexture = null;
		Texture tosstexture = null;
		Texture backtexture = null;	
		Texture helmettexture = null;
		Texture leggingstexture = null;
		Texture chestplatetexture = null;
		Texture bootstexture = null;
		int i, row, col;
		int cellsize = 75;
		int mod = 10;

		
		invtx = TextureMapper.getTexture("res/menus/playerinventory.png");
		hottx = TextureMapper.getTexture("res/menus/playerhotbar.png");
		deletetexture = TextureMapper.getTexture("res/menus/"+"deletebutton.png");
		tosstexture = TextureMapper.getTexture("res/menus/"+"tossbutton.png");
		craft1texture = TextureMapper.getTexture("res/menus/"+"craft1.png");
		timertexture = TextureMapper.getTexture("res/menus/"+"timer.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		helmettexture = TextureMapper.getTexture("res/menus/"+"invhelmet.png");
		chestplatetexture = TextureMapper.getTexture("res/menus/"+"invchestplate.png");
		leggingstexture = TextureMapper.getTexture("res/menus/"+"invleggings.png");
		bootstexture = TextureMapper.getTexture("res/menus/"+"invboots.png");
		
		
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
		
		
		startx = middle;
		starty += 500;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 4008)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 4007)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 4006)); //delete button
		



		starty = top_of_display-100;
		
		startx = middle+00;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(0), 3, 0, 4000));
		starty -= 200;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(1), 3, 1, 4001));
		starty -= 75;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(2), 3, 2, 4002));

		starty = top_of_display-100;
		//starty =  bottom_of_display+650;
		startx += 100;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(3), 3, 3, 4003));
		starty -= 200;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(4), 3, 4, 4004));
		starty -= 75;
		drawRectangleWithTexture(craft1texture, (startx+180), (starty-70), 50, 50);
		buttons.add(new MyButtonHandler((int)((startx+185)), (int)((starty-65)), (int)(40), (int)(40), ec.getInventory(5), 3, 5, 4005));
		
		


		//catch-all!
		//buttons.add(new MyButtonHandler(0, 0, DangerZone.screen_width, DangerZone.screen_height, null, null, -1, -1));
		starty = top_of_display-170;
		//starty = bottom_of_display+650-70;
		startx = middle + 80;
		
		startx -= 33;
		starty -= 275;
		
		float pu = ec.getVarFloat(6); //fuel remaining	over fuel started with	
		if(pu != 0)pu = ec.getVarFloat(4)/pu;
		if(pu > 1)pu = 1;
		if(pu < 0)pu = 0;
		float xs = 64 * pu;
		if(xs > 0){
			//These are OK? We are in 2D mode. Check in VR!!!
			GL11.glPushMatrix(); //save position
			GL11.glScalef(scalex, scaley, 1);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslatef(startx, starty, 0f);
			GL11.glScalef(1f, 1f, 1f);
			GL11.glBegin(GL11.GL_QUADS);	
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(20, xs, 0); // Top Right
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(0, xs, 0); // Top Left
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(0, 0, 0); // Bottom left
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(20, 0, 0); // Bottom right		
			GL11.glEnd(); // Done Drawing The Quad
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
		}
		
		startx += 100;
		pu = ec.getVarFloat(7); //fuel remaining	over fuel started with	
		if(pu != 0)pu = ec.getVarFloat(5)/pu;
		
		if(pu > 1)pu = 1;
		if(pu < 0)pu = 0;
		xs = 64 * pu;
		if(xs > 0){
			//These are OK? We are in 2D mode. Check in VR!!!
			GL11.glPushMatrix(); //save position
			GL11.glScalef(scalex, scaley, 1);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glTranslatef(startx, starty, 0f);
			GL11.glScalef(1f, 1f, 1f);
			GL11.glBegin(GL11.GL_QUADS);	
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(20, xs, 0); // Top Right
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(0, xs, 0); // Top Left
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(0, 0, 0); // Bottom left
			GL11.glColor3f(0.65f, 0, 0);
			GL11.glVertex3f(20, 0, 0); // Bottom right		
			GL11.glEnd(); // Done Drawing The Quad
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPopMatrix();
		}
		
		GL11.glColor3f(1,1,1); //because above messed this up!
		
		starty = top_of_display-170;
		//starty = bottom_of_display+650-70;
		startx = middle + 180;
		
		starty -= 75;
		startx += 20;
		pu = ec.getVarFloat(8);
		xs = ec.getVarFloat(10);
		if(xs != 0)pu = pu/xs;
		drawRectangleWithTexture(timertexture, (startx), (starty), 45, 45, pu*360);
		startx += 110;
		pu = ec.getVarFloat(9);
		xs = ec.getVarFloat(11);
		if(xs != 0)pu = pu/xs;
		drawRectangleWithTexture(timertexture, (startx), (starty), 45, 45, pu*360);
		
		
		int high_button = std_draw();

		std_clicker(high_button);
		
		std_text(high_button);
		
	}
	
	public void ImAllDone(){
		
		ClearTable();
		
		ec = null;
				
		super.ImAllDone();
	}
	

	


}
