package antimattermod.core.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by C6H2Cl2 on 2016/09/19.
 */
public class MetaItem extends Item {
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
    @SuppressWarnings("unchecked")
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
