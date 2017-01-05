package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 * Interface for Machines using AP.
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPReceiver {
    fun getMaxRecieveVoltage(): APVoltage
    fun getConnectableSide(): ForgeDirection
    fun getAddableEnergy(): Int
    fun addEnergy(value: Int, voltage: APVoltage)
    fun getPos(): BlockPos
    fun getTransfer(): BlockPos?
}