package antimattermod.core.World.Chunk

import java.util.Arrays
import java.util.HashMap

import antimattermod.core.AntiMatterModCore
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.block.Block
import net.minecraft.world.World
import net.minecraftforge.common.ForgeChunkManager
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback
import net.minecraftforge.common.ForgeChunkManager.Ticket
import net.minecraftforge.common.ForgeChunkManager.Type

/**
 * @author kojin15
 */
class AMMChunkManager: LoadingCallback {

    override fun ticketsLoaded(tickets: List<Ticket>, world: World) {

        for (t in tickets) {

            if (this.isBlockTicket(t)) {


                val blockPos = BlockPos(t.modData.getInteger("x"), t.modData.getInteger("y"), t.modData.getInteger("z"))

                if (this.getBlock(t, world) is IChunkLoaderBlock) {

                    if ((this.getBlock(t, world) as IChunkLoaderBlock).canLoad(world, blockPos)) {
                        setBlockTicket(world, blockPos)
                    } else {
                        removeBlockTicket(world, blockPos)
                    }

                } else {
                    removeBlockTicket(world, blockPos)
                }

            }

        }

    }

    /**
     * ChunkLoaderとして起動する
     */
    fun setBlockTicket(world: World, blockPos: BlockPos): Boolean {

        val t = ForgeChunkManager.requestTicket(AntiMatterModCore.INSTANCE, world, Type.NORMAL) ?: return false

        setBlockType(t)
        setBlock(t, blockPos)
        ticketList.put(blockPos, t)
        ForgeChunkManager.forceChunk(t, world.getChunkFromBlockCoords(blockPos.getX(), blockPos.getZ()).chunkCoordIntPair)
        return true
    }

    /**
     * ChunkLoaderを停止する
     */
    fun removeBlockTicket(world: World, blockPos: BlockPos) {

        if (ticketList.containsKey(blockPos)) {

            if (!ForgeChunkManager.getPersistentChunksFor(ticketList[blockPos]?.world).isEmpty) {
                ForgeChunkManager.unforceChunk(ticketList[blockPos], world.getChunkFromBlockCoords(blockPos.getX(), blockPos.getZ()).chunkCoordIntPair)
            }

            ticketList.remove(blockPos)

        }

    }

    fun setBlockType(ticket: Ticket) {
        ticket.modData.setString("type", "block")
    }

    fun setBlock(ticket: Ticket, blockPos: BlockPos) {

        ticket.modData.setInteger("x", blockPos.getX())
        ticket.modData.setInteger("y", blockPos.getY())
        ticket.modData.setInteger("z", blockPos.getZ())

    }

    fun isBlockTicket(ticket: Ticket): Boolean {
        return ticket.modData.getString("type") == "block"
    }

    fun getBlock(ticket: Ticket, world: World): Block {
        return BlockPos(ticket.modData.getInteger("x"), ticket.modData.getInteger("y"), ticket.modData.getInteger("z")).getBlockFromPos(world)
    }

    interface IChunkLoaderBlock {

        fun canLoad(world: World, blockPos: BlockPos): Boolean

    }

    companion object {
        val instance: AMMChunkManager? = AMMChunkManager()

        protected val ticketList = HashMap<BlockPos, Ticket>()


    }

}
