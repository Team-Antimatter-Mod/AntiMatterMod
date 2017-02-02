package antimattermod.core.Fluid.tank

import antimattermod.core.AMMRegistry
import antimattermod.core.Fluid.AMMFluidTank
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class ItemBlockBasicTank : ItemBlock {
    constructor():super(AMMRegistry.basicTank)
    constructor(block: Block):super(block)
    override fun placeBlockAt(stack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float, metadata: Int): Boolean {
        val flag = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata)
        if (stack.hasTagCompound() && flag) {
            val tile = TileBasicTank()
            tile.readFromNBT(stack.tagCompound)
            world.setTileEntity(x, y, z, tile)
            return true
        }
        return flag
    }

    @Suppress("unchecked")
    override fun addInformation(itemStack: ItemStack?, player: EntityPlayer?, list: MutableList<Any?>?, isdebug: Boolean) {
        itemStack?:return
        if (itemStack.hasTagCompound() && itemStack.tagCompound.hasKey("productTank")) {
            val productTank: AMMFluidTank = AMMFluidTank(16000)
            productTank.readFromNBT(itemStack.tagCompound.getCompoundTag("productTank"))

            list!!.add(productTank.getFluidLocalizedName())
            list.add("${productTank.fluidAmount}mb")
        }
    }
}