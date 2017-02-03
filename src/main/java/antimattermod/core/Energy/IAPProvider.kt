package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 * Interface for AP Generators
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPProvider : IAPAccessible {
    fun handleRequest(target: BlockPos, value: Int): EnergyNode
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, PROVIDER)
}

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, PROVIDER)
    }
}