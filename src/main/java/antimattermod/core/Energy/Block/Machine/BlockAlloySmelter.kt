package antimattermod.core.Energy.Block.Machine

import antimattermod.core.AMMGuiHandler
import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter
import c6h2cl2.YukariLib.Block.BlockWithTileEntity
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class BlockAlloySmelter(private val tier: Int) : BlockWithTileEntity<TileAlloySmelter>(
        Material.rock, TileAlloySmelter::class, { world, meta -> TileAlloySmelter(tier)}, "amm.AlloySmelter.$tier"
) {
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
}
