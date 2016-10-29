package antimattermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
open class IAPProvider : IAPAccessible(){
    protected var maxOutputEnergy = voltage.maxEnergy*2
    protected var usedEnergyOnTick = 0
    init {

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        tag.setInteger("maxOutputEnergy",maxOutputEnergy)
        tag.setInteger("usedEnergyOnTick",usedEnergyOnTick)
        tagCompound.setTag("IAPProvider",tag)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val tag = tagCompound.getCompoundTag("IAPProvider")
        maxOutputEnergy = tag.getInteger("maxOutputEnergy")
        usedEnergyOnTick = tag.getInteger("usedEnergyOnTick")
    }

    open fun getConOutputEnergy():Int{
        return if (voltage.maxEnergy > storedEnergy){
            storedEnergy
        }else{
            voltage.maxEnergy
        }
    }

    fun setUseEnergy(value : Int){

    }
}