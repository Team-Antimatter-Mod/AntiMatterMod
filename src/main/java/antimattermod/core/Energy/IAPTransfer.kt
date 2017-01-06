package antimattermod.core.Energy

import antimattermod.core.IAPAccessible
import c6h2cl2.YukariLib.Util.BlockPos

/**
 * @author C6H2Cl2
 * Interface for Connector with EnergyController.
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPTransfer {
    fun isProvider(): Boolean
    fun getTarget(): IAPAccessible
    fun getController(): BlockPos
    fun getPos(): BlockPos
}