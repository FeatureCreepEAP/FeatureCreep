package dangerzone.items;


import org.lwjgl.opengl.GL11;

import dangerzone.InventoryContainer;
import dangerzone.ModelBase;
import dangerzone.ModelRenderer;
import dangerzone.entities.Entity;

//
// This model is built with the grip (and all positions) right at 0,0,0 and everything else at an offset.
// right-side up, facing forward, as if the user was holding it up in position and ready to use it.
//
public class ModelPistol extends ModelBase
{
	ModelRenderer top;
	ModelRenderer grip;
	ModelRenderer base;
	ModelRenderer barrel;
	ModelRenderer rbsite;
	ModelRenderer lbsite;
	ModelRenderer fcsite;

	public ModelPistol()
	{

		top = new ModelRenderer(this, 29, 0);
		top.addBox(-1.5F, -3F, -8F, 3, 2, 14);
		top.setRotationPoint(0F, 0F, 0F);
		top.setTextureSize(64, 32);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		grip = new ModelRenderer(this, 31, 18);
		grip.addBox(-1F, 0F, 0F, 2, 8, 4);
		grip.setRotationPoint(0F, 0F, 0F);
		grip.setTextureSize(64, 32);
		grip.mirror = true;
		setRotation(grip, 0.122173F, 0F, 0F);
		base = new ModelRenderer(this, 0, 17);
		base.addBox(-1F, -1F, -8F, 2, 1, 13);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		barrel = new ModelRenderer(this, 0, 0);
		barrel.addBox(-0.5F, -1.5F, -10F, 1, 1, 6);
		barrel.setRotationPoint(0F, 0F, 0F);
		barrel.setTextureSize(64, 32);
		barrel.mirror = true;
		setRotation(barrel, 0F, 0F, 0F);
		rbsite = new ModelRenderer(this, 22, 0);
		rbsite.addBox(-1.5F, -4F, 4F, 1, 1, 1);
		rbsite.setRotationPoint(0F, 0F, 0F);
		rbsite.setTextureSize(64, 32);
		rbsite.mirror = true;
		setRotation(rbsite, 0F, 0F, 0F);
		lbsite = new ModelRenderer(this, 17, 0);
		lbsite.addBox(0.5F, -4F, 4F, 1, 1, 1);
		lbsite.setRotationPoint(0F, 0F, 0F);
		lbsite.setTextureSize(64, 32);
		lbsite.mirror = true;
		setRotation(lbsite, 0F, 0F, 0F);
		fcsite = new ModelRenderer(this, 21, 4);
		fcsite.addBox(0F, -4F, -7F, 0, 1, 1);
		fcsite.setRotationPoint(0F, 0F, 0F);
		fcsite.setTextureSize(64, 32);
		fcsite.mirror = true;
		setRotation(fcsite, 0F, 0F, 0F);
	}

	public void render(Entity e, InventoryContainer ic)
	{
		int pushed = 0;
		GL11.glScalef(0.75f, 0.75f, 0.75f); //it's too big! Make it smaller!

		grip.render(1);
		base.render(1);
		barrel.render(1);

		if(ic != null){
			if(ic.tmpi > 0){
				if(ic.tmpi > 10)ic.tmpi = 10;
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0, -5+Math.abs(5-ic.tmpi));
				pushed = 1;
			}
		}
		
		top.render(1);	
		rbsite.render(1);
		lbsite.render(1);
		fcsite.render(1);
		
		if(pushed != 0){
			GL11.glPopMatrix();
			ic.tmpi--;
		}

	}

}
