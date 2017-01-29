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
class ItemWrench(name: String, texture: String, private val addfunc: AddInformationfunction) : Item(), AMMItemBase {

    init {
        creativeTab = AntiMatterModRegistry.tabTools
        unlocalizedName = name
        setTextureName(AntiMatterModCore.MOD_ID + ":" + "tool/" + texture)
    }

    override fun isFull3D(): Boolean {
        return true
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
        val blockPos = BlockPos(x, y, z)
        val clickPos = ClickPos(side, posX, posY, posZ)
        val wrenchMode = WrenchMode.values()

        when (wrenchMode[itemStack!!.tagCompound.getInteger("WrenchMode")]) {
            WrenchMode.Block -> {
                if (block is IDirectionWrenchAction) {
                    if (!isSneaking) {
                        this.settingDirection(world, block, x, y, z, meta, clickPos, block.isVerticalChange())
                    } else block.onBlockRemoval(block, player, world, blockPos, clickPos, side, meta)
                    return true
                }
            }
            WrenchMode.Transceiver -> {
                if (block is IEnergyWrenchAction) {
                    //block.settingTransceiver(itemStack, player, world, x, y, z, side, isSneaking)
                    this.settingTransceiver(itemStack, player, world, blockPos, side, isSneaking)
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
     */
    fun settingTransceiver(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: Int, isSneaking: Boolean) {
        TODO("Transferを消したことに伴う仕様変更")
        /*
        //ぬるぽ対策
        if (!itemStack.hasTagCompound()) {
            itemStack.tagCompound = NBTTagCompound()
        }
        //ItemStackのタグの参照
        val tag = itemStack.tagCompound
        val tile = pos.getTileEntityFromPos(world)
        /**
         * @throws IllegalArgumentException
         * IAPControllerまたはIAPTransferを継承していないクラスは禁止
         * ItemStackのNBTに書き込み処理
         */
        when (tile) {
            is IAPTransfer -> {
                if (tile.isProvider()){
                    tile.getTarget().getPos().writeToNBT(tag,"provider")
                }else{
                    tile.getTarget().getPos().writeToNBT(tag,"receiver")
                }
                tile.getPos().writeToNBT(tag,"transfer")
            }
            is IAPController -> tile.getPos().writeToNBT(tag,"controller")
            else -> throw IllegalArgumentException("TileEntity in pos must implement IAPTransfer or IAPController")
        }
        //Controllerと、Provider or Receiverの両方が揃っている場合、Controllerに接続情報を書き込む
        if (tag.hasKey("controller")) {
            if (!tag.hasKey("provider") && !tag.hasKey("receiver")) return
            //ControllerのTileEntity取得
            val posController = BlockPos(tag,"controller")
            val tileController = posController.getTileEntityFromPos(world) as? IAPController ?: throw IllegalArgumentException()
            val posTransfer = BlockPos(tag,"transfer")
            //Providerの時
            if (tag.hasKey("provider")) {
                //書き込み用のBlockPos取得
                val posProvider = BlockPos(tag, "provider")
                tileController.setProvider(posProvider,posTransfer)
                tag.removeTag("provider")
            } else if (tag.hasKey("Receiver")) {
                //Receiverの時 Providerと同様
                val posReceiver = BlockPos(tag, "receiver")
                tileController.setReceiver(posReceiver,posTransfer)
                tag.removeTag("receiver")
            }
            tag.removeTag("controller")
        }
        */
    }

    override fun addInformation(item: ItemStack?, player: EntityPlayer?, list: List<*>?, p_77624_4_: Boolean) {
        addfunc.addInformation(item, player, list, p_77624_4_)
    }
}

