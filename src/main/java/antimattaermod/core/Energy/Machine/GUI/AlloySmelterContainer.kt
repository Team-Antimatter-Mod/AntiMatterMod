package antimattaermod.core.Energy.Machine.GUI

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

/**
 * @author C6H2Cl2
 */
class AlloySmelterContainer(x:Int,y:Int,z:Int) : Container() {
    private var x = 0
    private var y = 0
    private var z = 0
    init {
        this.x = x
        this.y = y
        this.z = z
    }
    override fun canInteractWith(p_75145_1_: EntityPlayer?): Boolean {
        return true
    }
}