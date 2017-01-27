package antimattermod.core.Fluid.tank

import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

/**
 * @author kojin15.
 */
class RenderTileTank : TileEntitySpecialRenderer() {

    override fun renderTileEntityAt(tile: TileEntity?, x: Double, y: Double, z: Double, ff: Float) {
        renderFluid(tile as TileBasicTank, x, y, z)
    }

    fun renderFluid(tile: TileBasicTank, x: Double, y: Double, z: Double) {
        val tessellator: Tessellator = Tessellator.instance
        val fluidIcon: IIcon? = tile.getFluidIcon()

        val tankMinX = x + 0.126
        val tankMaxX = x + 0.874
        val tankMinZ = z + 0.126
        val tankMaxZ = z + 0.874
        if (fluidIcon != null) {
            GL11.glPushMatrix()
            GL11.glEnable(GL12.GL_RESCALE_NORMAL)
            GL11.glEnable(GL11.GL_BLEND)
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
            GL11.glColor4f(2.0f, 2.0f, 2.0f, 0.75f)

            val minU: Double = fluidIcon.minU.toDouble()
            val maxU: Double = fluidIcon.maxU.toDouble()
            val minV: Double = fluidIcon.minV.toDouble()
            val maxV: Double = fluidIcon.maxV.toDouble()

            val c = 0.98 / tile.maxCapacity
            val high = (c * (tile.productTank.fluidAmount)) + 0.01

            val iconU = maxU - minU
            val iconC = iconU / tile.maxCapacity
            val iconHighU = (iconC * (tile.productTank.fluidAmount)) + minU

            val tankMaxV = (((maxV - minV) / 10) * 8 ) + minV

            bindTexture(TextureMap.locationBlocksTexture)

            tessellator.startDrawingQuads()
            tessellator.addVertexWithUV(tankMinX, y + high, tankMinZ, minU, minV)
            tessellator.addVertexWithUV(tankMinX, y + high, tankMaxZ, minU, maxV)
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMaxZ, maxU, maxV)
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMinZ, maxU, minV)
            tessellator.draw()


            tessellator.startDrawingQuads()
            tessellator.addVertexWithUV(tankMinX, y + 0.01, tankMinZ, minU, minV)
            tessellator.addVertexWithUV(tankMinX, y + 0.01, tankMaxZ, minU, tankMaxV)
            tessellator.addVertexWithUV(tankMinX, y + high, tankMaxZ, iconHighU, tankMaxV)
            tessellator.addVertexWithUV(tankMinX, y + high, tankMinZ, iconHighU, minV)
            tessellator.draw()

            tessellator.startDrawingQuads()
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMinZ, iconHighU, minV)
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMaxZ, iconHighU, tankMaxV)
            tessellator.addVertexWithUV(tankMaxX, y + 0.01, tankMaxZ, minU, tankMaxV)
            tessellator.addVertexWithUV(tankMaxX, y + 0.01, tankMinZ, minU, minV)
            tessellator.draw()

            tessellator.startDrawingQuads()
            tessellator.addVertexWithUV(tankMinX, y + high, tankMinZ, iconHighU, minV)
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMinZ, iconHighU, tankMaxV)
            tessellator.addVertexWithUV(tankMaxX, y + 0.01, tankMinZ, minU, tankMaxV)
            tessellator.addVertexWithUV(tankMinX, y + 0.01, tankMinZ, minU, minV)
            tessellator.draw()

            tessellator.startDrawingQuads()
            tessellator.addVertexWithUV(tankMinX, y + 0.01, tankMaxZ, minU, minV)
            tessellator.addVertexWithUV(tankMaxX, y + 0.01, tankMaxZ, minU, tankMaxV)
            tessellator.addVertexWithUV(tankMaxX, y + high, tankMaxZ, iconHighU, tankMaxV)
            tessellator.addVertexWithUV(tankMinX, y + high, tankMaxZ, iconHighU, minV)
            tessellator.draw()

            GL11.glDisable(GL12.GL_RESCALE_NORMAL)
            GL11.glDisable(GL11.GL_BLEND)
            GL11.glPopMatrix()

        }
    }

}