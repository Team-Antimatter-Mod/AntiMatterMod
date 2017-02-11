package antimattermod.core.Energy.Filler

import antimattermod.core.AMMGuiHandler
import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Filler.TileFiller
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author kojin15.
 */
class BlockFiller : BlockContainer(Material.rock) {
    init {
        setBlockName("AMMFiller")
        setCreativeTab(AntiMatterModRegistry.tabMachines)
    }

    override fun onBlockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        player!!.openGui(AntiMatterModCore.INSTANCE, AMMGuiHandler.GuiID_Filler, world, x, y, z)
        return true
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity {
        return TileFiller()
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }
}