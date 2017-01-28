package antimattermod.core.Energy.MultiBlock

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity

/**
 * Created by kojin15.
 */
class TileMultiController : TileEntity() {

    // worldObj may not be initialized when construct
    var _blockMeta: Int? = null
    val blockMeta: Int
        get() {
            if (_blockMeta==null)
                _blockMeta = worldObj?.getBlockMetadata(xCoord, yCoord, zCoord)
            return _blockMeta ?: throw AssertionError("worldObj must be initialized before using")
        }

    var page: Int = 0
    var multiPlaceIndex: Int = -1

    var isShowAssist: Boolean = false
    var isDetails: Boolean = false

    var coreBlockName: String = BlockMultiController().unlocalizedName

    var thisTilePos: BlockPos? = BlockPos(xCoord, yCoord, zCoord)
    var machineType: MachineType = MachineType.Controller

    override fun readFromNBT(nbtTagCompound: NBTTagCompound?) {
        if (nbtTagCompound != null) {
            page = nbtTagCompound.getInteger("page")
            isShowAssist = nbtTagCompound.getBoolean("isShowAssist")
            isDetails = nbtTagCompound.getBoolean("isDetails")
            coreBlockName = nbtTagCompound.getString("coreBlockName")
            multiPlaceIndex = nbtTagCompound.getInteger("Index")

            val machinetype = MachineType.values()
            machineType = machinetype[nbtTagCompound.getInteger("MachineTypeIndex")]
        }
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        if (nbtTagCompound != null) {
            nbtTagCompound.setInteger("page", page)
            nbtTagCompound.setBoolean("isShowAssist", isShowAssist)
            nbtTagCompound.setBoolean("isDetails", isDetails)
            nbtTagCompound.setString("coreBlockName", coreBlockName)
            nbtTagCompound.setInteger("Index", multiPlaceIndex)
            nbtTagCompound.setInteger("MachineTypeIndex", machineType.ordinal)
        }

    }
}