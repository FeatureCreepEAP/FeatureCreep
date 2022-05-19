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
import dangerzone.entities.EntityShredder;




public class PlayerShredderGUI extends GuiInterface {
	
	public EntityShredder ec = null;
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

			//System.out.printf("Left in %d,  %d\n", ih, index);
			if(ih == 6){ //delete!
				DeleteMouseBite();
				return;
			}
			
			if(ih == 3){				
				ClickedEntityInventory(ec.entityID, 0, 0, shifted);
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
				ClickedInventory(index, 0, shifted);
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
				ClickedEntityInventory(ec.entityID, 0, 1, shifted);
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
	

	
	public PlayerShredderGUI(){
		super();
		buttons = null;
		mousebite = null;
	}
	
	public void process(){
		Texture shreddertexture = null;
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
		shreddertexture = TextureMapper.getTexture("res/menus/"+"shredder.png");
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

		
		
		
		starty = top_of_display-200;	
		startx = middle+125;
		drawRectangleWithTexture(shreddertexture, (startx), (starty-70), 100, 100);
		buttons.add(new MyButtonHandler((int)((startx)), (int)((starty-65)), (int)(90), (int)(90), ec.getInventory(0), 3, 0, 4000));
		

		starty = top_of_display-200;		
		startx = middle;
		
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty)), 100, 100, backtexture, null, -2, -2, 4008)); //back button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-100)), 100, 100, tosstexture, null, -1, -1, 4007)); //toss button
		buttons.add(new MyButtonHandler((int)((startx-100)), (int)((starty-200)), 100, 100, deletetexture, null, 6, 0, 4006)); //delete button


		
		int high_button = std_draw();
		//System.out.printf("high-button = %d\n",  high_button);

		std_clicker(high_button);
	}
	
	public void ImAllDone(){		
		ClearTable();	
		ec = null;				
		super.ImAllDone();
	}
	

	


}
