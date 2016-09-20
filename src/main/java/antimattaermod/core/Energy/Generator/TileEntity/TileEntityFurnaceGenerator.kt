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
    private val energyStorage : Int = 1000000
    private val maxFuel : Int = 4096
    //変数
    private var storedEnergy : Int = 0
    private var fuel : Int = 0
    init {

    }

    public fun addFuel(amount: Int) :Int{
        var value = amount / 1600
        fuel += amount
        if (fuel > maxFuel){
            val over = fuel - maxFuel
            fuel = maxFuel
            return over
        }else{
            return 0
        }
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
