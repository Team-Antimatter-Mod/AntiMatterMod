package antimattermod.core.Energy

import antimattermod.core.MachineTier
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class EnergyNode {
    private var voltage: APVoltage = APVoltage.ZeroVoltage
    private var energyValue: Int = 0
    private var source: BlockPos = BlockPos(0, 0, 0)
    private var target: BlockPos = BlockPos(0, 0, 0)
    private var machineTier = MachineTier.NoTier

    constructor(voltage: APVoltage, value: Int, sourcePos: BlockPos, targetPos: BlockPos) {
        this.voltage = voltage
        this.energyValue = value
        this.source = sourcePos
        this.target = targetPos
    }

    constructor()

    fun writeToNBT(tagCompound: NBTTagCompound, tagName: String = ENERGY_NODE) {
        val tag = NBTTagCompound()
        voltage.writeToNBT(tag)
        tag.setInteger(ENERGY_VALUE, energyValue)
        source.writeToNBT(tag, SOURCE_POS)
        target.writeToNBT(tag, TARGET_POS)
        tagCompound.setTag(tagName, tag)
    }

    fun readFromNBT(tagCompound: NBTTagCompound, tagName: String = ENERGY_NODE) {
        val tag = tagCompound.getCompoundTag(tagName)
        voltage.readFromNBT(tag)
        energyValue = tag.getInteger(ENERGY_VALUE)
        source.readFromNBT(tag, SOURCE_POS)
        target.readFromNBT(tag, TARGET_POS)
    }

    fun applyDecayRtoC(pos: BlockPos) {
        if (energyValue < voltage.maxEnergy) {
            energyValue = (energyValue / Math.pow(machineTier.efficiency, pos.getDistance(target))).toInt()
            if (energyValue > voltage.maxEnergy) {
                energyValue = voltage.maxEnergy
            }
        }
    }

    fun appleDecayCtoP(pos: BlockPos) {
        if (energyValue < voltage.maxEnergy) {
            energyValue = (energyValue / Math.pow(machineTier.efficiency, pos.getDistance(source))).toInt()
            if (energyValue > voltage.maxEnergy) {
                energyValue = voltage.maxEnergy
            }
        }
    }

    fun appleDecayPtoR(pos: BlockPos, world: World) {
        energyValue = (energyValue * Math.pow(machineTier.efficiency, pos.getDistance(source))).toInt()
        val controller = pos.getTileEntityFromPos(world) as IAPController
        if (controller.voltage.maxEnergy > energyValue) {
            world.createExplosion(null, pos.getX().toDouble(), pos.getY().toDouble(), pos.getZ().toDouble(),
                    Math.pow(energyValue - controller.voltage.maxEnergy.toDouble(), 2.0).toFloat(), true)
        }
        energyValue = (energyValue * Math.pow(machineTier.efficiency, pos.getDistance(target))).toInt()
        val receiver = target.getTileEntityFromPos(world) as IAPReceiver
        if (receiver.voltage.maxEnergy > energyValue) {
            world.createExplosion(null, pos.getX().toDouble(), pos.getY().toDouble(), pos.getZ().toDouble(),
                    Math.pow(energyValue - receiver.voltage.maxEnergy.toDouble(), 2.0).toFloat(), true)
        }
    }

    fun getVoltage(): APVoltage = voltage
    fun getEnergyValue(): Int = energyValue
    fun getSource(): BlockPos = source
    fun getTargetPos(): BlockPos = target
    fun setTarget(pos: BlockPos) {
        if (target == BlockPos(0, 0, 0)) {
            target = pos
        }
    }
}