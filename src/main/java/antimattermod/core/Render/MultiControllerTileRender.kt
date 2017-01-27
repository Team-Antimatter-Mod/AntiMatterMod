package antimattermod.core.Render

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Block.ClayCrucible
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible
import antimattermod.core.Energy.MultiBlock.BlockMultiController
import antimattermod.core.Energy.MultiBlock.TileMultiController
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.IIcon
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

/**
 * Created by kojin15.
 */
class MultiControllerTileRender : TileEntitySpecialRenderer() {

    override fun renderTileEntityAt(tile: TileEntity?, x: Double, y: Double, z: Double, ff: Float) {
        renderAssist(tile as TileMultiController, x, y, z, ff)
    }

    private fun renderAssist(tileMultiController: TileMultiController, x: Double, y: Double, z: Double, ff: Float) {
        if (tileMultiController.isShowAssist) {
            val tessellator = Tessellator.instance

            GL11.glPushMatrix()
            GL11.glEnable(GL12.GL_RESCALE_NORMAL)
            GL11.glEnable(GL11.GL_BLEND)
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
            GL11.glColor4f(2.0f, 2.0f, 2.0f, 0.5f)

            this.bindTexture(TextureMap.locationBlocksTexture)

            val aabb = AxisAlignedBB.getBoundingBox(x, y + 1, z, x + 1, y + 2, z + 1)
            drawBlock(tessellator, aabb, Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0))
            val aaabb = AxisAlignedBB.getBoundingBox(x, y + 2, z, x + 1, y + 3, z + 1)
            drawBlock(tessellator, aaabb, Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0))
            val aabbb = AxisAlignedBB.getBoundingBox(x, y + 3, z, x + 1, y + 4, z + 1)
            drawBlock(tessellator, aabbb, Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0), Blocks.stone.getIcon(0, 0))


            when (tileMultiController.blockMeta) {
                2 -> {}
                3 -> {}
                4 -> {}
                5 -> {}
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL)
            GL11.glDisable(GL11.GL_BLEND)
            GL11.glPopMatrix()
        }


    }

    fun drawBlock(tessellator: Tessellator, aabb: AxisAlignedBB, icon0: IIcon, icon1: IIcon, icon2: IIcon, icon3: IIcon, icon4: IIcon, icon5: IIcon) {
        val minX = aabb.minX
        val maxX = aabb.maxX
        val minY = aabb.minY
        val maxY = aabb.maxY
        val minZ = aabb.minZ
        val maxZ = aabb.maxZ

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(maxX, minY, minZ, icon0.maxU.toDouble(), icon0.minV.toDouble())
        tessellator.addVertexWithUV(maxX, minY, maxZ, icon0.maxU.toDouble(), icon0.maxV.toDouble())
        tessellator.addVertexWithUV(minX, minY, maxZ, icon0.minU.toDouble(), icon0.maxV.toDouble())
        tessellator.addVertexWithUV(minX, minY, minZ, icon0.minU.toDouble(), icon0.minV.toDouble())
        tessellator.draw()

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(minX, maxY, minZ, icon1.minU.toDouble(), icon1.minV.toDouble())
        tessellator.addVertexWithUV(minX, maxY, maxZ, icon1.minU.toDouble(), icon1.maxV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, maxZ, icon1.maxU.toDouble(), icon1.maxV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, minZ, icon1.maxU.toDouble(), icon1.minV.toDouble())
        tessellator.draw()

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(minX, minY, minZ, icon2.maxU.toDouble(), icon2.maxV.toDouble())
        tessellator.addVertexWithUV(minX, maxY, minZ, icon2.maxU.toDouble(), icon2.minV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, minZ, icon2.minU.toDouble(), icon2.minV.toDouble())
        tessellator.addVertexWithUV(maxX, minY, minZ, icon2.minU.toDouble(), icon2.maxV.toDouble())
        tessellator.draw()

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(maxX, minY, maxZ, icon3.maxU.toDouble(), icon3.maxV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, maxZ, icon3.maxU.toDouble(), icon3.minV.toDouble())
        tessellator.addVertexWithUV(minX, maxY, maxZ, icon3.minU.toDouble(), icon3.minV.toDouble())
        tessellator.addVertexWithUV(minX, minY, maxZ, icon3.minU.toDouble(), icon3.maxV.toDouble())
        tessellator.draw()

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(minX, minY, minZ, icon4.minU.toDouble(), icon4.maxV.toDouble())
        tessellator.addVertexWithUV(minX, minY, maxZ, icon4.maxU.toDouble(), icon4.maxV.toDouble())
        tessellator.addVertexWithUV(minX, maxY, maxZ, icon4.maxU.toDouble(), icon4.minV.toDouble())
        tessellator.addVertexWithUV(minX, maxY, minZ, icon4.minU.toDouble(), icon4.minV.toDouble())
        tessellator.draw()

        tessellator.startDrawingQuads()
        tessellator.addVertexWithUV(maxX, minY, minZ, icon5.maxU.toDouble(), icon5.maxV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, minZ, icon5.maxU.toDouble(), icon5.minV.toDouble())
        tessellator.addVertexWithUV(maxX, maxY, maxZ, icon5.minU.toDouble(), icon5.minV.toDouble())
        tessellator.addVertexWithUV(maxX, minY, maxZ, icon5.minU.toDouble(), icon5.maxV.toDouble())
        tessellator.draw()

    }

}