package antimattermod.core.Energy

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
abstract class IAPAccessible : TileEntity() {

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
    }

    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound)
    }

    var voltage: APVoltage = APVoltage.ZeroVoltage
    protected set
    var maxStoredEnergy: Int = 0
    protected set
    var storedEnergy: Int = 0
    protected set

    public abstract fun explode()
}
