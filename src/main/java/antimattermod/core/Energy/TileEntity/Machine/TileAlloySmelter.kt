package antimattermod.core.Energy.TileEntity.Machine

import antimattermod.core.Energy.APVoltage
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileAlloySmelter(private var tier: Int) : TileEntity(), IInventory {

    private var slotSize = 0
    /*Slotの数について
    SlotNumは、材料のSlotの数とする
    なので、IInventoryからの取得分は成果物のスロット数2を足して返す
    なお、スロット0～slotSize-1は材料、slotSize～slotSize+2は成果物
     */
    private var materialSlotItem: Array<ItemStack?> = emptyArray()
    private var resultSlotItem: Array<ItemStack?> = arrayOfNulls(2)
    private var voltage: APVoltage = APVoltage.ZeroVoltage
    private var storedEnergy = 0
    private var maxEnergy = 0

    init {
        when (tier) {
            1 -> {
                slotSize = 2
                voltage = APVoltage.MV
                maxEnergy = voltage.maxEnergy * 20 * 20
            }
            2 -> {
                slotSize = 4
                voltage = APVoltage.HV
                maxEnergy = voltage.maxEnergy * 20 * 20
            }
            3 -> {
                slotSize = 9
                voltage = APVoltage.EV
                maxEnergy = voltage.maxEnergy * 20 * 20
            }
        }
        materialSlotItem = arrayOfNulls(slotSize)
    }

    public fun getTier(): Int = tier
    public fun getSlotSize(): Int = slotSize

    //tickごとの処理
    override fun updateEntity() {
        super.updateEntity()
    }

    //NBTに書き込みしてデータ保存
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        tag.setInteger("tier", tier)
        tag.setInteger("slotSize", slotSize)
        tagCompound.setTag("data", tag)
    }

    //NBTからデータ読み込み
    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        val tag = tagCompound.getCompoundTag("data")
        tier = tag.getInteger("tier")
        slotSize = tag.getInteger("slotSize")
    }

    //同期用のパケット読み込み
    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    //同期用のパケット返す
    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound)
    }

    //slotNumberのSlotに、ItemStack突っ込むだけ
    override fun setInventorySlotContents(slotNumber: Int, itemStack: ItemStack?) {
        if (itemStack != null && itemStack.stackSize > inventoryStackLimit) {
            itemStack.stackSize = inventoryStackLimit
        }
        if (slotNumber < slotSize) {
            materialSlotItem[slotNumber] = itemStack
        } else {
            resultSlotItem[slotNumber - slotSize] = itemStack
        }
    }

    override fun isUseableByPlayer(p_70300_1_: EntityPlayer?): Boolean {
        return true
    }

    override fun getInventoryName(): String {
        return "alloySmelterTier" + tier
    }

    override fun isItemValidForSlot(p_94041_1_: Int, p_94041_2_: ItemStack?): Boolean {
        return true
    }

    override fun hasCustomInventoryName(): Boolean {
        return false
    }

    override fun getSizeInventory(): Int {
        return slotSize + 2
    }

    //第一引数のスロットのItemStackのStack数を最大で第二引数個減らし、減らした分のItemStackを返す
    override fun decrStackSize(slotNumber: Int, stackSize: Int): ItemStack? {
        if (materialSlotItem[slotNumber] != null) {
            if (materialSlotItem[slotNumber]!!.stackSize <= stackSize) {
                val itemStack = materialSlotItem[slotNumber]
                materialSlotItem[slotNumber] = null
                markDirty()
                return itemStack
            } else {
                val itemStack = materialSlotItem[slotNumber]!!.splitStack(stackSize)
                markDirty()
                return itemStack
            }
        } else {
            return null
        }
    }

    override fun openInventory() {
        //何もなしでおｋ
    }

    override fun closeInventory() {
        //何もなしでおｋ
    }

    //InventoryのSlotごとのスタック数の上限
    override fun getInventoryStackLimit(): Int {
        return 64
    }

    //そのまま返せばおｋ
    override fun getStackInSlotOnClosing(slotNumber: Int): ItemStack? {
        return getStackInSlot(slotNumber)
    }

    //そのまま返せばおｋ
    override fun getStackInSlot(slotNumber: Int): ItemStack? {
        return if (slotNumber < slotSize) {
            materialSlotItem[slotNumber]
        } else {
            resultSlotItem[slotNumber - slotSize]
        }
    }

    /*override fun getStoredEnergy(): Int {
        return storedEnergy
    }

    override fun getReceiveVoltage(): APVoltage {
        return voltage
    }

    override fun getSendVoltage(): APVoltage {
        return APVoltage.ZeroVoltage
    }

    override fun canReceiveEnergy(): Boolean {
        return true
    }

    override fun canSendEnergy(): Boolean {
        return false
    }

    override fun getMaxStoreEnergy(): Int {
        return maxStoreEnergy
    }*/
}