package antimattaermod.core.Util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by Raiti on 2016/09/25.
 */
@FunctionalInterface
public interface AddInformationfunction {
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_);
	
	@SuppressWarnings("unchecked")
	public static void IngotInformation(ItemStack item, EntityPlayer player, List list, boolean isdebug){
		if(!(list.get(0) instanceof String))return;
		if(item.getItemDamage() == 0){
			list.add(StatCollector.translateToLocal("tile.crystal_01_11.information_1"));
			list.add(StatCollector.translateToLocal("tile.crystal_01_11.information_2"));
			list.add(ChatFormatting.RED+StatCollector.translateToLocal("tile.crystal_01_11.information.name"));
		}
	}
	
}
