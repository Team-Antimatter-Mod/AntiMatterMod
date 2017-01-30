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
    fun getMaxProvideVoltage(): APVoltage
    fun getConnectableSide(): ForgeDirection
    fun getUseableEnergy(): Int
    fun useEnergy(value: Int, voltage: APVoltage)
    fun handleRequest(controller: BlockPos)
    fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos)
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, PROVIDER)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, PROVIDER)
    }
}