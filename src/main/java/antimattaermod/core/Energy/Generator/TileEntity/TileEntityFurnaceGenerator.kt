package antimattaermod.core.Energy.Generator.TileEntity

import antimattaermod.core.Energy.APVoltage
import antimattaermod.core.Energy.IAPGenerator
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEntityFurnaceGenerator : TileEntity(),IAPGenerator{
    //定数
    private val voltage : APVoltage = APVoltage.HV
    private val energyStorage : Int = voltage.maxEnergy * 20 * 600
    private val maxFuel : Int = 2048
    //変数
    private var storedEnergy : Int = 0
    private var fuel : Int = 0
    init {

    }

    public fun addFuel(amount: Int) :Int{
        val value = amount / 1600
        fuel += value
        if (fuel > maxFuel){
            val over = fuel - maxFuel
            fuel = maxFuel
            return over
        }else{
            return 0
        }
    }

    public fun isFuelMax() : Boolean{
        return fuel == maxFuel
    }

    override fun getReceiveVoltage(): APVoltage {
        return APVoltage.ZeroVoltage
    }

    override fun getSendVoltage(): APVoltage {
        return voltage
    }

    override fun canReceiveEnergy(): Boolean {
        return false
    }

    override fun canSendEnergy(): Boolean {
        return true
    }

    override fun getMaxStoreEnergy(): Int {
        return energyStorage
    }
}
