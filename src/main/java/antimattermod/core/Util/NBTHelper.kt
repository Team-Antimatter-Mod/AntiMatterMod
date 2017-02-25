package antimattermod.core.Util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

/**
 * @author kojin15.
 */
object NBTHelper {

    fun setItemStack(tagCompound: NBTTagCompound, tagName: String = "itemStack", itemStack: ItemStack?): NBTTagCompound {
        val tag = NBTTagCompound()
        if (itemStack != null) {
            tagCompound.setTag(tagName, itemStack.writeToNBT(tag))
        }
        return tagCompound
    }

    fun getItemStack(tagCompound: NBTTagCompound, tagName: String = "itemStack"): ItemStack? {
        val tag = tagCompound.getCompoundTag(tagName)
        return ItemStack.loadItemStackFromNBT(tag)
    }

}