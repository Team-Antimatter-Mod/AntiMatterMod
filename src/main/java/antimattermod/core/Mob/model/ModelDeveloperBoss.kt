package antimattermod.core.Mob.model

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity

class ModelDeveloperBoss : ModelBase() {
    //fields
    internal var head: ModelRenderer
    internal var body: ModelRenderer
    internal var rightarm: ModelRenderer
    internal var leftarm: ModelRenderer
    internal var rightleg: ModelRenderer
    internal var leftleg: ModelRenderer

    init {
        textureWidth = 64
        textureHeight = 32

        head = ModelRenderer(this, 0, 0)
        head.addBox(-4f, -8f, -4f, 8, 8, 8)
        head.setRotationPoint(0f, 0f, 0f)
        head.setTextureSize(64, 32)
        head.mirror = true
        setRotation(head, 0f, 0f, 0f)
        body = ModelRenderer(this, 16, 16)
        body.addBox(-4f, 0f, -2f, 8, 12, 4)
        body.setRotationPoint(0f, 0f, 0f)
        body.setTextureSize(64, 32)
        body.mirror = true
        setRotation(body, 0f, 0f, 0f)
        rightarm = ModelRenderer(this, 40, 16)
        rightarm.addBox(-3f, -2f, -2f, 4, 12, 4)
        rightarm.setRotationPoint(-5f, 2f, 0f)
        rightarm.setTextureSize(64, 32)
        rightarm.mirror = true
        setRotation(rightarm, 0f, 0f, 0f)
        leftarm = ModelRenderer(this, 40, 16)
        leftarm.addBox(-1f, -2f, -2f, 4, 12, 4)
        leftarm.setRotationPoint(5f, 2f, 0f)
        leftarm.setTextureSize(64, 32)
        leftarm.mirror = true
        setRotation(leftarm, 0f, 0f, 0f)
        rightleg = ModelRenderer(this, 0, 16)
        rightleg.addBox(-2f, 0f, -2f, 4, 12, 4)
        rightleg.setRotationPoint(-2f, 12f, 0f)
        rightleg.setTextureSize(64, 32)
        rightleg.mirror = true
        setRotation(rightleg, 0f, 0f, 0f)
        leftleg = ModelRenderer(this, 0, 16)
        leftleg.addBox(-2f, 0f, -2f, 4, 12, 4)
        leftleg.setRotationPoint(2f, 12f, 0f)
        leftleg.setTextureSize(64, 32)
        leftleg.mirror = true
        setRotation(leftleg, 0f, 0f, 0f)
    }

    override fun render(entity: Entity, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.render(entity, f, f1, f2, f3, f4, f5)
        setRotationAngles(f, f1, f2, f3, f4, f5)
        head.render(f5)
        body.render(f5)
        rightarm.render(f5)
        leftarm.render(f5)
        rightleg.render(f5)
        leftleg.render(f5)
    }

    private fun setRotation(model: ModelRenderer, x: Float, y: Float, z: Float) {
        model.rotateAngleX = x
        model.rotateAngleY = y
        model.rotateAngleZ = z
    }

    fun setRotationAngles(f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null)
    }

}
