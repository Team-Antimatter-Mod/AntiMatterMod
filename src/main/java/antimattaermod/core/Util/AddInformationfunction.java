package antimattaermod.core.Util;

import antimattaermod.core.AntiMatterModCore;
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
	
	void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_);
	
	@SuppressWarnings("unchecked")
	static void IngotInformation(ItemStack item, EntityPlayer player, List list, boolean isdebug){
		switch (item.getItemDamage()){
			case 0:
				list.add(StatCollector.translateToLocal("tile.ingot_01_0.information_1"));
				list.add(StatCollector.translateToLocal("tile.ingot_01_0.information_2"));
				list.add(ChatFormatting.RED+StatCollector.translateToLocal("tile.ingot_01_0.information.name"));
				break;
			case 1:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+AntiMatterModCore.modMetadata.authorList.get(1));
				break;
			case 2:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+AntiMatterModCore.modMetadata.authorList.get(2));
				break;
			case 3:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+AntiMatterModCore.modMetadata.authorList.get(0));
				break;
			case 4:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+AntiMatterModCore.modMetadata.authorList.get(3));
				break;
			case 5:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+AntiMatterModCore.modMetadata.authorList.get(4));
				break;
			case 6:
				list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information")+"Emilin");
				break;
		}
	}
	
}
