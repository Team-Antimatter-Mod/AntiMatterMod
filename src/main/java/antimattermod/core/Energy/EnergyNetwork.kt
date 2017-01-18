package antimattermod.core.Energy

import antimattermod.core.Util.BlockPosMap
import antimattermod.core.Util.BlockPosSet
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import org.jetbrains.kotlin.utils.toReadOnlyList
import java.util.*

/**
 * @author C6H2Cl2
 * @property providers : Map<Provider,Transfer>
 * @property receivers : Map<Receiver,Transfer>
 * @property transfers : Set<Transfer>
 */
class EnergyNetwork(private val controller: IAPController) {
    private val providers = BlockPosMap(controller.getPos()).toMutableMap()
    private val receivers = BlockPosMap(controller.getPos()).toMutableMap()
    private val transfers = BlockPosSet(controller.getPos()).toMutableSet()

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
            return providers + receivers
        }
    }

    fun getConnectedProviders(transfer: BlockPos): Array<BlockPos>? {
        if (!transfers.contains(transfer)) return null
        val providers = LinkedList<BlockPos>()
        this.providers
                .filterKeys { it == transfer }
                .entries.forEach { providers.add(it.key) }
        return providers.toTypedArray()
    }

    fun getConnectedReceivers(transfer: BlockPos): Array<BlockPos>? {
        if (!transfers.contains(transfer)) return null
        val receivers = LinkedList<BlockPos>()
        this.receivers
                .filterKeys { it == transfer }
                .entries.forEach { receivers.add(it.key) }
        return receivers.toTypedArray()
    }

    fun addProvider(provider: IAPProvider) {
        providers[provider.getPos()] = provider.getTransfer() as BlockPos
        transfers.add(provider.getTransfer() as BlockPos)
    }

    fun addReceiver(receiver: IAPReceiver) {
        receivers[receiver.getPos()] = receiver.getTransfer() as BlockPos
        transfers.add(receiver.getTransfer() as BlockPos)
    }

    fun readFromNBT(tagCompound: NBTTagCompound, name: String = "network"): EnergyNetwork {
        val tag = tagCompound.getTag(name) as NBTTagCompound
        nbtToMap(providers, "provider", tag)
        nbtToMap(receivers, "receiver", tag)
        val tagList = tagCompound.getTagList("transfer", tagCompound.getInteger("transfer.size"))
        for (i in 0 until tagList.tagCount()) {
            val value = BlockPos(0, 0, 0)
            value.readFromNBT(tagList.getCompoundTagAt(i))
            transfers.add(value)
        }
        return this
    }

    fun writeToNBT(tagCompound: NBTTagCompound, name: String = "network"): NBTTagCompound {
        val tag = NBTTagCompound()
        mapToNBT(providers, "provider", tag)
        mapToNBT(receivers, "receiver", tag)
        val tagList = NBTTagList()
        transfers.forEach {
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

    fun getProviders() = providers.keys.toReadOnlyList()

    fun getProvidersMap() = providers.toMap() as BlockPosMap

    fun getReceivers() = receivers.keys.toReadOnlyList()

    fun getReceiversMap() = receivers.toMap() as BlockPosMap

    fun getTransfers() = transfers.toReadOnlyList()

    fun getTransfersSet() = transfers.toSet() as BlockPosSet

    fun addProvider(provider: BlockPos, transfer: BlockPos){
        providers[provider] = transfer
        transfers.add(transfer)
    }

    fun addReceiver(receiver: BlockPos, transfer: BlockPos){
        receivers[receiver] = transfer
        transfers.add(transfer)
    }

    fun removeProvider(provider: BlockPos, transfer: BlockPos){
        if(!providers.remove(provider,transfer)){
            throw IllegalArgumentException()
        }
        if(!providers.containsValue(transfer) && !receivers.containsValue(transfer)){
            transfers.remove(transfer)
        }
    }

    fun removeReceiver(receiver: BlockPos,transfer: BlockPos){
        if(!receivers.remove(receiver,transfer)){
            throw IllegalArgumentException()
        }
        if(!providers.containsValue(transfer) && !receivers.containsValue(transfer)){
            transfers.remove(transfer)
        }
    }

    operator fun plusAssign(value: EnergyNetwork) {
        this.providers += value.providers
        this.receivers += value.receivers
        this.transfers += value.transfers
    }

    operator fun plusAssign(value: IAPProvider) {
        val transfer = value.getTransfer()
        if (transfer != null) {
            this.providers[value.getPos()] = transfer
            this.transfers += transfer
        }
    }

    operator fun plusAssign(value: IAPReceiver) {
        val transfer = value.getTransfer()
        if (transfer != null) {
            this.receivers[value.getPos()] = transfer
            this.transfers += transfer
        }
    }

    operator fun minusAssign(value: IAPProvider){
        if(value.getTransfer() != null){
            providers.remove(value.getPos())
            if(!(providers.containsValue(value.getTransfer()!!) || receivers.containsValue(value.getTransfer()!!))){
                transfers.remove(value.getTransfer()!!)
            }
        }
    }

    operator fun minusAssign(value: IAPReceiver){
        if(value.getTransfer() != null){
            receivers.remove(value.getPos())
            if(!(providers.containsValue(value.getTransfer()!!) || receivers.containsValue(value.getTransfer()!!))){
                transfers.remove(value.getTransfer()!!)
            }
        }
    }

    operator fun plus(value: EnergyNetwork): EnergyNetwork {
        val network = EnergyNetwork(controller)
        network.providers += this.providers
        network.providers += value.providers
        network.receivers += this.receivers
        network.receivers += value.receivers
        network.transfers += this.transfers
        network.transfers += this.transfers
        return network
    }

    operator fun contains(pos: BlockPos): Boolean = (pos in providers) || (pos in receivers) || (pos in transfers)
    operator fun contains(provider: IAPProvider): Boolean = provider.getPos() in providers
    operator fun contains(receiver: IAPReceiver): Boolean = receiver.getPos() in receivers
    operator fun contains(transfer: IAPTransfer): Boolean = transfer.getPos() in transfers
}