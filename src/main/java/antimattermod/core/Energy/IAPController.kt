package antimattermod.core.Energy

import antimattermod.core.MachineTier
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
interface IAPController {
    val voltage: APVoltage
    val tier: MachineTier
    fun setProvider(provider: IAPProvider)
    fun setProvider(provider: BlockPos)
    fun setReceiver(receiver: IAPReceiver)
    fun setReceiver(receiver: BlockPos)
    fun removeProvider(provider: IAPProvider)
    fun removeProvider(provider: BlockPos)
    fun removeReceiver(receiver: IAPReceiver)
    fun removeReceiver(receiver: BlockPos)
    fun writeToNBT(tagCompound: NBTTagCompound, name: String = CONTROLLER): NBTTagCompound
    fun readFromNBT(tagCompound: NBTTagCompound, name: String): IAPController
    fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, name = CONTROLLER)
    }

    fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, CONTROLLER)
    }

    fun getPos(): BlockPos
    fun sendRequest(node: EnergyNode)

    operator fun contains(provider: IAPProvider): Boolean
    operator fun contains(receiver: IAPReceiver): Boolean
    operator fun contains(pos: BlockPos): Boolean
}