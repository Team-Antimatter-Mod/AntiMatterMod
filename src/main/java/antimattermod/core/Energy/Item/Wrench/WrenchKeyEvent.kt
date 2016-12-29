package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.client.ClientAntiMatterModCoreProxy
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import org.lwjgl.input.Mouse

/**
 * Created by kojin15.
 */
class WrenchKeyEvent {
    @SubscribeEvent
    fun KeyHandlingEvent(event: InputEvent.KeyInputEvent) {
        val player: EntityPlayer? = Minecraft.getMinecraft().thePlayer
        val world: World? = Minecraft.getMinecraft().theWorld

        if (player != null && world != null) {
            val itemStack: ItemStack? = player.currentEquippedItem

            if (itemStack != null && itemStack.item is ItemWrench) {

                if (ClientAntiMatterModCoreProxy.WrenchSetting.isKeyPressed) {
                    if (itemStack.hasTagCompound()) {
                        val mode: Int = itemStack.tagCompound.getInteger("WrenchMode")
                        val maxMode = (WrenchMode.ぬるぽ.ordinal) - 1
                        //System.out.println(mode)

                        when (mode) {
                            maxMode -> itemStack.tagCompound.setInteger("WrenchMode", 0)
                            else -> itemStack.tagCompound.setInteger("WrenchMode", mode + 1)
                        }
                    }
                }
            }
        }
    }
}