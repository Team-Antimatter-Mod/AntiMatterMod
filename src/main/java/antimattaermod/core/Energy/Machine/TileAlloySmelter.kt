package antimattaermod.core.Energy.Machine

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileAlloySmelter(private var tier:Int) :TileEntity(){
    private var slotSize = 0
    init {
        when(tier){
            1 -> slotSize = 2
            2 -> slotSize = 4
            3 -> slotSize = 9
        }
    }

    public fun getTier():Int = tier
    public fun getSlotSize():Int = slotSize

    override fun updateEntity() {
        super.updateEntity()
    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        tag.setInteger("tier",tier)
        tag.setInteger("slotSize",slotSize)
        tagCompound.setTag("data",tag)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val tag = tagCompound.getCompoundTag("data")
        tier = tag.getInteger("tier")
        slotSize = tag.getInteger("slotSize")
    }

    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,tagCompound)
    }
}