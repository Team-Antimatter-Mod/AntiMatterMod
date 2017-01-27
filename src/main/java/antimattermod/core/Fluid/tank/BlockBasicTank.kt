package antimattermod.core.Fluid.tank

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Item.Wrench.ItemWrench
import c6h2cl2.YukariLib.Util.BlockPos
import com.sun.org.apache.xpath.internal.operations.Bool
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidContainerRegistry
import net.minecraftforge.fluids.FluidStack

/**
 * @author kojin15.
 */
class BlockBasicTank : BlockContainer(Material.iron) {
    init {
        setBlockName("BasicTank")
        setCreativeTab(AntiMatterModRegistry.tabMachines)
        setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F)
    }

    @SideOnly(Side.CLIENT)
    var topIcon: IIcon? = null
    @SideOnly(Side.CLIENT)
    var sideIcon: IIcon? = null

    override fun onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        //nullならreturn
        val currentItem: ItemStack? = player.inventory.getCurrentItem()
        currentItem ?: return true
        //requireNotNull()を使うことで、強制的にNotNullに
        val tile: TileBasicTank = requireNotNull(world.getTileEntity(x, y, z) as TileBasicTank?)
        val blockPos: BlockPos = BlockPos(x, y, z)
        //事前にnullチェックをしておくと、if分のネストが減るので可読性が上がる。
        //nullの時に何かする気が無いのなら、if(something != null){}ではなく、if(something == null) returnもしくは、something?:returnを使う方がよい。
        //多重ネストは醜くなるので、できるだけ減らすこと。
        val tankFluid = tile.productTank.fluid
        val currentFluid = FluidContainerRegistry.getFluidForFilledItem(currentItem)
        if (isNotNull(currentFluid)) {
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
                markDirty(world, player, tile, blockPos)

                return true
            }
            //ItemWrenchを持っている時にこの処理を行う。アイテムとしてドロップ。
        } else if (currentItem.item is ItemWrench/* && player.isSneaking*/) {
            val tankStack = ItemStack(this)
            if (tankStack.hasTagCompound()) {
                tile.writeToNBT(tankStack.tagCompound)
            } else {
                val tagCompound = NBTTagCompound()
                tile.writeToNBT(tagCompound)
                tankStack.tagCompound = tagCompound
            }
            if(!player.inventory.addItemStackToInventory(tankStack)){
                world.spawnEntityInWorld(EntityItem(world, x.toDouble(), y.toDouble(), z.toDouble(), tankStack))
            }
            world.setBlockToAir(x, y, z)
        } else {
            if (isNotNull(tankFluid)) {
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
                    markDirty(world, player, tile, blockPos)
                    return true
                }
            } else return true
        }
        return true
    }

    override fun createNewTileEntity(p_149915_1_: World?, p_149915_2_: Int): TileEntity {
        return TileBasicTank()
    }

    override fun isNormalCube(): Boolean {
        return false
    }

    override fun isOpaqueCube(): Boolean {
        return false
    }

    override fun renderAsNormalBlock(): Boolean {
        return false
    }

    override fun getIcon(side: Int, meta: Int): IIcon? {
        when (side) {
            0, 1 -> return topIcon
            else -> return sideIcon
        }
    }

    override fun registerBlockIcons(register: IIconRegister?) {
        topIcon = register!!.registerIcon("${AntiMatterModCore.MOD_ID}:tank/tank_basic_top")
        sideIcon = register.registerIcon("${AntiMatterModCore.MOD_ID}:tank/tank_basic_side")
    }

    fun markDirty(world: World, player: EntityPlayer, tile: TileEntity, blockPos: BlockPos) {
        tile.markDirty()
        player.inventory.markDirty()
        world.markBlockForUpdate(blockPos.getX(), blockPos.getY(), blockPos.getZ())
    }

    fun isNotNull(fluid: FluidStack?): Boolean {
        return fluid != null && fluid.getFluid() != null
    }
}