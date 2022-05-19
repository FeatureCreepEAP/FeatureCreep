package dangerzone.gui;
/*
 * This code is copyright Richard H. Clark, TheyCallMeDanger, OreSpawn, 2015-2021.
 * You may use this code for reference for modding the DangerZone game program,
 * and are perfectly welcome to cut'n'paste portions for your mod as well.
 * DO NOT USE THIS CODE FOR ANY PURPOSE OTHER THAN MODDING FOR THE DANGERZONE GAME.
 * DO NOT REDISTRIBUTE THIS CODE. 
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


public class PlayerEscapeGUI extends GuiInterface {

	
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
				DangerZone.gameover = 1;
				ImAllDone();
			}
			if(buttonid == 2){
				DangerZone.renderdistance++;
				if(DangerZone.renderdistance > 24)DangerZone.renderdistance = 24;
				if(!DangerZone.bits64mode){
					if(DangerZone.renderdistance > 16)DangerZone.renderdistance = 16;
				}
				//lower max for when connected to real server
				if(!DangerZone.start_server){
					if(DangerZone.renderdistance > 16)DangerZone.renderdistance = 16;
				}
				
			}
			if(buttonid == 3){
				DangerZone.renderdistance--;
				if(DangerZone.renderdistance < 8)DangerZone.renderdistance = 8;
				
			}
			if(buttonid == 4){
				if(DangerZone.soundmangler.master_volume < 100)DangerZone.soundmangler.master_volume++;
			}
			if(buttonid == 5){
				if(DangerZone.soundmangler.master_volume > 0)DangerZone.soundmangler.master_volume--;
			}
			if(buttonid == 6){
				if(DangerZone.fieldOfView < 100)DangerZone.fieldOfView += 5;
			}
			if(buttonid == 7){
				if(DangerZone.fieldOfView > 30)DangerZone.fieldOfView -= 5;
			}
			if(buttonid == 8){
				if(DangerZone.mouseSensitivity < 9)DangerZone.mouseSensitivity++;
				
			}
			if(buttonid == 9){
				if(DangerZone.mouseSensitivity > -9)DangerZone.mouseSensitivity--;
				
			}
			if(buttonid == 10){
				if(DangerZone.mindrawlevel < 40)DangerZone.mindrawlevel++;
			}
			if(buttonid == 11){
				if(DangerZone.mindrawlevel > 0)DangerZone.mindrawlevel--;
			}
			if(buttonid == 12){
				DangerZone.all_sides = true;
				//if(!DangerZone.bits64mode){
				//	DangerZone.all_sides = false;
				//}
			}
			if(buttonid == 13){
				DangerZone.all_sides = false;
			}
			if(buttonid == 14){
				DangerZone.light_speed = true;
			}
			if(buttonid == 15){
				DangerZone.light_speed = false;
			}
			if(buttonid == 16){
				DangerZone.show_clouds = true;
			}
			if(buttonid == 17){
				DangerZone.show_clouds = false;
			}
			if(buttonid == 18){
				DangerZone.show_rain = true;
			}
			if(buttonid == 19){
				DangerZone.show_rain = false;
			}
			if(buttonid == 20){
				if(DangerZone.soundmangler.music_master_volume < 100)DangerZone.soundmangler.music_master_volume++;
				DangerZone.soundmangler.setMusicVolume();
			}
			if(buttonid == 21){
				if(DangerZone.soundmangler.music_master_volume > 0)DangerZone.soundmangler.music_master_volume--;
				DangerZone.soundmangler.setMusicVolume();
			}
			if(buttonid == 22){
				DangerZone.fullscreen = true;
			}
			if(buttonid == 23){
				DangerZone.fullscreen = false;
			}
			if(buttonid == 24){
				DangerZone.fog_enable = true;
			}
			if(buttonid == 25){
				DangerZone.fog_enable = false;
			}
			if(buttonid == 26){
				DangerZone.crafting_animation = true;
			}
			if(buttonid == 27){
				DangerZone.crafting_animation = false;
			}
			
			if(buttonid == 28){
				DangerZone.showhitbox = true;
			}
			if(buttonid == 29){
				DangerZone.showhitbox = false;
			}
			if(buttonid == 30){
				DangerZone.movepart = true;
			}
			if(buttonid == 31){
				DangerZone.movepart = false;
			}
			if(buttonid == 32){
				DangerZone.soundmangler.stopTheMusic();
			}
			if(buttonid == 33){
				String rndfile = DangerZone.soundmangler.getRandomMusicFile();
				DangerZone.soundmangler.playThisMusic(rndfile);
			}
			if(buttonid == 34){
				DangerZone.character_feel = 0;
			}
			if(buttonid == 35){
				DangerZone.character_feel = 1;
			}
			if(buttonid == 36){
				DangerZone.character_feel = 2;
			}


		}
		public void rightclickhandler(){
			leftclickhandler();
		}
	}
	
	public PlayerEscapeGUI(){
		super();
	}
	
	/*
	 * Nice reasonably simple button event processing....
	 */
	public void process(){
		Texture backtexture = null;
		Texture exittexture = null;
		Texture buttontexture = null;
		
		std_setup();
		GL11.glColor3f(1,1,1); //because text messes this up!	
		
		backtexture = TextureMapper.getTexture("res/menus/"+"back.png");
		exittexture = TextureMapper.getTexture("res/menus/"+"exit.png");
		buttontexture = TextureMapper.getTexture("res/menus/"+"button.png");
		
		startx += 100;

		//Build the button list... 
		buttons = new ArrayList<ButtonHandler>();
		buttons.add(new MyButtonHandler(middle - 150, starty - 200, 100, 100, backtexture, null, 0));
		buttons.add(new MyButtonHandler(middle + 50, starty - 200, 100, 100, exittexture, null, 1));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 300, 75, 25, buttontexture, "More", 2));
		buttons.add(new MyButtonHandler(startx + 350, starty - 300, 75, 25, buttontexture, "Less", 3));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 350, 75, 25, buttontexture, "More", 6));
		buttons.add(new MyButtonHandler(startx + 350, starty - 350, 75, 25, buttontexture, "Less", 7));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 400, 75, 25, buttontexture, "More", 8));
		buttons.add(new MyButtonHandler(startx + 350, starty - 400, 75, 25, buttontexture, "Less", 9));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 450, 75, 25, buttontexture, "Higher", 10));
		buttons.add(new MyButtonHandler(startx + 350, starty - 450, 75, 25, buttontexture, "Lower", 11));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 500, 75, 25, buttontexture, "High", 12));
		buttons.add(new MyButtonHandler(startx + 350, starty - 500, 75, 25, buttontexture, "Low", 13));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 550, 75, 25, buttontexture, "High", 14));
		buttons.add(new MyButtonHandler(startx + 350, starty - 550, 75, 25, buttontexture, "Low", 15));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 600, 75, 25, buttontexture, "True", 16));
		buttons.add(new MyButtonHandler(startx + 350, starty - 600, 75, 25, buttontexture, "False", 17));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 650, 75, 25, buttontexture, "True", 18));
		buttons.add(new MyButtonHandler(startx + 350, starty - 650, 75, 25, buttontexture, "False", 19));
		
		buttons.add(new MyButtonHandler(startx + 250, starty - 700, 75, 25, buttontexture, "Light", 34));
		buttons.add(new MyButtonHandler(startx + 350, starty - 700, 75, 25, buttontexture, "Med", 35));
		buttons.add(new MyButtonHandler(startx + 450, starty - 700, 75, 25, buttontexture, "Heavy", 36));
		
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 300, 75, 25, buttontexture, "More", 4));
		buttons.add(new MyButtonHandler(middle + 350, starty - 300, 75, 25, buttontexture, "Less", 5));
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 350, 75, 25, buttontexture, "More", 20));
		buttons.add(new MyButtonHandler(middle + 350, starty - 350, 75, 25, buttontexture, "Less", 21));
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 400, 75, 25, buttontexture, "True", 22));
		buttons.add(new MyButtonHandler(middle + 350, starty - 400, 75, 25, buttontexture, "False", 23));
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 450, 75, 25, buttontexture, "True", 24));
		buttons.add(new MyButtonHandler(middle + 350, starty - 450, 75, 25, buttontexture, "False", 25));
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 500, 75, 25, buttontexture, "True", 26));
		buttons.add(new MyButtonHandler(middle + 350, starty - 500, 75, 25, buttontexture, "False", 27));

		buttons.add(new MyButtonHandler(middle + 250, starty - 550, 75, 25, buttontexture, "True", 28));
		buttons.add(new MyButtonHandler(middle + 350, starty - 550, 75, 25, buttontexture, "False", 29));
		
		buttons.add(new MyButtonHandler(middle + 250, starty - 600, 75, 25, buttontexture, "True", 30));
		buttons.add(new MyButtonHandler(middle + 350, starty - 600, 75, 25, buttontexture, "False", 31));

		buttons.add(new MyButtonHandler(middle + 250, starty - 650, 75, 25, buttontexture, "Stop", 32));
		buttons.add(new MyButtonHandler(middle + 350, starty - 650, 75, 25, buttontexture, "Play", 33));
		
		

		int high_button = std_draw();

		
		textAt(startx +  50, starty - 300, "Render Distance:");
		textAt(startx +  460, starty - 300, String.format("FPS: %d @ %d", DangerZone.wr.fps, DangerZone.renderdistance));
				
		textAt(startx +  50, starty - 350, "Field of View:");
		textAt(startx +  460, starty - 350, String.format("FOV: %d", DangerZone.fieldOfView));
		
		textAt(startx +  50, starty - 400, "Mouse Sensitivity:");
		textAt(startx +  460, starty - 400, String.format("MSV: %d", DangerZone.mouseSensitivity));
		
		textAt(startx +  50, starty - 450, "Min Draw Level:");
		textAt(startx +  460, starty - 450, String.format("MDL: %d", DangerZone.mindrawlevel));
		
		textAt(startx +  50, starty - 500, "Graphics:");
		textAt(startx +  460, starty - 500, String.format("GFX: %s", DangerZone.all_sides?"High":"Low"));
		
		textAt(startx +  50, starty - 550, "Lighting:");
		textAt(startx +  460, starty - 550, String.format("LGT: %s", DangerZone.light_speed?"High":"Low"));
		
		textAt(startx +  50, starty - 600, "Show Clouds:");
		textAt(startx +  460, starty - 600, String.format("SHC: %s", DangerZone.show_clouds?"True":"False"));
		
		textAt(startx +  50, starty - 650, "Show Rain:");
		textAt(startx +  460, starty - 650, String.format("SHR: %s", DangerZone.show_rain?"True":"False"));
		
		textAt(startx +  50, starty - 700, "Player Feel:");
		String feel = "Light";
		if(DangerZone.character_feel == 1)feel = "Medium";
		if(DangerZone.character_feel == 2)feel = "Heavy";
		textAt(startx +  560, starty - 700, String.format("PFL: %s", feel));
		
		
		textAt( middle + 50, starty - 300, "Sounds Volume:");
		textAt( middle + 460, starty - 300, String.format("VOL: %d", DangerZone.soundmangler.master_volume));
		
		textAt( middle + 50, starty - 350, "Music Volume:");
		textAt( middle + 460, starty - 350, String.format("MVL: %d", DangerZone.soundmangler.music_master_volume));
		
		textAt( middle + 50, starty - 400, "FullScreen:");
		textAt( middle + 460, starty - 400, String.format("FSC: %s", DangerZone.fullscreen?"True":"False"));
		
		textAt( middle + 50, starty - 450, "FogEnable:");
		textAt( middle + 460, starty - 450, String.format("FOG: %s", DangerZone.fog_enable?"True":"False"));
		
		textAt( middle + 50, starty - 500, "Craft Animation:");
		textAt( middle + 460, starty - 500, String.format("CAN: %s", DangerZone.crafting_animation?"True":"False"));
		
		textAt( middle + 50, starty - 550, "Show Hitbox:");
		textAt( middle + 460, starty - 550, String.format("SHB: %s", DangerZone.showhitbox?"True":"False"));
		
		textAt( middle + 50, starty - 600, "Move Particles:");
		textAt( middle + 460, starty - 600, String.format("MVP: %s", DangerZone.movepart?"True":"False"));
		
		textAt( middle + 50, starty - 650, "Music Control::");
		
				
		std_clicker(high_button);
				
		
	}
	
	

}
