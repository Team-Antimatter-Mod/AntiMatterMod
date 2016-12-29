package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.APType
import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Item.tool.AMMItemBase
import antimattermod.core.Util.AddInformationfunction
import antimattermod.core.Util.ClickPos
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

/**
 * Created by kojin15.
 */
class ItemWrench(name: String, texture: String) : Item(), AMMItemBase {


    init {
        creativeTab = AntiMatterModRegistry.tabTools
        unlocalizedName = name
        setTextureName(AntiMatterModCore.MOD_ID + ":" + "tool/" + texture)
    }

    override fun isFull3D(): Boolean {
        return true
    }

    override fun onUpdate(itemStack: ItemStack?, world: World?, entity: Entity?, i: Int, flag: Boolean) {
        if (!(itemStack!!.hasTagCompound())) {
            val nbt = NBTTagCompound()
            itemStack.tagCompound = nbt
            itemStack.tagCompound.setInteger("WrenchMode", WrenchMode.Block.ordinal)
        }

    }

    override fun onItemUse(itemStack: ItemStack?, player: EntityPlayer?, world: World?, x: Int, y: Int, z: Int, side: Int, posX: Float, posY: Float, posZ: Float): Boolean {
        val isSneaking: Boolean = player!!.isSneaking
        val block = world!!.getBlock(x, y, z)
        val meta = world.getBlockMetadata(x, y, z)
        val blockPos = BlockPos(x, y, z)
        val clickPos = ClickPos(side, posX, posY, posZ)
        val wrenchMode = WrenchMode.values()

        when (wrenchMode[itemStack!!.tagCompound.getInteger("WrenchMode")]) {
            WrenchMode.Block -> {
                if (block is IDirectionWrenchAction) {
                    if (!isSneaking) {
                        this.settingDirection(world, block, x, y, z, meta, clickPos, block.isVerticalChange())
                    } else block.onBlockRemoval(block, player, world, blockPos, clickPos, side, meta)
                    return true
                }
            }
            WrenchMode.Transceiver -> {
                if (block is IEnergyWrenchAction) {
                    this.settingTransceiver(itemStack, player, world, blockPos, side, isSneaking)
                    block.settingTransceiver(itemStack, player, world, blockPos, side, isSneaking)
                }
                return true
            }
            else -> return false
        }
        return false
    }

    fun settingDirection(world: World, block: Block, x: Int, y: Int, z: Int, meta: Int, clickPos: ClickPos, isVertical: Boolean) {
        val cside = clickPos.getClickedDirection()
        if (isVertical) {
            if (meta < 6) {
                world.setBlockMetadataWithNotify(x, y, z, cside, 2)
            } else {
                world.setBlockMetadataWithNotify(x, y, z, cside + 6, 2)
            }
        } else {
            if (cside != 0 && cside != 1) {
                if (meta < 6) {
                    world.setBlockMetadataWithNotify(x, y, z, cside, 2)
                } else {
                    world.setBlockMetadataWithNotify(x, y, z, cside + 6, 2)
                }
            }
        }

    }

    /**
     * レンチ側の送受信設定
     */
    fun settingTransceiver(itemStack: ItemStack, player: EntityPlayer, world: World, blockPos: BlockPos, side: Int, isSneaking: Boolean) {

    }

    override fun addInformation(p_77624_1_: ItemStack, p_77624_2_: EntityPlayer, p_77624_3_: MutableList<Any?>, p_77624_4_: Boolean) {
        antimattermod.core.Util.AddInformationfunction.WrenchInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
    }
}

