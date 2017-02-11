package antimattermod.core.Energy.Filler.ModePattern

import antimattermod.core.AMMRegistry
import net.minecraft.item.ItemStack

/**
 * @author kojin15.
 * @param modeName Guiに表示されるモード名
 * @param itemStack Guiに置かれるItemStack
 */
abstract class FillerModePattern(private var modeName: String, private var itemStack: ItemStack) : IFillerMode {

    fun getModeName(): String = modeName

    fun getItemStack(): ItemStack = itemStack

    companion object {
        object FillerRegistry {
            fun registerMode(mode: FillerModePattern) {
                AMMRegistry.fillerModeList.add(mode)
            }
        }

    }
}