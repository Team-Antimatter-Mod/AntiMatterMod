package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.EnergyNetwork
import antimattermod.core.Energy.EnergyNode
import antimattermod.core.Energy.IAPController
import antimattermod.core.Energy.IAPProvider
import antimattermod.core.Energy.IAPReceiver
import antimattermod.core.Energy.Item.Wrench.IEnergyWrenchAction
import antimattermod.core.Energy.IAPAccessible
import antimattermod.core.Energy.MAX_CONNECT
import antimattermod.core.Energy.MachineTier
import antimattermod.core.Energy.MachineTier.NoTier
import antimattermod.core.Energy.TIER
import antimattermod.core.Energy.VOLTAGE
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class TileEnergyController : TileEntity, IEnergyWrenchAction, IAPController, IAPAccessible {
    override val tier: MachineTier
        get() = _tier
    override val voltage: APVoltage
        get() = _voltage
    override val maxConnection: Int
        get() = maxConnect
    private var _tier: MachineTier
    private var _voltage: APVoltage
    private var maxConnect: Int
    private val network = EnergyNetwork(this)
    private var requests = ArrayList<EnergyNode>().toMutableList()
    var energy = 0
        private set
    var maxEnergyStorage = 0
        private set
        get() = _voltage.maxEnergy * 20 * maxConnect

    constructor() : this(NoTier)

    constructor(machineTier: MachineTier) : super() {
        _tier = machineTier
        _voltage = tier.voltage
        maxConnect = tier.maxConnect
    }

    override fun updateEntity() {
        super.updateEntity()
        val pos = BlockPos(xCoord, yCoord, zCoord)
        var energyAfford = maxEnergyStorage - energy
        //IAPProviderから、エネルギー受取り
        network.getProviders().forEach {
            if (energyAfford <= 0) return@forEach
            val provider = it.getTileEntityFromPos(worldObj) as? IAPProvider ?: return@forEach
            energyAfford -= provider.handleRequest(pos, energyAfford).appleDecay(pos, it).getEnergyValue()
        }
        energy = maxEnergyStorage - energyAfford
        //リクエストの処理
        val handledRequests = ArrayList<EnergyNode>().toMutableList()
        requests.forEach {
            if (energy <= 0) return@forEach
            it.unappleDecay(pos, it.getTargetPos())
            val target = it.getTargetPos().getTileEntityFromPos(worldObj) as IAPReceiver
            if (it.getEnergyValue() > energy) {
                target.addEnergy(EnergyNode(voltage, energy, pos, it.getTargetPos()))
                energy = 0
            } else {
                target.addEnergy(it)
                energy -= it.getEnergyValue()
            }
            handledRequests.add(it)
        }
        requests.removeAll(handledRequests)
    }

    override fun sendRequest(node: EnergyNode) {
        requests.add(node)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super<TileEntity>.writeToNBT(tagCompound)
        network.writeToNBT(tagCompound)
        _tier.writeToNBT(tagCompound)
        _voltage.writeToNBT(tagCompound)
        tagCompound.setInteger(MAX_CONNECT, maxConnect)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound {
        val tag = NBTTagCompound()
        writeToNBT(tag)
        tagCompound.setTag(name, tag)
        return tagCompound
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super<TileEntity>.readFromNBT(tagCompound)
        network.readFromNBT(tagCompound)
        _tier = _tier.readFromNBT(tagCompound)
        _voltage = _voltage.readFromNBT(tagCompound)
        maxConnect = tagCompound.getInteger(MAX_CONNECT)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound, name: String): TileEnergyController {
        val tag = tagCompound.getTag(name)
        readFromNBT(tag as NBTTagCompound)
        return this
    }

    override fun getEnergyStorageValue() = maxEnergyStorage

    override fun getEnergyValue() = energy

    override fun getPos(): BlockPos {
        return BlockPos(xCoord, yCoord, zCoord)
    }

    override fun setProvider(provider: IAPProvider): Boolean {
        if (network.getCountConnected() < maxConnection) {
            network += provider
            return true
        }
        return false
    }

    override fun setProvider(provider: BlockPos): Boolean {
        if (network.getCountConnected() < maxConnection) {
            network.addProvider(provider)
            return true
        }
        return false
    }

    override fun setReceiver(receiver: IAPReceiver): Boolean {
        if (network.getCountConnected() < maxConnection) {
            network += receiver
            return true
        }
        return false

    }

    override fun setReceiver(receiver: BlockPos): Boolean {
        if (network.getCountConnected() < maxConnection) {
            network.addReceiver(receiver)
            return true
        }
        return false
    }

    override fun removeProvider(provider: IAPProvider) {
        network -= provider
    }

    override fun removeProvider(provider: BlockPos) {
        network.removeReceiver(provider)
    }

    override fun removeReceiver(receiver: IAPReceiver) {
        network -= receiver
    }

    override fun removeReceiver(receiver: BlockPos) {
        network.removeReceiver(receiver)
    }

    override operator fun contains(provider: IAPProvider): Boolean {
        return provider in network
    }

    override operator fun contains(receiver: IAPReceiver): Boolean {
        return receiver in network
    }

    override operator fun contains(pos: BlockPos): Boolean {
        return pos in network
    }

    override fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos) {
        world.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(),
                Math.pow((value - voltage.maxEnergy) * 1.5, 3.0).toFloat(), true)
    }

}