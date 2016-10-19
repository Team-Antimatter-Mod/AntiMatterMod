package antimattermod.core.Item

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator
import antimattermod.core.Energy.IAPGenerator
import antimattermod.core.Energy.IAPMachine
import antimattermod.core.Energy.IAPTransfer
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World

/**
 * @author C6H2Cl2
 */
class StatesChecker : Item(){
    init {
        unlocalizedName = "StatesChecker"
        setTextureName(AntiMatterModCore.MOD_ID+":tool/stateschecker")
        creativeTab = AntiMatterModRegistry.tabMachines
        setMaxStackSize(1)
        val a : IAPGenerator? = null
    }
/*
    override fun onItemUse(itemStack: ItemStack?, player: EntityPlayer?, world: World, x: Int, y: Int, z: Int, p_77648_7_: Int, p_77648_8_: Float, p_77648_9_: Float, p_77648_10_: Float): Boolean {
        val targetTile : TileEntity? = world.getTileEntity(x,y,z)
        if(!(targetTile != null && targetTile is IAPMachine)){
            return false
        }
        if (!world.isRemote){
            if (targetTile is IAPGenerator){
                val targetGenerator : IAPGenerator = targetTile
                player!!.addChatComponentMessage(ChatComponentText("Fuel Value:" + targetGenerator.fuelValue + "/" + targetGenerator.maxFuelValue))
                player.addChatComponentMessage(ChatComponentText("Stored Energy:"+targetGenerator.storedEnergy + "/" + targetGenerator.maxStoreEnergy))
                player.addChatComponentMessage(ChatComponentText("Generating:"+targetGenerator.currentGenerate+"AP"))
            }else if (targetTile is IAPTransfer){
                val targetTransfer : IAPTransfer = targetTile
                player!!.addChatComponentMessage(ChatComponentText("Transfer Voltage:" + targetTransfer.sendVoltage))
                player.addChatComponentMessage(ChatComponentText("Transfer Energy:" + targetTransfer.sendVoltage.maxEnergy))
            }else{
                val targetMachine : IAPMachine = targetTile
                player!!.addChatComponentMessage(ChatComponentText("Energy:" + targetMachine.storedEnergy + "/" + targetMachine.maxStoreEnergy))
                if (targetMachine.canReceiveEnergy()){
                    player.addChatComponentMessage(ChatComponentText("Receive Voltage:" + targetMachine.receiveVoltage))
                }else{
                    player.addChatComponentMessage(ChatComponentText("Can't Receive Energy"))
                }
                if(targetMachine.canSendEnergy()){
                    player.addChatComponentMessage(ChatComponentText("Send Voltage:" + targetMachine.sendVoltage))
                }else{
                    player.addChatComponentMessage(ChatComponentText("Can't Send Energy"))
                }
            }
        }
        return true
    }*/
}
