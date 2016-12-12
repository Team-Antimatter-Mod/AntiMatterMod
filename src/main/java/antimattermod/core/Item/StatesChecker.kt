package antimattermod.core.Item

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator
import antimattermod.core.Energy.IAPGenerator
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
    }

    override fun onItemUse(itemStack: ItemStack?, player: EntityPlayer?, world: World?, x: Int, y: Int, z: Int, p_77648_7_: Int, p_77648_8_: Float, p_77648_9_: Float, p_77648_10_: Float): Boolean {
        /*val targetBlock : Block = world!!.getBlock(x,y,z)
        val targetTile : TileEntity? = world.getTileEntity(x,y,z)
        if(!(targetBlock is IAPGenerator && targetTile != null && targetTile is IAPGenerator)){
            return false
        }
        if (!world.isRemote){
            val targetGenerator = targetTile as TileEntityFurnaceGenerator
            player!!.addChatComponentMessage(ChatComponentText("Fuel Value:" + targetGenerator.fuelValue + "/" + targetGenerator.maxFuelValue))
            player.addChatComponentMessage(ChatComponentText("Stored Energy:"+targetGenerator.storedEnergy + "/" + targetGenerator.maxStoreEnergy))
            player.addChatComponentMessage(ChatComponentText("Generating:"+targetGenerator.currentGenerate+"AP"))
        }*/
        return true
    }
}
