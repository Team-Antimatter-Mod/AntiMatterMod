package antimattermod.core.Render

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Energy.Filler.TileMarker
import antimattermod.core.Model.ModelLaser
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11

/**
 * @author kojin15.
 */
class RenderMarker : TileEntitySpecialRenderer() {

    val redLaser = ResourceLocation("${AntiMatterModCore.MOD_ID}:textures/models/laser_0.png")
    val purpleLaser = ResourceLocation("${AntiMatterModCore.MOD_ID}:textures/models/laser_1.png")
    val laser = ResourceLocation("${AntiMatterModCore.MOD_ID}:textures/models/laser_2.png")

    override fun renderTileEntityAt(tileEntity: TileEntity?, x: Double, y: Double, z: Double, f: Float) {
        renderLaser(tileEntity as TileMarker, x, y, z)
    }

    private fun renderLaser(tile: TileMarker, x: Double, y: Double, z: Double) {
        val model = ModelLaser()

        this.bindTexture(redLaser)
        for (i in 0..5) {
            renderXLine(model, x + 0.5 + i, y, z)
        }


    }

    fun renderXLine(model: ModelLaser, x: Double, y: Double, z: Double) {
        GL11.glPushMatrix()
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5)
        GL11.glRotated(180.0, 0.0, 0.0, 1.0)
        model.renderXLine(0.0625f)
        GL11.glPopMatrix()
    }

}