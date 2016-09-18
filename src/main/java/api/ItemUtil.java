/*
 * 
 */
package api;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/** <h1>ItemUtil</h1>
 * 中間アイテム簡易登録クラス<br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class ItemUtil {
	
	private ItemUtil() {}
	
	/**
	 * <h1>CreateItem</h1>
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
	public static Item CreateItem(String name , String textureName , CreativeTabs addTab , int stackSize , boolean hasSubtype , 
			int maxMeta , boolean isFull3D, Item containerItem) {
		
		Item item = hasSubtype ? new MetaItem(maxMeta) : new Item();
		item.setUnlocalizedName(name);
		item.setTextureName(textureName);
		if(addTab != null) item.setCreativeTab(addTab);
		item.setMaxStackSize(stackSize);
		item.setMaxDamage(0);
		if(isFull3D) item.setFull3D();
		if(containerItem != null) item.setContainerItem(containerItem);
		
		return item;
	}
	
	
}

class MetaItem extends Item{
	
	/**
	 * アイテムのテクスチャ―
	 */
	private IIcon[] icons;
	
	/**
	 * <B>コンストラクター</B><br>
	 * @param maxMeta 
	 */
	public MetaItem(int maxMeta) {
		icons = new IIcon[maxMeta];
		this.setHasSubtypes(true);
	}
	
	/**<h1>registerIcons</h1>
	 * オーバーライド
	 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iiconregister) {
		for(int i = 0; i < this.icons.length; i++) {
			this.icons[i] = iiconregister.registerIcon(this.getIconString() + "_" + i);
		}
	}
	
	/**<h1>getIconFromDamage</h1>
	 * オーバーライド
	 * @see net.minecraft.item.Item#getIconFromDamage(int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return this.icons[meta];
	}
	
	/**<h1>getSubItems</h1>
	 * オーバーライド
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item, net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List itemlist) {
		for(int i = 0; i < this.icons.length; i++) {
			itemlist.add(new ItemStack(this,1,i));
		}
	}
	
	/**<h1>getMetadata</h1>
	 * オーバーライド
	 * @see net.minecraft.item.Item#getMetadata(int)
	 */
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	/**<h1>getUnlocalizedName</h1>
	 * オーバーライド
	 * @see net.minecraft.item.Item#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getUnlocalizedName(ItemStack istack) {
		return super.getUnlocalizedName() + "_" + istack.getItemDamage();
	}
}

