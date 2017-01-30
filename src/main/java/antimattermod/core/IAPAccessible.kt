package antimattermod.core

import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.IAPProvider
import antimattermod.core.Energy.TileEntity.TileEnergyController
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
interface IAPAccessible {
    val voltage: APVoltage
    val tier: MachineTier
    fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound
    fun readFromNBT(tagCompound: NBTTagCompound, name: String): TileEnergyController
    fun writeToNBT(tagCompound: NBTTagCompound)
    fun readFromNBT(tagCompound: NBTTagCompound)
    fun getPos(): BlockPos
    fun getEnergyValue(): Int
    fun getEnergyStorage(): Int
}