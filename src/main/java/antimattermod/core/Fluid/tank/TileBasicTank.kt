package antimattermod.core.Fluid.tank

import antimattermod.core.Fluid.AMMFluidTank
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S35PacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTankInfo
import net.minecraftforge.fluids.IFluidHandler
import net.minecraft.network.NetworkManager
import net.minecraft.util.IIcon


/**
 * @author kojin15.
 */
class TileBasicTank : TileEntity(), IFluidHandler {

    var maxCapacity: Int = 16000
    var productTank: AMMFluidTank = AMMFluidTank(maxCapacity)

    override fun readFromNBT(nbtTagCompound: NBTTagCompound?) {
        super.readFromNBT(nbtTagCompound)
        if (nbtTagCompound != null) {

            productTank = AMMFluidTank(maxCapacity)
            if (nbtTagCompound.hasKey("productTank")) {
                productTank.readFromNBT(nbtTagCompound.getCompoundTag("productTank"))
            }
        }

    }

    override fun writeToNBT(nbtTagCompound: NBTTagCompound?) {
        super.writeToNBT(nbtTagCompound)
        if (nbtTagCompound != null) {

            val tank: NBTTagCompound = NBTTagCompound()
            productTank.writeToNBT(tank)
            nbtTagCompound.setTag("productTank", tank)
        }
    }

    override fun getDescriptionPacket(): Packet {
        val nbtTagCompound: NBTTagCompound = NBTTagCompound()
        writeToNBT(nbtTagCompound)
        return S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTagCompound)
    }

    override fun onDataPacket(net: NetworkManager?, pkt: S35PacketUpdateTileEntity?) {
        readFromNBT(pkt!!.func_148857_g())
    }

    @SideOnly(Side.CLIENT)
    fun getFluidIcon(): IIcon? {
        val fluid = productTank.getFluidType()
        if (fluid != null) {
            return fluid.icon
        } else return null
    }

    override fun isInvalid() = false


    //IFluidHandler---------------------------------------------------------------------------------------------------------
    override fun canDrain(from: ForgeDirection?, fluid: Fluid?): Boolean {
        return true
    }

    override fun canFill(from: ForgeDirection?, fluid: Fluid?): Boolean {
        return fluid != null && productTank.isEmpty()
    }

    override fun getTankInfo(from: ForgeDirection): Array<FluidTankInfo> {
        return arrayOf(productTank.info)
    }

    override fun drain(from: ForgeDirection?, resource: FluidStack?, doDrain: Boolean): FluidStack? {
        if (resource != null && productTank.getFluidType() == resource.getFluid()) {
            return productTank.drain(resource.amount, doDrain)
        } else return null
    }

    override fun drain(from: ForgeDirection?, maxDrain: Int, doDrain: Boolean): FluidStack {
        return productTank.drain(maxDrain, doDrain)
    }

    override fun fill(from: ForgeDirection?, resource: FluidStack?, doFill: Boolean): Int {
        if (resource == null || resource.getFluid() == null) return 0

        val current: FluidStack? = productTank.fluid
        val resourceCopy: FluidStack = resource.copy()
        if (current != null && current.amount > 0 && !current.isFluidEqual(resourceCopy)) {
            return 0
        }

        var i = 0
        val used = productTank.fill(resourceCopy, doFill)
        resourceCopy.amount -= used
        i += used
        return i


    }

}