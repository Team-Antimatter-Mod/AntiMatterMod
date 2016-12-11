package antimattermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
open class IAPProvider : IAPAccessible() {
    protected var maxOutputEnergy = voltage.maxEnergy * 2
    protected var usedEnergyOnTick = 0

    init {

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        tag.setInteger("maxOutputEnergy", maxOutputEnergy)
        tag.setInteger("usedEnergyOnTick", usedEnergyOnTick)
        tagCompound.setTag("IAPProvider", tag)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val tag = tagCompound.getCompoundTag("IAPProvider")
        maxOutputEnergy = tag.getInteger("maxOutputEnergy")
        usedEnergyOnTick = tag.getInteger("usedEnergyOnTick")
    }

    open fun getCanOutputEnergy(): Int {
        return if (maxOutputEnergy-usedEnergyOnTick > voltage.maxEnergy){
            if(voltage.maxEnergy > storedEnergy){
                storedEnergy
            }else{
                voltage.maxEnergy
            }
        }else{
            if(maxOutputEnergy-usedEnergyOnTick > storedEnergy){
                storedEnergy
            }else{
                maxOutputEnergy-usedEnergyOnTick
            }
        }
    }

    fun useEnergy(value: Int): Int {
        val usableEnergy = getCanOutputEnergy()
        if (usableEnergy >= value) {
            usedEnergyOnTick += value
            storedEnergy -= value
            return 0
        }else{
            usedEnergyOnTick += usableEnergy
            storedEnergy -= usableEnergy
            return value - usableEnergy
        }
    }
}