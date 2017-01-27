package antimattermod.core.Fluid

import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

/**
 * @author kojin15.
 */
class AMMFluidTank : FluidTank {

    constructor(capacity: Int) : super(capacity) {
    }

    constructor(stack: FluidStack, capacity: Int) : super(stack, capacity) {
    }

    constructor(fluid: Fluid, amount: Int, capacity: Int) : super(fluid, amount, capacity) {
    }

    fun getFluidType(): Fluid? {
        return if (getFluid() != null) getFluid().getFluid() else null
    }

    fun getFluidNameJa(): String {
        return if (this.fluid != null && this.fluid.getFluid() != null) this.fluid.getFluid().getLocalizedName(this.fluid) else "Empty"
    }

    fun isEmpty(): Boolean {
        return (getFluid() == null) || getFluid().getFluid() == null || (getFluid().amount <= 0)
    }

    fun isFull(): Boolean {
        return getFluid() != null && getFluid().amount == getCapacity()
    }

    @SideOnly(Side.CLIENT)
    fun setAmount(amount: Int) {
        if (fluid != null && fluid.getFluid() != null) {
            fluid.amount = amount
        }
    }

}
