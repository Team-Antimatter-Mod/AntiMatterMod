package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.EnergyNetwork
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class TileEnergyController : TileEntity(), IEnergyWrenchAction {

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