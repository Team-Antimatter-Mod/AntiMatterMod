package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.tileentity.TileEntity
import java.util.*
/**
 * @author C6H2Cl2
 */
class EnergyNetwork {

    private val networkComponentsProvider = LinkedList<BlockPos>()
    private val networkComponentsReceiver = LinkedList<BlockPos>()
    private val networkComponentsTransfer = LinkedList<BlockPos>()

    fun writeToNBT(tagCompound: NBTTagCompound){
        val nbtTagCompound = NBTTagCompound()
        var tagList = NBTTagList()
        var tag = NBTTagCompound()
        for (pos:BlockPos in networkComponentsProvider){
            pos.writeToNBT(tag)
            tagList.appendTag(tag)
            tag = NBTTagCompound()
        }
        nbtTagCompound.setTag("Provider",tagList)
        nbtTagCompound.setInteger("ProviderList",tagList.tagCount())
        tagList = NBTTagList()
        for (pos:BlockPos in networkComponentsReceiver){
            pos.writeToNBT(tag)
            tagList.appendTag(tag)
            tag = NBTTagCompound()
        }
        nbtTagCompound.setTag("Receiver",tagList)
        nbtTagCompound.setInteger("ReceiverList",tagList.tagCount())
        tagList = NBTTagList()
        for (pos:BlockPos in networkComponentsTransfer){
            pos.writeToNBT(tag)
            tagList.appendTag(tag)
            tag = NBTTagCompound()
        }
        nbtTagCompound.setTag("Transfer",tagList)
        nbtTagCompound.setInteger("TransferList",tagList.tagCount())
        tagCompound.setTag("energyNetworkAP",nbtTagCompound)
    }

    fun readFromNBT(tagCompound: NBTTagCompound){
        val nbtTagCompound = tagCompound.getCompoundTag("energyNetworkAP")
        var tagList = nbtTagCompound.getTagList("Provider",nbtTagCompound.getInteger("ProviderList"))
        if (tagList.tagCount() > 0){
            for (i in 0..tagList.tagCount()-1){
                val blockPos = BlockPos(0,0,0)
                blockPos.readFromNBT(tagList.getCompoundTagAt(i))
                networkComponentsProvider.add(blockPos)
            }
        }
        tagList = nbtTagCompound.getTagList("Receiver",nbtTagCompound.getInteger("ReceiverList"))
        if (tagList.tagCount() > 0){
            for (i in 0..tagList.tagCount()-1){
                val blockPos = BlockPos(0,0,0)
                blockPos.readFromNBT(tagList.getCompoundTagAt(i))
                networkComponentsReceiver.add(blockPos)
            }
        }
        tagList = nbtTagCompound.getTagList("Transfer",nbtTagCompound.getInteger("TransferList"))
        if(tagList.tagCount() > 0){
            for (i in 0..tagList.tagCount()){
                val blockPos = BlockPos(0,0,0)
                blockPos.readFromNBT(tagList.getCompoundTagAt(i))
                networkComponentsTransfer.add(blockPos)
            }
        }
    }

    fun registerTileEntity(blockPos: BlockPos,apType: APType){
        when(apType){
            APType.Receiver -> networkComponentsReceiver.add(blockPos)
            APType.Transfer -> networkComponentsTransfer.add(blockPos)
            APType.Provider -> networkComponentsProvider.add(blockPos)
            else -> throw IllegalArgumentException()
        }
    }

    fun registerTileEntity(tileEntity: TileEntity,apType: APType){
        registerTileEntity(BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord),apType)
    }

    fun registerTileEntity(tileEntity: TileEntity){
        when(tileEntity){
            is IAPProvider -> registerTileEntity(tileEntity,APType.Provider)
            is IAPReceiver -> registerTileEntity(tileEntity,APType.Receiver)
            is IAPTransfer -> registerTileEntity(tileEntity,APType.Transfer)
        }
    }

    fun isContains(blockPos: BlockPos):Boolean{
        return isContains(blockPos,APType.Provider) || isContains(blockPos,APType.Receiver) || isContains(blockPos,APType.Transfer)
    }

    fun isContains(blockPos: BlockPos,apType: APType):Boolean{
        val targetList = when(apType){
            APType.Provider -> networkComponentsProvider
            APType.Receiver -> networkComponentsReceiver
            APType.Transfer -> networkComponentsTransfer
            APType.ぬるぽ -> null
        } ?: return false
        for (pos : BlockPos in targetList){
            if(pos == blockPos){
                return true
            }
        }
        return false
    }

    fun isContains(tileEntity: TileEntity):Boolean{
        val blockPos = BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord)
        return when(tileEntity){
            is IAPTransfer -> isContains(blockPos,APType.Transfer)
            is IAPProvider -> isContains(blockPos,APType.Provider)
            is IAPReceiver -> isContains(blockPos,APType.Receiver)
            else -> false
        }
    }

    fun getProviders():LinkedList<BlockPos> = networkComponentsProvider
}