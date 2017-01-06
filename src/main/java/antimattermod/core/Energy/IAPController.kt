package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
interface IAPController {
    fun setProvider(provider: IAPProvider)
    fun setProvider(provider: BlockPos, transfer: BlockPos)
    fun setReceiver(receiver: IAPReceiver)
    fun setReceiver(receiver: BlockPos, transfer: BlockPos)
    fun removeProvider(provider: IAPProvider)
    fun removeProvider(provider: BlockPos, transfer: BlockPos)
    fun removeReceiver(receiver: IAPReceiver)
    fun removeReceiver(receiver: BlockPos, transfer: BlockPos)
    fun writeToNBT(tagCompound: NBTTagCompound, name: String = "controller"): NBTTagCompound
    fun readFromNBT(tagCompound: NBTTagCompound, name: String): IAPController
    fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, name = "controller")
    }

    fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, "controller")
    }

    fun getPos(): BlockPos

    operator fun contains(provider: IAPProvider): Boolean
    operator fun contains(receiver: IAPReceiver): Boolean
}