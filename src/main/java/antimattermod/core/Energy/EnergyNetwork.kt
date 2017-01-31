package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import c6h2cl2.YukariLib.Util.BlockPosSet
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import org.jetbrains.kotlin.utils.toReadOnlyList

/**
 * @author C6H2Cl2
 * @property providers : BlockPosSet(Mutable) all BlockPos of IAPProvider in network.
 * @property receivers : BlockPosSet(Mutable) all BlockPos of IAPReceiver in network.
 */
class EnergyNetwork(private val controller: IAPController) {
    private val providers = BlockPosSet().toMutableSet()
    private val receivers = BlockPosSet().toMutableSet()

    fun addProvider(provider: IAPProvider) {
        providers.add(provider.getPos())
    }

    fun addReceiver(receiver: IAPReceiver) {
        receivers.add(receiver.getPos())
    }

    fun readFromNBT(tagCompound: NBTTagCompound, name: String = NETWORK): EnergyNetwork {
        val tag = tagCompound.getTag(name) as NBTTagCompound
        val num_p = tag.getInteger(NUM_P)
        val num_r = tag.getInteger(NUM_R)
        val list_p = tag.getTagList(PROVIDERS, num_p)
        val list_r = tag.getTagList(RECEIVERS, num_r)
        (0..num_p).mapTo(providers) { BlockPos(list_p.getCompoundTagAt(it)) }
        (0..num_r).mapTo(receivers) { BlockPos(list_r.getCompoundTagAt(it)) }
        return this
    }

    fun writeToNBT(tagCompound: NBTTagCompound, name: String = NETWORK): NBTTagCompound {
        val tag = NBTTagCompound()
        val list_p = NBTTagList()
        val list_r = NBTTagList()
        providers.forEach {
            val nbt = NBTTagCompound()
            it.writeToNBT(nbt)
            list_p.appendTag(nbt)
        }
        receivers.forEach {
            val nbt = NBTTagCompound()
            it.writeToNBT(nbt)
            list_r.appendTag(nbt)
        }
        tag.setTag(PROVIDERS, list_p)
        tag.setTag(RECEIVERS, list_r)
        tag.setInteger(NUM_P, list_p.tagCount())
        tag.setInteger(NUM_R, list_r.tagCount())
        tagCompound.setTag(name, tag)
        return tagCompound
    }

    fun getProviders() = providers.toReadOnlyList()

    fun getReceivers() = receivers.toReadOnlyList()

    fun addProvider(provider: BlockPos) {
        providers.add(provider)
    }

    fun addReceiver(receiver: BlockPos) {
        receivers.add(receiver)
    }

    fun removeProvider(provider: BlockPos) {
        providers.remove(provider)
    }

    fun removeReceiver(receiver: BlockPos) {
        receivers.remove(receiver)
    }

    fun getCountConnected() = providers.size + receivers.size

    operator fun plusAssign(value: EnergyNetwork) {
        this.providers += value.providers
        this.receivers += value.receivers
    }

    operator fun plusAssign(value: IAPProvider) {
        providers.add(value.getPos())
    }

    operator fun plusAssign(value: IAPReceiver) {
        receivers.add(value.getPos())
    }

    operator fun minusAssign(value: IAPProvider) {
        providers.remove(value.getPos())
    }

    operator fun minusAssign(value: IAPReceiver) {
        receivers.remove(value.getPos())
    }

    operator fun plus(value: EnergyNetwork): EnergyNetwork {
        val network = EnergyNetwork(controller)
        network.providers += this.providers
        network.providers += value.providers
        network.receivers += this.receivers
        network.receivers += value.receivers
        return network
    }

    operator fun contains(pos: BlockPos): Boolean = (pos in providers) || (pos in receivers)
    operator fun contains(provider: IAPProvider): Boolean = provider.getPos() in providers
    operator fun contains(receiver: IAPReceiver): Boolean = receiver.getPos() in receivers
}