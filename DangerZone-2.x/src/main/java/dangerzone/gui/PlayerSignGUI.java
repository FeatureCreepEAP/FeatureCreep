package dangerzone.gui;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import dangerzone.DangerZone;
import dangerzone.TextureMapper;
import dangerzone.entities.Entity;




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
public class PlayerSignGUI extends GuiInterface {

	public Entity pet = null;
	String currstring1 = null;
	String currstring2 = null;
	String currstring3 = null;
	int curline = 1;
	int rotate = 0;

	
	private class MyButtonHandler extends ButtonHandler {
		
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i){
			super(xpos, ypos, bxsize, bysize, tx, ot, i);
	
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 0){
				ImAllDone();
			}
			if(buttonid == 1){
				if(pet.getBID() == 0){
					DangerZone.server_connection.sendVarIntMessage(pet.entityID, 0, 1);
				}else{
					DangerZone.server_connection.sendVarIntMessage(pet.entityID, 0, 0);
				}
			}

		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	public PlayerSignGUI(){
		super();
		curline = 1;
	}
	public void init(Entity inpet){

		pet = inpet;
		currstring1 = pet.getVarString(3);
		currstring2 = pet.getVarString(4);
		currstring3 = pet.getVarString(5);
		if(currstring1 == null)currstring1 = new String();
		if(currstring2 == null)currstring2 = new String();
		if(currstring3 == null)currstring3 = new String();
		rotate = pet.getVarInt(0); //also getBID();
		curline = 1;
	}
	
	/*
	 * Make a sign!
	 * 
	 */
	public void process(){
		Texture checktexture = null;
		Texture unchecktexture = null;
		Texture backtexture = null;
		int i;

		checktexture = TextureMapper.getTexture("res/menus/"+"check.png");
		unchecktexture = TextureMapper.getTexture("res/menus/"+"uncheck.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!
		
		starty = bottom_of_display + 100;
		
		textAt(startx + 50, starty + 250, "Rotate Sign: ");
		
		if(curline == 1)drawRectangleWithTexture(DangerZone.textinputtexture, startx + 180, starty + 160, 450, 30);
		textAt(startx + 50, starty + 160, "Text Line 1:   " + currstring1);
		
		if(curline == 2)drawRectangleWithTexture(DangerZone.textinputtexture, startx + 180, starty + 110, 450, 30);
		textAt(startx + 50, starty + 110, "Text Line 2:   " + currstring2);
		
		if(curline == 3)drawRectangleWithTexture(DangerZone.textinputtexture, startx + 180, starty + 60, 450, 30);
		textAt(startx + 50, starty + 60, "Text Line 3:   " + currstring3);
		

		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(startx + 250, starty + 250, 40, 40, pet.getBID()!=0?unchecktexture:checktexture, null, 1));
		buttons.add(new MyButtonHandler(middle - 150, top_of_display - 200, 100, 100, backtexture, null, 0));
		
		starty += 450;
		startx += 200;
	
		
		int high_button = std_draw();
		
		//Check for exit via keypad
		String s = std_keypad_get(high_button);
		if(escaped){
			ImAllDone();
			return;
		}
		
		if(entered){

			//Send it!
			if(curline == 1)DangerZone.server_connection.sendVarStringMessage(pet.entityID, 3, currstring1);
			if(curline == 2)DangerZone.server_connection.sendVarStringMessage(pet.entityID, 4, currstring2);
			if(curline == 3)DangerZone.server_connection.sendVarStringMessage(pet.entityID, 5, currstring3);
			//we don't record it here, because we will get it back when it is broadcast out from the server	
			curline++;
			if(curline > 3){
				curline = 1;
				ImAllDone();
			}
			return;
		}
		if(curline == 1 && s != null){
			if(!s.equals("delete")){
				//add a new char
				if(currstring1.length() < 35)currstring1 += s;
			}else{
				//delete the last char
				if(currstring1.length() > 0){
					String newstring = new String();
					for(i=0;i<currstring1.length()-1;i++){
						newstring += currstring1.charAt(i);
					}
					currstring1 = newstring;
				}
			}
		}
		if(curline == 2 && s != null){
			if(!s.equals("delete")){
				//add a new char
				if(currstring2.length() < 35)currstring2 += s;
			}else{
				//delete the last char
				if(currstring2.length() > 0){
					String newstring = new String();
					for(i=0;i<currstring2.length()-1;i++){
						newstring += currstring2.charAt(i);
					}
					currstring2 = newstring;
				}
			}
		}
		if(curline == 3 && s != null){
			if(!s.equals("delete")){
				//add a new char
				if(currstring3.length() < 35)currstring3 += s;
			}else{
				//delete the last char
				if(currstring3.length() > 0){
					String newstring = new String();
					for(i=0;i<currstring3.length()-1;i++){
						newstring += currstring3.charAt(i);
					}
					currstring3 = newstring;
				}
			}
		}
		
		if(s == null) {
			std_clicker(high_button);
		}

	}	

}
