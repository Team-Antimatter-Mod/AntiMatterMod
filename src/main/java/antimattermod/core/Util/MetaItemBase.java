/*
 * 
 */
package antimattermod.core.Util;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/** <h1>MetaItemBase</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
 public class MetaItemBase extends Item{
	
	/**
	 * アイテムのテクスチャ―
	 */
	private IIcon[] icons;
	
	private AddInformationfunction addfunc;
	
	/**
	 * <B>コンストラクター</B><br>
	 * @param maxMeta メタデータ最大値
	 * @param addfunc 情報記載関数
	 */
	public MetaItemBase(int maxMeta, AddInformationfunction addfunc) {
		icons = new IIcon[maxMeta];
		this.setHasSubtypes(true);
		this.addfunc = addfunc;
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

	@SuppressWarnings("unchecked")
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
	
	/**<h1>addInformation</h1>
	 * オーバーライド<br>
	 * ツールチップへの表示メッセージ
	 * @see net.minecraft.item.Item#addInformation(net.minecraft.item.ItemStack, net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_){
		addfunc.addInformation(item,player,list,p_77624_4_);
	}
	
}
