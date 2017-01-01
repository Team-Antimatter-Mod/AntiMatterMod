package antimattermod.core.Energy.Block.Generator

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.APVoltage
import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Energy.TileEntity.Generator.TileEntityFurnaceGenerator
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

/**
 * @author C6H2Cl2
 */
class BlockFurnaceGenerator : BlockContainer(Material.rock), IDirectionWrenchAction {
    //定数
    private val voltage = APVoltage.HV
    private val energyStorage = voltage.maxEnergy * 20 * 600

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
    /*
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(player.getHeldItem() == null){
            return false;
        }
        ItemStack heldItem = player.getHeldItem();
        int fuelVal = GameRegistry.getFuelValue(heldItem);
        if(fuelVal == 0){
            fuelVal = TileEntityFurnace.getItemBurnTime(heldItem);
        }
        if(fuelVal < 1600){
            return false;
        }
        TileEntityFurnaceGenerator tileEntity = (TileEntityFurnaceGenerator)world.getTileEntity(x,y,z);
        if(tileEntity.isFuelMax()){
            return false;
        }
        int meta = world.getBlockMetadata(x,y,z);
        world.setBlockMetadataWithNotify(x,y,z,meta < 6 ? meta+6 : meta,2);
        int stackSize = heldItem.stackSize;
        for (int i=0;i<stackSize;++i){
            float remainder = tileEntity.addFuel(fuelVal);
            heldItem.stackSize --;
            if(remainder > 0){
                break;
            }
        }

        return true;
    }*/

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

    override fun onBlockPlacedBy(world: World?, x: Int, y: Int, z: Int, p_149689_5_: EntityLivingBase?, p_149689_6_: ItemStack?) {

        val playerDir = MathHelper.floor_double((p_149689_5_!!.rotationYaw * 4.0f / 360.0f).toDouble() + 0.5) and 3
        val blockDir = arrayOf(ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST)
        world!!.setBlockMetadataWithNotify(x, y, z, blockDir[playerDir].ordinal, 2)
    }

    override fun getLightValue(world: IBlockAccess, x: Int, y: Int, z: Int): Int {
        return if (world.getBlockMetadata(x, y, z) < 6) 0 else 15
    }

    override fun createNewTileEntity(p_149915_1_: World, p_149915_2_: Int): TileEntity {
        return TileEntityFurnaceGenerator()
    }

    override fun isVerticalChange(): Boolean {
        return false
    }

    override fun onBlockRemoval(block: Block, player: EntityPlayer, world: World, blockPos: BlockPos, clickPos: ClickPos, side: Int, meta: Int) {

    }
}
