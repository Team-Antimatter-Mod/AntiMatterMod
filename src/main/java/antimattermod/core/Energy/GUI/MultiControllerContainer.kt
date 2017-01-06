package antimattermod.core.Energy.GUI

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

/**
 * Created by kojin15.
 */
class MultiControllerContainer : Container() {

    override fun canInteractWith(p_75145_1_: EntityPlayer?): Boolean {
        return true
    }
}