package antimattermod.core.Util;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.Item.MetaItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Raiti-chan
 * 中間アイテム簡易登録クラス<br>
 * @author Raiti
 * 
 */
public class ItemUtil {
	/**
	 * 無機能なアイテムオブジェクトを作成します<br>
	 * @param name アイテム名 {@link Item#setUnlocalizedName(String)}
	 * @param textureName テクスチャ―名 {@link Item#setTextureName(String)}
	 * @param addTab 追加タブ {@link Item#setCreativeTab(CreativeTabs)}
	 * @param stackSize 最大スタックサイズ {@link Item#setMaxStackSize(int)}
	 * @param hasSubtype メタ値でアイテムが分けられているか {@link Item#setHasSubtypes(boolean)}
	 * @param maxMeta
	 * @param isFull3D アイテムを3Dで表示するか {@link Item#isFull3D()}
	 * @param containerItem クラフト時に帰ってくるアイテム {@link Item#setContainerItem(Item)}
	 * @return アイテムオブジェクト
	 */
	public static Item CreateItem(@NotNull String name , @Nullable String textureName ,@Nullable CreativeTabs addTab ,int stackSize , boolean hasSubtype ,
								  int maxMeta , boolean isFull3D,@Nullable Item containerItem) {
		
		Item item = hasSubtype ? new MetaItem(maxMeta) : new Item();
		item.setUnlocalizedName(name);
		if(textureName == null || textureName.isEmpty()){
			textureName = name;
		}
		item.setTextureName(AntiMatterModCore.MOD_ID+":"+textureName);
		if(addTab != null) item.setCreativeTab(addTab);
		item.setMaxStackSize(stackSize);
		item.setMaxDamage(0);
		if(isFull3D) item.setFull3D();
		if(containerItem != null) item.setContainerItem(containerItem);
		
		return item;
	}
	
	/**
	 * {@link ItemUtil#CreateItem(String, String, CreativeTabs, int, boolean, int, boolean, Item)}のラッパー関数
	 * @param name 名前
	 * @param textureName テクスチャ名
	 * @param maxMeta メタデータ最大値
	 * @param tabs クリエイティブタブ
	 * @return アイテムオブジェクト
	 */
	public static Item CreateItem(@NotNull String name,@NotNull String textureName,int maxMeta,@Nullable CreativeTabs tabs){
		return CreateItem(name, textureName, tabs, 64, true, maxMeta, false, null);
	}
	
	/**
	 * {@link MetaItemBase}クラスを継承したクラスを使用してアイテムを生成します。<br>
	 * 主にツールチップの説明表示などに使います。
	 * @param name アイテム名 {@link Item#setUnlocalizedName(String)}
	 * @param textureName テクスチャ―名 {@link Item#setTextureName(String)}
	 * @param maxMeta 最大メタ値
	 * @param addTab 追加タブ {@link Item#setCreativeTab(CreativeTabs)}
	 * @param addfunc 情報追加関数 {@link AddInformationfunction#addInformation(ItemStack, EntityPlayer, List, boolean)}
	 * @return アイテムオブジェクト
	 */
	public static Item CreateItem(String name, String textureName, int maxMeta, CreativeTabs addTab, AddInformationfunction addfunc) {
		MetaItemBase item = new MetaItemBase(maxMeta, addfunc);
		item.setUnlocalizedName(name);
		item.setTextureName(AntiMatterModCore.MOD_ID+":"+textureName);
		if(addTab != null) item.setCreativeTab(addTab);
		item.setMaxStackSize(64);
		item.setMaxDamage(0);
		return item;
	}
	
}
