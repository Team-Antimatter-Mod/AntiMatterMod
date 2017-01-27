package antimattermod.core.Energy.Block.Machine

import antimattermod.core.AMMGuiHandler
import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class BlockAlloySmelter(private val tier: Int) : BlockContainer(Material.rock) {
    init {
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setBlockName("alloySmelter_tier" + tier)
        setBlockTextureName(AntiMatterModCore.MOD_ID.toLowerCase() + ":alloySmelter_tier" + tier)
        setHardness(40f)
        setResistance(50f)
        setHarvestLevel("pickaxe", tier + 2)
    }

    override fun onBlockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        player!!.openGui(AntiMatterModCore.INSTANCE, AMMGuiHandler.GuiID_AlloySmelter, world, x, y, z)
        return true
    }

    override fun createNewTileEntity(p_149915_1_: World, p_149915_2_: Int): TileEntity {
        return TileAlloySmelter(tier)
    }
}
