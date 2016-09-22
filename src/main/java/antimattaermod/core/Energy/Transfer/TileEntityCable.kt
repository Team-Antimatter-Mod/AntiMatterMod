package antimattaermod.core.Energy.Transfer

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import java.util.*

/**
 * @author C6H2Cl2
 */
class TileEntityCable :TileEntity(){
    private val isConnected : HashMap<ForgeDirection,Boolean> = HashMap()
    init {
        isConnected[ForgeDirection.UP] = false
        isConnected[ForgeDirection.DOWN] = false
        isConnected[ForgeDirection.EAST] = false
        isConnected[ForgeDirection.WEST] = false
        isConnected[ForgeDirection.NORTH] = false
        isConnected[ForgeDirection.SOUTH] = false
    }

    public fun setConnection(direction : ForgeDirection, isConnect : Boolean) {
        isConnected[direction] = isConnect
    }

    public fun getConnect(direction: ForgeDirection) : Boolean{
        if(isConnected[direction] == null){
            return false
        }else{
            return isConnected[direction] as Boolean
        }
    }
}