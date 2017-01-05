package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos

/**
 * @author C6H2Cl2
 */
interface IAPController {
    fun setProvider(provider: IAPProvider)
    fun setReceiver(receiver: IAPReceiver)
    fun removeProvider(provider: IAPProvider)
    fun removeReceiver(receiver: IAPReceiver)
    operator fun contains(provider: IAPProvider): Boolean
    operator fun contains(receiver: IAPReceiver): Boolean
}