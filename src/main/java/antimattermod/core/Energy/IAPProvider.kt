package antimattermod.core.Energy

import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 * Interface for AP Generators
 * Implemented class must extend net.minecraft.tileentity.TileEntity.
 */
interface IAPProvider {
    fun getMaxProvideVoltage(): APVoltage
    fun getConnectableSide(): ForgeDirection
    fun getUseableEnergy(): Int
    fun useEnergy(value: Int, voltage: APVoltage)
    fun explode(value: Int, voltage: APVoltage, world: World, blockPos: BlockPos)
    fun getPos(): BlockPos
    fun getTransfer(): BlockPos?
}