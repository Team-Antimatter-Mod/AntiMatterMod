package antimattaermod.core.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created by worldofthetakumi on 2016/10/10.
 */
public class FurnaceModel extends ModelBase{


    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;

    public FurnaceModel()
    {
        textureWidth = 64;
        textureHeight = 128;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(0F, 0F, 0F, 14, 14, 1);
        Shape1.setRotationPoint(-7F, 10F, 7F);
        Shape1.setTextureSize(64, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 14, 14, 1);
        Shape2.setRotationPoint(-8F, 10F, 7F);
        Shape2.setTextureSize(64, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 1.570796F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 14, 14, 1);
        Shape3.setRotationPoint(7F, 10F, 7F);
        Shape3.setTextureSize(64, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 1.570796F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 14, 1, 14);
        Shape4.setRotationPoint(-7F, 9F, -7F);
        Shape4.setTextureSize(64, 128);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 2, 1, 10);
        Shape5.setRotationPoint(4F, 23F, -5F);
        Shape5.setTextureSize(64, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 0F, 0F, 2, 1, 10);
        Shape6.setRotationPoint(1F, 23F, -5F);
        Shape6.setTextureSize(64, 128);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(0F, 0F, 0F, 2, 1, 10);
        Shape7.setRotationPoint(-3F, 23F, -5F);
        Shape7.setTextureSize(64, 128);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 0, 0);
        Shape8.addBox(0F, 0F, 0F, 2, 1, 10);
        Shape8.setRotationPoint(-6F, 23F, -5F);
        Shape8.setTextureSize(64, 128);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 0, 0);
        Shape9.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape9.setRotationPoint(-7F, 10F, -7F);
        Shape9.setTextureSize(64, 128);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 1, 1, 14);
        Shape10.setRotationPoint(6F, 10F, -7F);
        Shape10.setTextureSize(64, 128);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 0, 0);
        Shape11.addBox(0F, 0F, 0F, 2, 14, 1);
        Shape11.setRotationPoint(5F, 10F, -8F);
        Shape11.setTextureSize(64, 128);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 0, 0);
        Shape12.addBox(0F, 0F, 0F, 2, 14, 1);
        Shape12.setRotationPoint(-7F, 10F, -8F);
        Shape12.setTextureSize(64, 128);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 0, 0);
        Shape13.addBox(0F, 0F, 0F, 10, 5, 1);
        Shape13.setRotationPoint(-5F, 10F, -8F);
        Shape13.setTextureSize(64, 128);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 0, 0);
        Shape14.addBox(0F, 0F, 0F, 1, 14, 1);
        Shape14.setRotationPoint(-7F, 10F, -7F);
        Shape14.setTextureSize(64, 128);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 0F, 0F);
        Shape15 = new ModelRenderer(this, 0, 0);
        Shape15.addBox(0F, 0F, 0F, 1, 14, 1);
        Shape15.setRotationPoint(-7F, 10F, 6F);
        Shape15.setTextureSize(64, 128);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 0F, 0F);
        Shape16 = new ModelRenderer(this, 0, 0);
        Shape16.addBox(0F, 0F, 0F, 1, 14, 1);
        Shape16.setRotationPoint(6F, 10F, 6F);
        Shape16.setTextureSize(64, 128);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, 0F, 0F);
        Shape17 = new ModelRenderer(this, 0, 0);
        Shape17.addBox(0F, 0F, 0F, 1, 14, 1);
        Shape17.setRotationPoint(6F, 10F, -7F);
        Shape17.setTextureSize(64, 128);
        Shape17.mirror = true;
        setRotation(Shape17, 0F, 0F, 0F);
        Shape18 = new ModelRenderer(this, 0, 0);
        Shape18.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape18.setRotationPoint(-5F, 15F, -8F);
        Shape18.setTextureSize(64, 128);
        Shape18.mirror = true;
        setRotation(Shape18, 0F, 0F, 0F);
        Shape19 = new ModelRenderer(this, 0, 0);
        Shape19.addBox(0F, 0F, 0F, 2, 2, 1);
        Shape19.setRotationPoint(3F, 15F, -8F);
        Shape19.setTextureSize(64, 128);
        Shape19.mirror = true;
        setRotation(Shape19, 0F, 0F, 0F);
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
        Shape6.render(f5);
        Shape7.render(f5);
        Shape8.render(f5);
        Shape9.render(f5);
        Shape10.render(f5);
        Shape11.render(f5);
        Shape12.render(f5);
        Shape13.render(f5);
        Shape14.render(f5);
        Shape15.render(f5);
        Shape16.render(f5);
        Shape17.render(f5);
        Shape18.render(f5);
        Shape19.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    }

}

