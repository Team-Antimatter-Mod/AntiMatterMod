package antimattermod.core.Item.tool

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Block.IWrenchAction
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
class ItemWrench(name: String, texture: String) : Item(), AMMItemBase {

    init {
        creativeTab = AntiMatterModRegistry.tabTools
        unlocalizedName = name
        setTextureName(AntiMatterModCore.MOD_ID + ":" + "tool/" + texture)
    }

    override fun onItemUse(itemStack: ItemStack?, player: EntityPlayer?, world: World?, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        val block = world!!.getBlock(x, y, z)
        val meta = world.getBlockMetadata(x, y, z)
        if (block is IWrenchAction) {
            if (!player!!.isSneaking) {
                block.onWrenchClick(world, player, x, y, z, meta, side, hitDirection(hitX.toDouble(), hitY.toDouble(), hitZ.toDouble(), side))
            } else {
                block.onWrenchShiftClick(world, player, x, y, z, meta, side, hitDirection(hitX.toDouble(), hitY.toDouble(), hitZ.toDouble(), side))
            }
            return true
        }
        return false
    }

    private fun hitDirection(hitX: Double, hitY: Double, hitZ: Double, side: Int): Int {
        if (onTop(hitX, hitY, hitZ, side)) {
            return 0
        }
        if (onBottom(hitX, hitY, hitZ, side)) {
            return 1
        }
        if (onNorth(hitX, hitY, hitZ, side)) {
            return 2
        }
        if (onSouth(hitX, hitY, hitZ, side)) {
            return 3
        }
        if (onEast(hitX, hitY, hitZ, side)) {
            return 4
        }
        if (onWest(hitX, hitY, hitZ, side)) {
            return 5
        }
        return -1
    }

    companion object {


        fun onTop(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if (hitX >= 0.25 && hitX <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                    return true
                }
                1 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                    return true
                }
                2 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.0 && hitY < 0.25) {
                    return true
                }
                3 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.0 && hitY < 0.25) {
                    return true
                }
                4 -> if (hitY > 0.0 && hitY < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                5 -> if (hitY > 0.0 && hitY < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                else -> return false
            }
            return false
        }

        private fun onBottom(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                    return true
                }
                1 -> if (hitX >= 0.25 && hitX <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                    return true
                }
                2 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.75 && hitY < 1.0) {
                    return true
                }
                3 -> if (hitX > 0.25 && hitX < 0.75 && hitY > 0.75 && hitY < 1.0) {
                    return true
                }
                4 -> if (hitY > 0.75 && hitY < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                5 -> if (hitY > 0.75 && hitY < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                else -> return false
            }
            return false
        }

        private fun onNorth(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                    return true
                }
                1 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                    return true
                }
                2 -> if (hitX >= 0.25 && hitX <= 0.75 && hitY >= 0.25 && hitY <= 0.75) {
                    return true
                }
                3 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0)) {
                    return true
                }
                4 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                    return true
                }
                5 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.0 && hitZ < 0.25) {
                    return true
                }
                else -> return false
            }
            return false
        }

        private fun onSouth(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                    return true
                }
                1 -> if (hitX > 0.25 && hitX < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                    return true
                }
                2 -> if ((hitX >= 0.0 && hitX <= 0.25 || hitX >= 0.75 && hitX <= 1.0) && (hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0)) {
                    return true
                }
                3 -> if (hitX >= 0.25 && hitX <= 0.75 && hitY >= 0.25 && hitY <= 0.75) {
                    return true
                }
                4 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                    return true
                }
                5 -> if (hitY > 0.25 && hitY < 0.75 && hitZ > 0.75 && hitZ < 1.0) {
                    return true
                }
                else -> return false
            }
            return false
        }

        private fun onEast(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if (hitX > 0.0 && hitX < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                1 -> if (hitX > 0.0 && hitX < 0.25 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                2 -> if (hitX > 0.0 && hitX < 0.25 && hitY > 0.25 && hitY < 0.75) {
                    return true
                }
                3 -> if (hitX > 0.0 && hitX < 0.25 && hitY > 0.25 && hitY < 0.75) {
                    return true
                }
                4 -> if (hitY >= 0.25 && hitY <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                    return true
                }
                5 -> if ((hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                    return true
                }
                else -> return false
            }
            return false
        }

        private fun onWest(hitX: Double, hitY: Double, hitZ: Double, side: Int): Boolean {
            when (side) {
                0 -> if (hitX > 0.75 && hitX < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                1 -> if (hitX > 0.75 && hitX < 1.0 && hitZ > 0.25 && hitZ < 0.75) {
                    return true
                }
                2 -> if (hitX > 0.75 && hitX < 1.0 && hitY > 0.25 && hitY < 0.75) {
                    return true
                }
                3 -> if (hitX > 0.75 && hitX < 1.0 && hitY > 0.25 && hitY < 0.75) {
                    return true
                }
                4 -> if ((hitY >= 0.0 && hitY <= 0.25 || hitY >= 0.75 && hitY <= 1.0) && (hitZ >= 0.0 && hitZ <= 0.25 || hitZ >= 0.75 && hitZ <= 1.0)) {
                    return true
                }
                5 -> if (hitY >= 0.25 && hitY <= 0.75 && hitZ >= 0.25 && hitZ <= 0.75) {
                    return true
                }
                else -> return false
            }
            return false
        }
    }

}

