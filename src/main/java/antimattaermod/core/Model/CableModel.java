package antimattaermod.core.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sun.security.provider.SHA;

public class CableModel extends ModelBase{

    private ModelRenderer Shape;

    public CableModel()
    {
        textureWidth = 64;
        textureHeight = 32;
        Shape = new ModelRenderer(this, 0, 0);
        
        Shape.setRotationPoint(-1.5F, 21F, -8F);
        Shape.setTextureSize(64, 32);
        Shape.mirror = true;
        setRotation(Shape, 0F, 0F, 0F);
    }

    public void connectNorth(){
        Shape.addBox(0,-6.5f,-1.5f,3,3,8);
    }

    public void connectSouth(){
        Shape.addBox(0,-6.5f,9.5f,3,3,8);
    }

    public void connectEast(){
        Shape.addBox(-6.5f,-6.5f,6.5f,8,3,3);
    }

    public void connectWest(){
        Shape.addBox(3f,-6.5f,6.5f,8,3,3);
    }

    public void connectUp(){
        Shape.addBox(0f,-14.5f,6.5f,3,8,3);
    }

    public void connectDown(){
        Shape.addBox(0f,-3.5f,6.5f,3,8,3);
    }

    public void connectNorthToSouth(){
//        Shape.addBox(0,-6.5f,0,3,3,16);
        connectNorth();
        connectSouth();
    }

    public void connectEastToWest(){
//        Shape.addBox(-7.5f,-6.5f,6.5f,16,3,3);
        connectEast();
        connectWest();
    }

    public void connectUpToDown(){
//        Shape.addBox(0,-13f,6.5f,3,16,3);
        connectUp();
        connectDown();
    }

    public void nonConnect(){
        Shape.addBox(0F, -6.5F, 6.5F, 3, 3, 3);
    }

    public void resetConnect(){
        Shape = new ModelRenderer(this,0,0);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        Shape.render(f5);
    }

    public void renderModel(float f){
        Shape.render(f);
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

    /*
    何もなし
    Shape.addBox(0F, -6.5F, 6.5F, 3, 3, 3);

    一箇所接続 @x
     East
     Shape.addBox(-5.5f,-6.5f,6.5f,11,3,3);
     West
     Shape.addBox(0f,-6.5f,6.5f,11,3,3);

     一箇所接続 @y
     Down
     Shape.addBox(0f,-6.5f,6.5f,3,11,3);
     Up
     Shape.addBox(0f,-13,6.5f,3,11,3);

     一箇所接続 @z
     North
     Shape.addBox(0,-6.5f,0,3,3,11);
     South
     Shape.addBox(0,-6.5f,5.5f,3,3,11);

     一本 @x
     Shape.addBox(-8,-6.5f,6.5f,16,3,3);
     一本 @y
     Shape.addBox(0,-13f,6.5f,3,16,3);
     一本 @z
     Shape.addBox(0,-6.5f,0,3,3,16);
     */
}
