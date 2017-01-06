package antimattermod.core.Energy.MultiBlock

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * Created by kojin15.
 */
class MultiControllerTile : TileEntity() {

    var blockMeta: Int = 0
    var page: Int = 0

    var isShowAssist: Boolean = false
    var isDetails: Boolean = false

    override fun readFromNBT(nbtTagCompound: NBTTagCompound?) {
        blockMeta = nbtTagCompound!!.getInteger("blockMeta")
        page = nbtTagCompound!!.getInteger("page")
        isShowAssist = nbtTagCompound.getBoolean("isShowAssist")
        isDetails = nbtTagCompound.getBoolean("isDetails")
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        nbtTagCompound!!.setInteger("blockMeta", blockMeta)
        nbtTagCompound.setInteger("page", page)
        nbtTagCompound.setBoolean("isShowAssist", isShowAssist)
        nbtTagCompound.setBoolean("isDetails", isDetails)
    }
}