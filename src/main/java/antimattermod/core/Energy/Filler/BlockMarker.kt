package antimattermod.core.Energy.Filler

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
import cpw.mods.fml.client.registry.RenderingRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.RenderBlocks
import net.minecraft.client.renderer.Tessellator
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import org.lwjgl.opengl.GL11

/**
 * @author kojin15.
 */
class BlockMarker : BlockContainer(Material.iron) {
    init {
        setBlockName("AMMMarker")
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setBlockBounds(0.375f, 0.375f, 0.375f, 0.625f, 0.625f, 0.625f)
        textureName = "${AntiMatterModCore.MOD_ID}:machine/marker"
    }

    override fun isNormalCube(): Boolean {
        return false
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }

    override fun getRenderType(): Int {
        return renderMarker.RenderID
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity {
        return TileMarker()
    }
}

class renderMarker : ISimpleBlockRenderingHandler {

    companion object {
        val RenderID = RenderingRegistry.getNextAvailableRenderId()
        val i: Array<Double> = arrayOf(0.0, 0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375, 0.5, 0.5625, 0.625, 0.6875, 0.75, 0.8125, 0.875, 0.9375, 1.0)
    }

    override fun renderInventoryBlock(block: Block?, metadata: Int, modelId: Int, renderer: RenderBlocks?) {

    }

    override fun renderWorldBlock(world: IBlockAccess?, x: Int, y: Int, z: Int, block: Block?, modelId: Int, renderer: RenderBlocks?): Boolean {
        if (modelId == this.renderId && renderer != null) {
            renderer.setRenderBounds(i[6], i[6], i[6], i[10], i[10], i[10])
            renderer.renderStandardBlock(block, x, y, z)
            return true
        }

        return false
    }

    override fun shouldRender3DInInventory(modelId: Int): Boolean {
        return false
    }

    override fun getRenderId(): Int {
        return RenderID
    }
}