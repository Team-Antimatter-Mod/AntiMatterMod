package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.client.ClientAntiMatterModCoreProxy
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.InputEvent
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.network.play.client.C09PacketHeldItemChange
import net.minecraft.util.ChatComponentText

/**
 * Created by kojin15.
 */
class WrenchKeyEvent {
    @SubscribeEvent
    fun KeyHandlingEvent(event: InputEvent.KeyInputEvent) {
        val player: EntityPlayer = Minecraft.getMinecraft().thePlayer ?: return
        val itemStack: ItemStack = player.currentEquippedItem ?: return
        if (itemStack.item is ItemWrench) {
            if (ClientAntiMatterModCoreProxy.wrenchSetting.isKeyPressed) {
                if(!itemStack.hasTagCompound()) (itemStack.item as ItemWrench).initTag(itemStack)
                var mode: Int = itemStack.tagCompound.getInteger("WrenchMode")
                val maxMode = (WrenchMode.ぬるぽ.ordinal) - 1
                mode = when (mode) {
                    maxMode -> 0
                    else -> mode + 1
                }
                itemStack.tagCompound.setInteger("WrenchMode", mode)
                player.inventory.mainInventory[player.inventory.currentItem] = itemStack
                player.addChatComponentMessage(ChatComponentText("Wrench Mode Changed:${WrenchMode.values()[mode]}"))
                Minecraft.getMinecraft().netHandler.addToSendQueue(C09PacketHeldItemChange(player.inventory.currentItem))
            }
        }
    }
}