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
public class ModelShotgun extends ModelBase
{
    ModelRenderer top;
    ModelRenderer grip;
    ModelRenderer base;
    ModelRenderer base2;
    ModelRenderer Shape1;
  

	public ModelShotgun()
	{

	      top = new ModelRenderer(this, 35, 0);
	      top.addBox(-1.5F, 0F, 2F, 3, 5, 16);
	      top.setRotationPoint(0F, 0F, 0F);
	      top.setTextureSize(128, 64);
	      top.mirror = true;
	      setRotation(top, -0.1396263F, 0F, 0F);
	      grip = new ModelRenderer(this, 0, 21);
	      grip.addBox(-2F, -1F, -1F, 4, 4, 13);
	      grip.setRotationPoint(0F, 0F, 0F);
	      grip.setTextureSize(128, 64);
	      grip.mirror = true;
	      setRotation(grip, -0.8203047F, 0F, 0F);
	      base = new ModelRenderer(this, 30, 0);
	      base.addBox(0F, -3F, -46F, 3, 3, 45);
	      base.setRotationPoint(0F, 0F, 0F);
	      base.setTextureSize(128, 64);
	      base.mirror = true;
	      setRotation(base, 0F, 0F, 0.7853982F);
	      base2 = new ModelRenderer(this, 30, 0);
	      base2.addBox(-3F, 0F, -46F, 3, 3, 45);
	      base2.setRotationPoint(0F, 0F, 0F);
	      base2.setTextureSize(128, 64);
	      base2.mirror = true;
	      setRotation(base2, 0F, 0F, 0.7853982F);
	      Shape1 = new ModelRenderer(this, 0, 0);
	      Shape1.addBox(-2F, 0F, 18F, 4, 7, 12);
	      Shape1.setRotationPoint(0F, 0F, 0F);
	      Shape1.setTextureSize(128, 64);
	      Shape1.mirror = true;
	      setRotation(Shape1, -0.1396263F, 0F, 0F);
	}

	public void render(Entity e, InventoryContainer ic)
	{

		GL11.glScalef(0.75f, 0.75f, 0.75f); //it's too big! Make it smaller!
	    top.render(1);
	    grip.render(1);
	    base.render(1);
	    base2.render(1);
	    Shape1.render(1);


	}

}