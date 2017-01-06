package antimattermod.core

import antimattermod.core.Energy.IAPProvider
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
interface IAPAccessible {
    fun writeToNBT(tagCompound: NBTTagCompound, name: String = "receiver"): NBTTagCompound
    fun readFromNBT(tagCompound: NBTTagCompound, name: String = "receiver"): IAPProvider
    fun writeToNBT(tagCompound: NBTTagCompound)
    fun readFromNBT(tagCompound: NBTTagCompound)
    fun getPos(): BlockPos
}