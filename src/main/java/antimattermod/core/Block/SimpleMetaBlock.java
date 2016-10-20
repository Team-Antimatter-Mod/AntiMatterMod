package antimattermod.core.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Raiti on 2016/10/13.
 */
public class SimpleMetaBlock extends Block{
	
	
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
	public void registerBlockIcons(IIconRegister p_149651_1_) {
		for (int i = 0; i < icons.length; i++){
			icons[i] = p_149651_1_.registerIcon(this.getTextureName()+"_"+i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return icons[p_149691_2_];
	}
}
