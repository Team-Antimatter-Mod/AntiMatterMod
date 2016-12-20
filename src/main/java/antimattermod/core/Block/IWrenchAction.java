package antimattermod.core.Block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by kojin15.
 */
public interface IWrenchAction {

    /**
     * レンチで右クリックされた時
     * @param  side 実際にクリックされた側面
     * *
     * @param  selected レンチで選択した側面
     */
    void onWrenchClick(World world, EntityPlayer player, int x, int y, int z, int meta, int side, int selected);

    /**
     * レンチで右クリックされた時
     * @param  side 実際にクリックされた側面
     * *
     * @param  selected レンチで選択した側面
     */
    void onWrenchShiftClick(World world, EntityPlayer player, int x, int y, int z, int meta, int side, int selected);
}
