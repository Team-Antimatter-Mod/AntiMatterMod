package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.AntiMatterModCore
import antimattermod.core.AntiMatterModRegistry
import antimattermod.core.Energy.*
import antimattermod.core.Item.tool.AMMItemBase
import antimattermod.core.Util.AddInformationfunction
import antimattermod.core.Util.ClickPos
import c6h2cl2.YukariLib.Util.BlockPos
import cpw.mods.fml.relauncher.Side.CLIENT
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.play.client.C09PacketHeldItemChange
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World

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

    override fun onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, posX: Float, posY: Float, posZ: Float): Boolean {
        val isSneaking: Boolean = player.isSneaking
        val block = world.getBlock(x, y, z)
        val meta = world.getBlockMetadata(x, y, z)
        val blockPos = BlockPos(x, y, z)
        val clickPos = ClickPos(side, posX, posY, posZ)
        val wrenchMode = WrenchMode.values()
        if (!itemStack.hasTagCompound()){
            initTag(itemStack)
        }
        when (wrenchMode[itemStack.tagCompound.getInteger(WRENCH_MODE)]) {
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

    private fun settingDirection(world: World, block: Block, x: Int, y: Int, z: Int, meta: Int, clickPos: ClickPos, isVertical: Boolean) {
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
    private fun settingTransceiver(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: Int, isSneaking: Boolean) {
        val tag = itemStack.tagCompound
        val tile = pos.getTileEntityFromPos(world)
        //Tileの種類によってNBTのタグ名を変える
        when (tile) {
            is IAPProvider -> {
                if (tag.hasKey(RECEIVER)){
                    tag.removeTag(RECEIVER)
                }
                removeAndSetTag(tag, pos, PROVIDER)
            }
            is IAPReceiver -> {
                if(tag.hasKey(PROVIDER)){
                    tag.removeTag(PROVIDER)
                }
                removeAndSetTag(tag, pos, RECEIVER)
            }
            is IAPController -> removeAndSetTag(tag, pos, CONTROLLER)
            else -> throw IllegalArgumentException("arg\"pos\" must implements IAPProvider , IAPReceiver, or IAPController")
        }
        //Controllerと、Provider/Receiverのどちらかの両方が揃っているときだけ先に進む
        if (!tag.hasKey(CONTROLLER) || !(tag.hasKey(PROVIDER) || tag.hasKey(RECEIVER))){
            return
        }
        val tileController = BlockPos(tag, CONTROLLER).getTileEntityFromPos(world) as IAPController
        if (tag.hasKey(PROVIDER)){
            tileController.setProvider(BlockPos(tag, PROVIDER))
            tag.removeTag(PROVIDER)
        }else{
            tileController.setProvider(BlockPos(tag, RECEIVER))
            tag.removeTag(RECEIVER)
        }
        tag.removeTag(CONTROLLER)
        if(world.isRemote){

        }else{
            player.addChatComponentMessage(ChatComponentText("Succeed to connect"))
            (player as EntityPlayerMP).playerNetServerHandler.sendPacket(tileController.getDescriptionPacket())
        }
    }

    private fun removeAndSetTag(tagCompound: NBTTagCompound, pos: BlockPos, name: String) {
        if (tagCompound.hasKey(name)) {
            tagCompound.removeTag(name)
        }
        pos.writeToNBT(tagCompound, name)
    }
    
    fun initTag(itemStack: ItemStack){
        itemStack.tagCompound = NBTTagCompound()
        itemStack.tagCompound.setInteger(WRENCH_MODE, WrenchMode.Block.ordinal)
    }

    override fun addInformation(item: ItemStack?, player: EntityPlayer?, list: List<*>?, p_77624_4_: Boolean) {
        addfunc.addInformation(item, player, list, p_77624_4_)
    }
}

