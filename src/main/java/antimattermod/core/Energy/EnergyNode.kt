package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class EnergyNode {
    private var voltage: APVoltage = APVoltage.ZeroVoltage
    private var energyValue: Int = 0
    private var source: BlockPos = BlockPos.Empty
    private var target: BlockPos = BlockPos.Empty
    private var machineTier = MachineTier.NoTier

    constructor(tier: MachineTier, value: Int, sourcePos: BlockPos, targetPos: BlockPos) {
        this.machineTier = tier
        this.voltage = tier.voltage
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

    fun applyDecay(from: BlockPos, to: BlockPos): EnergyNode {
        energyValue = (Math.pow(machineTier.efficiency, from.getDistance(to)) * energyValue).toInt()
        return this
    }

    fun unapplyDecay(from: BlockPos, to: BlockPos): EnergyNode {
        if (energyValue < voltage.maxEnergy) {
            energyValue = (energyValue / Math.pow(machineTier.efficiency, from.getDistance(to))).toInt()
            if (energyValue > voltage.maxEnergy) {
                energyValue = voltage.maxEnergy
            }
        }
        return this
    }

    fun explode(pos: BlockPos, world: World, voltage: APVoltage) {
        world.createExplosion(null, pos.getX().toDouble(), pos.getY().toDouble(), pos.getZ().toDouble(),
                Math.pow(energyValue - voltage.maxEnergy.toDouble(), 2.0).toFloat(), false)
    }

    fun getVoltage(): APVoltage = voltage
    fun getEnergyValue(): Int = energyValue
    fun getSource(): BlockPos = source
    fun getTargetPos(): BlockPos = target
    fun setTarget(pos: BlockPos) {
        if (target == BlockPos.Empty) {
            target = pos
        }
    }
    fun setSource(pos: BlockPos){
        if(source == BlockPos.Empty){
            source = pos
        }
    }
}