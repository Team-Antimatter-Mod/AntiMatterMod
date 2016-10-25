package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraft.world.WorldSavedData
import net.minecraft.world.storage.MapStorage
import java.util.*

/**
 * @author C6H2Cl2
 */
class EnergyNetworkGlobal : WorldSavedData(DATA_NAME) {

    private val energyNetworkList = LinkedList<EnergyNetwork>()

    companion object{
        val DATA_NAME = "AMMGlobalEnergyNetwork"
        fun get(world : World):EnergyNetworkGlobal{
            val storage :MapStorage = if (world.isRemote){
                world.mapStorage
            }else{
                world.perWorldStorage
            }
            @Suppress("JAVA_CLASS_ON_COMPANION")
            var instance = storage.loadData(EnergyNetworkGlobal.javaClass, DATA_NAME) as EnergyNetworkGlobal?
            if(instance == null){
                instance = EnergyNetworkGlobal()
                storage.setData(DATA_NAME,instance)
            }
            return instance
        }
    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound) {
        val tagCompound = NBTTagCompound()
        val tagList = NBTTagList()
        var tag = NBTTagCompound()
        for (network in energyNetworkList){
            network.writeToNBT(tag)
            tagList.appendTag(tag)
            tag = NBTTagCompound()
        }
        tagCompound.setTag("energyNetworkList",tagList)
        tagCompound.setInteger("networkNum",tagList.tagCount())
        nbtTagCompound.setTag(DATA_NAME,tagCompound)
    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound) {
        val tagCompound = nbtTagCompound.getCompoundTag(DATA_NAME)
        val tagList = nbtTagCompound.getTagList("energyNetworkList",tagCompound.getInteger("networkNum"))
        for (i in 0..tagList.tagCount()-1){
            val network = EnergyNetwork()
            network.readFromNBT(tagList.getCompoundTagAt(i))
            energyNetworkList.add(network)
        }
    }

    fun getEnergyNetwork(blockPos: BlockPos):LinkedList<EnergyNetwork>{
        val energyNetwork = LinkedList<EnergyNetwork>()
        for (network in energyNetworkList){
            if(network.isContains(blockPos)){
                energyNetwork.add(network)
                break
            }
        }
        return energyNetwork
    }

    fun getEnergyNetwork(tileEntity: TileEntity):LinkedList<EnergyNetwork>{
        return getEnergyNetwork(BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord))
    }

    fun registerTileEntity(tileEntity: TileEntity):Unit{
        //IAPAccessible実装してないTileEntityなんていらない
        if(tileEntity !is IAPAccessible){
            throw IllegalArgumentException()
        }
        val tilePos = BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord)
        val world = tileEntity.worldObj
        if (world.isRemote){
            return
        }
        if (tilePos.up.getTileEntityFromPos(world) is IAPAccessible && tilePos.up.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.up)){
                energyNetwork.registerTileEntity(tilePos.up.getTileEntityFromPos(world))
            }
        }
        if (tilePos.down.getTileEntityFromPos(world) is IAPAccessible && tilePos.down.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.down)){
                energyNetwork.registerTileEntity(tilePos.down.getTileEntityFromPos(world))
            }
        }
        if (tilePos.north.getTileEntityFromPos(world) is IAPAccessible && tilePos.north.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.north)){
                energyNetwork.registerTileEntity(tilePos.north.getTileEntityFromPos(world))
            }
        }
        if (tilePos.south.getTileEntityFromPos(world) is IAPAccessible && tilePos.south.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.south)){
                energyNetwork.registerTileEntity(tilePos.south.getTileEntityFromPos(world))
            }
        }
        if (tilePos.east.getTileEntityFromPos(world) is IAPAccessible && tilePos.east.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.east)){
                energyNetwork.registerTileEntity(tilePos.east.getTileEntityFromPos(world))
            }
        }
        if (tilePos.west.getTileEntityFromPos(world) is IAPAccessible && tilePos.west.getTileEntityFromPos(world) !is IAPReceiver){
            for (energyNetwork in getEnergyNetwork(tilePos.west)){
                energyNetwork.registerTileEntity(tilePos.west.getTileEntityFromPos(world))
            }
        }
    }
}