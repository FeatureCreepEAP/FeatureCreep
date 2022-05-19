package dangerzone.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import dangerzone.DangerZone;
import dangerzone.TextureMapper;
import dangerzone.entities.Entity;

import org.newdawn.slick.opengl.Texture;


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
public class PlayerPetNameGUI extends GuiInterface {

	public Entity pet = null;
	String currstring = new String();
	
	private class MyButtonHandler extends ButtonHandler {
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
			buttonid = i;
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
	
	public PlayerPetNameGUI(){
		super();
	}
	
	/*
	 * Rename a pet
	 * 
	 */
	public void process(){

		int i;
		
		Texture backtexture = null;
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!

		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 150, starty - 200, 100, 100, backtexture, null, 0, 0));
		
		starty = bottom_of_display + 100;

		drawRectangleWithTexture(DangerZone.textinputtexture, startx + 160, starty + 100, 350, 30);
		textAt(startx + 50, starty + 100, "Pet Name:   " + currstring);
		
		starty += 300;
		startx += 200;
	
		
		int high_button = std_draw();
		
		//Check for exit via keypad
		String s = std_keypad_get( high_button);
		if(escaped){
			ImAllDone();
			return;
		}
		
		if(entered){

			//Send it!
			DangerZone.server_connection.sendPetNameMessage(pet.entityID, currstring.length(), currstring);
			//we don't record it here, because we will get it back when it is broadcast out from the server			
			ImAllDone();
			return;
		}
		if(s != null){
			if(!s.equals("delete")){
				//add a new char
				currstring += s;
			}else{
				//delete the last char
				if(currstring.length() > 0){
					String newstring = new String();
					for(i=0;i<currstring.length()-1;i++){
						newstring += currstring.charAt(i);
					}
					currstring = newstring;
				}
			}
		}
		
		if(s == null) {
			std_clicker(high_button);
		}

	}	

}
