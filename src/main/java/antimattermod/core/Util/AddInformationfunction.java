package antimattermod.core.Util;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Energy.Item.Wrench.ItemWrench;
import antimattermod.core.Energy.Item.Wrench.WrenchMode;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by Raitiーchan on 2016/09/25.
 * アイテムに情報を追加する関数インターフェイス
 *
 * @author Raiti-chan
 */
@FunctionalInterface
public interface AddInformationfunction {

    /**
     * 情報を追加する処理
     * playerに判定をつけると、この人だけにこの情報とか、<br>
     * 表示させた人の名前を表示とかができる
     *
     * @param item       トリガーアイテム
     * @param player     情報を表示させようとしたプレイヤー
     * @param list       情報を格納するリスト、要素1つにつき1行
     * @param p_77624_4_ F3+H キーが有効か
     */
    void addInformation(ItemStack item, EntityPlayer player, List list, boolean p_77624_4_);

    /**
     * {@link AntiMatterModRegistry#ingot_01}の情報
     */
    @SuppressWarnings("unchecked")
    static void IngotInformation(ItemStack item, @SuppressWarnings("unused") EntityPlayer player, List list, @SuppressWarnings("unused") boolean isdebug) {
        switch (item.getItemDamage()) {
            case 0:
                list.add(StatCollector.translateToLocal("tile.ingot_01_0.information_1"));
                list.add(StatCollector.translateToLocal("tile.ingot_01_0.information_2"));
                list.add(ChatFormatting.RED + StatCollector.translateToLocal("tile.ingot_01_0.information.name"));
                break;
            case 1:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + AntiMatterModCore.modMetadata.authorList.get(1));
                break;
            case 2:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + AntiMatterModCore.modMetadata.authorList.get(2));
                break;
            case 3:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + AntiMatterModCore.modMetadata.authorList.get(0));
                break;
            case 4:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + AntiMatterModCore.modMetadata.authorList.get(3));
                break;
            case 5:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + AntiMatterModCore.modMetadata.authorList.get(4));
                break;
            case 6:
                list.add(StatCollector.translateToLocal("tile.ingot_01_1>6.information") + "Emilin");
                break;
        }
    }

    @SuppressWarnings("unchecked")
    static void WrenchInformation(ItemStack item, @SuppressWarnings("unused") EntityPlayer player, List list, @SuppressWarnings("unused") boolean isdebug) {
        if (item.getItem() instanceof ItemWrench) {
            if (item.hasTagCompound()) {
                list.add(StatCollector.translateToLocal("item.wrench.mode") + ":" + StatCollector.translateToLocal("wrench.mode.name." + (WrenchMode.values()[item.getTagCompound().getInteger("WrenchMode")]).toString()));
                //list.add("Mode:" + (WrenchMode.values()[item.getTagCompound().getInteger("WrenchMode")]).toString());
            }
        }
    }

}
