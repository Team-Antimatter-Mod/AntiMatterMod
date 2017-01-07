package antimattermod.core.Render

import antimattermod.core.AntiMatterModCore
import antimattermod.core.Energy.MultiBlock.MultiControllerTile
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL12

/**
 * Created by kojin15.
 */
class MultiControllerTileRender : TileEntitySpecialRenderer() {

    val controllerOverray = ResourceLocation(AntiMatterModCore.MOD_ID, "textures/blocks/multiblock/multicontroller.png")

    override fun renderTileEntityAt(tileEntity: TileEntity?, x: Double, y: Double, z: Double, ff: Float) {
        renderAssist(tileEntity as MultiControllerTile, x, y, z, ff)
    }

    private fun renderAssist(tile: MultiControllerTile, x: Double, y: Double, z: Double, ff: Float) {

    }
}