package antimattermod.core.Model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAMCell extends ModelBase
{
  //fields
    ModelRenderer cellCase1;
    ModelRenderer cellCase2;
    ModelRenderer cellPiller1;
    ModelRenderer cellPiller2;
    ModelRenderer cellPiller3;
    ModelRenderer cellPiller4;
    ModelRenderer cellCase3;
    ModelRenderer cellCase4;
    ModelRenderer antiMatter;
    ModelRenderer cellGlass1;
    ModelRenderer cellGlass2;
    ModelRenderer cellGlass3;
    ModelRenderer cellGlass4;
  
  public ModelAMCell()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      cellCase1 = new ModelRenderer(this, 0, 0);
      cellCase1.addBox(0F, 0F, 0F, 8, 4, 8);
      cellCase1.setRotationPoint(-4F, 0F, -4F);
      cellCase1.setTextureSize(64, 32);
      cellCase1.mirror = true;
      setRotation(cellCase1, 0F, 0F, 0F);
      cellCase2 = new ModelRenderer(this, 32, 0);
      cellCase2.addBox(0F, 0F, 0F, 8, 4, 8);
      cellCase2.setRotationPoint(-4F, 12F, -4F);
      cellCase2.setTextureSize(64, 32);
      cellCase2.mirror = true;
      setRotation(cellCase2, 0F, 0F, 0F);
      cellPiller1 = new ModelRenderer(this, 0, 12);
      cellPiller1.addBox(0F, 0F, 0F, 1, 8, 1);
      cellPiller1.setRotationPoint(-3.5F, 4F, -3.5F);
      cellPiller1.setTextureSize(64, 32);
      cellPiller1.mirror = true;
      setRotation(cellPiller1, 0F, 0F, 0F);
      cellPiller2 = new ModelRenderer(this, 0, 12);
      cellPiller2.addBox(0F, 0F, 0F, 1, 8, 1);
      cellPiller2.setRotationPoint(2.5F, 4F, -3.5F);
      cellPiller2.setTextureSize(64, 32);
      cellPiller2.mirror = true;
      setRotation(cellPiller2, 0F, 0F, 0F);
      cellPiller3 = new ModelRenderer(this, 0, 12);
      cellPiller3.addBox(0F, 0F, 0F, 1, 8, 1);
      cellPiller3.setRotationPoint(-3.5F, 4F, 2.5F);
      cellPiller3.setTextureSize(64, 32);
      cellPiller3.mirror = true;
      setRotation(cellPiller3, 0F, 0F, 0F);
      cellPiller4 = new ModelRenderer(this, 0, 12);
      cellPiller4.addBox(0F, 0F, 0F, 1, 8, 1);
      cellPiller4.setRotationPoint(2.5F, 4F, 2.5F);
      cellPiller4.setTextureSize(64, 32);
      cellPiller4.mirror = true;
      setRotation(cellPiller4, 0F, 0F, 0F);
      cellCase3 = new ModelRenderer(this, 16, 12);
      cellCase3.addBox(0F, 0F, 0F, 6, 1, 6);
      cellCase3.setRotationPoint(-3F, -1F, -3F);
      cellCase3.setTextureSize(64, 32);
      cellCase3.mirror = true;
      setRotation(cellCase3, 0F, 0F, 0F);
      cellCase4 = new ModelRenderer(this, 16, 19);
      cellCase4.addBox(0F, 0F, 0F, 6, 1, 6);
      cellCase4.setRotationPoint(-3F, 16F, -3F);
      cellCase4.setTextureSize(64, 32);
      cellCase4.mirror = true;
      setRotation(cellCase4, 0F, 0F, 0F);
      antiMatter = new ModelRenderer(this, 40, 12);
      antiMatter.addBox(0F, 0F, 0F, 4, 4, 4);
      antiMatter.setRotationPoint(-2F, 6F, -2F);
      antiMatter.setTextureSize(64, 32);
      antiMatter.mirror = true;
      setRotation(antiMatter, 0F, 0F, 0F);
      cellGlass1 = new ModelRenderer(this, 4, 12);
      cellGlass1.addBox(0F, 0F, 0F, 0, 8, 6);
      cellGlass1.setRotationPoint(-3F, 4F, -3F);
      cellGlass1.setTextureSize(64, 32);
      cellGlass1.mirror = true;
      setRotation(cellGlass1, 0F, 0F, 0F);
      cellGlass2 = new ModelRenderer(this, 4, 12);
      cellGlass2.addBox(0F, 0F, 0F, 0, 8, 6);
      cellGlass2.setRotationPoint(-3F, 4F, 3F);
      cellGlass2.setTextureSize(64, 32);
      cellGlass2.mirror = true;
      setRotation(cellGlass2, 0F, 1.570796F, 0F);
      cellGlass3 = new ModelRenderer(this, 4, 12);
      cellGlass3.addBox(0F, 0F, 0F, 0, 8, 6);
      cellGlass3.setRotationPoint(3F, 4F, 3F);
      cellGlass3.setTextureSize(64, 32);
      cellGlass3.mirror = true;
      setRotation(cellGlass3, 0F, 3.141593F, 0F);
      cellGlass4 = new ModelRenderer(this, 4, 12);
      cellGlass4.addBox(0F, 0F, 0F, 0, 8, 6);
      cellGlass4.setRotationPoint(3F, 4F, -3F);
      cellGlass4.setTextureSize(64, 32);
      cellGlass4.mirror = true;
      setRotation(cellGlass4, 0F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    cellCase1.render(f5);
    cellCase2.render(f5);
    cellPiller1.render(f5);
    cellPiller2.render(f5);
    cellPiller3.render(f5);
    cellPiller4.render(f5);
    cellCase3.render(f5);
    cellCase4.render(f5);
    antiMatter.render(f5);
    cellGlass1.render(f5);
    cellGlass2.render(f5);
    cellGlass3.render(f5);
    cellGlass4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}
