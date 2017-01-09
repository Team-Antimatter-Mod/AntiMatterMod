package antimattermod.core.Energy.MultiBlock

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * Created by kojin15.
 */
class TileMultiController : TileEntity() {

    var blockMeta: Int = 0
    var page: Int = 0
    var multiPlaceIndex: Int = -1

    var isShowAssist: Boolean = false
    var isDetails: Boolean = false

    var coreBlockName: String = BlockMultiController().unlocalizedName

    var thisTilePos: BlockPos? = null
    var machineType: MachineType = MachineType.Controller

    override fun readFromNBT(nbtTagCompound: NBTTagCompound?) {
        blockMeta = nbtTagCompound!!.getInteger("blockMeta")
        page = nbtTagCompound.getInteger("page")
        isShowAssist = nbtTagCompound.getBoolean("isShowAssist")
        isDetails = nbtTagCompound.getBoolean("isDetails")
        coreBlockName = nbtTagCompound.getString("coreBlockName")
        multiPlaceIndex = nbtTagCompound.getInteger("Index")

        val machinetype = MachineType.values()
        machineType = machinetype[nbtTagCompound.getInteger("MachineTypeIndex")]

        thisTilePos = BlockPos(nbtTagCompound.getInteger("x"), nbtTagCompound.getInteger("y"), nbtTagCompound.getInteger("z"))

    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        nbtTagCompound!!.setInteger("blockMeta", blockMeta)
        nbtTagCompound.setInteger("page", page)
        nbtTagCompound.setBoolean("isShowAssist", isShowAssist)
        nbtTagCompound.setBoolean("isDetails", isDetails)
        nbtTagCompound.setString("coreBlockName", coreBlockName)
        nbtTagCompound.setInteger("Index", multiPlaceIndex)

        nbtTagCompound.setInteger("MachineTypeIndex", machineType.ordinal)

        nbtTagCompound.setInteger("x", thisTilePos!!.getX())
        nbtTagCompound.setInteger("y", thisTilePos!!.getY())
        nbtTagCompound.setInteger("z", thisTilePos!!.getZ())
    }
}