package antimattaermod.core.Energy.Generator.TileEntity

import antimattaermod.core.Energy.APVoltage
import antimattaermod.core.Energy.IAPGenerator
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S25PacketBlockBreakAnim
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEntityFurnaceGenerator : TileEntity(),IAPGenerator{

    //定数
    private val voltage : APVoltage = APVoltage.HV
    private val energyStorage : Int = voltage.maxEnergy * 20 * 600
    private val maxFuel : Float = 2048f
    //変数
    private var storedEnergy : Int = 0
    private var fuel : Float = 0f
    private var currentGenerate : Int = 0
    init {

    }

    //NBTに書き込み
    override fun writeToNBT(tagCompound: NBTTagCompound?) {
        super.writeToNBT(tagCompound)
        tagCompound!!.setInteger("storedEnergy",storedEnergy)
        tagCompound.setFloat("fuel",fuel)
    }

    //NBTから読み出し
    override fun readFromNBT(tagCompound: NBTTagCompound?) {
        super.readFromNBT(tagCompound)
        storedEnergy = tagCompound!!.getInteger("storedEnergy")
        fuel = tagCompound.getFloat("fuel")
    }

    //tickごとの処理
    override fun updateEntity() {
        super.updateEntity()
        if(fuel > 1f){
            currentGenerate = voltage.maxEnergy
            storedEnergy += voltage.maxEnergy
            fuel -= 1f
        }else if (fuel > 0f){
            currentGenerate = (voltage.maxEnergy * fuel).toInt()
            storedEnergy += currentGenerate
            fuel = 0f
        }else{
            currentGenerate = 0
        }
    }

    //燃料追加
    public fun addFuel(amount: Float) :Float{
        val value :Float= amount / 1600
        fuel += value
        if (fuel > maxFuel){
            val over = fuel - maxFuel
            fuel = maxFuel
            return over
        }else{
            return 0f
        }
    }

    //データの同期
    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    override fun getDescriptionPacket(): Packet {
        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)
        return S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,1,tagCompound)
    }

    override fun getStoredEnergy(): Int {
        return storedEnergy
    }
    //Interfaceの実装
    override fun isFuelMax() : Boolean{
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

    override fun getCurrentGenerate(): Int {
        return currentGenerate
    }

    override fun getFuelValue(): Float {
        return fuel
    }

    override fun getMaxFuelValue(): Float {
        return maxFuel
    }
}
