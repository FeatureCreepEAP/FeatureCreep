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



import java.util.Iterator;
import java.util.List;




import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;


import dangerzone.DangerZone;
import dangerzone.InventoryContainer;

import dangerzone.blocks.Blocks;
import dangerzone.items.Item;
import dangerzone.items.ItemArmor;
import dangerzone.items.Items;

/*
 * Only two routines you need to mess with when inheriting from this interface.
 * Call super() in your constructor,
 * and then override the process() routine.
 * That's it!
 * Call ImAllDone() when you don't want your GUI up any more.
 */
public class GuiInterface {
	
	public boolean escaped = false;
	public boolean entered = false;
	public boolean arrow_up = false;
	public boolean arrow_down = false;
	boolean caps = false;
	public InventoryContainer mousebite = null;
	public boolean grab_mouse = false;
	public List<ButtonHandler> buttons = null; //more like inventory squares, really, but they do nicely as buttons
	public int clickx, clicky;
	public int startx, starty, middle, bottom_of_display, top_of_display, left_of_display;
	public float scalex = DangerZone.screen_width/1920f;
	public float scaley = DangerZone.screen_height/1080f;
	public boolean leftdown = false;
	public boolean rightdown = false;
	public boolean middledown = false;
	public String guimessagestring = null;
	public int guimessagetimer = 0;


	//convenience... use it or not...
	public class ButtonHandler {
		public Texture t;
		public int x;
		public int y;
		public int xsize;
		public int ysize;
		public String s;
		public InventoryContainer ic;
		public int buttonid = 0;

		
		public ButtonHandler(int xpos, int ypos, int bxsize, int bysize, Texture tx, String ot, int bid){
			x = xpos;
			y = ypos;
			xsize = bxsize;
			ysize = bysize;
			t = tx;
			s = ot;
			ic = null;
			buttonid = bid;
		}
		
		public ButtonHandler(int xpos, int ypos, int bxsize, int bysize, InventoryContainer iic, int bid){
			x = xpos;
			y = ypos;
			xsize = bxsize;
			ysize = bysize;
			t = null;
			s = null;
			ic = iic;
			buttonid = bid;
		}
		
		public void leftclickhandler(){			
		}
		public void rightclickhandler(){			
		}
		public void middleclickhandler(){			
		}
		
		public void draw(){
			if(ic != null){
				t = ic.getTexture();
				s = null;
				if(ic.count > 1){
					s = String.format("%d", ic.count);
				}
				if(t != null){
					GL11.glColor3f(1,1,1);
					drawRectangleWithTexture(t, x, y, xsize, ysize);
				}
				if(s != null && !s.equals("")){
					textAt( x+6, y, s);
					GL11.glColor3f(1,1,1); //because text messes this up!
				}
				//FIXME TODO - damage indicator! (if ic.count == 1 && ic.damage != 0 && ic.maxdamage != 0)
				if(ic.count == 1){
					if(ic.currentuses > 0){
						if(ic.getMaxStack() == 1){
							Item it = ic.getItem();
							if(it != null){
								int md = it.maxuses;
								if(md > 0){
									float pu = (float)ic.currentuses/(float)md;
									drawUsed(pu, x, y, xsize, 2);
								}
							}
						}
					}
				}
			}else{
				if(t != null){
					GL11.glColor3f(1,1,1);
					drawRectangleWithTexture(t, x, y, xsize, ysize);
				}
				if(s != null && !s.equals("")){
					textAt(x+6, y, s);
					GL11.glColor3f(1,1,1); //because text messes this up!
				}
			}
		}
	
	
	public void draw(float tcolor){
		if(ic != null){
			t = ic.getTexture();
			s = null;
			if(ic.count > 1){
				s = String.format("%d", ic.count);
			}
			if(t != null){
				GL11.glColor3f(tcolor,tcolor,tcolor);
				drawRectangleWithTexture(t, x, y, xsize, ysize);
			}
			if(s != null && !s.equals("")){
				textAt( x+6, y, s);
				GL11.glColor3f(1,1,1); //because text messes this up!
			}
			//FIXME TODO - damage indicator! (if ic.count == 1 && ic.damage != 0 && ic.maxdamage != 0)
			if(ic.count == 1){
				if(ic.currentuses > 0){
					if(ic.getMaxStack() == 1){
						Item it = ic.getItem();
						if(it != null){
							int md = it.maxuses;
							if(md > 0){
								float pu = (float)ic.currentuses/(float)md;
								drawUsed(pu, x, y, xsize, 2);
							}
						}
					}
				}
			}
		}else{
			if(t != null){
				GL11.glColor3f(tcolor,tcolor,tcolor);
				drawRectangleWithTexture(t, x, y, xsize, ysize);
			}
			if(s != null && !s.equals("")){
				textAt(x+6, y, s);
				GL11.glColor3f(1,1,1); //because text messes this up!
			}
		}
	}
	
}

	
	public GuiInterface(){
		//nothing to do...
	}
	
	/*
	 * Do your thing here! Draw, and then process inputs, all in one go.
	 * You will get called approximately 60 times a second, same as the frame rate.
	 * Try not to slow things down too much!
	 * You are in menu-mode for drawing, with 0,0 in the lower left.
	 * DangerZone.screen_width and DangerZone.screen_height is the size.
	 * Completely over-ride this routine! 
	 * Don't call super() unless you want the keyboard and mouse queues cleared.
	 * OK, maybe last, before you return... lol!
	 * MODE != 0 == VR
	 */
	public void process(){		
		while(K_next()){}; //clear out the keyboard queue
		while(M_next()){}; //clear out the mouse queue
	}
	
	/*
	 * Clean exit from your gui, when you are all done.
	 */
	public void ImAllDone(){
		while(K_next()){}; //clear out the keyboard queue
		while(M_next()){}; //clear out the mouse queue
		DangerZone.clearActiveGui();
		DangerZone.world.playSound("DangerZone:pop", DangerZone.player.dimension, DangerZone.player.posx, DangerZone.player.posy, DangerZone.player.posz, 0.25f, 1.0f);
	}
	
	//convenience text routine! 
	//Remember to reset the brightness after drawing text!
	
	public void textAt( float xpos, float ypos, String text){
		DangerZone.wr.loadtexture(Blocks.stone.getTexture(0)); //it just wants a texture loaded, or it gets mad....
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glTranslatef(xpos, ypos+DangerZone.font.getHeight(), 0f); 
		GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f); // Don't ask me why, but the text is upside down! Flip it!
		DangerZone.font.drawString(0, 0, text, Color.white);	
		GL11.glPopMatrix();  //restore position
	}
	
	public void smallTextAt( float xpos, float ypos, String text){
		DangerZone.wr.loadtexture(Blocks.stone.getTexture(0)); //it just wants a texture loaded, or it gets mad....
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glTranslatef(xpos, ypos+DangerZone.font16.getHeight(), 0f); 
		GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f); // Don't ask me why, but the text is upside down! Flip it!
		DangerZone.font16.drawString(0, 0, text, Color.white);	
		GL11.glPopMatrix();  //restore position
	}
	
	
	//convenience box...
	public void drawRectangleWithTexture(Texture t, float xpos, float ypos, float xsize, float ysize){
		DangerZone.wr.forceloadtexture(t);
		float w = t.getWidth();
		float h = t.getHeight();
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glTranslatef(xpos, ypos, 0f); 
		GL11.glScalef(xsize/t.getWidth(), ysize/t.getHeight(), 1f);
		GL11.glBegin(GL11.GL_QUADS);	
		GL11.glTexCoord2f(w,0);
		GL11.glVertex3f(1, 1, 0); // Top Right
		GL11.glTexCoord2f(0,0);
		GL11.glVertex3f(0, 1, 0); // Top Left
		GL11.glTexCoord2f(0,h);
		GL11.glVertex3f(0, 0, 0); // Bottom left
		GL11.glTexCoord2f(w,h);
		GL11.glVertex3f(1, 0, 0); // Bottom right		
		GL11.glEnd(); // Done Drawing The Quad
		GL11.glPopMatrix();
	}
	
	
	//convenience box, with rotation...
	public void drawRectangleWithTexture(Texture t, float xpos, float ypos, float xsize, float ysize, float zrot){
		DangerZone.wr.forceloadtexture(t);
		float w = t.getWidth();
		float h = t.getHeight();
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glTranslatef(xpos, ypos, 0f); 
		//GL11.glPushMatrix(); //save position
		GL11.glRotatef(zrot, 0, 0, 1.0f);
		GL11.glScalef(xsize/t.getWidth(), ysize/t.getHeight(), 1f);
		GL11.glBegin(GL11.GL_QUADS);	
		GL11.glTexCoord2f(w,0);
		GL11.glVertex3f(1, 1, 0); // Top Right
		GL11.glTexCoord2f(0,0);
		GL11.glVertex3f(-1, 1, 0); // Top Left
		GL11.glTexCoord2f(0,h);
		GL11.glVertex3f(-1, -1, 0); // Bottom left
		GL11.glTexCoord2f(w,h);
		GL11.glVertex3f(1, -1, 0); // Bottom right		
		GL11.glEnd(); // Done Drawing The Quad
		//GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	//convenience box, with rotation...
	public void drawRectangleWithTextureTwo(Texture t, float xpos, float ypos, float xsize, float ysize, float zrot, float extrascale){
		DangerZone.wr.forceloadtexture(t);
		float w = t.getWidth();
		float h = t.getHeight();
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glTranslatef(xpos, ypos, 0f); 	
		GL11.glScalef(extrascale*xsize/t.getWidth(), extrascale*ysize/t.getHeight(), 1f);
		GL11.glRotatef(zrot, 0, 0, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);	
		GL11.glTexCoord2f(w,0);
		GL11.glVertex3f(1, 1, 0); // Top Right
		GL11.glTexCoord2f(0,0);
		GL11.glVertex3f(-1, 1, 0); // Top Left
		GL11.glTexCoord2f(0,h);
		GL11.glVertex3f(-1, -1, 0); // Bottom left
		GL11.glTexCoord2f(w,h);
		GL11.glVertex3f(1, -1, 0); // Bottom right		
		GL11.glEnd(); // Done Drawing The Quad
		GL11.glPopMatrix();
	}
	
	//messes up colors! make sure you reset back to what you want
	public void drawUsed(float used, float xpos, float ypos, float xsize, float ysize){
		float xs;
		if(used > 1)used = 1;
		if(used < 0)used = 0;
		xs = xsize * (1f - used);
		if(xs < 1)xs = 1;
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glTranslatef(xpos, ypos, 0f); 
		GL11.glScalef(1f, 1f, 1f);
		GL11.glBegin(GL11.GL_QUADS);	
		GL11.glColor3f(used, 1f-used, 0);
		GL11.glVertex3f(xs, ysize, 0); // Top Right
		GL11.glColor3f(used, 1f-used, 0);
		GL11.glVertex3f(0, ysize, 0); // Top Left
		GL11.glColor3f(used, 1f-used, 0);
		GL11.glVertex3f(0, 0, 0); // Bottom left
		GL11.glColor3f(used, 1f-used, 0);
		GL11.glVertex3f(xs, 0, 0); // Bottom right		
		GL11.glEnd(); // Done Drawing The Quad
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}
	
	//messes up colors! make sure you reset back to what you want
	public void drawcoloredsquare(int xpos, int ypos, int xsize, int ysize, float r, float g, float b, float a){
		GL11.glPushMatrix(); //save position
		GL11.glScalef(scalex, scaley, 1);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef(xpos, ypos, 0f); 		
		GL11.glBegin(GL11.GL_QUADS);	
		GL11.glColor4f(r, g, b, a);
		GL11.glVertex3f(xsize, ysize, 0); // Top Right
		GL11.glColor4f(r, g, b, a);
		GL11.glVertex3f(0, ysize, 0); // Top Left
		GL11.glColor4f(r, g, b, a);
		GL11.glVertex3f(0, 0, 0); // Bottom left
		GL11.glColor4f(r, g, b, a);
		GL11.glVertex3f(xsize, 0, 0); // Bottom right		
		GL11.glEnd(); // Done Drawing The Quad
		GL11.glDisable(GL11.GL_BLEND);		
		GL11.glEnable(GL11.GL_TEXTURE_2D);	
		GL11.glPopMatrix();
	}
	
	/*
	 * You are in complete control of the keyboard and mouse inputs.
	 * Make sure you do the proper while() loops to clear the events.
	 * And yes, you have to go through these routines, because Mouse and Keyboard
	 * only have context in the main routine, nowhere else, and cannot be passed...
	 */
	public boolean K_next(){
		return DangerZone.K_next();
	}
	
	public int K_getEventKey(){
		return DangerZone.K_getEventKey();
	}
	
	public boolean K_isEventKeyDown(){
		return DangerZone.K_getEventKeyState();
	}
	
	public boolean K_isKeyDown(int key){
		return DangerZone.K_isKeyDown(key);
	}
	
	public boolean M_next(){
		return DangerZone.M_next();
	}
	
	public int M_getEventButton(){
		return DangerZone.M_getEventButton();
	}
	
	public boolean M_getEventButtonState(){
		return DangerZone.M_getEventButtonState();
	}
	
	public int M_getEventX(){
		return DangerZone.M_getEventX();
	}
	
	public int M_getEventY(){
		return DangerZone.M_getEventY();
	}
	
	public int M_getCurX(){
		return DangerZone.M_getX();
	}
	
	public int M_getCurY(){
		return DangerZone.M_getY();
	}
	

	
	//gets one char at a time. returns null if none, "delete" if delete!
	//sets global "escaped" flag too...
	//and the "entered" flag...
	public String getTextChar(){
		int currk;
		escaped = false;
		entered = false;
		arrow_up = false;
		arrow_down = false;
		
		
		while (K_next()) {	
			
			
			currk = K_getEventKey();
			
			//System.out.printf("Key! 0x%x, %s\n", currk, K_isEventKeyDown()?"true":"false");
			
			if (currk == Keyboard.KEY_ESCAPE){
				if(K_isEventKeyDown()) {
					escaped = true;
				}else {
					escaped = false;
				}
			}
			if (currk == Keyboard.KEY_RETURN){
				if(K_isEventKeyDown()) {
					entered = true;
				}else {
					entered = false;
				}
			}
			if (currk == Keyboard.KEY_UP){
				if(K_isEventKeyDown()) {
					arrow_up = true;
				}else {
					arrow_up = false;
				}
			}
			if (currk == Keyboard.KEY_DOWN){
				if(K_isEventKeyDown()) {
					arrow_down = true;
				}else {
					arrow_down = false;
				}
			}
			
			if (currk == Keyboard.KEY_LSHIFT || currk == Keyboard.KEY_RSHIFT){
				if(K_isEventKeyDown()) {
					caps = true;
				}else {
					caps = false;
				}
			}

			
			if(!K_isEventKeyDown())continue; //only care if down event for the rest.
			
			switch(currk){
			
			case Keyboard.KEY_DELETE:
				return "delete";
			case Keyboard.KEY_BACK:
				return "delete";
				
			case Keyboard.KEY_0:
				if(caps)return ")";
				return "0";
			case Keyboard.KEY_1:
				if(caps)return "!";
				return "1";
			case Keyboard.KEY_2:
				if(caps)return "@";
				return "2";
			case Keyboard.KEY_3:
				if(caps)return "#";
				return "3";
			case Keyboard.KEY_4:
				if(caps)return "$";
				return "4";
			case Keyboard.KEY_5:
				if(caps)return "%";
				return "5";
			case Keyboard.KEY_6:
				if(caps)return "^";
				return "6";
			case Keyboard.KEY_7:
				if(caps)return "&";
				return "7";
			case Keyboard.KEY_8:
				if(caps)return "*";
				return "8";
			case Keyboard.KEY_9:
				if(caps)return "(";
				return "9";
			case Keyboard.KEY_A:
				if(caps)return "A";
				return "a";
			case Keyboard.KEY_B:
				if(caps)return "B";
				return "b";
			case Keyboard.KEY_C:
				if(caps)return "C";
				return "c";
			case Keyboard.KEY_D:
				if(caps)return "D";
				return "d";
			case Keyboard.KEY_E:
				if(caps)return "E";
				return "e";
			case Keyboard.KEY_F:
				if(caps)return "F";
				return "f";
			case Keyboard.KEY_G:
				if(caps)return "G";
				return "g";
			case Keyboard.KEY_H:
				if(caps)return "H";
				return "h";
			case Keyboard.KEY_I:
				if(caps)return "I";
				return "i";
			case Keyboard.KEY_J:
				if(caps)return "J";
				return "j";
			case Keyboard.KEY_K:
				if(caps)return "K";
				return "k";
			case Keyboard.KEY_L:
				if(caps)return "L";
				return "l";
			case Keyboard.KEY_M:
				if(caps)return "M";
				return "m";
			case Keyboard.KEY_N:
				if(caps)return "N";
				return "n";
			case Keyboard.KEY_O:
				if(caps)return "O";
				return "o";
			case Keyboard.KEY_P:
				if(caps)return "P";
				return "p";
			case Keyboard.KEY_Q:
				if(caps)return "Q";
				return "q";
			case Keyboard.KEY_R:
				if(caps)return "R";
				return "r";
			case Keyboard.KEY_S:
				if(caps)return "S";
				return "s";
			case Keyboard.KEY_T:
				if(caps)return "T";
				return "t";
			case Keyboard.KEY_U:
				if(caps)return "U";
				return "u";
			case Keyboard.KEY_V:
				if(caps)return "V";
				return "v";
			case Keyboard.KEY_W:
				if(caps)return "W";
				return "w";
			case Keyboard.KEY_X:
				if(caps)return "X";
				return "x";
			case Keyboard.KEY_Y:
				if(caps)return "Y";
				return "y";
			case Keyboard.KEY_Z:
				if(caps)return "Z";
				return "z";				
				
			case Keyboard.KEY_PERIOD:
				if(caps)return ">";
				return ".";
			case Keyboard.KEY_COMMA:
				if(caps)return "<";
				return ",";
			case Keyboard.KEY_SLASH:
				if(caps)return "?";
				return "/";				
			case Keyboard.KEY_SEMICOLON:
				if(caps)return ":";
				return ";";
			case Keyboard.KEY_APOSTROPHE:
				if(caps)return "\"";
				return "'";				
			case Keyboard.KEY_SPACE:
			case Keyboard.KEY_TAB:				
				return " ";				
			case Keyboard.KEY_BACKSLASH:
				if(caps)return "|";
				return "\\";
			case Keyboard.KEY_LBRACKET:
				if(caps)return "{";
				return "[";
			case Keyboard.KEY_RBRACKET:
				if(caps)return "}";
				return "]";				
			case Keyboard.KEY_MINUS:
				if(caps)return "_";
				return "-";
			case Keyboard.KEY_EQUALS:
				if(caps)return "+";
				return "=";
				
			default:
				
			}
		}
		return null;
	}
	

	
	public void showArmorValue(int which, int x, int y){
		InventoryContainer ic = DangerZone.player.getArmor(which);
		if(ic != null){
			if(ic.count == 1){
				Item it = ic.getItem();
				if(it instanceof ItemArmor){
					ItemArmor ia = (ItemArmor)it;
					String s = String.format("%3.1f", ia.protection);
					textAt( (x), (y), s);
					GL11.glColor3f(1,1,1); //because text messes this up!
				}
			}
		}
	}
	
	/*
	 * ALL inventory handling is done on the server to prevent rampant cheating.
	 */
	public void ClickedHotBar(int which, int leftrightmid, boolean shifted){	
		DangerZone.server_connection.handleInventory(0, which, leftrightmid, shifted?1:0, 0);
	}
	public void ClickedInventory(int which, int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(1, which, leftrightmid, shifted?1:0, 0);
	}
	public void ClickedEntityInventory(int eid, int which, int leftrightmid, boolean shifted){	
		DangerZone.server_connection.handleInventory(2, eid, which, leftrightmid, shifted?1:0);
	}
	public void ClickedCreativeInventory(int iid, int bid, boolean shifted){
		DangerZone.server_connection.handleInventory(3, iid, bid, shifted?1:0, 0);
	}
	public void ClickedCrafted(int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(4, leftrightmid, shifted?1:0, 0, 0);
	}
	public void ClickedCrafting(int which, int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(5, which, leftrightmid, shifted?1:0, 0);
	}	
	public void SpreadListAdd(int which, int iid, int bid){	
		DangerZone.server_connection.handleInventory(6, which, iid, bid, 0);
	}
	public void SpreadListClear(){
		DangerZone.server_connection.handleInventory(7, 0, 0, 0, 0);
	}
	public void ClearTable(){
		DangerZone.server_connection.handleInventory(8, 0, 0, 0, 0);
	}
	public void ClickedArmor(int which, int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(9, which, leftrightmid, shifted?1:0, 0);
	}
	public void DeleteMouseBite(){
		DangerZone.server_connection.handleInventory(10, 0, 0, 0, 0);
	}
	public void SpitMouseBite(){
		DangerZone.server_connection.handleInventory(11, 0, 0, 0, 0);
	}
	public void ClickedInventoryWithEntity(int which, int leftrightmid, boolean shifted, int eid){ //only for entities with full 50 inventory!
		DangerZone.server_connection.handleInventory(12, which, leftrightmid, shifted?1:0, eid);
	}
	public void SpitOneHotbar(){
		DangerZone.server_connection.handleInventory(13, 0, 0, 0, 0);
	}
	public void SellToEntity(int eid){ 
		DangerZone.server_connection.handleInventory(14, eid, 0, 0, 0);
	}
	public void BuyFromEntity(int which, int eid){ 
		DangerZone.server_connection.handleInventory(15, which, eid, 0, 0);
	}
	public void UnStayEntity(int eid){ 
		DangerZone.server_connection.handleInventory(16, eid, 0, 0, 0);
	}
	public void MouseBiteToEntity(int eid){ 
		DangerZone.server_connection.handleInventory(17, eid, 0, 0, 0);
	}
	public void ClickedDeskCrafted(int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(18, leftrightmid, shifted?1:0, 0, 0);
	}
	public void ClickedDeskCrafting(int which, int leftrightmid, boolean shifted){
		DangerZone.server_connection.handleInventory(19, which, leftrightmid, shifted?1:0, 0);
	}	

	public void std_setup() {
		
		scalex = (float)DangerZone.screen_width/1920.0f;
		scaley = (float)DangerZone.screen_height/1080.0f;
		starty = 1080;
		middle = 1920/2;
		startx = 0;
		bottom_of_display = 0;		
		startx += 100;				
		top_of_display = starty;
		left_of_display = startx;
		
	}
	
	public int std_draw(){

		Texture tx = null;
		String s = null;
		//Draw buttons!
		clickx = 0;
		clicky = 0;
		int high_button = findHighlightedButton();
		
		if(guimessagetimer > 0) {
			guimessagetimer--;
			
			if(guimessagestring != null) {
				drawRectangleWithTexture(DangerZone.textinputtexture, middle - (5 * guimessagestring.length()) - 50, bottom_of_display + 95, 
						(13 * guimessagestring.length())+20, 30);
				textAt( middle - (5 * guimessagestring.length()) - 30, bottom_of_display + 95, guimessagestring);
			}
			
			if(guimessagetimer <= 0)guimessagestring = null;
		}

		//Draw buttons!
		if(buttons != null) {
			Iterator<ButtonHandler> bb = buttons.iterator();
			ButtonHandler thisbutton = null;
			float diff = 0.55f;
			diff = 0.75f;
			while(bb.hasNext()){
				thisbutton = bb.next();
				thisbutton.draw(thisbutton.buttonid==high_button?1.0f:diff);
			}
		}

		//Draw the things under the mouse
		mousebite = DangerZone.player.getMouseBite();
		
			if(mousebite != null){
				float x = M_getCurX();
				float y = M_getCurY();
				x /= scalex;
				y /= scaley;
				
				tx = mousebite.getTexture();
				s = null;
				if(mousebite.count > 1){
					s = String.format("%d", mousebite.count);
				}
				if(tx != null){
					GL11.glColor3f(1,1,1);
					drawRectangleWithTexture(tx, x-24, y-24, 48, 48);
				}
				if(s != null && !s.equals("")){
					textAt( x-18, y-24, s);
					GL11.glColor3f(1,1,1); //because text messes this up!
				}
			}
		
		return high_button;
	}
	
	public int findHighlightedButton() {
		if(buttons == null)return -1;
		

			//Highlight only when cursor on top of button
			float x = M_getCurX();
			float y = M_getCurY();
			x /= scalex;
			y /= scaley;
			clickx = (int)x;
			clicky = (int)y;
			
			Iterator<ButtonHandler> bb = buttons.iterator();
			ButtonHandler thisbutton = null;

			while(bb.hasNext()){
				thisbutton = bb.next();
				if(clickx >= thisbutton.x && clickx <= (thisbutton.x+thisbutton.xsize)){
					if(clicky >= thisbutton.y && clicky <= (thisbutton.y+thisbutton.ysize)){
						return thisbutton.buttonid;
					}
				}
			}

		return -1;
	}
	
	public void std_clicker(int high_button) {
		ButtonHandler fb = null;
		ButtonHandler mb = null;
		
		//Check for exit via keypad
		while(K_next()){
			if (K_getEventKey() == Keyboard.KEY_E && K_isKeyDown(Keyboard.KEY_E)){
				ImAllDone();
				return;
			}

			if (K_getEventKey() == Keyboard.KEY_ESCAPE && K_isKeyDown(Keyboard.KEY_ESCAPE)){
				ImAllDone();
				return;
			}
		}
		
		if(buttons == null)return;

		Iterator<ButtonHandler> bb = buttons.iterator();
		

			//Check for mouse events!
			while(M_next()){
				float x = M_getEventX();
				float y = M_getEventY();
				x /= scalex;
				y /= scaley;
				clickx = (int)x;
				clicky = (int)y;

				if(M_getEventButtonState()){
					if(M_getEventButton() >= 0){ //clicked!

						//Find which "button" they clicked on
						bb = buttons.iterator();
						fb = null;
						while(bb.hasNext()){
							mb = bb.next();
							if(clickx >= mb.x && clickx <= (mb.x+mb.xsize)){
								if(clicky >= mb.y && clicky <= (mb.y+mb.ysize)){
									fb = mb;
									break;
								}
							}
						}
						// 0 = left, 1 = right, 2 = middle
						if(M_getEventButton() == 0){
							if(fb != null)fb.leftclickhandler();
							leftdown = true;
						}
						if(M_getEventButton() == 1){
							if(fb != null)fb.rightclickhandler();
							rightdown = true;
						}
						if(M_getEventButton() == 2){
							if(fb != null)fb.middleclickhandler();
							middledown = true;
						}
					}
				}else{
					if(M_getEventButton() == 0){
						leftdown = false;
					}
					if(M_getEventButton() == 1){
						rightdown = false;
					}
					if(M_getEventButton() == 2){
						middledown = false;
					}
				}

			}


	
	}
	
	public void std_text(int high_button) {

		ButtonHandler mb = null;
		
		//Check for exit
		while(K_next()){
			if (K_getEventKey() == Keyboard.KEY_E && K_isKeyDown(Keyboard.KEY_E)){
				ImAllDone();
				return;
			}
			if (K_getEventKey() == Keyboard.KEY_ESCAPE && K_isKeyDown(Keyboard.KEY_ESCAPE)){
				ImAllDone();
				return;
			}
		}
		if(buttons == null)return;
		
		Iterator<ButtonHandler> bb = buttons.iterator();
		InventoryContainer ic = null;

		//show some descriptive text!

			int cx, cy;
			float x = M_getCurX();
			float y = M_getCurY();
			x /= scalex;
			y /= scaley;
			cx = (int)x;
			cy = (int)y;
			
			bb = buttons.iterator();

			while(bb.hasNext()){
				mb = bb.next();
				if(cx >= mb.x && cx <= (mb.x+mb.xsize)){
					if(cy >= mb.y && cy <= (mb.y+mb.ysize)){
						ic = mb.ic;
						if(ic != null){
							String hotstring = null;
							String[] hss = null;
							if(ic.bid != 0){
								hotstring = Blocks.getUniqueName(ic.bid);
							}
							if(ic.iid != 0){
								hotstring = Items.getUniqueName(ic.iid);
							}
							if(hotstring != null){
								hss = hotstring.split(":");
								if(hss.length >= 2){
									guimessagestring = hss[1];
									guimessagetimer = 10;
									//int xoff = 0;
									//if(cx > DangerZone.screen_width/2)xoff = -(hss[1].length());
									//WorldRendererUtils.textAt(font, cx + xoff, cy - 5, hss[1]);
								}
							}
						}
					}
				}
			}


	}
	
	
	public String std_keypad_get(int high_button) {
		
		entered = false;
		escaped = false;
		arrow_up = false;
		arrow_down = false;
		
		return getTextChar(); //check real keyboard
		
	}

}
