package antimattermod.core.Item.tool;

import antimattermod.core.Util.AMMToolMaterial;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/22.
 * 範囲採掘ハンマー
 *
 * @author Raiti-chan
 */
public class MiningHammer extends AMMTool {
	
	private int harvestRange = 1;
	
	/**
	 * 範囲採掘できるマイニングハンマーの作成
	 *
	 * @param name        ツール名
	 * @param textureName テクスチャ―名
	 * @param material    ツールマテリアル
	 * @param range       採掘範囲 range*2 + 1 平方ブロックの範囲採掘
	 */
	public MiningHammer(String name, String textureName, AMMToolMaterial material, int range) {
		super(name, textureName, material, 4F);
		this.harvestRange = range;
		setHarvestLevel("pickaxe", toolMaterial.getHarvestLevel());
	}
	
	/**
	 * ブロックがこのツールによって破壊されたときに呼ばれる。
	 *
	 * @param toolStack ツールのItemStack
	 * @param world     world
	 * @param block     破壊するブロック
	 * @param x         x座標
	 * @param y         y座標
	 * @param z         z座標
	 * @param entity    player
	 * @return 不明(とりあえずtrueを返しておく)
	 */
	@Override
	public boolean onBlockDestroyed(ItemStack toolStack, World world, Block block, int x, int y, int z, EntityLivingBase entity) {
		if (entity instanceof EntityPlayerMP) {
			ForgeDirection direction = getPlayerDirection(entity);
			if (direction == null) return false;
			int xEx = direction.offsetX == 0 ? this.harvestRange : 0, yEx = direction.offsetY == 0 ? this.harvestRange : 0, zEx = direction.offsetZ == 0 ? this.harvestRange : 0;
			toolStack.damageItem(1, entity);
			for (int xPoint = -xEx; xPoint <= xEx; xPoint++) {
				for (int yPoint = -yEx; yPoint <= yEx; yPoint++) {
					for (int zPoint = -zEx; zPoint <= zEx; zPoint++) {
						blockDestroy(world, x + xPoint, y + yPoint, z + zPoint, (EntityPlayerMP) entity, block, x, y, z, toolStack);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * ブロックを破壊します
	 *
	 * @param world       world
	 * @param x           x座標
	 * @param y           y座標
	 * @param z           z座標
	 * @param player      プレイヤー
	 * @param centerBlock 中心のブロック
	 * @param cX          中心のブロックのx座標
	 * @param cY          中心のブロックのy座標
	 * @param cZ          中心のブロックのz座標
	 * @param toolStack   破壊に使用したツールのItemStack
	 */
	private void blockDestroy(World world, int x, int y, int z, EntityPlayerMP player, Block centerBlock, int cX, int cY, int cZ, ItemStack toolStack) {
		if (!world.isAirBlock(x, y, z)) {
			Block block = world.getBlock(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			if (canDestroyBlock(toolStack, block, meta) && (centerBlock.getBlockHardness(world, cX, cY, cZ) - block.getBlockHardness(world, x, y, z)) <= 3F) {
				BreakEvent event = ForgeHooks.onBlockBreakEvent(world, player.theItemInWorldManager.getGameType(), player, x, y, z);
				if (!event.isCanceled()) {
					block.onBlockHarvested(world, x, y, z, meta, player);
					if (block.removedByPlayer(world, player, x, y, z, false)) {
						block.onBlockDestroyedByPlayer(world, x, y, z, meta);
						block.harvestBlock(world, player, x, y, z, meta);
					}
					if (!world.isRemote) {
						player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
					}
					
				}
			}
		}
	}
	
	/**
	 * ブロックが破壊可能かを取得します
	 *
	 * @param toolStack 破壊に使用したツール
	 * @param block     破壊ターゲットブロック
	 * @param meta      ブロックのメタデータ
	 * @return 破壊可能ならtrue
	 */
	private boolean canDestroyBlock(ItemStack toolStack, Block block, int meta) {
		String harvestToolClass = block.getHarvestTool(meta);
		for (String toolClass : this.getToolClasses(toolStack)) {
			if (this.getHarvestLevel(toolStack, toolClass) >= block.getHarvestLevel(meta) && toolClass.equals(harvestToolClass))
				return true;
		}
		return false;
	}
	
	/**
	 * プレイヤーの向きを取得します
	 *
	 * @param player プレイヤー
	 * @return 向きの {@link ForgeDirection}
	 */
	private static ForgeDirection getPlayerDirection(EntityLivingBase player) {
		int playerDirY = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int playerDirP = MathHelper.floor_double((double) (player.rotationPitch * 4.0F / 360.0F) + 0.5D) & 3;
		switch (playerDirP) {
			case 1:
				return ForgeDirection.DOWN;
			case 3:
				return ForgeDirection.UP;
			default:
		}
		
		switch (playerDirY) {
			case 0:
				return ForgeDirection.NORTH;
			case 1:
				return ForgeDirection.EAST;
			case 2:
				return ForgeDirection.SOUTH;
			case 3:
				return ForgeDirection.WEST;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List informationList, boolean advanced){
		int range = harvestRange*2+1;
		informationList.add(range+"X"+range);
		informationList.add(getMaxDamage()-getDamage(itemStack)+"/"+getMaxDamage());
	}
}
