package antimattermod.core.Energy.TileEntity

import antimattermod.core.Energy.MultiBlock.MachineType
import antimattermod.core.Energy.MultiBlock.MultiBlockPlace
import net.minecraft.tileentity.TileEntity

/**
 * Created by kojin15.
 */
abstract class TileMultiCore : TileEntity() {

    /**
     * 設置の仕方
     */
    abstract fun getMultiBlockType(): MultiBlockPlace

    /**
     * 機械の種類
     */
    abstract fun getMachineType(): MachineType
}