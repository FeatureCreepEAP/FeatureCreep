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



public class PlayerHelpGUI extends GuiInterface {


	public int startat = 0;
	
	public String helpstrings[] = {
			"w, a, s, d - Move forward, left, back, right",
			"space - jump, or fly up",
			"shift (left) - fly down",
			"g - toggle game modes",
			"m - toggle survival difficulty",
			"y, Y, ctrl-y - change dimensions",
			"x, c, v - rotate block in X, Y, Z",
			"t - Talk/Chat for multiplayer",
			"h - Help",
			"\\ - show client/server entities nearby",
			"e - Inventory",
			"q - Drop held item/block",
			"F2 - Screen Capture",
			"F3 - Status overlay",
			"F4 - Player Statistics",
			"F5 - Narcissism toggle",
			"F6 - View underground ores",
			"F7 - Overlay screens on/off",
			"F11 - Achievements",
			"F12 - Pause/Showcase",
			"right-click on entity - mount/dismount",
			"jump on cockroach - change dimension",
			"esc - in-game graphics options",
			"mouse wheel - select hotbar item",
			"alt (left) - ready HARM spell",
			"control (left) - ready HEAL spell",
			"alt (left) + control (left) - ready DESTROY spell",
			"/ - COMMANDS -------",
			" time set (day|night|dawn|dusk|0-359)",
			" tp (playername | x y z | playername1 playername2 | playername x y z)",
			" stop",
			" kill (all|hostile|pets|volcanoes|playername|items)",
			" kick (playername)",
			" op (playername)",
			" deop (playername)",			
			" givepriv (playername) (gamemode|op|teleport|kill|weather|time|shutdown|nofire|chat)",
			" takepriv (playername) (gamemode|op|teleport|kill|weather|time|shutdown|nofire|chat)",
			" ban (playername)",
			" unban (playername)",
			" playnicely (true|false)",
			" firedamage (true|false)",
			" petprotection (true|false)",
			" defaultprivs (list of: gamemode|op|teleport|kill|weather|time|shutdown|nofire|chat)",
			" maxplayers (number)",
			" nofire",
			" items (search string)",
			" blocks (search string)",
			" give (playername) (item|block) (id) (count)",
			" clearinventory (playername)",
			" validateplayers (true|false)",
			" allowanonymous (true|false)",
			" cavegeneration (true|false)",
			" freezeworld (true|false)",
			" who",
			" showprivs (playername)",
			" private_server (true|false)",
			" whitelist (playername)",
			" unwhitelist (playername)",
			" weather clear",
			" whereis (playername)",
			" chunkowner (playername(s)|null)",
			" rebuild (chunk|area)",
			" spawn - go back to spawn",
			" home - go back to wherever you set home",
			" sethome - set home location",
			" ",
			" ",
	};
	
	private class MyButtonHandler extends ButtonHandler {
		
		
		
		MyButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int i){
			super(xpos, ypos, bxsize, bysize, tx, ot, i);
		}
		
		public void leftclickhandler(){
			DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);		

			if(buttonid == 0){
				ImAllDone();
			}
			if(buttonid == 7){
				startat += 10;
				if(startat >= helpstrings.length)startat = 0;
			}
			if(buttonid == 8){
				startat -= 10;
				if(startat < 0)startat = 0;
			}

		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	
	public PlayerHelpGUI(){
		super();
	}
	
	/*
	 * Nice reasonably simple button event processing....
	 */
	public void process(){
		Texture backtexture = null;
		Texture buttontexture = null;

		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!
		
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");

		starty -= 100;
		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 250, starty - 100, 100, 100, backtexture, null, 0));

		buttons.add(new MyButtonHandler(middle - 50, starty - 100, 75, 25, buttontexture, "Next", 7));
		buttons.add(new MyButtonHandler(middle - 50, starty - 125, 75, 25, buttontexture, "Prev", 8)); 
			

		int high_button = std_draw();
		
		textAt( startx + 250, starty-560,  helpstrings[(startat+9)%helpstrings.length]);
		textAt( startx + 250, starty-520, helpstrings[(startat+8)%helpstrings.length]);
		textAt( startx + 250, starty-480, helpstrings[(startat+7)%helpstrings.length]);
		textAt( startx + 250, starty-440, helpstrings[(startat+6)%helpstrings.length]);
		textAt( startx + 250, starty-400, helpstrings[(startat+5)%helpstrings.length]);
		textAt( startx + 250, starty-360, helpstrings[(startat+4)%helpstrings.length]);
		textAt( startx + 250, starty-320, helpstrings[(startat+3)%helpstrings.length]);
		textAt( startx + 250, starty-280, helpstrings[(startat+2)%helpstrings.length]);
		textAt( startx + 250, starty-240, helpstrings[(startat+1)%helpstrings.length]);
		textAt( startx + 250, starty-200, helpstrings[(startat  )%helpstrings.length]);

		std_clicker(high_button);


	}
	



}
