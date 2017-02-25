package antimattermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 * # 日本語
 * Controllerを除く、APを受け取ることのできる機械のTileEntity用のInterfaceです。
 * 必ず[net.minecraft.tileentity.TileEntity]を継承したクラスに実装してください。
 */
interface IAPReceiver : IAPAccessible {
    /**
     * Controllerから受け取ったときに呼ばれます。
     * エネルギーは[EnergyNode]形式で送信されます。
     */
    fun addEnergy(energy: EnergyNode)

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        writeToNBT(tagCompound, RECEIVER)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        readFromNBT(tagCompound, RECEIVER)
    }
}