package antimattermod.core.Model

import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.entity.Entity

/**
 * @author kojin15.
 */
class ModelLaser : ModelBase() {

    internal var xLine: ModelRenderer? = null
    internal var yLine: ModelRenderer? = null
    internal var zLine: ModelRenderer? = null

    init {
        textureWidth = 64
        textureHeight = 32

        xLine = ModelRenderer(this, 0, 0)
        xLine!!.addBox(-8f, -1f, -1f, 16, 2, 2)
        xLine!!.setRotationPoint(0f, 16f, 0f)
        xLine!!.setTextureSize(64, 32)
        xLine!!.mirror = true
        setRotation(xLine, 0.0349066f, 0f, 0f)
        yLine = ModelRenderer(this, 0, 0)
        yLine!!.addBox(-1f, -8f, -1f, 2, 16, 2)
        yLine!!.setRotationPoint(0f, 16f, 0f)
        yLine!!.setTextureSize(64, 32)
        yLine!!.mirror = true
        setRotation(yLine, 0f, 0f, 0f)
        zLine = ModelRenderer(this, 0, 0)
        zLine!!.addBox(-1f, -1f, -8f, 2, 2, 16)
        zLine!!.setRotationPoint(0f, 16f, 0f)
        zLine!!.setTextureSize(64, 32)
        zLine!!.mirror = true
        setRotation(zLine, 0f, 0f, 0f)
    }

    override fun render(entity: Entity, f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.render(entity, f, f1, f2, f3, f4, f5)
        setRotationAngles(f, f1, f2, f3, f4, f5)
        xLine!!.render(f5)
        yLine!!.render(f5)
        zLine!!.render(f5)
    }

    fun renderXLine(f: Float) {
        xLine!!.render(f)
    }

    fun renderYLine(f: Float) {
        yLine!!.render(f)
    }

    fun renderZLine(f: Float) {
        zLine!!.render(f)
    }

    private fun setRotation(model: ModelRenderer?, x: Float, y: Float, z: Float) {
        model!!.rotateAngleX = x
        model.rotateAngleY = y
        model.rotateAngleZ = z
    }

    fun setRotationAngles(f: Float, f1: Float, f2: Float, f3: Float, f4: Float, f5: Float) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null)
    }

}