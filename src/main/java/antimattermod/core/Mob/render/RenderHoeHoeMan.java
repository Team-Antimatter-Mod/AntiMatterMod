package antimattermod.core.Mob.render;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Mob.EntityHoeHoeMan;
import antimattermod.core.Mob.model.ModelHoeHoeMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

/**
 * Created by worldofthetakumi on 2016/10/26.
 */
public class RenderHoeHoeMan extends RenderLiving {

    public RenderHoeHoeMan(){
        super(new ModelHoeHoeMan(),0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity){
        return new ResourceLocation(AntiMatterModCore.MOD_ID+":textures/mobs/HoeHoeMan.png");
    }

    public void doRender(EntityHoeHoeMan entity,double d0,double d1,double d2,float f0,float f1){
        BossStatus.setBossStatus(entity,false);
        super.doRender((EntityLiving)entity,d0,d1,d2,f0,f1);
    }

    public void doRender(EntityLivingBase livingBase,double d0,double d1,double d2,float f0, float f1){
        this.doRender((EntityHoeHoeMan)livingBase,d0,d1,d2,f0,f1);
    }

    public void doRender(EntityLiving living,double d0, double d1,double d2,float f0,float f1){
        this.doRender((EntityHoeHoeMan)living,d0,d1,d2,f0,f1);
    }
}
