package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.Packet

/**
 * @author C6H2Cl2
 */
interface IAPController {
    val voltage: APVoltage
    val tier: MachineTier
    val maxConnection: Int
    fun setProvider(provider: IAPProvider): Boolean
    fun setProvider(provider: BlockPos): Boolean
    fun setReceiver(receiver: IAPReceiver): Boolean
    fun setReceiver(receiver: BlockPos): Boolean
    fun removeProvider(provider: IAPProvider)
    fun removeProvider(provider: BlockPos)
    fun removeReceiver(receiver: IAPReceiver)
    fun removeReceiver(receiver: BlockPos)
    fun getDescriptionPacket(): Packet
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
    fun getNumConnected(): Int

    operator fun contains(provider: IAPProvider): Boolean
    operator fun contains(receiver: IAPReceiver): Boolean
    operator fun contains(pos: BlockPos): Boolean
}