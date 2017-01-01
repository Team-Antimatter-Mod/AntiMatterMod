package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos

/**
 * @author C6H2Cl2
 */
interface IAPController {
    fun setProvider(pos: BlockPos)
    fun setReceiver(pos: BlockPos)
    fun removeProvider(pos: BlockPos)
    fun removeReceiver(pos: BlockPos)
    fun isContains(pos: BlockPos)
}