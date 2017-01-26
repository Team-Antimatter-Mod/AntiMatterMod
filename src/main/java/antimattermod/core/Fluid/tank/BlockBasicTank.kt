package antimattermod.core.Fluid.tank

import antimattermod.core.AntiMatterModRegistry
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.FluidContainerRegistry
import net.minecraftforge.fluids.FluidStack

/**
 * @author kojin15.
 */
class BlockBasicTank : BlockContainer(Material.iron) {
    init {
        setBlockName("BasicTank")
        setCreativeTab(AntiMatterModRegistry.tabMachines)
    }

    override fun onBlockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val currentItem: ItemStack? = player!!.inventory.getCurrentItem()
        val tile: TileBasicTank? = world!!.getTileEntity(x, y, z) as TileBasicTank

        if (tile != null) {
            val tankFluid = tile.productTank.fluid

            if (currentItem != null) {
                val currentFluid = FluidContainerRegistry.getFluidForFilledItem(currentItem)

                if (currentFluid != null && currentFluid.getFluid() != null) {
                    val put: Int = tile.fill(ForgeDirection.UNKNOWN, currentFluid, false)

                    if (put == currentFluid.amount) {
                        tile.fill(ForgeDirection.UNKNOWN, currentFluid, true)
                        val usedContainer: ItemStack = FluidContainerRegistry.drainFluidContainer(currentItem)

                        if (!player.inventory.addItemStackToInventory(usedContainer.copy())) {
                            player.entityDropItem(usedContainer.copy(), 1f)
                        }
                        if (!player.capabilities.isCreativeMode && currentItem.stackSize-- <= 0) {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, null)
                        }
                        tile.markDirty()
                        player.inventory.markDirty()
                        world.markBlockForUpdate(x, y, z)

                        return true
                    }
                } else {
                    if (tankFluid != null && tankFluid.getFluid() != null) {
                        if (tankFluid.amount < 1000) return true
                        val get: ItemStack? = FluidContainerRegistry.fillFluidContainer(FluidStack(tankFluid.getFluid(), 1000), currentItem)
                        if (get != null) {
                            tile.drain(ForgeDirection.UNKNOWN, 1000, true)

                            if (!player.inventory.addItemStackToInventory(get.copy())) {
                                player.entityDropItem(get.copy(), 1f)
                            }
                            if (!player.capabilities.isCreativeMode && currentItem.stackSize-- <= 0) {
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, null)
                            }
                            tile.markDirty()
                            player.inventory.markDirty()
                            world.markBlockForUpdate(x, y, z)
                            return true
                        }
                    } else return true
                }
            }
        }
        return true
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity {
        return TileBasicTank()
    }
}