package antimattermod.core.Block;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucibleHeater;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


/**
 * Created by Raiti-chan on 2016/10/27.
 * 粘土かまどヒーター
 *
 * @author Raiti-chan
 */
public class ClayCrucibleHeater extends BlockContainer {
	
	
	private IIcon iIcon_TOP;
	
	//コンストラクタ
	public ClayCrucibleHeater() {
		super(Material.rock);
		this.setBlockName("claycrucibleheater");
		this.setBlockTextureName(AntiMatterModCore.MOD_ID + ":another/claycrucibleheater");
		this.setCreativeTab(AntiMatterModRegistry.tabMachines);
		this.setResistance(5F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	//TileEntityの取得
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityClayCrucibleHeater();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float pX, float pY, float pZ) {
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityClayCrucibleHeater) {
			ItemStack stack = player.getHeldItem();
			TileEntityClayCrucibleHeater tile = (TileEntityClayCrucibleHeater) tileEntity;
			
			if (stack == null) {
				stack = tile.removeFuel();
				player.inventory.mainInventory[player.inventory.currentItem] = stack;
				return true;
			} else if (stack.getItem() == Items.coal) {
				tile.addFuel(stack);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack) {
		super.dropBlockAsItem(world, x, y, z, stack);
	}
	
	//ブロック破壊時のアイテムドロップ
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity entity;
		if ((entity = world.getTileEntity(x, y, z)) instanceof TileEntityClayCrucibleHeater)
			((TileEntityClayCrucibleHeater) entity).dropItems(this, world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	//テクスチャ―のリソース生成
	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.iIcon_TOP = register.registerIcon(this.getTextureName());
	}
	
	//テクスチャ―の取得
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		if (side == 1) return this.iIcon_TOP;
		if (side == 0) return Blocks.furnace.getIcon(0, 0);
		return side == meta ? Blocks.furnace.getIcon(2, 2) : side == meta - 6 ? Blocks.lit_furnace.getIcon(2, 2) : Blocks.furnace.getIcon(2, 3);
	}
	
	//テクスチャ―の取得
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (side) {
			case 0:
				return Blocks.furnace.getIcon(0, 0);
			case 1:
				return this.iIcon_TOP;
			case 3:
				return Blocks.furnace.getIcon(2, 2);
			default:
				return Blocks.furnace.getIcon(2, 3);
		}
	}
	
	//ブロックが設置されたとき
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
		
		int playerDir = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		ForgeDirection[] blockDir = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
		world.setBlockMetadataWithNotify(x, y, z, blockDir[playerDir].ordinal(), 2);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) < 6 ? 0 : 15;
	}

}
