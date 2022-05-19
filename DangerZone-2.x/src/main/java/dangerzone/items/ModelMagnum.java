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
public class ModelMagnum extends ModelBase
{
    ModelRenderer carts;
    ModelRenderer grip;
    ModelRenderer base;
    ModelRenderer barrel;
    ModelRenderer rbsite;
    ModelRenderer lbsite;
    ModelRenderer fcsite;

	public ModelMagnum()
	{

	      carts = new ModelRenderer(this, 29, 0);
	      carts.addBox(-3F, -1F, -5F, 4, 4, 4);
	      carts.setRotationPoint(0F, 0F, 0F);
	      carts.setTextureSize(64, 32);
	      carts.mirror = true;
	      setRotation(carts, 0F, 0F, -0.7853982F);
	      grip = new ModelRenderer(this, 45, 12);
	      grip.addBox(-1F, 0F, 0F, 2, 9, 4);
	      grip.setRotationPoint(0F, 0F, 0F);
	      grip.setTextureSize(64, 32);
	      grip.mirror = true;
	      setRotation(grip, 0.122173F, 0F, 0F);
	      base = new ModelRenderer(this, 0, 11);
	      base.addBox(-1.5F, -2F, -9F, 3, 2, 14);
	      base.setRotationPoint(0F, 0F, 0F);
	      base.setTextureSize(64, 32);
	      base.mirror = true;
	      setRotation(base, 0F, 0F, 0F);
	      barrel = new ModelRenderer(this, 18, 9);
	      barrel.addBox(-1F, -3F, -15.1F, 2, 2, 20);
	      barrel.setRotationPoint(0F, 0F, 0F);
	      barrel.setTextureSize(64, 32);
	      barrel.mirror = true;
	      setRotation(barrel, 0F, 0F, 0F);
	      rbsite = new ModelRenderer(this, 22, 0);
	      rbsite.addBox(-0.5F, -4F, 4F, 0, 1, 1);
	      rbsite.setRotationPoint(0F, 0F, 0F);
	      rbsite.setTextureSize(64, 32);
	      rbsite.mirror = true;
	      setRotation(rbsite, 0F, 0F, 0F);
	      lbsite = new ModelRenderer(this, 17, 0);
	      lbsite.addBox(0.5F, -4F, 4F, 0, 1, 1);
	      lbsite.setRotationPoint(0F, 0F, 0F);
	      lbsite.setTextureSize(64, 32);
	      lbsite.mirror = true;
	      setRotation(lbsite, 0F, 0F, 0F);
	      fcsite = new ModelRenderer(this, 21, 4);
	      fcsite.addBox(0F, -4F, -14F, 0, 1, 1);
	      fcsite.setRotationPoint(0F, 0F, 0F);
	      fcsite.setTextureSize(64, 32);
	      fcsite.mirror = true;
	      setRotation(fcsite, 0F, 0F, 0F);
	}

	public void render(Entity e, InventoryContainer ic)
	{
		GL11.glScalef(0.75f, 0.75f, 0.75f); //it's too big! Make it smaller!

	    carts.render(1);
	    grip.render(1);
	    base.render(1);
	    barrel.render(1);
	    rbsite.render(1);
	    lbsite.render(1);
	    fcsite.render(1);

	}

}