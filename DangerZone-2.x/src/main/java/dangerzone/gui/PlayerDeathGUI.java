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
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.TextureMapper;




public class PlayerDeathGUI extends GuiInterface {

	int deadcounter = 0;
	
	private class MyButtonHandler extends ButtonHandler {
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			buttonid = i;
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 0){
				DangerZone.player.server_connection.sendRespawn();				
				int which = DangerZone.rand.nextInt(3);
				if(which == 0)DangerZone.player.world.playSoundCloseClient("DangerZone:teleport1", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 1, 1);
				if(which == 1)DangerZone.player.world.playSoundCloseClient("DangerZone:teleport2", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 1, 1);
				if(which == 2)DangerZone.player.world.playSoundCloseClient("DangerZone:teleport3", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 1, 1);
				ImAllDone();

			}
			if(buttonid == 1){
				DangerZone.gameover = 1;
				DangerZone.player.setHealth(-1);
				ImAllDone();
			}			
		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	
	public PlayerDeathGUI(){
		super();



	}
	
	/*
	 * Nice reasonably simple button event processing....
	 */
	public void process(){

		String respawn = "ReSpawn";
		Texture respawntexture = null;
		Texture exittexture = null;
		
		
		respawntexture = TextureMapper.getTexture("res/menus/"+"respawnbutton.png");
		exittexture = TextureMapper.getTexture("res/menus/"+"exit.png");
		
		std_setup();
		
		//Player died??????
		if(deadcounter < DangerZone.wr.fps*4){
			String oops = "Oops. You died...";
			deadcounter++;

			starty -= 400;
			GL11.glColor3f(1.0f, 0.2f, 0.2f); //red!
			drawRectangleWithTexture(DangerZone.logotexture, (middle)-300, (starty)-300, 600, 600);
			GL11.glColor3f(1.0f, 1.0f, 1.0f); //brighten things up a bit!			
			textAt((middle)-(oops.length()*5), starty, oops); 
			
			while(K_next()){};
			while(M_next()){};
			return;
	
		}
		
		starty -= 300;

		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();

		buttons.add(new MyButtonHandler(middle - 150, starty, 300, 100, respawntexture, null, 0, 0));		
		buttons.add(new MyButtonHandler(middle - 50, starty - 150, 100, 100, exittexture, null, 1, 1));

		int high_button = std_draw();
		
		GL11.glColor3f(1.0f, 1.0f, 1.0f); //brighten things up a bit!		
		textAt(middle-(respawn.length()*7), starty + 30, respawn); 

		std_clicker(high_button);

	}
	
	public void ImAllDone(){
		DangerZone.doDeathGUI = false;
		deadcounter = 0;
		super.ImAllDone();
	}
	

	

}
