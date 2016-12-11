package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import antimattermod.core.Util.QuickSort
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
    private val energyRequest = LinkedList<EnergyNode>()
    private val energyProvide = LinkedList<EnergyNode>()

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

    fun onTick(world: World){
        //ToDo:1tickごとの処理
        //リクエストの処理
        for (node in energyRequest){
            val receiver = node.getTargetPos()
            val pos = node.getSource()
            //✗2つの直線距離が一番近いのを探す
            //○距離でソートするためにとりあえず距離を求める
            val distances = HashMap<Double,BlockPos>()
            getEnergyNetwork(receiver)
                    .flatMap { it.getProviders() }
                    .forEach {
                        var dist = it.getDistance(pos)
                        while (distances.containsKey(dist)){
                            dist *= 1.0000000001
                        }
                    }
            //クイックソート
            val sort = QuickSort()
            val sortedDistances :Array<Double> = sort.quickSort(distances.keys.toDoubleArray().toTypedArray())
            var sentEnergy = node.getEnergyValue()
            var i = 0
            while (sentEnergy > 0){
                val sender = distances[sortedDistances[i]]?.getTileEntityFromPos(world)
                if(sender is IAPProvider){
                    sentEnergy = sender.useEnergy(sentEnergy)
                    var energyNode :EnergyNode? = null
                    if (sender.voltage.maxEnergy > node.getVoltage().maxEnergy){
                        //energyNode = EnergyNode(node.getVoltage(),node.getEnergyValue() - sentEnergy,pos,)
                    }else{

                    }
                }
            }

            energyProvide.add(EnergyNode(node.getVoltage(),node.getEnergyValue(),pos,node.getTargetPos()))
        }
        //輸送処理

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
        registerTileEntity(tileEntity,tilePos.up.getTileEntityFromPos(world))
        registerTileEntity(tileEntity,tilePos.down.getTileEntityFromPos(world))
        registerTileEntity(tileEntity,tilePos.north.getTileEntityFromPos(world))
        registerTileEntity(tileEntity,tilePos.south.getTileEntityFromPos(world))
        registerTileEntity(tileEntity,tilePos.east.getTileEntityFromPos(world))
        registerTileEntity(tileEntity,tilePos.west.getTileEntityFromPos(world))
    }

    private fun registerTileEntity(tileEntityBase: TileEntity, tileEntityNeighbor:TileEntity?){
        if(tileEntityNeighbor == null || tileEntityBase !is IAPAccessible || tileEntityNeighbor !is IAPAccessible){
            return
        }
        if (tileEntityBase is IAPReceiver && tileEntityNeighbor is IAPReceiver){
            return
        }else{
            for (energyNetwork in getEnergyNetwork(tileEntityNeighbor)){
                energyNetwork.registerTileEntity(tileEntityBase)
            }
        }
    }

    fun requestEnergy(energyNode: EnergyNode){
        energyRequest.add(energyNode)
    }

    fun requestEnergy(pos : BlockPos,voltage:APVoltage,energy:Int = voltage.maxEnergy){
        requestEnergy(EnergyNode(voltage,energy, BlockPos(0,0,0),pos))
    }
}