/*
 * 
 */
package antimattaermod.core.Block.Ores;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * ドロップアイテムが鉱石ブロックじゃないブロック
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class DifferentOreBlock extends OreBlock{

	private static Random random = new Random();
	
	private int[] doropmeta;
	
	private Item doropitems;
	
	private int[] dropQuantitys;
	
	/**
	 * ドロップアイテムが鉱石ブロックじゃないブロックの追加
	 * @param material 材質
	 * @param name ブロック名
	 * @param baseTextureName ベーステクスチャ名
	 * @param handle ベーステクスチャの適応判定数値(2進数表記で1になるところのメタ値と対応したメタがベーステクスチャが反映される)
	 * @param overlayTextureName 鉱石テクスチャ名(いわゆる鉱石が露出してたりするところ)
	 * @param tabs タブ
	 * @param maxMeta メタデータ最大値(1~16)
	 * @param herdness 硬さの配列
	 * @param harvestLevels 採掘レベルの配列
	 * @param doropitems ドロップアイテムのインスタンス
	 * @param doropmeta ブロックのメタ値に対応するドロップするアイテムのメタ値
	 */
	public DifferentOreBlock(Material material, String name, String baseTextureName, int handle, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int [] doropmeta) {
		super(material,name,baseTextureName,handle,overlayTextureName,tabs,maxMeta,herdness,harvestLevels);
		this.doropmeta = doropmeta;
		this.doropitems = doropitems;
	}
	
	/**
	 * ドロップアイテムが鉱石ブロックじゃないブロックの追加
	 * @param material 材質
	 * @param name ブロック名
	 * @param baseTextureName ベーステクスチャ名
	 * @param handle ベーステクスチャの適応判定数値(2進数表記で1になるところのメタ値と対応したメタがベーステクスチャが反映される)
	 * @param overlayTextureName 鉱石テクスチャ名(いわゆる鉱石が露出してたりするところ)
	 * @param tabs タブ
	 * @param maxMeta メタデータ最大値(1~16)
	 * @param herdness 硬さの配列
	 * @param harvestLevels 採掘レベルの配列
	 * @param doropitems ドロップアイテムのインスタンス
	 * @param doropmeta ブロックのメタ値に対応するドロップするアイテムのメタ値
	 * @param dropQuantity ドロップ量(これだけ配列の長さはmaxMeta×2 {基本ドロップ量,最大加算ドロップ量...})
	 */
	public DifferentOreBlock(Material material, String name, String baseTextureName, int handle, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta, int[] dropQuantity){
		this(material,name,baseTextureName,handle,overlayTextureName,tabs,maxMeta,herdness,harvestLevels,doropitems,doropmeta);
		this.dropQuantitys = dropQuantity;
	}
	
	/**
	 * ドロップアイテムが鉱石ブロックじゃないブロックの追加
	 * @param material 材質
	 * @param name ブロック名
	 * @param overlayTextureName 鉱石テクスチャ名(いわゆる鉱石が露出してたりするところ)
	 * @param tabs タブ
	 * @param maxMeta メタデータ最大値(1~16)
	 * @param herdness 硬さの配列
	 * @param harvestLevels 採掘レベルの配列
	 * @param doropitems ドロップアイテムのインスタンス
	 * @param doropmeta ブロックのメタ値に対応するドロップするアイテムのメタ値
	 */
	public DifferentOreBlock(Material material, String name, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta){
		this(material,name,null,0,overlayTextureName,tabs,maxMeta,herdness,harvestLevels,doropitems,doropmeta);
	}
	
	/**
	 * ドロップアイテムが鉱石ブロックじゃないブロックの追加
	 * @param material 材質
	 * @param name ブロック名
	 * @param overlayTextureName 鉱石テクスチャ名(いわゆる鉱石が露出してたりするところ)
	 * @param tabs タブ
	 * @param maxMeta メタデータ最大値(1~16)
	 * @param herdness 硬さの配列
	 * @param harvestLevels 採掘レベルの配列
	 * @param doropitems ドロップアイテムのインスタンス
	 * @param doropmeta ブロックのメタ値に対応するドロップするアイテムのメタ値
	 * @param dropQuantity ドロップ量(これだけ配列の長さはmaxMeta×2 {基本ドロップ量,最大加算ドロップ量...})
	 */
	public DifferentOreBlock(Material material, String name, String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta, int[] dropQuantity){
		this(material,name,null,0,overlayTextureName,tabs,maxMeta,herdness,harvestLevels,doropitems,doropmeta);
		this.dropQuantitys = dropQuantity;
	}
	
	/**
	 * ドロップアイテムが鉱石ブロックじゃないブロックの追加
	 * @param material 材質
	 * @param name ブロック名
	 * @param baseTextureNames ベーステクスチャの名前配列
	 * @param overlayTextureName 鉱石テクスチャ名(いわゆる鉱石が露出してたりするところ)
	 * @param tabs タブ
	 * @param maxMeta メタデータ最大値(1~16)
	 * @param herdness 硬さの配列
	 * @param harvestLevels 採掘レベルの配列
	 * @param doropitems ドロップアイテムのインスタンス
	 * @param doropmeta ブロックのメタ値に対応するドロップするアイテムのメタ値
	 * @deprecated まだテクスチャの判定がされない。
	 */
	@Deprecated
	public DifferentOreBlock(Material material, String name, String baseTextureNames[], String overlayTextureName, CreativeTabs tabs, int maxMeta, float[] herdness, byte[] harvestLevels, Item doropitems, int[] doropmeta) {
		super(material, name, baseTextureNames, overlayTextureName, tabs, maxMeta, herdness, harvestLevels);
		this.doropmeta = doropmeta;
		this.doropitems = doropitems;
	}
	
	
	
	@Override
	public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
		return MathHelper.getRandomIntegerInRange(random, 7, 10+(fortune*3));
	}
	
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return this.doropitems;
	}
	
	@Override
	public int damageDropped(int meta) {
		return this.doropmeta[meta];
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		int i = random.nextInt(fortune + 2) - 1;
		if(i < 0) i = 0;
		return this.quantityDropped(meta,random) * (i + 1);
	}
	
	public int quantityDropped(int meta,Random p_149745_1_) {
		if(this.dropQuantitys == null){
			return 1;
		}else {
			if(this.dropQuantitys.length < meta*2) return 1;
			int quantity = this.dropQuantitys[meta*2];
			quantity += this.dropQuantitys[meta*2+1] > 0 ? p_149745_1_.nextInt(this.dropQuantitys[meta*2+1]+1) : 0;
			return quantity;
		}
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		Item item = getItem(world, x, y, z);

		if (item == null)
		{
			return null;
		}
		return new ItemStack(item, 1, world.getBlockMetadata(x,y,z));
	}
}
