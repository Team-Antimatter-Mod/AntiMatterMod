package antimattaermod.core.Energy.Machine.GUI

import antimattaermod.core.Energy.Machine.TileAlloySmelter
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container

/**
 * @author C6H2Cl2
 */
class AlloySmelterContainer(private val x:Int, private val y:Int, private val z:Int,private val tile:TileAlloySmelter) : Container() {
    init {

    }
    override fun canInteractWith(p_75145_1_: EntityPlayer?): Boolean {
        return true
    }
}