package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import java.util.*

/**
 * @author C6H2Cl2
 * @property networkComponentsProvider : Map<Provider,Transfer>
 * @property networkComponentsReceiver : Map<Receiver,Transfer>
 * @property networkComponentsTransfer : Set<Transfer>
 */
class EnergyNetwork {
    private val networkComponentsProvider = HashMap<BlockPos, BlockPos>().toMutableMap()
    private val networkComponentsReceiver = HashMap<BlockPos, BlockPos>().toMutableMap()
    private val networkComponentsTransfer = HashSet<BlockPos>().toMutableSet()

    /**
     * get connected Providers/Receivers with Transfer.
     *
     * @param transfer BlockPos of TileEntity implements IAPTransfer
     */
    fun getConnected(transfer: BlockPos): Array<BlockPos>? {
        val providers = getConnectedProviders(transfer)
        val receivers = getConnectedReceivers(transfer)
        if (providers == null || receivers == null) {
            return null
        } else {
            return providers.plus(receivers)
        }
    }

    fun getConnectedProviders(transfer: BlockPos): Array<BlockPos>? {
        if (!networkComponentsTransfer.contains(transfer)) return null
        val providers = LinkedList<BlockPos>()
        networkComponentsProvider
                .filterKeys { it == transfer }
                .entries.forEach { providers.add(it.key) }
        return providers.toTypedArray()
    }

    fun getConnectedReceivers(transfer: BlockPos): Array<BlockPos>? {
        if (!networkComponentsTransfer.contains(transfer)) return null
        val receivers = LinkedList<BlockPos>()
        networkComponentsReceiver
                .filterKeys { it == transfer }
                .entries.forEach { receivers.add(it.key) }
        return receivers.toTypedArray()
    }

    fun addProvider(provider: IAPProvider) {
        networkComponentsProvider[provider.getPos()] = provider.getTransfer() as BlockPos
        networkComponentsTransfer.add(provider.getTransfer() as BlockPos)
    }

    fun addReceiver(receiver: IAPReceiver) {
        networkComponentsReceiver[receiver.getPos()] = receiver.getTransfer() as BlockPos
        networkComponentsTransfer.add(receiver.getTransfer() as BlockPos)
    }

    fun readFromNBT(tagCompound: NBTTagCompound, name: String = "network"): EnergyNetwork {
        val tag = tagCompound.getTag(name) as NBTTagCompound
        nbtToMap(networkComponentsProvider, "provider", tag)
        nbtToMap(networkComponentsReceiver, "receiver", tag)
        val tagList = tagCompound.getTagList("transfer", tagCompound.getInteger("transfer.size"))
        for (i in 0 until tagList.tagCount()) {
            val value = BlockPos(0, 0, 0)
            value.readFromNBT(tagList.getCompoundTagAt(i))
            networkComponentsTransfer.add(value)
        }
        return this
    }

    fun writeToNBT(tagCompound: NBTTagCompound, name: String = "network"): NBTTagCompound {
        val tag = NBTTagCompound()
        mapToNBT(networkComponentsProvider, "provider", tag)
        mapToNBT(networkComponentsReceiver, "receiver", tag)
        val tagList = NBTTagList()
        networkComponentsTransfer.forEach {
            val nbtTagCompound = NBTTagCompound()
            it.writeToNBT(nbtTagCompound)
            tagList.appendTag(nbtTagCompound)
        }
        tag.setTag("transfer", tagList)
        tag.setInteger("transfer.size", tagList.tagCount())
        tagCompound.setTag(name, tag)
        return tagCompound
    }

    private fun mapToNBT(map: Map<BlockPos, BlockPos>, name: String = "map", tagCompound: NBTTagCompound = NBTTagCompound()): NBTTagCompound {
        val tagList = NBTTagList()
        map.toList().forEach {
            val tag = NBTTagCompound()
            it.first.writeToNBT(tag, "key")
            it.second.writeToNBT(tag, "value")
            tagList.appendTag(tag)
        }
        tagCompound.setTag(name, tagList)
        tagCompound.setInteger("$name.size", tagList.tagCount())
        return tagCompound
    }

    private fun nbtToMap(map: MutableMap<BlockPos, BlockPos>, name: String = "map", tagCompound: NBTTagCompound = NBTTagCompound()): MutableMap<BlockPos, BlockPos> {
        val tagList = tagCompound.getTagList(name, tagCompound.getInteger("$name.size"))
        for (i in 0 until tagList.tagCount()) {
            val tag = tagList.getCompoundTagAt(i)
            val key = BlockPos(0, 0, 0)
            val value = BlockPos(0, 0, 0)
            key.readFromNBT(tag, "key")
            value.readFromNBT(tag, "value")
            map[key] = value
        }
        return map
    }

    operator fun plusAssign(value: EnergyNetwork) {
        this.networkComponentsProvider += value.networkComponentsProvider
        this.networkComponentsReceiver += value.networkComponentsReceiver
        this.networkComponentsTransfer += value.networkComponentsTransfer
    }

    operator fun plusAssign(value: IAPProvider) {
        val transfer = value.getTransfer()
        if (transfer != null) {
            this.networkComponentsProvider[value.getPos()] = transfer
            this.networkComponentsTransfer += transfer
        }
    }

    operator fun plusAssign(value: IAPReceiver) {
        val transfer = value.getTransfer()
        if (transfer != null) {
            this.networkComponentsReceiver[value.getPos()] = transfer
            this.networkComponentsTransfer += transfer
        }
    }

    operator fun minusAssign(value: IAPProvider){
        if(value.getTransfer() != null){
            networkComponentsProvider.remove(value.getPos())
            if(!(networkComponentsProvider.containsValue(value.getTransfer()!!) || networkComponentsReceiver.containsValue(value.getTransfer()!!))){
                networkComponentsTransfer.remove(value.getTransfer()!!)
            }
        }
    }

    operator fun minusAssign(value: IAPReceiver){
        if(value.getTransfer() != null){
            networkComponentsReceiver.remove(value.getPos())
            if(!(networkComponentsProvider.containsValue(value.getTransfer()!!) || networkComponentsReceiver.containsValue(value.getTransfer()!!))){
                networkComponentsTransfer.remove(value.getTransfer()!!)
            }
        }
    }

    operator fun plus(value: EnergyNetwork): EnergyNetwork {
        val network = EnergyNetwork()
        network.networkComponentsProvider += this.networkComponentsProvider
        network.networkComponentsProvider += value.networkComponentsProvider
        network.networkComponentsReceiver += this.networkComponentsReceiver
        network.networkComponentsReceiver += value.networkComponentsReceiver
        network.networkComponentsTransfer += this.networkComponentsTransfer
        network.networkComponentsTransfer += this.networkComponentsTransfer
        return network
    }

    operator fun contains(pos: BlockPos): Boolean = (pos in networkComponentsProvider) || (pos in networkComponentsReceiver) || (pos in networkComponentsTransfer)
    operator fun contains(provider: IAPProvider): Boolean = provider.getPos() in networkComponentsProvider
    operator fun contains(receiver: IAPReceiver): Boolean = receiver.getPos() in networkComponentsReceiver
    operator fun contains(transfer: IAPTransfer): Boolean = transfer.getPos() in networkComponentsTransfer
}