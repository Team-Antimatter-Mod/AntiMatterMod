package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 * Interface for Connector with EnergyController.
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPTransfer {
    fun isConnecter():Boolean
    fun getTarget():ForgeDirection
    fun getController():BlockPos
}