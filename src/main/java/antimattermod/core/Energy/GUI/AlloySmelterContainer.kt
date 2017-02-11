package antimattermod.core.Energy.GUI

import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot

/**
 * @author C6H2Cl2
 */
class AlloySmelterContainer(private val x: Int, private val y: Int, private val z: Int, private val tile: TileAlloySmelter, private val playerInventory: InventoryPlayer) : Container() {
    init {
        val i = -19
        for (j in 0..1) {
            for (k in 0..8) {
                this.addSlotToContainer(net.minecraft.inventory.Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i))
            }
        }

        for (j in 0..8) {
            this.addSlotToContainer(net.minecraft.inventory.Slot(playerInventory, j, 8 + j * 18, 161 + i))
        }

        when (tile.getSlotSize()) {
            2 -> {
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 0, 36, 23))
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 1, 54, 23))
            }
            4 -> {
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 0, 44, 23))
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 1, 26, 41))
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 2, 44, 41))
                this.addSlotToContainer(net.minecraft.inventory.Slot(tile, 3, 62, 41))
            }
            9 -> {
                for (j in 0..2) {
                    for (k in 0..2) {
                        this.addSlotToContainer(net.minecraft.inventory.Slot(tile, j * 3 + k, 26 + k * 18, 5 + j * 18))
                    }
                }
            }
        }
        this.addSlotToContainer(net.minecraft.inventory.Slot(tile, tile.getSlotSize(), 134, 23))
        this.addSlotToContainer(net.minecraft.inventory.Slot(tile, tile.getSlotSize() + 1, 152, 23))
    }

    override fun canInteractWith(player: EntityPlayer?): Boolean {
        return true
    }
}