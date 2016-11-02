package antimattermod.core.Block;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Item.ClayCruciblePattern;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/15.
 * 粘土るつぼ
 *
 * @author Raiti-chan
 */
public class ClayCrucible extends BlockContainer {
	
	public IIcon meltedIron;
	
	public ClayCrucible() {
		super(Material.rock);
		this.setBlockName("claycrucible");
		this.setCreativeTab(AntiMatterModRegistry.tabMachines);
		this.setResistance(5F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityClayCrucible();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		//手持ちアイテムの判定
		ItemStack heldItem;
		
		
		if (world.getTileEntity(x, y, z) instanceof TileEntityClayCrucible) {
			
			//タイルエンチチーのしゅとく
			TileEntityClayCrucible tile = (TileEntityClayCrucible) world.getTileEntity(x, y, z);
			
			//手持ち無しの場合か、粘土るつぼパターンの場合
			if ((heldItem = player.getHeldItem()) == null || heldItem.getItem() instanceof ClayCruciblePattern) {
				player.inventory.mainInventory[player.inventory.currentItem] = tile.setMode(heldItem);
				return true;
			}
			
			//その他だった場合
			int addSize = tile.addOres(player.getHeldItem());
			if (addSize != 0) {
				heldItem.stackSize -= addSize;
				tile.markDirty();
				player.inventory.markDirty();
				world.markBlockForUpdate(x, y, z);
			}
			return addSize > 0;
		} else {
			return false;
		}
	}
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityClayCrucible) {
			TileEntityClayCrucible entity = (TileEntityClayCrucible) tileEntity;
			switch (entity.getState()) {
				case NONE:
				case HEATING:
					if (entity.getStack() != null) this.dropBlockAsItem(world, x, y, z, entity.getStack());
					ItemStack stack;
					if ((stack = entity.setMode(null)) != null) {
						this.dropBlockAsItem(world, x, y, z, stack);
					}
					break;
				
				case MELTED:
					break;
				case SOLIDIFIED:
					this.dropBlockAsItem(world, x, y, z, entity.getDropCompletionItem());
					break;
			}
			
		}
		
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityClayCrucible) {
			TileEntityClayCrucible entity = (TileEntityClayCrucible) tileEntity;
			switch (entity.getState()) {
				case MELTED:
					player.attackEntityFrom(DamageSource.magic, 10F);
					break;
			}
		}
	}
	
	
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		return meta == 0 && super.canHarvestBlock(player, meta);
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	@Override
	public boolean isReplaceableOreGen(World world, int x, int y, int z, Block target) {
		return true;
	}
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
		
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
		this.setBlockBounds(2F / 16F, 0F, 2F / 16F, 14F / 16F, 14F / 16, 14F / 16F);
		super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		this.blockIcon = Blocks.hardened_clay.getIcon(0, 0);
		this.meltedIron = register.registerIcon(AntiMatterModCore.MOD_ID + ":liquid/liquid_iron");
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
}
