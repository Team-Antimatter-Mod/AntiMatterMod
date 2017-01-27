package antimattermod.core.Fluid.tank

import antimattermod.core.AMMRegistry
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
}