package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
interface IAPAccessible {
    val voltage: APVoltage
    val tier: MachineTier
    fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound
    fun readFromNBT(tagCompound: NBTTagCompound, name: String): IAPAccessible
    fun writeToNBT(tagCompound: NBTTagCompound)
    fun readFromNBT(tagCompound: NBTTagCompound)
    fun getPos(): BlockPos
    fun getEnergyValue(): Int
    fun getEnergyStorageValue(): Int
    fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos)
}