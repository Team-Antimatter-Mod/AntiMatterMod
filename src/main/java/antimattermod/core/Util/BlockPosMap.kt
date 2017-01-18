package antimattermod.core.Util

import c6h2cl2.YukariLib.Util.BlockPos
import java.util.*

/**
 * @author C6H2Cl2
 */
class BlockPosMap(private val target:BlockPos) :TreeMap<BlockPos,BlockPos>(
        Comparator {
            o1: @ParameterName(name = "o1") BlockPos, o2: @ParameterName(name = "o2") BlockPos ->
            (o1.getDistance(target) - o2.getDistance(target)).toInt()
        })