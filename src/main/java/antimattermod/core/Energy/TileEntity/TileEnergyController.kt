package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.EnergyNetwork
import antimattermod.core.Energy.IAPController
import antimattermod.core.Energy.IAPProvider
import antimattermod.core.Energy.IAPReceiver
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEnergyController : TileEntity(), IEnergyWrenchAction, IAPController {
    var network = EnergyNetwork()

    init {

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super<TileEntity>.writeToNBT(tagCompound)
        network.writeToNBT(tagCompound)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound {
        val tag = NBTTagCompound()
        writeToNBT(tag)
        tagCompound.setTag(name, tag)
        return tagCompound
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super<TileEntity>.readFromNBT(tagCompound)
        network.readFromNBT(tagCompound)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound, name: String): IAPController {
        val tag = tagCompound.getTag(name)
        readFromNBT(tag as NBTTagCompound)
        return this
    }

    override fun getPos(): BlockPos {
        return BlockPos(xCoord, yCoord, zCoord)
    }

    override fun setProvider(provider: IAPProvider) {
        network += provider
    }

    override fun setProvider(provider: BlockPos, transfer: BlockPos) {
        network.addProvider(provider, transfer)
    }

    override fun setReceiver(receiver: IAPReceiver) {
        network += receiver
    }

    override fun setReceiver(receiver: BlockPos, transfer: BlockPos) {
        network.addReceiver(receiver, transfer)
    }

    override fun removeProvider(provider: IAPProvider) {
        network -= provider
    }

    override fun removeProvider(provider: BlockPos, transfer: BlockPos) {
        network.removeProvider(provider, transfer)
    }

    override fun removeReceiver(receiver: IAPReceiver) {
        network -= receiver
    }

    override fun removeReceiver(receiver: BlockPos, transfer: BlockPos) {
        network.removeReceiver(receiver, transfer)
    }

    override operator fun contains(provider: IAPProvider): Boolean {
        return provider in network
    }

    override operator fun contains(receiver: IAPReceiver): Boolean {
        return receiver in network
    }
}