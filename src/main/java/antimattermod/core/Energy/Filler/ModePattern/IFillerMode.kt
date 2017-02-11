package antimattermod.core.Energy.Filler.ModePattern

import antimattermod.core.Energy.Filler.TileFiller
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.world.World

/**
 * @author kojin15.
 */
interface IFillerMode {

    fun tick(world: World, blockPos: BlockPos, xRange: Int, yRange: Int, zRange: Int, tickCount: Int, tile: TileFiller)
}