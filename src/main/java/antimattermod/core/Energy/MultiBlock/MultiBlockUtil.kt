package antimattermod.core.Energy.MultiBlock

import c6h2cl2.YukariLib.Util.BlockPos

/**
 * Created by kojin15.
 */

/**
 * @param blockPos ブロックの座標
 * @param type 0:ItemIn 1:ItemOut 2:FluidIn 3:FluidOut 4:EnergyIn 5:EnergyOut 6:Core 7:Controller
 */
data class MultiBlockForm(var blockPos:BlockPos, var type: Int)

/**
 * Created by kojin15.
 * @param Generator 発電機
 * @param Processing 加工機
 * @param
 */
enum class MachineType {
    Generator,
    Processing,
    Controller
    ;
}
