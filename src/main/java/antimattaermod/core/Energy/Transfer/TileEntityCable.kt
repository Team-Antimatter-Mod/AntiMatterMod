package antimattaermod.core.Energy.Transfer

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import java.util.*

/**
 * @author C6H2Cl2
 */
class TileEntityCable :TileEntity(){
    private val isConnected : HashMap<ForgeDirection,Boolean> = HashMap()
    init {
        isConnected[ForgeDirection.UP] = false
        isConnected[ForgeDirection.DOWN] = false
        isConnected[ForgeDirection.EAST] = false
        isConnected[ForgeDirection.WEST] = false
        isConnected[ForgeDirection.NORTH] = false
        isConnected[ForgeDirection.SOUTH] = false
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        super.writeToNBT(nbtTagCompound)
        val tagCompound :NBTTagCompound = NBTTagCompound()
        tagCompound.setBoolean("Up",isConnected[ForgeDirection.UP] as Boolean)
        tagCompound.setBoolean("Down",isConnected[ForgeDirection.DOWN] as Boolean)
        tagCompound.setBoolean("East",isConnected[ForgeDirection.EAST] as Boolean)
        tagCompound.setBoolean("West",isConnected[ForgeDirection.WEST] as Boolean)
        tagCompound.setBoolean("North",isConnected[ForgeDirection.NORTH] as Boolean)
        tagCompound.setBoolean("South",isConnected[ForgeDirection.SOUTH] as Boolean)
        nbtTagCompound!!.setTag("Connection",tagCompound)
    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound?) {
        super.readFromNBT(nbtTagCompound)
        val tagCompound : NBTTagCompound = nbtTagCompound!!.getCompoundTag("Connection")
        isConnected[ForgeDirection.UP] = tagCompound.getBoolean("Up")
        isConnected[ForgeDirection.DOWN] = tagCompound.getBoolean("Down")
        isConnected[ForgeDirection.EAST] = tagCompound.getBoolean("East")
        isConnected[ForgeDirection.WEST] = tagCompound.getBoolean("West")
        isConnected[ForgeDirection.NORTH] = tagCompound.getBoolean("North")
        isConnected[ForgeDirection.SOUTH] = tagCompound.getBoolean("South")
    }

    public fun setConnection(direction : ForgeDirection, isConnect : Boolean) {
        isConnected[direction] = isConnect
    }

    public fun getConnect(direction: ForgeDirection) : Boolean{
        if(isConnected[direction] == null){
            return false
        }else{
            return isConnected[direction] as Boolean
        }
    }
}