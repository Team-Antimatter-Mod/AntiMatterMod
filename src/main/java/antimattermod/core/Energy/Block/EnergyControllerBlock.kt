package antimattermod.core.Energy.Block

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import antimattermod.core.Energy.TileEntity.TileEnergyController
import antimattermod.core.Energy.MachineTier
import antimattermod.core.Energy.MachineTier.Tier1
import c6h2cl2.YukariLib.Block.BlockWithTileEntity
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class EnergyControllerBlock(val tier: Int) : BlockWithTileEntity<TileEnergyController>(
        Material.rock, TileEnergyController::class, { world, meta -> TileEnergyController(MachineTier.getFromTier(tier)) }, "amm.EnergyController.$tier"
), IEnergyWrenchAction {
    init {
        setBlockName("amm.energyController_tier$tier")
        textureName = "${AntiMatterModCore.MOD_ID}:energyController_$tier"
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setBlockUnbreakable()
    }
}