package antimattermod.core.Energy.TileEntity.Generator

import antimattermod.core.Energy.*
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
abstract class APGeneratorBase(private var genVoltage: APVoltage, private var genTier: MachineTier) : TileEntity(), IAPProvider {
    override val tier: MachineTier
        get() = genTier
    override val voltage: APVoltage
        get() = genVoltage
    protected var energy = 0
    var currentGenerate = 0
    protected set
    var energyStorage = genVoltage.maxEnergy * 2000
    protected set

    override fun updateEntity() {
        super.updateEntity()
        if(canGenerate()){
            generate()
        }else{
            currentGenerate = 0
        }
    }

    protected abstract fun generate()
    protected abstract fun canGenerate(): Boolean
    abstract fun getFuelValue(): Float
    abstract fun getFuelType(): String
    abstract fun isFuelMax(): Boolean

    override fun handleRequest(target: BlockPos, value: Int): EnergyNode {
        val provide =
                if(value > genVoltage.maxEnergy){
                    if(energy >= genVoltage.maxEnergy){
                        genVoltage.maxEnergy
                    }else{
                        energy
                    }
                }else{
                    if(energy >= value){
                        value
                    }else{
                        energy
                    }
                }
        return EnergyNode(voltage, provide, getPos(), target)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, GENERATOR)
    }

    override fun writeToNBT(tagCompound: NBTTagCompound, name: String): NBTTagCompound {
        super<TileEntity>.readFromNBT(tagCompound)
        val tag = NBTTagCompound()
        genVoltage.writeToNBT(tag)
        genTier.writeToNBT(tag)
        tag.setInteger(ENERGY_VALUE, energy)
        tagCompound.setTag(name, tag)
        return tagCompound
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, GENERATOR)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound, name: String): APGeneratorBase {
        super<TileEntity>.readFromNBT(tagCompound)
        val tag = tagCompound.getTag(name) as NBTTagCompound
        genVoltage = genVoltage.readFromNBT(tag)
        genTier = genTier.readFromNBT(tag)
        energy = tag.getInteger(ENERGY_VALUE)
        genVoltage.maxEnergy * 2000
        return this
    }

    override fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos) {
        world.createExplosion(null, xCoord.toDouble(), yCoord.toDouble(), zCoord.toDouble(),
                Math.pow((value - voltage.maxEnergy) * 1.5, 3.0).toFloat(), true)
    }

    override fun getEnergyStorageValue() = energyStorage

    override fun getEnergyValue() = energy

    override fun getPos() = BlockPos(xCoord, yCoord, zCoord)

}