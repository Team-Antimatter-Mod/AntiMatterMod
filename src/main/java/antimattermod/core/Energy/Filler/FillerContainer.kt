package antimattermod.core.Energy.Filler

import antimattermod.core.Energy.Filler.TileFiller
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

/**
 * @author kojin15.
 */
class FillerContainer(private val x: Int, private val y: Int, private val z: Int, private val tile: TileFiller, private val playerInventory: InventoryPlayer) : Container() {
    init {
        for (i in 0..5) {
            for (j in 0..3) {
                this.addSlotToContainer(Slot(tile, j + i * 4, 186 + j * 18, 8 + i * 18))
            }
        }
        this.addSlotToContainer(patternSlot(tile, 24, 80, 14))

        for (i in 0..2) {
            for (j in 0..8) {
                this.addSlotToContainer(Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 108 + i * 18))
            }
        }

        for (i in 0..8) {
            this.addSlotToContainer(Slot(playerInventory, i, 8 + i * 18, 166))
        }

    }

    val fillerSize = 24
    val playerSize = 27
    val hotbarSize = 9

    val fillerIndex = 0
    val playerIndex = fillerIndex + fillerSize
    val hotbarIndex = playerIndex + playerSize


    override fun canInteractWith(player: EntityPlayer?): Boolean {
        return true
    }

    override fun transferStackInSlot(player: EntityPlayer?, clickedIndex: Int): ItemStack? {
        var itemstack: ItemStack? = null
        val slot: Slot = this.inventorySlots[clickedIndex] as Slot

        if (slot.hasStack) {
            val baseStack = slot.stack
            itemstack = baseStack.copy()

            if (clickedIndex < playerIndex) {
                if (!this.mergeItemStack(baseStack, playerIndex, hotbarIndex + hotbarSize, true)) return null

            } else if (!this.mergeItemStack(baseStack, fillerIndex, fillerIndex + fillerSize, false)) return null


            if (baseStack.stackSize == 0) slot.putStack(null) else slot.onSlotChanged()
        }

        return itemstack
    }

    class patternSlot(iInventory: IInventory, id: Int, x: Int, y: Int) : Slot(iInventory, id, x, y) {

        override fun canTakeStack(p_82869_1_: EntityPlayer?): Boolean {
            return false
        }

        override fun isItemValid(p_75214_1_: ItemStack?): Boolean {
            return false
        }
    }
}