package antimattaermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */

enum class APVoltage private constructor(val maxEnergy: Int) {
    ZeroVoltage(0), ULV(8), LV(32), MV(128), HV(512), EV(2048), IV(8192), LuV(32768), ZPMV(131072), UV(524288), MaxV(Int.MAX_VALUE);

    public fun getVoltageFromEnergy(maxEnergy: Int): APVoltage {
        return when (maxEnergy) {
            8 -> ULV
            32 -> LV
            128 -> MV
            512 -> HV
            2048 -> EV
            8192 -> IV
            32768 -> LuV
            131072 -> ZPMV
            524288 -> UV
            Int.MAX_VALUE -> MaxV
            else -> ZeroVoltage
        }
    }

    public fun writeToNBT(tagCompound: NBTTagCompound):Unit{
        val tag : NBTTagCompound = NBTTagCompound()
        tag.setInteger("energy",maxEnergy)
        tagCompound.setTag("voltage",tag)
    }

    public fun readFromNBT(tagCompound: NBTTagCompound):APVoltage{
        val tag = tagCompound.getCompoundTag("voltage")
        return getVoltageFromEnergy(tag.getInteger("energy"))
    }
}
