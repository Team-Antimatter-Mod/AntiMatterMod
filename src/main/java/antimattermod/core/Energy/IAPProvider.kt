package antimattermod.core.Energy

import antimattermod.core.IAPAccessible
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 * Interface for AP Generators
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPProvider :IAPAccessible{
    fun handleRequest(target: BlockPos, value: Int): EnergyNode
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, PROVIDER)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, PROVIDER)
    }
}