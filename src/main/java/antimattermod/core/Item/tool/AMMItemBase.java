package antimattermod.core.Item.tool;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/26.
 * Itemクラスの日本語訳JavaDoc
 *
 * @author Raiti-chan
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public interface AMMItemBase {
	
	/**
	 * ブロックがこのツールによって破壊された時({@link net.minecraft.item.Item#onBlockDestroyed(ItemStack, World, Block, int, int, int, EntityLivingBase)}より前)
	 * に呼ばれます
	 *
	 * @param itemStack ツールのItemStack
	 * @param x         x座標
	 * @param y         y座標
	 * @param z         z座標
	 * @param player    player
	 * @return trueを返すとブロックが壊れない。(ひび割れのアニメーションは出る)
	 */
	boolean onBlockStartBreak(ItemStack itemStack, int x, int y, int z, EntityPlayer player);
	
	/**
	 * 金床によるツールの修復
	 *
	 * @param itemStack1 左スロットのアイテム
	 * @param itemStack2 右スロットのアイテム
	 * @return できる場合true
	 */
	boolean getIsRepairable(ItemStack itemStack1, ItemStack itemStack2);
	
	/**
	 * 採掘速度を返します。
	 *
	 * @param toolStack 自身のItemStack
	 * @param block     採掘対象ブロック
	 * @param metadata  採掘対象ブロックのメタ値
	 * @return 採掘速度
	 */
	float getDigSpeed(ItemStack toolStack, Block block, int metadata);
	
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
	boolean onBlockDestroyed(ItemStack toolStack, World world, Block block, int x, int y, int z, EntityLivingBase entity);
	
	/**
	 * Itemの詳細情報表示欄に表示する情報を追加します
	 *
	 * @param itemStack       追加するItemStack
	 * @param player          プレイヤー
	 * @param informationList 情報リスト(1要素1行)
	 * @param advanced        デバッグキー「F3+H」によって表示情報を拡張する設定が有効になっているときtrueになります
	 */
	void addInformation(ItemStack itemStack, EntityPlayer player, List informationList, boolean advanced);
}
