package antimattaermod.core.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.util.ForgeDirection;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CableModel extends ModelBase{

    ModelRenderer Shape1;

    public CableModel()
    {
        textureWidth = 64;
        textureHeight = 32;
        Shape1 = new ModelRenderer(this, 0, 0);
        //何もなし
        //Shape1.addBox(0F, -6.5F, 6.5F, 3, 3, 3);
        //一箇所接続 @x
        //East
        //Shape1.addBox(-5.5f,-6.5f,6.5f,11,3,3);
        //West
        //Shape1.addBox(0f,-6.5f,6.5f,11,3,3);
        //一箇所接続 @y
        //Down
        //Shape1.addBox(0f,-6.5f,6.5f,3,11,3);
        //Up
        //Shape1.addBox(0f,-13,6.5f,3,11,3);
        //一箇所接続 @z
        //North
        //Shape1.addBox(0,-6.5f,0,3,3,11);
        //South
        //Shape1.addBox(0,-6.5f,5.5f,3,3,11);
        //一本 @x
        //Shape1.addBox(-8,-6.5f,6.5f,16,3,3);
        //一本 @y
        //Shape1.addBox(0,-13f,6.5f,3,16,3);
        //一本 @z
        //Shape1.addBox(0,-6.5f,0,3,3,16);
        Shape1.setRotationPoint(-1.5F, 21F, -8F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        Shape1.render(f5);
    }

    public void renderModel(float f){
        Shape1.render(f);
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
