package antimattaermod.core.Energy.Machine

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileAlloySmelter(private var tier:Int) :TileEntity(),IInventory{

    private var slotSize = 0
    private var items : Array<ItemStack?> = emptyArray()
    init {
        when(tier){
            1 -> slotSize = 2
            2 -> slotSize = 4
            3 -> slotSize = 9
        }
        items = kotlin.arrayOfNulls(slotSize)
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

    override fun setInventorySlotContents(p_70299_1_: Int, p_70299_2_: ItemStack?) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeInventory() {
        //ToDo: Inventoryを閉じたときの処理
    }

    override fun isUseableByPlayer(p_70300_1_: EntityPlayer?): Boolean {
        return true
    }

    override fun getInventoryName(): String {
        return "alloySmelterTier"+tier
    }

    override fun isItemValidForSlot(p_94041_1_: Int, p_94041_2_: ItemStack?): Boolean {
        return true
    }

    override fun hasCustomInventoryName(): Boolean {
        return false
    }

    override fun getSizeInventory(): Int {
        return slotSize
    }

    override fun decrStackSize(p_70298_1_: Int, p_70298_2_: Int): ItemStack {
        //ToDo: ???
        return items[p_70298_1_] as ItemStack
    }

    override fun openInventory() {
        //ToDo: Inventoryを開いたときの処理
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun getStackInSlotOnClosing(p_70304_1_: Int): ItemStack? {
        //ToDO: Slot内のItemStackを返す
        return items[p_70304_1_]
    }

    override fun getStackInSlot(p_70301_1_: Int): ItemStack? {
        //ToDO: Slot内のItemStackを返す
        return items[p_70301_1_]
    }
}