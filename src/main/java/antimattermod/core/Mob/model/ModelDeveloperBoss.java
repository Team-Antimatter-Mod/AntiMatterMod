package antimattermod.core.Mob.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

/**
 * 開発者ボスのモデルのひな型
 * <br>Created by Raiti-chan on 2016/12/29.
 *
 * @author Raiti-chan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@SideOnly(Side.CLIENT)
public class ModelDeveloperBoss extends ModelBase {
    public ModelRenderer bipedHead;
    public ModelRenderer bipedHeadwear;
    
    public ModelRenderer bipedBodyInner;
    public ModelRenderer bipedBody;
    
    public ModelRenderer bipedRightArmInner;
    public ModelRenderer bipedLeftArmInner;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    
    public ModelRenderer bipedRightLegInner;
    public ModelRenderer bipedLeftLegInner;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    
    public ModelRenderer bipedEars;
    public ModelRenderer bipedCloak;
    /**
     * Records whether the model should be rendered holding an item in the left hand, and if that item is a block.
     */
    public int heldItemLeft;
    
    /**
     * Records whether the model should be rendered holding an item in the right hand, and if that item is a block.
     */
    public int heldItemRight;
    
    
    public boolean isSneak;
    /**
     * Records whether the model should be rendered aiming a bow.
     */
    public boolean aimedBow;
    
    public ModelDeveloperBoss() {
        this(0.0F);
    }
    
    public ModelDeveloperBoss(float p_i1148_1_) {
        this(p_i1148_1_, 0.0F, 64, 64);
    }
    
    public ModelDeveloperBoss(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_) {
        this.textureWidth = p_i1149_3_;
        this.textureHeight = p_i1149_4_;
        this.bipedCloak = new ModelRenderer(this, 0, 0);
        this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i1149_1_);
        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i1149_1_);
        
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_ + 0.2F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        
        this.bipedBodyInner = new ModelRenderer(this, 16, 16);
        this.bipedBodyInner.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1149_1_);
        this.bipedBodyInner.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 32);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1149_1_ + 0.2F);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        
        this.bipedRightArmInner = new ModelRenderer(this, 40, 16);
        this.bipedRightArmInner.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedRightArmInner.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 32);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_ + 0.2F);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
        
        this.bipedLeftArmInner = new ModelRenderer(this, 32, 48);
        this.bipedLeftArmInner.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedLeftArmInner.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 48, 48);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_ + 0.2F);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
        
        this.bipedRightLegInner = new ModelRenderer(this, 0, 16);
        this.bipedRightLegInner.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedRightLegInner.setRotationPoint(-1.9F, 12.0F + p_i1149_2_, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 32);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_ + 0.2F);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + p_i1149_2_, 0.0F);
        
        this.bipedLeftLegInner = new ModelRenderer(this, 16, 48);
        this.bipedLeftLegInner.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedLeftLegInner.setRotationPoint(1.9F, 12.0F + p_i1149_2_, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 48);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_ + 0.2F);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + p_i1149_2_, 0.0F);
    }
    
    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        
        if (this.isChild) {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHead.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.bipedBodyInner.render(p_78088_7_);
            this.bipedRightArmInner.render(p_78088_7_);
            this.bipedLeftArmInner.render(p_78088_7_);
            this.bipedRightLegInner.render(p_78088_7_);
            this.bipedLeftLegInner.render(p_78088_7_);
            
            this.bipedHeadwear.render(p_78088_7_);
            this.bipedBody.render(p_78088_7_);
            this.bipedRightArm.render(p_78088_7_);
            this.bipedLeftArm.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
            GL11.glPopMatrix();
        } else {
            this.bipedHead.render(p_78088_7_);
            this.bipedBodyInner.render(p_78088_7_);
            this.bipedRightArmInner.render(p_78088_7_);
            this.bipedLeftArmInner.render(p_78088_7_);
            this.bipedRightLegInner.render(p_78088_7_);
            this.bipedLeftLegInner.render(p_78088_7_);
            
            this.bipedHeadwear.render(p_78088_7_);
            this.bipedBody.render(p_78088_7_);
            this.bipedRightArm.render(p_78088_7_);
            this.bipedLeftArm.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
        }
    }
    
    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.bipedHead.rotateAngleY = p_78087_4_ / (180F / (float) Math.PI);
        this.bipedHead.rotateAngleX = p_78087_5_ / (180F / (float) Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        
        this.bipedRightArmInner.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float) Math.PI) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
        
        this.bipedLeftArmInner.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
        this.bipedLeftArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
        
        this.bipedRightArmInner.rotateAngleZ = 0.0F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        
        this.bipedLeftArmInner.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        
        this.bipedRightLegInner.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.bipedRightLeg.rotateAngleX = this.bipedRightLegInner.rotateAngleX;
        
        this.bipedLeftLegInner.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float) Math.PI) * 1.4F * p_78087_2_;
        this.bipedLeftLeg.rotateAngleX = this.bipedLeftLegInner.rotateAngleX;
        
        this.bipedRightLegInner.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLegInner.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        
        if (this.isRiding) {
            this.bipedRightArmInner.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotateAngleX += -((float) Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            this.bipedRightLegInner.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleX = this.bipedRightLegInner.rotateAngleX;
            
            this.bipedLeftLegInner.rotateAngleX = -((float) Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = this.bipedLeftLegInner.rotateAngleX;
            
            this.bipedRightLegInner.rotateAngleY = ((float) Math.PI / 10F);
            this.bipedRightLeg.rotateAngleY = this.bipedRightLegInner.rotateAngleY;
            
            this.bipedLeftLegInner.rotateAngleY = -((float) Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = this.bipedLeftLegInner.rotateAngleY;
        }
        
        if (this.heldItemLeft != 0) {
            this.bipedLeftArmInner.rotateAngleX = this.bipedLeftArmInner.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * (float) this.heldItemLeft;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
        }
        
        if (this.heldItemRight != 0) {
            this.bipedRightArmInner.rotateAngleX = this.bipedRightArmInner.rotateAngleX * 0.5F - ((float) Math.PI / 10F) * (float) this.heldItemRight;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
        }
        
        this.bipedRightArmInner.rotateAngleY = 0.0F;
        this.bipedRightArm.rotateAngleY = 0.0F;
        
        this.bipedLeftArmInner.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        
        float f6;
        float f7;
        
        if (this.onGround > -9990.0F) {
            f6 = this.onGround;
            this.bipedBodyInner.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float) Math.PI * 2.0F) * 0.2F;
            this.bipedBody.rotateAngleY = this.bipedBodyInner.rotateAngleY;
            
            this.bipedRightArmInner.rotationPointZ = MathHelper.sin(this.bipedBodyInner.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleZ = this.bipedRightArmInner.rotateAngleZ;
            this.bipedRightArmInner.rotationPointX = -MathHelper.cos(this.bipedBodyInner.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotationPointZ = -MathHelper.sin(this.bipedBodyInner.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotateAngleZ = this.bipedLeftArmInner.rotateAngleZ;
            this.bipedLeftArmInner.rotationPointX = MathHelper.cos(this.bipedBodyInner.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            this.bipedRightArmInner.rotateAngleY += this.bipedBodyInner.rotateAngleY;
            this.bipedRightArm.rotateAngleY = this.bipedRightArmInner.rotateAngleY;
            
            this.bipedLeftArmInner.rotateAngleY += this.bipedBodyInner.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = this.bipedLeftArmInner.rotateAngleY;
            this.bipedLeftArmInner.rotateAngleX += this.bipedBodyInner.rotateAngleY;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float) Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArmInner.rotateAngleX = (float) ((double) this.bipedRightArmInner.rotateAngleX - ((double) f7 * 1.2D + (double) f8));
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            this.bipedRightArmInner.rotateAngleY += this.bipedBodyInner.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleY = this.bipedRightArmInner.rotateAngleY;
            this.bipedRightArmInner.rotateAngleZ = MathHelper.sin(this.onGround * (float) Math.PI) * -0.4F;
            this.bipedRightArm.rotateAngleZ = this.bipedRightArmInner.rotateAngleZ;
        }
        
        if (this.isSneak) {
            this.bipedBodyInner.rotateAngleX = 0.5F;
            this.bipedBody.rotateAngleX = 0.5F;
            
            this.bipedRightArmInner.rotateAngleX += 0.4F;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            this.bipedRightLegInner.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointZ =4.0F;
            
            this.bipedLeftLegInner.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            
            this.bipedRightLegInner.rotationPointY = 9.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            
            this.bipedLeftLegInner.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
        } else {
            this.bipedBodyInner.rotateAngleX = 0.0F;
            this.bipedBody.rotateAngleX = 0.0F;
            
            this.bipedRightLegInner.rotationPointZ = 0.1F;
            this.bipedRightLeg.rotationPointZ = 0.1F;
            
            this.bipedLeftLegInner.rotationPointZ = 0.1F;
            this.bipedLeftLegInner.rotationPointZ = 0.1F;
            
            this.bipedRightLegInner.rotationPointY = 12.0F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            
            this.bipedLeftLegInner.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
        }
        
        this.bipedRightArmInner.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleZ = this.bipedRightArmInner.rotateAngleZ;
        
        this.bipedLeftArmInner.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ = this.bipedLeftArmInner.rotateAngleZ;
        
        this.bipedRightArmInner.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
        
        this.bipedLeftArmInner.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
        
        if (this.aimedBow) {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArmInner.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            
            this.bipedLeftArmInner.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            
            this.bipedRightArmInner.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedRightArm.rotateAngleY = this.bipedRightArmInner.rotateAngleY;
            
            this.bipedLeftArmInner.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedLeftArm.rotateAngleY = this.bipedLeftArmInner.rotateAngleY;
            
            this.bipedRightArmInner.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            this.bipedRightArmInner.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
            
            this.bipedRightArmInner.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleZ = this.bipedRightArmInner.rotateAngleZ;
            
            this.bipedLeftArmInner.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ = this.bipedLeftArmInner.rotateAngleZ;
            
            this.bipedRightArmInner.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
            this.bipedRightArm.rotateAngleX = this.bipedRightArmInner.rotateAngleX;
            
            this.bipedLeftArmInner.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArmInner.rotateAngleX;
        }
    }
    
    /**
     * renders the ears (specifically, deadmau5's)
     */
    public void renderEars(float p_78110_1_) {
        this.bipedEars.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedEars.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedEars.rotationPointX = 0.0F;
        this.bipedEars.rotationPointY = 0.0F;
        this.bipedEars.render(p_78110_1_);
    }
    
    /**
     * Renders the cloak of the current biped (in most cases, it's a player)
     */
    public void renderCloak(float p_78111_1_) {
        this.bipedCloak.render(p_78111_1_);
    }
}
