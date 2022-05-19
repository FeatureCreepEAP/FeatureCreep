package dangerzone.items;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import dangerzone.DamageTypes;
import dangerzone.DangerZone;
import dangerzone.GameModes;
import dangerzone.InventoryContainer;
import dangerzone.ItemAttribute;
import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.WorldRenderer;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityExtendedRangeDamage;
import dangerzone.gui.InventoryMenus;

public class ItemMagnum extends Item {
	
	Texture texturebig = null;
	ModelMagnum ma = null;
	
	//Example of a nice tame little peashooter.
	//Not too fast.
	//Not too accurate.
	//But then what did you expect for a 22?
	//Make your own!
	
	public ItemMagnum(String n, String txt, int att, int uses) {
		super(n, txt);
		this.maxstack = 1;
		this.maxuses = uses;
		this.attackstrength = att;
		menu = InventoryMenus.HARDWARE;
		hold_straight = true;
		do_shoot_clicks = true; //don't eat or hit!!! SHOOT!!!
	}
	
	public void renderMe(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		
		if(texturebig == null){
			texturebig = TextureMapper.getTexture("res/items/Magnumtexture.png");
		}
		DangerZone.wr.loadtexture(texturebig);
		
		if(ma == null){
			ma = new ModelMagnum();
		}		
		
		//you see that little test block down in renderMeHeld()? 
		//You could use that here too... 
		//ONE AT A TIME! GET THIS ONE RIGHT FIRST!!!
		//Should look ok just held normally, and in F5 (front and back)
		GL11.glRotatef(82, 0, 1, 0);
		GL11.glRotatef(170, 0, 0, 1);
		GL11.glRotatef(41, 1, 0, 0);
		GL11.glTranslatef(2, -23, 1);
		if(ma != null)ma.render(e, ic); //e and ic are passed in case there is a short animation. ic now has a nice little temp int just for this!
		
	}
	
	public void renderMeHeld(WorldRenderer wr, Entity e, InventoryContainer ic, boolean isdisplay){
		if(e == null)return;

		float count = e.getRightButtonDownCount();
		if(count > getfullholdcount())count = getfullholdcount();
		float pct = count/getfullholdcount();
		
		if(isdisplay && e.getRightButtonDownCount() != 0){	
			/*
			//use this test block to tweak "sighted" position!
			//uncomment the test* stuff in DangerZone.java so you can use it!
			GL11.glRotatef(DangerZone.testr, 0, 0, 1);
			GL11.glRotatef(DangerZone.testp, 0, 1, 0);
			GL11.glRotatef(DangerZone.testw, 1, 0, 0);
			GL11.glTranslatef(DangerZone.testx*pct, DangerZone.testy*pct, DangerZone.testz*pct);
			//end test block
			*/
			//this is for when just the right button is being held down!
			//it is additional adjustments to renderMe() to get the pistol dead center and sighted.
			GL11.glRotatef(8.6f*pct, 0, 0, 1);
			GL11.glRotatef(16.8f*pct, 0, 1, 0);
			GL11.glRotatef(-3.9f*pct, 1, 0, 0);
			GL11.glTranslatef(-4.4f*pct, -8.1f*pct, 7.4f*pct);
		}

		renderMe( wr,  e, ic, isdisplay);

	}
	
	//this is a little single-shot pea-shooter! (but you can uncomment these others and try it!)
	//public boolean fullauto(Entity holder, InventoryContainer ic, int holdcount){
	//public boolean semiauto(Entity holder, InventoryContainer ic, int holdcount){
	public boolean singleshot(Entity holder, InventoryContainer ic, int holdcount){

		if(holder.world.isServer){

			int spam = ic.getAttribute(ItemAttribute.SPAM);			//spell effect
			int acc = ic.getAttribute(ItemAttribute.ACCURACY);		//spell effect
			int dmg = ic.getAttribute(ItemAttribute.DAMAGE);		//spell effect
			int rch = ic.getAttribute(ItemAttribute.REACH);			//spell effect
			float held = holdcount+10*spam;
			if(acc < 1)acc = 1;
			float acu = 1/acc;
			float dam = this.attackstrength + 2*dmg;
			float spd = 0.75f*rch;
			if(held > getfullholdcount())held = getfullholdcount();

			//System.out.printf("got to pistol item with %d and %f\n", holdcount, held);

			if(this.maxuses-ic.currentuses <= 1)return false; //EMPTY!!!
			if(held < getfullholdcount())return false; //Not ready!!!
			
			float mx, my, mz;

			//create invisible entity because bullets are too small and fast to see.
			EntityExtendedRangeDamage e = (EntityExtendedRangeDamage)holder.world.createEntityByName("DangerZone:ExtendedRangeDamage", 
					holder.dimension, 
					holder.posx+(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1.5f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
					holder.posy+(holder.getHeight()*9.25f/10f) - (float)Math.sin(Math.toRadians(holder.rotation_pitch_head))*(holder.getWidth()+1.5f),
					holder.posz+(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+1.5f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)));
			if(e != null){				
				e.init();
				e.setDamageType(DamageTypes.PROJECTILE); 				//damagetype
				e.setRange(256); 										//absolute max range in blocks
				e.thrower = holder; 									//shooter!
				e.setAttackDamage(dam); 								//attackdamage (damage when hits entity)
				e.setItemDamage(1);										//can hit and destroy items!
				
				//we will use m* to set blast particle direction below!
				mx = (float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head));
				my = -(float)Math.sin(Math.toRadians(holder.rotation_pitch_head-0.45f)); //just a smidge high (-0.45f)
				mz = (float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head));
						
				e.setDirectionAndVelocity(
						mx, 
						my, 
						mz,
						25.25f + spd, 0.01f * acu);
				
				holder.world.spawnEntityInWorld(e);


				//if(holder.world.rand.nextBoolean()){
				//	holder.world.playSound("DangerZone:357cal_1", holder.dimension, holder.posx, holder.posy+holder.getHeight(), holder.posz, 1.55f, 1.0f+((holder.world.rand.nextFloat()-holder.world.rand.nextFloat())*0.2f));
				//}else{
					holder.world.playSound("DangerZone:357cal_2", holder.dimension, holder.posx, holder.posy+holder.getHeight(), holder.posz, 1.55f, 1.0f+((holder.world.rand.nextFloat()-holder.world.rand.nextFloat())*0.2f));
				//}

				Utils.spawnParticlesFromServerScaled(holder.world, "DangerZone:ParticleDust", 20, holder.dimension, 
						holder.posx+(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+0.35f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
						holder.posy+(holder.getHeight()*9.25f/10f) - (float)Math.sin(Math.toRadians(holder.rotation_pitch_head))*(holder.getWidth()+0.35f),
						holder.posz+(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+0.35f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
						0, 0.15f*(holder.getWidth()+holder.getHeight())/4);
				
				Utils.spawnParticlesFromServerScaledWithDirection(holder.world, "DangerZone:ParticleBlast", 50, holder.dimension, 
						holder.posx+(float)Math.sin(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+0.35f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
						holder.posy+(holder.getHeight()*9.25f/10f) - (float)Math.sin(Math.toRadians(holder.rotation_pitch_head))*(holder.getWidth()+0.35f),
						holder.posz+(float)Math.cos(Math.toRadians(holder.rotation_yaw_head))*(holder.getWidth()+0.35f)*(float)Math.cos(Math.toRadians(holder.rotation_pitch_head)),
						mx, my, mz, 0, 0.15f*(holder.getWidth()+holder.getHeight())/4, 1.25f, 0.50f);

				holder.addKnockback(e, 0.3f, 0.1f); // knockback!!!
				
			}else{
				return false;
			}

			if(holder instanceof Player){
				if(holder.getGameMode() != GameModes.SURVIVAL){
					return false; //Don't decrement arrows (below), and don't bother with uses either...
				}
			}
			return true; //inc currentuses...
		}
		return false;

	}
	
	
	public float getfullholdcount(){
		//time to get ready
		//holdcount is roughly 100ths of a second
		return 20;
	}


}
