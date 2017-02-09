package antimattermod.core.Energy.Item

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.IAPAccessible
import antimattermod.core.Energy.IAPController
import antimattermod.core.Energy.IAPProvider
import antimattermod.core.Energy.TileEntity.Generator.APGeneratorBase
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ChatComponentText
import net.minecraft.util.ChatComponentTranslation
import net.minecraft.util.StatCollector
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class StatesChecker : Item() {
    init {
        unlocalizedName = "StatesChecker"
        setTextureName(AntiMatterModCore.MOD_ID + ":tool/stateschecker")
        creativeTab = AntiMatterModRegistry.tabMachines
        setMaxStackSize(1)
    }

    override fun onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if(world.isRemote) return true
        val block = world.getBlock(x, y, z)
        val tile = world.getTileEntity(x, y, z)
        if (tile !is IAPAccessible && tile !is IAPController) return true
        val bar = ChatComponentText("==============================")
        val bar_long = ChatComponentText("========================================")
        if (tile is IAPAccessible) {
            player.addChatComponentMessage(bar_long)
            player.addChatComponentMessage(ChatComponentTranslation("amm.text.ap_info.title"))
            player.addChatComponentMessage(ChatComponentText("${StatCollector.translateToLocal("amm.text.voltage").trim()}: ${tile.voltage}"))
            player.addChatComponentMessage(ChatComponentText("${StatCollector.translateToLocal("amm.text.tier").trim()}: ${tile.tier.ordinal}"))
            player.addChatComponentMessage(ChatComponentText(
                    "${StatCollector.translateToLocal("amm.text.energy_val").trim()}: ${tile.getEnergyValue()} / ${tile.getEnergyStorageValue()}"))
        }
        if (tile is IAPController) {
            player.addChatComponentMessage(if(tile is IAPAccessible) bar else bar_long)
            player.addChatComponentMessage(ChatComponentTranslation("amm.text.ap_control.title"))
            if (tile !is IAPAccessible) {
                player.addChatComponentMessage(ChatComponentText("${StatCollector.translateToLocal("amm.text.voltage").trim()}: ${tile.voltage}"))
                player.addChatComponentMessage(ChatComponentText("${StatCollector.translateToLocal("amm.text.tier").trim()}: ${tile.tier.ordinal}"))
            }
            player.addChatComponentMessage(ChatComponentText(
                    "${StatCollector.translateToLocal("amm.text.connect_value").trim()}: ${tile.getNumConnected()} / ${tile.maxConnection}"))
        }
        if(tile is APGeneratorBase){
            player.addChatComponentMessage(bar)
            player.addChatComponentMessage(ChatComponentTranslation("amm.text.ap_gen.title"))
            player.addChatComponentMessage(ChatComponentText(
                    "${StatCollector.translateToLocal("amm.text.genVal").trim()}: ${tile.currentGenerate} / ${tile.voltage.maxEnergy}"))
            player.addChatComponentMessage(ChatComponentText(
                    "${StatCollector.translateToLocal("amm.text.fuelVal").trim()}: ${tile.getFuelValue()} / ${tile.getFuelMax()}"))
            player.addChatComponentMessage(ChatComponentText("${StatCollector.translateToLocal("amm.text.fuelType")}: ${tile.getFuelType()}"))
        }
        player.addChatComponentMessage(bar_long)
        return true
    }
}
