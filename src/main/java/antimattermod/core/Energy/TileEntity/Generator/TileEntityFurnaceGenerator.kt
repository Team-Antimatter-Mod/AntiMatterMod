package antimattermod.core.Energy.TileEntity.Generator

import antimattermod.core.Energy.*
import antimattermod.core.Energy.EnergyGroup.*
import antimattermod.core.Energy.MachineTier.*
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity

/**
 * @author C6H2Cl2
 */
class TileEntityFurnaceGenerator : APGeneratorBase {
    //定数
    //private val voltage: APVoltage = APVoltage.HV
    //private val energyStorage: Int = voltage.maxEnergy * 20 * 600
    //変数
    private var fuel: Float = 0f
    private var maxFuel: Float = 0f
    //ToDo Tierによって、消費燃料が変わるようにする

    constructor() : this(NoTier)

    constructor(tier: MachineTier) : super(tier.voltage, tier) {
        maxFuel = when (tier.group) {
            Low -> 2048f
            Middle -> 8192f
            High -> 65536f
            SuperHigh -> 524288f
            UltraHigh -> 4194304f
            Ultimate -> 67108864f
            else -> maxFuel
        }
    }

    //NBTに書き込み
    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
        tagCompound.setFloat(FUEL, fuel)
        tagCompound.setFloat(MAX_FUEL, maxFuel)
    }

    //NBTから読み出し
    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
        fuel = tagCompound.getFloat(FUEL)
        maxFuel = tagCompound.getFloat(MAX_FUEL)
    }

    //tickごとの処理
    override fun updateEntity() {
        super.updateEntity()
    }

    //燃料追加
    fun addFuel(amount: Float): Float {
        val value: Float = amount / 1600
        fuel += value
        if (fuel > maxFuel) {
            val over = fuel - maxFuel
            fuel = maxFuel
            return over
        } else {
            return 0f
        }
    }

    override fun generate() {
        if (fuel > 1f) {
            if (energy + voltage.maxEnergy > energyStorage) {
                currentGenerate = energyStorage - energy
                fuel -= currentGenerate.toFloat() / voltage.maxEnergy.toFloat()
                energy = energyStorage
                setGenOff()
            } else {
                currentGenerate = voltage.maxEnergy
                energy += voltage.maxEnergy
                fuel -= 1f
            }
        } else if (fuel > 0f) {
            currentGenerate = (voltage.maxEnergy * fuel).toInt()
            if (energy + currentGenerate > energyStorage) {
                currentGenerate = energyStorage - energy
                fuel -= currentGenerate.toFloat() / voltage.maxEnergy.toFloat()
                energy = energyStorage
            } else {
                energy += currentGenerate
                fuel = 0f
            }
            setGenOff()
        }
    }

    private fun setGenOff() {
        val meta: Int = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)
        worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, if (meta > 5) meta - 6 else meta, 2)
    }

    override fun canGenerate() = fuel > 0f && energy != energyStorage

    override fun getFuelValue() = fuel

    override fun getFuelMax() = maxFuel

    override fun getFuelType() = BURNING

    override fun isFuelMax() = fuel == maxFuel
}
