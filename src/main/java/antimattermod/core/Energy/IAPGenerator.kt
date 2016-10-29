package antimattermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
open class IAPGenerator : IAPProvider(){
    init {

    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
    }

    var currentGenerateValue = 0
    protected set
    var maxGenerateValue = 0
    protected set
    var isGenerating = false
    protected set
}