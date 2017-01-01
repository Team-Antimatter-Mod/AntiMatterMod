package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.EnergyNetwork
import antimattermod.core.Energy.IAPController
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class TileEnergyController : TileEntity(), IEnergyWrenchAction, IAPController {

    var network = EnergyNetwork()

    init {

    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound) {
        super.readFromNBT(nbtTagCompound)
        //network.readFromNBT(nbtTagCompound)
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound) {
        super.writeToNBT(nbtTagCompound)
        //network.writeToNBT(nbtTagCompound)
    }

    override fun removeProvider(pos: BlockPos) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeReceiver(pos: BlockPos) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isContains(pos: BlockPos) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setReceiver(pos: BlockPos) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setProvider(pos: BlockPos) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}