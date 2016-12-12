package antimattermod.core.Energy.Transfer

import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.IAPTransfer
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import java.util.*

/**
 * @author C6H2Cl2
 */
class TileEntityCable : TileEntity {

    private var voltage : APVoltage = APVoltage.ZeroVoltage
    private var storedEnergy = 0
    private var maxEnergy = 0
    private val isConnected : HashMap<ForgeDirection, Boolean> = HashMap()

    constructor() : super(){
        isConnected[ForgeDirection.UP] = false
        isConnected[ForgeDirection.DOWN] = false
        isConnected[ForgeDirection.EAST] = false
        isConnected[ForgeDirection.WEST] = false
        isConnected[ForgeDirection.NORTH] = false
        isConnected[ForgeDirection.SOUTH] = false
    }

    constructor(voltage : APVoltage) : this(){
        this.voltage = voltage
        if(voltage.maxEnergy >= Int.MAX_VALUE /10){
            maxEnergy = Int.MAX_VALUE
        }else{
            maxEnergy = voltage.maxEnergy
        }
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        super.writeToNBT(nbtTagCompound)
        val tagCompound : NBTTagCompound = NBTTagCompound()
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

    override fun updateEntity() {
        super.updateEntity()
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

    //データの同期
    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,tagCompound)
    }
}