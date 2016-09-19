/*
 * 
 */
package antimattaermod.core.item;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import antimattaermod.core.util.ItemUtil;
import antimattaermod.core.util.MetaItemBase;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/** <h1>CoerItems</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class CoreItems {
	private CoreItems() {}
	
	/**
	 * 素材タブ
	 */
	public final static CreativeTabs tabMaterials = new CreativeTabs("amaterials") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.diamond;
		}
	};
	
	//==================================================================================================================
	/**
	 * 素材
	 */
	public static Item materials = ItemUtil.CreateItem(new MetaItemBase(13) {
		@Override
		public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_) {
			if(item.getItemDamage() == 11) {
				list.add(StatCollector.translateToLocal("tile.material_11.information_1"));
				list.add(StatCollector.translateToLocal("tile.material_11.information_2"));
				list.add(ChatFormatting.RED+StatCollector.translateToLocal("tile.material_11.information.name"));
			}
		}
	},"material", "antimattermodcore:material", tabMaterials);
	
	//==================================================================================================================
	
	/**
	 * <h1>addItem</h1>
	 * GameRegistryへの登録<br>
	 */
	public static void addItem() {
		GameRegistry.registerItem(materials, "material");
	}
	
	

	
		
}
