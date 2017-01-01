package antimattermod.core.Energy.Block

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.TileEntity.TileEnergyController
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class EnergyControllerBlock : BlockContainer(Material.rock) {
    init {
        setBlockName("amm.energyController")
        textureName = "${AntiMatterModCore.MOD_ID}:energyController"
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setBlockUnbreakable()
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int) = TileEnergyController()
}