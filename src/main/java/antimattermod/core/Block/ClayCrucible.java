package antimattermod.core.Block;

import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Item.ClayCruciblePattern;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/15.
 * 粘土るつぼ
 *
 * @author Raiti-chan
 */
public class ClayCrucible extends BlockContainer {
	
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
		return meta == 0 ? new TileEntityClayCrucible() : null;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		//手持ちアイテムの判定
		ItemStack heldItem;
		
		
		if (world.getTileEntity(x, y, z) instanceof TileEntityClayCrucible) {
			
			//タイルエンチチーのしゅとく
			TileEntityClayCrucible tile = (TileEntityClayCrucible) world.getTileEntity(x, y, z);
			
			//手持ち無しの場合か、粘土るつぼパターンの場合
			if ((heldItem = player.getHeldItem()) == null || heldItem.getItem() instanceof ClayCruciblePattern){
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
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityClayCrucible) {
			TileEntityClayCrucible entity = (TileEntityClayCrucible)tileEntity;
			if(entity.getOreBlock() != null)this.dropBlockAsItem(world,x,y,z,entity.getOreBlock());
			ItemStack stack;
			if((stack = entity.setMode(null)) != null){
				this.dropBlockAsItem(world,x,y,z,stack);
			}
		}
		
		super.breakBlock(world, x, y, z, block, meta);
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
