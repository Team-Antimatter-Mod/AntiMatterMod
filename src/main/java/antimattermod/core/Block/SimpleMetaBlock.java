package antimattermod.core.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/13.
 * メタデータを持つ基本的なアイテム
 * @author Raiti-chan
 */
public class SimpleMetaBlock extends AMMBlock{
	
	
	private IIcon[] icons;
	
	public SimpleMetaBlock(Material p_i45394_1_, int maxMeta) {
		super(p_i45394_1_);
		if (maxMeta > 16) throw new IllegalArgumentException("ブロックメタ値の最大は16です");
		icons = new IIcon[maxMeta];
	}
	
	
	@Override
	public int damageDropped(int p_149692_1_) {
		return p_149692_1_;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		for (int i = 0; i < icons.length; i++) {
			p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for (int i = 0; i < icons.length; i++){
			icons[i] = register.registerIcon(this.getTextureName()+"_"+i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}
}
