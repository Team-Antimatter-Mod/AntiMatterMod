package antimattermod.core.Energy.Block.Generator

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Energy.MachineTier
import antimattermod.core.Energy.TileEntity.Generator.TileEntityFurnaceGenerator
import antimattermod.core.Util.ClickPos
import c6h2cl2.YukariLib.Util.BlockPos
import cpw.mods.fml.common.registry.GameRegistry
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
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.util.IIcon
import net.minecraft.util.MathHelper
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author C6H2Cl2
 */
class BlockFurnaceGenerator(val tier: MachineTier) : BlockContainer(Material.rock), IDirectionWrenchAction {

    //ブロックテクスチャ―
    @SideOnly(Side.CLIENT)
    private var Front_OFF: IIcon? = null
    @SideOnly(Side.CLIENT)
    private var Front_ON: IIcon? = null
    @SideOnly(Side.CLIENT)
    private var AnotherIcon: IIcon? = null

    init {
        //他modとの競合回避でAPつけた
        setBlockName("furnaceGeneratorAP")
        setHardness(50f)
        setResistance(50f)
        setHarvestLevel("pickaxe", 3)
        setCreativeTab(AntiMatterModRegistry.tabMachines)
    }

    override fun onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, p_149727_6_: Int, p_149727_7_: Float, p_149727_8_: Float, p_149727_9_: Float): Boolean {
        player.heldItem ?: return false
        val heldItem = player.heldItem
        var fuelVal = GameRegistry.getFuelValue(heldItem)
        if (fuelVal == 0) {
            fuelVal = TileEntityFurnace.getItemBurnTime(heldItem)
        }
        if (fuelVal < 1600) return false
        val tile = world.getTileEntity(x, y, z) as TileEntityFurnaceGenerator
        if (tile.isFuelMax()) return false
        val meta = world.getBlockMetadata(x, y, z)
        world.setBlockMetadataWithNotify(x, y, z, if (meta < 6) meta + 6 else meta, 2)
        while (!tile.isFuelMax() && heldItem.stackSize > 0) {
            tile.addFuel(fuelVal.toFloat())
            heldItem.stackSize--
        }
        return true
    }

    @SideOnly(Side.CLIENT)
    override fun registerBlockIcons(register: IIconRegister) {
        this.Front_OFF = register.registerIcon(AntiMatterModCore.MOD_ID + ":machine/tier1_furnacegenerator_off")
        this.Front_ON = register.registerIcon(AntiMatterModCore.MOD_ID + ":machine/tier1_furnacegenerator_on")
        this.AnotherIcon = register.registerIcon(AntiMatterModCore.MOD_ID + ":machine/tier1_hull")
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(side: Int, meta: Int): IIcon {
        return if (side == ForgeDirection.SOUTH.ordinal) this.Front_OFF as IIcon else this.AnotherIcon as IIcon
    }

    @SideOnly(Side.CLIENT)
    override fun getIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon {
        return if (side == world.getBlockMetadata(x, y, z)) this.Front_OFF as IIcon else if (side == world.getBlockMetadata(x, y, z) - 6) this.Front_ON as IIcon else this.AnotherIcon as IIcon
    }

    override fun onBlockPlacedBy(world: World?, x: Int, y: Int, z: Int, entityLivingBase: EntityLivingBase?, itemStack: ItemStack?) {

        val playerDir = MathHelper.floor_double((entityLivingBase!!.rotationYaw * 4.0f / 360.0f).toDouble() + 0.5) and 3
        val blockDir = arrayOf(ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST)
        world!!.setBlockMetadataWithNotify(x, y, z, blockDir[playerDir].ordinal, 2)
    }

    override fun getLightValue(world: IBlockAccess, x: Int, y: Int, z: Int): Int {
        return if (world.getBlockMetadata(x, y, z) < 6) 0 else 15
    }

    override fun createNewTileEntity(p_149915_1_: World, p_149915_2_: Int): TileEntity {
        return TileEntityFurnaceGenerator(tier)
    }

    override fun isVerticalChange(): Boolean {
        return false
    }

    override fun onBlockRemoval(block: Block, player: EntityPlayer, world: World, blockPos: BlockPos, clickPos: ClickPos, side: Int, meta: Int) {

    }
}
