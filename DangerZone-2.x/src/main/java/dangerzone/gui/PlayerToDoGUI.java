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
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DangerZone;
import dangerzone.TextureMapper;
import dangerzone.ToDoItem;
import dangerzone.ToDoList;



public class PlayerToDoGUI extends GuiInterface {
	int start_offset = 0;
	
	private class MyButtonHandler extends ButtonHandler {
		
		public String helper = null;
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
	
		}
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, String help, int i, int bid){
			super(xpos, ypos, bxsize, bysize, tx, ot, bid);
	
			helper = help;
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 7){
				start_offset += 30;
				return;
			}
			if(buttonid == 8){
				start_offset -= 30;
				if(start_offset < 0)start_offset = 0;
				return;
			}
			if(buttonid == 0){
				ImAllDone();
			}
		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	
	public PlayerToDoGUI(){
		super();
		start_offset = 0;
	}
	
	/*
	 * Nice reasonably simple button event processing....
	 */
	public void process(){
		Texture checktexture = null;
		Texture unchecktexture = null;
		Texture buttontexture = null;
		Texture backtexture = null;

		int totalfound = 0;
		int hidden = 0;
		int row, col;
		ToDoItem todo = null;
		
		buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");
		checktexture = TextureMapper.getTexture("res/menus/"+"check.png");
		unchecktexture = TextureMapper.getTexture("res/menus/"+"uncheck.png");
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!

		starty -= 200;
		
		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 250, starty , 100, 100, backtexture, null, 0, 0));

		//prev-next buttons
		buttons.add(new MyButtonHandler(middle - 125, starty + 30, (int)(75), (int)(25), buttontexture, "Next", 7, 7));
		buttons.add(new MyButtonHandler(middle - 125, starty, (int)(75), (int)(25), buttontexture, "Prev", 8, 8)); 
		
		starty -= 150;
		row = col = 0;
		int listlen = ToDoList.todo.size();
		while(totalfound < 30){
			if(start_offset + totalfound + hidden >= listlen)break;

			todo = ToDoList.todo.get(start_offset + totalfound + hidden);

			if(todo.gettitle() == null){ //hidden!
				hidden++;
			}else{
				smallTextAt(startx + 48 + (col*450), (starty+3) - (row*40), String.format("%d", start_offset + totalfound + 1));
				buttons.add(new MyButtonHandler(startx + 120 + (col*450), starty - (row*40), 225, 25, null, todo.gettitle(), todo.gethelptext(), -2, 1000 + totalfound));			
				buttons.add(new MyButtonHandler(startx + 85 + (col*450), starty - (row*40), 25, 25, todo.isTrue()?checktexture:unchecktexture, null, -1, 2000 + totalfound));			
				totalfound++;
				col++;
				if(col >= 3){
					col = 0;
					row++;
				}
				if(row >= 10)break;
			}
		}
		
		if(totalfound == 0){
			start_offset -= 30;
			if(start_offset < 0)start_offset = 0;
		}


			
		
		int high_button = std_draw();
		std_clicker(high_button);
		
		//show the descriptions!
		Iterator<ButtonHandler>bb = buttons.iterator();
		MyButtonHandler mb = null;
		while(bb.hasNext()){
			mb = (MyButtonHandler) bb.next();
			if(mb.buttonid == high_button){				
				if(mb.helper != null){	
					String[] temps = mb.helper.split("\n");
					for(int j=0 ; j<temps.length;j++){
						if(temps[j] != null)textAt(middle + 50, (top_of_display-50) - (75 +(j*30)), temps[j]);
					}
				}
				break;
			}
		}
	}
	

	

}





















