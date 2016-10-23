package antimattermod.core.Block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Raiti-chan on 2016/10/23.
 * AMMのブロックのベースクラス。<br>
 * TileEntityを持たない通常ブロックはこれを継承するようにしましょう。
 * @author Raiti-chan
 */
@SuppressWarnings("WeakerAccess")
public class AMMBlock extends Block{
	
	protected AMMBlock(Material material){
	    super(material);
	}
	
	/**
	 * ブロックが回収されようとしたときに呼ばれます
	 * @param world world
	 * @param x x座標
	 * @param y y座標
	 * @param z z座標
	 * @param meta メタデータ
	 * @param player プレイヤー
	 */
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		super.onBlockHarvested(world, x, y, z, meta, player);
	}
	
	/**
	 * ブロックが破壊される前に呼び出されます。
	 * @param world world
	 * @param x x座標
	 * @param y y座標
	 * @param z z座標
	 * @param meta メタデータ
	 */
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	/**
	 * ブロックの破壊してアイテムがドロップするときに呼ばれます。
	 * @param world world
	 * @param player プレイヤー
	 * @param x x座標
	 * @param y y座標
	 * @param z z座標
	 * @param meta メタデータ
	 */
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		super.harvestBlock(world, player, x, y, z, meta);
	}
}
