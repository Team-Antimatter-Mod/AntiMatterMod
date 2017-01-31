package antimattermod.core.Energy

import antimattermod.core.IAPAccessible
import antimattermod.core.MachineTier
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 * Interface for Machines using AP.
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPReceiver : IAPAccessible {
    fun addEnergy(energy: EnergyNode)
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, RECEIVER)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, RECEIVER)
    }
}