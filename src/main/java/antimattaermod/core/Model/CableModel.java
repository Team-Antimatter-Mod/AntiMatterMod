package antimattaermod.core.Model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CableModel extends ModelBase{

    private ModelRenderer Shape1;
    private ModelRenderer Shape2;
    private ModelRenderer Shape3;
    
    private ModelRenderer center;

    public CableModel()
    {
        textureWidth = 32;
        textureHeight = 32;
        
        Shape1 = new ModelRenderer(this, 0, 10);
        Shape1.setRotationPoint(0, 16, 0);
        Shape1.setTextureSize(32, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
    
        Shape2 = new ModelRenderer(this, 0, 20);
        Shape2.setRotationPoint(0, 16, 0);
        Shape2.setTextureSize(32, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
    
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.setRotationPoint(0, 16, 0);
        Shape3.setTextureSize(32, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        
        center = new ModelRenderer(this, 16,0);
        center.setRotationPoint(0,16,0);
        center.setTextureSize(32,32);
        center.addBox(-2F, -2F, -2F, 4, 4, 4);
        Shape1.mirror = true;
        setRotation(center,0F,0F,0F);
    }

    public void connectNorth(){
        Shape1.addBox(-2f,-2f,-8f,4,4,6);
    }

    public void connectSouth(){
        Shape1.addBox(-2f,-2f,2f,4,4,6);
    }

    public void connectEast(){
        Shape2.addBox(-8f,-2f,-2f,6,4,4);
    }

    public void connectWest(){
        Shape2.addBox(2f,-2f,-2f,6,4,4);
    }

    public void connectUp(){
        Shape3.addBox(-2f,-8f,-2f,4,6,4);
    }

    public void connectDown(){
        Shape3.addBox(-2f,2f,-2f,4,6,4);
    }

    public void connectNorthToSouth(){
//        Shape1.addBox(0,-6.5f,0,3,3,16);
        connectNorth();
        connectSouth();
    }

    public void connectEastToWest(){
//        Shape1.addBox(-7.5f,-6.5f,6.5f,16,3,3);
        connectEast();
        connectWest();
    }

    public void connectUpToDown(){
//        Shape1.addBox(0,-13f,6.5f,3,16,3);
        connectUp();
        connectDown();
    }

    public void nonConnect(){
        
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        center.render(f5);
    }

    public void renderModel(float f){
        Shape1.render(f);
        Shape2.render(f);
        Shape3.render(f);
        center.render(f);
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
    Shape1.addBox(0F, -6.5F, 6.5F, 3, 3, 3);

    一箇所接続 @x
     East
     Shape1.addBox(-5.5f,-6.5f,6.5f,11,3,3);
     West
     Shape1.addBox(0f,-6.5f,6.5f,11,3,3);

     一箇所接続 @y
     Down
     Shape1.addBox(0f,-6.5f,6.5f,3,11,3);
     Up
     Shape1.addBox(0f,-13,6.5f,3,11,3);

     一箇所接続 @z
     North
     Shape1.addBox(0,-6.5f,0,3,3,11);
     South
     Shape1.addBox(0,-6.5f,5.5f,3,3,11);

     一本 @x
     Shape1.addBox(-8,-6.5f,6.5f,16,3,3);
     一本 @y
     Shape1.addBox(0,-13f,6.5f,3,16,3);
     一本 @z
     Shape1.addBox(0,-6.5f,0,3,3,16);
     */
}
