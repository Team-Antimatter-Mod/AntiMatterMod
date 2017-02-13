package antimattermod.core.Energy.Filler

import antimattermod.core.AMMRegistry
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
    var patternStack: ItemStack? = null

    var xRange: Int = 1
    var yRange: Int = 1
    var zRange: Int = 1

    var modeIndex: Int = 0

    var isStart: Boolean = false

    var progressCount1: Int = 0
    var progressCount2: Int = 0
    var progressCount3: Int = 0

    //0～1199のループ
    var tickCount: Int = 0

    fun modeUp() {
        if (modeIndex != AMMRegistry.fillerModeList.size - 1) modeIndex++
        isStart == false
    }

    fun modeDown() {
        if (modeIndex != 0) modeIndex--
        isStart == false
    }

    fun startStop() {
        when (isStart) {
            true -> isStart = false
            false -> isStart = true
        }
    }

    override fun updateEntity() {
        super.updateEntity()
        if (tickCount == 1199) tickCount = 0 else tickCount++

        val pattern = AMMRegistry.fillerModeList[modeIndex]
        patternStack = pattern.getItemStack()

    }

    //ToDo:保存が上手くいってないっぽい
    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val range = BlockPos(tagCompound, "Range")
        xRange = range.getX()
        yRange = range.getY()
        zRange = range.getZ()

        for (i in 0..23) {
            itemStacks[i] = NBTHelper.getItemStack(tagCompound, "item" + i)
        }

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val range = BlockPos(xRange, yRange, zRange)
        range.writeToNBT(tagCompound, "Range")

        for (i in 0..23) {
            NBTHelper.setItemStack(tagCompound, "item" + i, itemStacks[i])
        }

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
