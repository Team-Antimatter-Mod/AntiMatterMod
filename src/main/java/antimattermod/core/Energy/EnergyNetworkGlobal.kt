package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.WorldSavedData
import java.util.*

/**
 * @author C6H2Cl2
 */
class EnergyNetworkGlobal : WorldSavedData(DATA_NAME) {
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object{
        val INSTANCE = EnergyNetworkGlobal()
        val DATA_NAME = "AMMGlobalEnergyNetwork"
    }

    private val energyNetworkList = LinkedList<EnergyNetwork>()

    public fun getEnergyNetwork(blockPos: BlockPos):EnergyNetwork?{
        var energyNetwork :EnergyNetwork? = null
        for (network in energyNetworkList){
            if(network.isContains(blockPos)){
                energyNetwork = network
                break
            }
        }
        return energyNetwork
    }

    public fun getEnergyNetwork(tileEntity: TileEntity):EnergyNetwork?{
        return getEnergyNetwork(BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord))
    }

    public fun registerTileEntity(tileEntity: TileEntity):Unit{
        val tilePos = BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord)


        var network :EnergyNetwork? = getEnergyNetwork(tileEntity)
        if(network == null){
            network = EnergyNetwork()
            energyNetworkList.add(network)
        }

    }

}