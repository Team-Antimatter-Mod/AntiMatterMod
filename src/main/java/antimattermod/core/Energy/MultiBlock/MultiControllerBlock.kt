package antimattermod.core.Energy.MultiBlock

import antimattermod.core.AMMGuiHandler
import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Util.ClickPos
import c6h2cl2.YukariLib.Util.BlockPos
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.util.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import java.util.*

/**
 * Created by kojin15.
 */
class MultiControllerBlock : BlockContainer(Material.rock), IDirectionWrenchAction {
    init {
        setBlockName("MultiController")
        textureName = "${AntiMatterModCore.MOD_ID}:casing/machinecasing_base"
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setTickRandomly(true)
    }

    @SideOnly(Side.CLIENT)
    var FrontIcon: IIcon? = null
    @SideOnly(Side.CLIENT)
    var AnotherIcon: IIcon? = null

    override fun updateTick(world: World?, x: Int, y: Int, z: Int, random: Random?) {
        if (world != null) {
            val meta = world.getBlockMetadata(x, y, z)
            val tile = world.getTileEntity(x, y, z)
            if (tile is MultiControllerTile) {
                tile.blockMeta = meta
            }
        }
    }

    override fun onBlockActivated(world: World?, x: Int, y: Int, z: Int, player: EntityPlayer?, side: Int, p_149727_7_: Float, p_149727_8_: Float, p_149727_9_: Float): Boolean {
        player!!.openGui(AntiMatterModCore.INSTANCE, AMMGuiHandler.GuiID_MultiController, world, x, y, z)
        return true
    }

    override fun onBlockPlacedBy(world: World?, x: Int, y: Int, z: Int, p_149689_5_: EntityLivingBase?, p_149689_6_: ItemStack?) {

        val playerDir = MathHelper.floor_double((p_149689_5_!!.rotationYaw * 4.0f / 360.0f).toDouble() + 0.5) and 3
        val blockDir = arrayOf(ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST)
        world!!.setBlockMetadataWithNotify(x, y, z, blockDir[playerDir].ordinal, 2)
    }

    override fun registerBlockIcons(register: IIconRegister?) {
        FrontIcon = register!!.registerIcon("${AntiMatterModCore.MOD_ID}:multiblock/multicontroller")
        AnotherIcon = register.registerIcon("${AntiMatterModCore.MOD_ID}:casing/machinecasing_base")
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(side: Int, meta: Int): IIcon {
        return if (side == ForgeDirection.SOUTH.ordinal) this.FrontIcon as IIcon else this.AnotherIcon as IIcon
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon {
        return if (side == world.getBlockMetadata(x, y, z)) this.FrontIcon as IIcon else this.AnotherIcon as IIcon
    }
    override fun isVerticalChange(): Boolean {
        return false
    }

    override fun onBlockRemoval(block: Block, player: EntityPlayer, world: World, blockPos: BlockPos, clickPos: ClickPos, side: Int, meta: Int) {

    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity {
        return MultiControllerTile()
    }
}