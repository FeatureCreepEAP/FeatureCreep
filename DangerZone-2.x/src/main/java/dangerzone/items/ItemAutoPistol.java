package dangerzone.items;

import org.lwjgl.opengl.GL11;

import dangerzone.DangerZone;
import dangerzone.InventoryContainer;
import dangerzone.TextureMapper;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;

public class ItemAutoPistol extends ItemPistol {

	ModelAutoPistol ap = null;
	
	public ItemAutoPistol(String n, String txt, int att, int uses) {
		super(n, txt, att, uses);
	}
	
	public boolean singleshot(Entity holder, InventoryContainer ic, int holdcount){
		return false;
	}
	
	public boolean semiauto(Entity holder, InventoryContainer ic, int holdcount){
		return false;
	}
	
	public boolean fullauto(Entity holder, InventoryContainer ic, int holdcount){
		return shoot_a_shot(holder, ic, holdcount);
	}
	
	public void renderMe(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		
		if(texturebig == null){
			texturebig = TextureMapper.getTexture("res/items/AutoPistoltexture.png");
		}
		DangerZone.wr.loadtexture(texturebig);
		
		if(ap == null){
			ap = new ModelAutoPistol();
		}		
		
		//you see that little test block down in renderMeHeld()? 
		//You could use that here too... 
		//ONE AT A TIME! GET THIS ONE RIGHT FIRST!!!
		//Should look ok just held normally, and in F5 (front and back)
		GL11.glRotatef(82, 0, 1, 0);
		GL11.glRotatef(170, 0, 0, 1);
		GL11.glRotatef(41, 1, 0, 0);
		GL11.glTranslatef(2, -23, 1);
		if(ap != null)ap.render(e, ic); //e and ic are passed in case there is a short animation. ic now has a nice little temp int just for this!
		
	}

}
