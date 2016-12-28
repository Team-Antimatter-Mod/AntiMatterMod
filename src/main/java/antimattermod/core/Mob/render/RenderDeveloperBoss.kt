package antimattermod.core.Mob.render

import antimattermod.core.Mob.EntityDeveloperBoss
import antimattermod.core.Mob.model.ModelDeveloperBoss
import net.minecraft.client.Minecraft
import net.minecraft.client.model.ModelBase
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLiving
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.boss.BossStatus
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

/**
 * Created by kojin15.
 */
class RenderDeveloperBoss(model: ModelBase) : RenderLiving(model, 0.5f) {

    val skin = ResourceLocation(null, "config/AntiMatterMod/developerBossTexture/Raitichan.png")

    override fun getEntityTexture(p_110775_1_: Entity): ResourceLocation {
        return skin
    }

    fun doRender(entity: EntityDeveloperBoss, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        BossStatus.setBossStatus(entity, false)
        super.doRender(entity as EntityLiving, d0, d1, d2, f0, f1)
    }

    override fun doRender(livingBase: EntityLivingBase, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        this.doRender(livingBase as EntityDeveloperBoss, d0, d1, d2, f0, f1)
    }

    override fun doRender(living: EntityLiving, d0: Double, d1: Double, d2: Double, f0: Float, f1: Float) {
        this.doRender(living as EntityDeveloperBoss, d0, d1, d2, f0, f1)
    }
}