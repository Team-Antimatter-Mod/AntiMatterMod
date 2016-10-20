package antimattermod.core.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.awt.*;

/**
 * Created by Raiti on 2016/10/15.
 */
public class ClayCrucibleModel extends ModelBase {
	
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	
	public ClayCrucibleModel(){
		textureWidth = 64;
		textureHeight = 32;
		
		Shape1 = new ModelRenderer(this, 24, 0);
		Shape1.addBox(0F, 0F, 0F, 11, 13, 1);
		Shape1.setRotationPoint(-6F, 10F, -6F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 1, 13, 11);
		Shape2.setRotationPoint(5F, 10F, -6F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		
		Shape3 = new ModelRenderer(this, 24, 0);
		Shape3.addBox(0F, 0F, 0F, 11, 13, 1);
		Shape3.setRotationPoint(-5F, 10F, 5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		
		Shape4 = new ModelRenderer(this, 0, 0);
		Shape4.addBox(0F, 0F, 0F, 1, 13, 11);
		Shape4.setRotationPoint(-6F, 10F, -5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		
		Shape5 = new ModelRenderer(this, 12, 14);
		Shape5.addBox(0F, 0F, 0F, 12, 1, 12);
		Shape5.setRotationPoint(-6F, 23F, -6F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5,entity);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}
	
	public void renderModel(float f){
		Shape1.render(f);
		Shape2.render(f);
		Shape3.render(f);
		Shape4.render(f);
		Shape5.render(f);
	}
	
	
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
	}
}
