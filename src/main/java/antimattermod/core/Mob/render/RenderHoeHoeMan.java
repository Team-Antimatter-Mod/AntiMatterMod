package antimattermod.core.Mob.render;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Mob.model.ModelHoeHoeMan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
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
}
