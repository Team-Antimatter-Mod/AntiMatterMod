package antimattermod.core.Energy.Item.Wrench

import antimattermod.core.Util.ClickPos
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

/**
 * Created by kojin15.
 */
interface IDirectionWrenchAction {

    /**
     * 縦にも向き変更をするか
     */
    fun isVerticalChange(): Boolean

    /**
     * 撤去しようとした時に呼ばれる
     */
    fun onBlockRemoval(block: Block, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, meta: Int, clickPos: ClickPos)
}