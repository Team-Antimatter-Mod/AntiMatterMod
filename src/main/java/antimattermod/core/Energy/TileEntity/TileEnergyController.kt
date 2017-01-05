package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.EnergyNetwork
import antimattermod.core.Energy.IAPController
import antimattermod.core.Energy.IAPProvider
import antimattermod.core.Energy.IAPReceiver
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEnergyController : TileEntity(), IEnergyWrenchAction, IAPController {
    var network = EnergyNetwork()

    init {

    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound) {
        super.readFromNBT(nbtTagCompound)
        network.readFromNBT(nbtTagCompound)
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound) {
        super.writeToNBT(nbtTagCompound)
        network.writeToNBT(nbtTagCompound)
    }

    override fun setProvider(provider: IAPProvider) {
        network += provider
    }

    override fun setReceiver(receiver: IAPReceiver) {
        network += receiver
    }

    override fun removeProvider(provider: IAPProvider) {
        network -= provider
    }

    override fun removeReceiver(receiver: IAPReceiver) {
        network -= receiver
    }

    override operator fun contains(provider: IAPProvider): Boolean {
        return provider in network
    }

    override operator fun contains(receiver: IAPReceiver): Boolean {
        return receiver in network
    }
}