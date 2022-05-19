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
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import dangerzone.DangerZone;
import dangerzone.TextureMapper;
import dangerzone.entities.Entity;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;



public class PlayerEntityGUI extends GuiInterface {

	int updatecounter = 0;
	Map<String, Integer> entitycounts = new HashMap<String, Integer>();
	int total = 0;
	int unique = 0;
	
	Map<String, Integer> s_entitycounts = new HashMap<String, Integer>();
	int s_total = 0;
	int s_unique = 0;
	
	private class MyButtonHandler extends ButtonHandler {
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i){
			super(xpos, ypos, bxsize, bysize, tx, ot, i);
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
	
	public PlayerEntityGUI(){
		super();
	}
	
	/*
	 * Show active entities, client and server....
	 */
	public void process(){
		Texture backtexture = null;
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!
		
		starty -= 100;

		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 150, starty - 100, 100, 100, backtexture, null, 0));
		
		updatecounter++;
		if(updatecounter > DangerZone.wr.fps){
			updatecounter = 0;

			entitycounts.clear();
			total = 0;
			unique = 0;
			
			s_entitycounts.clear();
			s_total = 0;
			s_unique = 0;
			
			Integer Icurcount;
			Entity e;
			for(int i=0;i < DangerZone.max_entities; i++){
				e = DangerZone.entityManager.entities[i];
				if(e != null){
					Icurcount = entitycounts.get(e.uniquename);
					if(Icurcount == null){
						entitycounts.put(e.uniquename, 1);
						unique++;
						total++;
					}else{
						entitycounts.replace(e.uniquename, Icurcount.intValue()+1);
						total++;
					}
				}
			}
			
			if(DangerZone.server != null){
				for(int i=0;i < DangerZone.max_entities; i++){
					e = DangerZone.server.entityManager.entities[i];
					if(e != null){
						Icurcount = s_entitycounts.get(e.uniquename);
						if(Icurcount == null){
							s_entitycounts.put(e.uniquename, 1);
							s_unique++;
							s_total++;
						}else{
							s_entitycounts.replace(e.uniquename, Icurcount.intValue()+1);
							s_total++;
						}
					}
				}	
			}
		}
		
		starty -= 75;
		textAt( startx+50, starty, String.format("TOTALS: %d, %d  :: %d, %d", unique, total, s_unique, s_total));
		starty -=75;
		
		int i = 80;
		int lines = 0;
		for(Map.Entry<String, Integer> entry: entitycounts.entrySet()){
			textAt( startx, starty - i, String.format("CLIENT: %d  %s", entry.getValue().intValue(), entry.getKey()));
			i += 25;
			lines++;
			if(lines >= 25) {
				textAt( startx, starty - i, "***");
				break;
			}
		}
		
		if(DangerZone.server != null){
			i = 80;
			lines = 0;
			int xoff = 0;
			for(Map.Entry<String, Integer> entry: s_entitycounts.entrySet()){
				textAt( middle-200+xoff, starty - i, String.format("SERVER: %d  %s", entry.getValue().intValue(), entry.getKey()));
				i += 25;
				lines++;
				if(xoff != 0 && lines >= 25) {
					textAt( middle-200+xoff, starty - i, "***");
					break;
				}
				if(lines >= 25) {
					xoff += 500;
					lines = 0;
					i = 80;
				}
			}
		}
		

		
		//Check for exit via keypad
		while(K_next()){
			if (K_getEventKey() == Keyboard.KEY_ESCAPE && K_isKeyDown(Keyboard.KEY_ESCAPE)){
				entitycounts.clear();
				s_entitycounts.clear();
				total = 0;
				unique = 0;
				s_total = 0;
				s_unique = 0;
				ImAllDone();
				return;
			}
			if (K_getEventKey() == Keyboard.KEY_BACKSLASH && K_isKeyDown(Keyboard.KEY_BACKSLASH)){
				entitycounts.clear();
				s_entitycounts.clear();
				total = 0;
				unique = 0;
				s_total = 0;
				s_unique = 0;
				ImAllDone();
				return;
			}
		}
		
		int high_button = std_draw();

		std_clicker(high_button);

	}
	

	

}
