package antimattermod.core.Energy.Filler

import antimattermod.core.AMMRegistry
import antimattermod.core.Energy.NBT_COMPOUND
import antimattermod.core.Util.NBTHelper
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.ISidedInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author kojin15.
 */
class TileFiller : TileEntity(), IInventory {

    val invSize: Int = 24
    var itemStacks = arrayOfNulls<ItemStack>(invSize)
        private set
    var patternStack: ItemStack? = null
        private set

    val rangeLimitHorizon = 128
    val rangeLimitVertical = 255

    var xRange: Int = 1
        set(value) {
            if (value > rangeLimitHorizon)
                field = rangeLimitHorizon
            else
                field = value
        }
    var yRange: Int = 1
        set(value) {
            if (value > rangeLimitVertical)
                field = rangeLimitVertical
            else
                field = value
        }
    var zRange: Int = 1
        set(value) {
            if (value > rangeLimitHorizon)
                field = rangeLimitHorizon
            else
                field = value
        }

    var modeIndex: Int = 0
        private set

    var isStart: Boolean = false
        private set

    private var progressCount1: Int = 0
    private var progressCount2: Int = 0
    private var progressCount3: Int = 0

    //0～1199のループ
    private var tickCount: Int = 0
        private set(value) {
            field = value % 1200
        }

    fun modeUp() {
        if (modeIndex != AMMRegistry.fillerModeList.size - 1) modeIndex++
        isStart = false
    }

    fun modeDown() {
        if (modeIndex != 0) modeIndex--
        isStart = false
    }

    fun startStop() {
        isStart = !isStart
    }

    override fun updateEntity() {
        super.updateEntity()
        tickCount++

        patternStack = AMMRegistry.fillerModeList[modeIndex].getItemStack()

    }

    //ToDo:保存が上手くいってないっぽい
    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val tag = tagCompound.getCompoundTag(FILLER)
        val range = BlockPos(tag, RANGE)
        xRange = range.getX()
        yRange = range.getY()
        zRange = range.getZ()
        val tagList = tagCompound.getTagList(INVRNTORY_ITEM, NBT_COMPOUND)
        for (i in 0..23) {
            itemStacks[i] = ItemStack.loadItemStackFromNBT(tagList.getCompoundTagAt(i))
        }
    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        val range = BlockPos(xRange, yRange, zRange)
        range.writeToNBT(tag, RANGE)
        val tagList = NBTTagList()
        itemStacks.forEach {
            val nbtTagCompound = NBTTagCompound()
            it?.writeToNBT(nbtTagCompound)
            tagList.appendTag(nbtTagCompound)
        }
        tagCompound.setTag(FILLER, tag)
        tagCompound.setTag(INVRNTORY_ITEM, tagList)

    }

    override fun getDescriptionPacket(): Packet {
        val nbtTagCompound: NBTTagCompound = NBTTagCompound()
        writeToNBT(nbtTagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound)
    }

    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    override fun isInvalid() = false

    //IInventory------------------------------------------------------------------------------------------------------------
    override fun getSizeInventory(): Int {
        return invSize + 1
    }

    override fun getStackInSlot(slot: Int): ItemStack? {
        return if (slot < 24) itemStacks[slot] else patternStack
    }

    override fun decrStackSize(slot: Int, amount: Int): ItemStack? {
        if (itemStacks[slot] == null) {
            return null
        }
        val itemstack: ItemStack?
        if (itemStacks[slot]!!.stackSize <= amount) {
            itemstack = itemStacks[slot]
            itemStacks[slot] = null
            return itemstack
        }
        itemstack = itemStacks[slot]!!.splitStack(amount)
        if (itemStacks[slot]!!.stackSize < 1) {
            itemStacks[slot] = null
        }
        return itemstack
    }

    override fun getStackInSlotOnClosing(slot: Int): ItemStack? {
        return null
    }

    override fun setInventorySlotContents(slot: Int, itemStack: ItemStack?) {
        if (slot < 24) {
            itemStacks[slot] = itemStack
            if (itemStack != null && itemStack.stackSize > this.inventoryStackLimit) {
                itemStack.stackSize = this.inventoryStackLimit
            }
        }

    }

    override fun hasCustomInventoryName(): Boolean {
        return false
    }

    override fun getInventoryName(): String {
        return "FillerGui"
    }

    override fun getInventoryStackLimit(): Int {
        return 64
    }

    override fun isUseableByPlayer(player: EntityPlayer): Boolean {
        return true
    }

    override fun openInventory() {
    }

    override fun closeInventory() {
    }

    override fun isItemValidForSlot(slot: Int, itemStack: ItemStack): Boolean {
        return true
    }

}
