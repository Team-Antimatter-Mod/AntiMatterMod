package antimattermod.core.Energy.Filler

import antimattermod.core.AMMRegistry
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

        //if (isStart) AMMRegistry.fillerModeList[modeIndex].tick(worldObj, BlockPos())

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        tagCompound.setInteger("xRange", xRange)
        tagCompound.setInteger("yRange", yRange)
        tagCompound.setInteger("zRange", zRange)
        tagCompound.setInteger("modeIndex", modeIndex)
        tagCompound.setInteger("tickCount", tickCount)

        val nbttaglist = NBTTagList()
        for (i in itemStacks.indices) {
            if (itemStacks[i] == null)
                continue
            val nbt1 = NBTTagCompound()
            nbt1.setByte("Slot", i.toByte())
            itemStacks[i]?.writeToNBT(nbt1)
            nbttaglist.appendTag(nbt1)
        }
        tagCompound.setTag("Items", nbttaglist)

        val patternTagList = NBTTagList()
        val nbt2 = NBTTagCompound()
        patternStack?.writeToNBT(nbt2)
        patternTagList.appendTag(nbt2)
        tagCompound.setTag("Pattern", patternTagList)

    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        xRange = tagCompound.getInteger("xRange")
        yRange = tagCompound.getInteger("yRange")
        zRange = tagCompound.getInteger("zRange")
        modeIndex = tagCompound.getInteger("modeIndex")
        tickCount = tagCompound.getInteger("tickCount")

        val nbttaglist = tagCompound.getTagList("Items", 10)
        itemStacks = arrayOfNulls<ItemStack>(invSize)
        for (i in 0..nbttaglist.tagCount() - 2) {
            val nbt1 = nbttaglist.getCompoundTagAt(i)
            val b0 = nbt1.getByte("Slot")
            if (0 <= b0 && b0 < itemStacks.size) {
                itemStacks[b0.toInt()] = ItemStack.loadItemStackFromNBT(nbt1)
            }
        }

        val patternTagList = tagCompound.getTagList("Pattern", 10)
        patternStack = null
        val nbt2 = patternTagList.getCompoundTagAt(patternTagList.tagCount())
        patternStack = ItemStack.loadItemStackFromNBT(nbt2)

    }

    override fun getDescriptionPacket(): Packet {
        val nbtTagCompound: NBTTagCompound = NBTTagCompound()
        writeToNBT(nbtTagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound)
    }

    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }


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
