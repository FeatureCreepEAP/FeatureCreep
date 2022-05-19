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




public class PlayerStatsGUI extends GuiInterface {

	
	private class MyButtonHandler extends ButtonHandler {
		
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 0){
				ImAllDone();
			}
		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	
	public PlayerStatsGUI(){
		super();
	}
	
	/*
	 * Nice reasonably simple button event processing....
	 */
	public void process(){
		Texture backtexture = null;
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!

		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 50, starty - 200, 100, 100, backtexture, null, 0, 0));

		
		String playerstat = "this is a nice long interesting string";
		starty = bottom_of_display+200;
		
		playerstat = String.format("Kills:  %d", DangerZone.player.kills);
		textAt( startx + 200, starty + 580, playerstat);
		playerstat = String.format("Deaths: %d", DangerZone.player.deaths);
		textAt( startx + 200, starty + 540, playerstat);
		playerstat = String.format("Damage Taken: %f", DangerZone.player.damage_taken);
		textAt( startx + 200, starty + 500, playerstat);
		playerstat = String.format("Damage Dealt: %f", DangerZone.player.damage_dealt);
		textAt( startx + 200, starty + 460, playerstat);
		playerstat = String.format("Blocks Broken:   %d", DangerZone.player.blocks_broken);
		textAt( startx + 200, starty + 420,  playerstat);
		playerstat = String.format("Blocks Placed:   %d", DangerZone.player.blocks_placed);
		textAt( startx + 200, starty + 380, playerstat);
		playerstat = String.format("Blocks Colored:  %d", DangerZone.player.blocks_colored);
		textAt( startx + 200, starty + 340, playerstat);
		playerstat = String.format("Blocks Traveled: %d", DangerZone.player.traveled);
		textAt( startx + 200, starty + 300, playerstat);
		playerstat = String.format("Crafted: %d", DangerZone.player.crafted);
		textAt( startx + 200, starty + 260, playerstat);
		playerstat = String.format("Bought:  %d", DangerZone.player.bought);
		textAt( startx + 200, starty + 220, playerstat);
		playerstat = String.format("Sold:    %d", DangerZone.player.sold);
		textAt( startx + 200, starty + 180, playerstat);
		playerstat = String.format("Tools Broken: %d", DangerZone.player.broken);
		textAt( startx + 200, starty + 140, playerstat);
		playerstat = String.format("Morphs:    %d", DangerZone.player.morphs);
		textAt( startx + 200, starty + 100, playerstat);
		playerstat = String.format("Teleports: %d", DangerZone.player.teleports);
		textAt( startx + 200, starty + 60, playerstat);
		
		startx = middle;
		//column two!	
		playerstat = String.format("Eaten:     %d", DangerZone.player.eaten);
		textAt( startx + 50, starty + 580, playerstat);
		playerstat = String.format("Roach Stomps:  %d", DangerZone.player.roachstomps);
		textAt( startx + 50, starty + 540, playerstat);
		playerstat = String.format("Flights: %d", DangerZone.player.flights);
		textAt( startx + 50, starty + 500, playerstat);		
		playerstat = String.format("Hard Landings: %d", DangerZone.player.hard_landings);
		textAt( startx + 50, starty + 460, playerstat);
		playerstat = String.format("Spells Cast:   %d", DangerZone.player.spells);
		textAt( startx + 50, starty + 420, playerstat);
		
		
		int high_button = std_draw();
		std_clicker(high_button);
		
	

	}
	

	

}
