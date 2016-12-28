package antimattermod.core.Mob.ItemEgg

import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Mob.EntityDeveloperBoss
import net.minecraft.block.BlockLiquid
import net.minecraft.entity.Entity
import net.minecraft.entity.IEntityLivingData
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Facing
import net.minecraft.util.MathHelper
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
class ItemDeveloperBossEgg : Item() {
    init {
        unlocalizedName = "ItemDeveloperBossEgg"
        creativeTab = AntiMatterModRegistry.tabTools
        setTextureName("")
    }

    override fun onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, posX: Float, posY: Float, posZ: Float): Boolean {
        var x = x
        var y = y
        var z = z
        //サーバー側の場合は処理をスキップする
        if (world.isRemote) {
            return true
        } else {
            val block = world.getBlock(x, y, z)
            x += Facing.offsetsXForSide[side]
            y += Facing.offsetsYForSide[side]
            z += Facing.offsetsZForSide[side]
            var height = 0.0

            if (side == 1 && block.renderType == 11) {
                height = 0.5
            }

            val entity = spawnEntity(world, x.toDouble() + 0.5, y.toDouble() + height, z.toDouble() + 0.5)

            if (entity != null) {

                if (!player.capabilities.isCreativeMode) {
                    --itemStack.stackSize
                }
            }

            return true
        }
    }

    /**アイテムを使ったときのメソッド。ItemMonsterPlacer参照。 */
    override fun onItemRightClick(itemStack: ItemStack, world: World, player: EntityPlayer): ItemStack {
        //サーバー側の場合は処理をスキップする
        if (world.isRemote) {
            return itemStack
        } else {
            val movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true)

            if (movingobjectposition == null) {
                return itemStack
            } else {
                if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    val x = movingobjectposition.blockX
                    val y = movingobjectposition.blockY
                    val z = movingobjectposition.blockZ

                    if (!world.canMineBlock(player, x, y, z)) {
                        return itemStack
                    }

                    if (!player.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemStack)) {
                        return itemStack
                    }

                    if (world.getBlock(x, y, z) is BlockLiquid) {
                        val entity = spawnEntity(world, x.toDouble(), y.toDouble(), z.toDouble())

                        if (entity != null) {
                            if (!player.capabilities.isCreativeMode) {
                                --itemStack.stackSize
                            }
                        }
                    }
                }

                return itemStack
            }
        }
    }


    fun spawnEntity(world: World, x: Double, y: Double, z: Double): Entity? {
        val entityliving = EntityDeveloperBoss(world)
        entityliving.setLocationAndAngles(x, y, z, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0f), 0.0f)
        entityliving.rotationYawHead = entityliving.rotationYaw
        entityliving.renderYawOffset = entityliving.rotationYaw
        entityliving.onSpawnWithEgg(null as IEntityLivingData?)
        world.spawnEntityInWorld(entityliving)
        entityliving.playLivingSound()
        return entityliving
    }
}