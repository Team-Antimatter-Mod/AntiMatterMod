package antimattermod.core.Energy.TileEntity.Machine

import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.APVoltage.ZeroVoltage
import antimattermod.core.Energy.EnergyNode
import antimattermod.core.Energy.IAPAccessible
import antimattermod.core.Energy.IAPReceiver
import antimattermod.core.Energy.MACHINE
import antimattermod.core.Energy.MachineTier
import antimattermod.core.Energy.MachineTier.NoTier
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
abstract class APMachineBase(private var genVoltage: APVoltage, private var genTier: MachineTier) : TileEntity(), IAPReceiver {

    @Suppress("UNUSED")
    constructor() : this(ZeroVoltage, NoTier)

    protected var energy = 0
    override val tier: MachineTier
        get() = genTier
    override val voltage: APVoltage
        get() = genVoltage
    var currentUsing = 0
        protected set
    var energyStorage = genVoltage.maxEnergy * 2000
        protected set

    override fun addEnergy(energyNode: EnergyNode) {
        energy += energyNode.getEnergyValue()
        if (energy > energyStorage) energy = energyStorage
    }

    override fun getPos(): BlockPos = BlockPos(xCoord, yCoord, zCoord)

    override fun getEnergyValue() = energy

    override fun getEnergyStorageValue() = energyStorage

    override fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos) {
        world.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(),
                Math.pow((value - voltage.maxEnergy) * 1.5, 3.0).toFloat(), true)
    }

    //NBT
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, MACHINE)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound {
        super<TileEntity>.writeToNBT(tagCompound)
        val tag = NBTTagCompound()
        return tagCompound
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, MACHINE)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound, name: String): IAPAccessible {
        super<TileEntity>.readFromNBT(tagCompound)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //テンプレ
    override fun canUpdate() = true

    //同期用のパケット読み込み
    override fun onDataPacket(net: NetworkManager, pkt: S35PacketUpdateTileEntity) {
        readFromNBT(pkt.func_148857_g())
    }

    //同期用のパケット返す
    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound)
    }
}