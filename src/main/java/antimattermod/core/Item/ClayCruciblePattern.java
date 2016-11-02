package antimattermod.core.Item;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/26.
 *
 * @author Raiti-chan
 */
public class ClayCruciblePattern extends Item {
	
	private IIcon[] icons;
	
	public ClayCruciblePattern() {
		this.setUnlocalizedName("clayCruciblePattern");
		this.setTextureName(AntiMatterModCore.MOD_ID + ":ccpattern/");
		this.setCreativeTab(AntiMatterModRegistry.tabMachines);
		this.setMaxStackSize(1);
		icons = new IIcon[ClayCruciblePatternList.values().length];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		for (int i = 0; i < icons.length; i++) {
			this.icons[i] = register.registerIcon(this.getIconString() + "_" + i);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "_" + itemStack.getItemDamage();
	}
	
	public enum ClayCruciblePatternList {
		HammerHead(6, AntiMatterModRegistry.iron_tool_material, 0),
		ScrewDriverHead(2, AntiMatterModRegistry.iron_tool_material, 1),
		PickaxeHead(3, AntiMatterModRegistry.iron_tool_material, 2),
		ShovelHead(1, AntiMatterModRegistry.iron_tool_material, 3),
		SwordBlade(2, AntiMatterModRegistry.iron_tool_material, 4),;
		
		public final int stackSize;
		public final Item completionItem;
		public final int itemMeta;
		
		ClayCruciblePatternList(int stackSize, Item completionItem, int itemMeta) {
			this.stackSize = stackSize;
			this.completionItem = completionItem;
			this.itemMeta = itemMeta;
		}
	}
	
}
