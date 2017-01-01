package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.*
import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Item.tool.AMMItemBase
import antimattermod.core.Util.AddInformationfunction
import antimattermod.core.Util.ClickPos
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection

/**
 * @author kojin15, C6H2Cl2
 */
class ItemWrench(name: String, texture: String) : Item(), AMMItemBase {


    init {
        creativeTab = AntiMatterModRegistry.tabTools
        unlocalizedName = name
        setTextureName(AntiMatterModCore.MOD_ID + ":" + "tool/" + texture)
    }

    override fun onUpdate(itemStack: ItemStack?, world: World?, entity: Entity?, i: Int, flag: Boolean) {
        if (!(itemStack!!.hasTagCompound())) {
            val nbt = NBTTagCompound()
            itemStack.tagCompound = nbt
            itemStack.tagCompound.setInteger("WrenchMode", WrenchMode.Block.ordinal)
        }

    }

    override fun onItemUse(itemStack: ItemStack?, player: EntityPlayer?, world: World?, x: Int, y: Int, z: Int, side: Int, posX: Float, posY: Float, posZ: Float): Boolean {
        val isSneaking: Boolean = player!!.isSneaking
        val block = world!!.getBlock(x, y, z)
        val meta = world.getBlockMetadata(x, y, z)
        val clickPos = ClickPos(side, posX, posY, posZ)
        val wrenchMode = WrenchMode.values()

        when (wrenchMode[itemStack!!.tagCompound.getInteger("WrenchMode")]) {
            WrenchMode.Block -> {
                if (block is IDirectionWrenchAction) {
                    if (!isSneaking) {
                        this.settingDirection(world, block, x, y, z, meta, clickPos, block.isVerticalChange())
                    } else block.onBlockRemoval(block, player, world, x, y, z, side, meta, clickPos)
                    return true
                }
            }
            WrenchMode.Transceiver -> {
                if (block is IEnergyWrenchAction) {
                    this.settingTransceiver(itemStack, player, world, x, y, z, side, isSneaking)
                    block.settingTransceiver(itemStack, player, world, x, y, z, side, isSneaking)
                }
                return true
            }
            else -> return false
        }
        return false
    }

    fun settingDirection(world: World, block: Block, x: Int, y: Int, z: Int, meta: Int, clickPos: ClickPos, isVertical: Boolean) {
        val cside = clickPos.getClickedDirection()
        if (isVertical) {
            if (meta < 6) {
                world.setBlockMetadataWithNotify(x, y, z, cside, 2)
            } else {
                world.setBlockMetadataWithNotify(x, y, z, cside + 6, 2)
            }
        } else {
            if (cside != 0 && cside != 1) {
                if (meta < 6) {
                    world.setBlockMetadataWithNotify(x, y, z, cside, 2)
                } else {
                    world.setBlockMetadataWithNotify(x, y, z, cside + 6, 2)
                }
            }
        }

    }

    /**
     * レンチ側の送受信設定
     * @author C6H2Cl2
     * @see c6h2cl2.YukariLib.Util.BlockPos
     * @throws TypeCastException
     */
    fun settingTransceiver(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, isSneaking: Boolean) {
        //ぬるぽ対策
        if (!itemStack.hasTagCompound()){
            itemStack.tagCompound = NBTTagCompound()
        }
        //ItemStackのタグの参照
        val tag = itemStack.tagCompound
        val pos = BlockPos(x, y, z)
        val tile = pos.getTileEntityFromPos(world)
        //対象のBlockPosから、種類を判別し、NBTTag用の名前を設定する
        val tagName : String = when(tile){
            is IAPController -> "Controller"
            is IAPTransfer -> if(tile.isProvider()){
                if (tag.hasKey("Receiver")) tag.removeTag("Receiver")
                "Provider"
            }else{
                if (tag.hasKey("Provider")) tag.removeTag("Provider")
                "Receiver"
            }
            else -> return
        }
        //NBTに座標情報の書き込み
        pos.writeToNBT(tag,tagName)
        //Controllerと、Provider or Receiverの両方が揃っている場合、Controllerに接続情報を書き込む
        if (tag.hasKey("Controller")){
            //Providerの時
            if (tag.hasKey("Provider")){
                //ControllerのTileEntity取得
                val posController = BlockPos(0,0,0)
                posController.readFromNBT(tag,"Controller")
                val tileController = posController.getTileEntityFromPos(world)
                //書き込み用のBlockPos取得
                val posProvider = BlockPos(0,0,0)
                posProvider.readFromNBT(tag,"Provider")
                /**
                 * @throws TypeCastException
                 * そもそもIAPControllerでない座標をControllerとして登録するな
                 */
                (tileController as IAPController).setProvider(posProvider)
                tag.removeTag("Provider")
                tag.removeTag("Controller")
            }else if (tag.hasKey("Receiver")){
                //Receiverの時 Providerと同様
                val posController = BlockPos(0,0,0)
                posController.readFromNBT(tag,"Receiver")
                val tileController = posController.getTileEntityFromPos(world)
                val posProvider = BlockPos(0,0,0)
                posProvider.readFromNBT(tag,"Receiver")
                /**
                 * @throws TypeCastException
                 */
                (tileController as IAPController).setProvider(posProvider)
                tag.removeTag("Receiver")
                tag.removeTag("Controller")
            }
        }
    }

    override fun addInformation(p_77624_1_: ItemStack, p_77624_2_: EntityPlayer, p_77624_3_: MutableList<Any?>, p_77624_4_: Boolean) {
        antimattermod.core.Util.AddInformationfunction.WrenchInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_)
    }
}

