package antimattermod.core.Energy.GUI

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

/**
 * Created by kojin15.
 */
class MultiControllerContainer : Container() {
    init {

    }

    override fun canInteractWith(player: EntityPlayer?): Boolean {
        return true
    }
}