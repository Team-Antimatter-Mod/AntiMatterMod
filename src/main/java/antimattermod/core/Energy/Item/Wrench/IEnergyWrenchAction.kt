package antimattermod.core.Energy.Item.Wrench

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
interface IEnergyWrenchAction {

    /**
     * ブロック側の送受信設定
     */
    fun settingTransceiver(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, isSneaking: Boolean)
}