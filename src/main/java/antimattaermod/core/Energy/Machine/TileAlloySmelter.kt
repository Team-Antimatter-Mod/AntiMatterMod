package antimattaermod.core.Energy.Machine

import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileAlloySmelter(tier:Int) :TileEntity(){
    private var tier = 0
    private var slotSize = 0
    init {
        this.tier = tier
        when(this.tier){
            1 -> slotSize = 2
            2 -> slotSize = 4
            3 -> slotSize = 9
        }
    }

    public fun getTier():Int = tier
    public fun getSlotSize():Int = slotSize
}