package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.EnergyNetwork
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEnergyController :TileEntity(){
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
}