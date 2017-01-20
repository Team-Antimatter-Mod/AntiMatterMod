package antimattermod.core.World.Chunk

import antimattermod.core.AntiMatterModRegistry
import c6h2cl2.YukariLib.Util.BlockPos
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * @author kojin15.
 */
class BlockChunkLoader : Block(Material.rock), AMMChunkManager.IChunkLoaderBlock {
    init {
        setBlockName("ChunkLoader")
        setCreativeTab(AntiMatterModRegistry.tabMachines)
    }

    override fun onBlockPlacedBy(world: World?, x: Int, y: Int, z: Int, entityLivingBase: EntityLivingBase?, itemStack: ItemStack?) {
        if (world != null) AMMChunkManager().setBlockTicket(world, BlockPos(x, y, z))
    }

    override fun breakBlock(world: World?, x: Int, y: Int, z: Int, block: Block?, meta: Int) {
        if (world != null) AMMChunkManager().removeBlockTicket(world, BlockPos(x, y, z))
    }

    override fun onBlockPreDestroy(world: World?, x: Int, y: Int, z: Int, meta: Int) {
        if (world != null) AMMChunkManager().removeBlockTicket(world, BlockPos(x, y, z))
    }

    override fun canLoad(world: World, blockPos: BlockPos): Boolean {
        return true
    }
}