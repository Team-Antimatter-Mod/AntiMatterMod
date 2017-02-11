package antimattermod.core.Energy.Filler.ModePattern

import antimattermod.core.AMMRegistry
import antimattermod.core.Energy.Filler.TileFiller
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * @author kojin15.
 */
class PatternErase : FillerModePattern("Erase", ItemStack(AMMRegistry.fillerPattern, 1, 2)) {

    override fun tick(world: World, blockPos: BlockPos, xRange: Int, yRange: Int, zRange: Int, tickCount: Int, tile: TileFiller) {

    }
}