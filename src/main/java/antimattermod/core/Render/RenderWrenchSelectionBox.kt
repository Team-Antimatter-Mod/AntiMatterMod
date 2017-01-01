package antimattermod.core.Render

import antimattermod.core.Energy.Item.Wrench.IDirectionWrenchAction
import antimattermod.core.Energy.Item.Wrench.ItemWrench
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.Tessellator
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.ChunkPosition
import net.minecraft.world.World
import net.minecraftforge.client.event.RenderWorldLastEvent
import net.minecraftforge.common.util.ForgeDirection
import org.lwjgl.opengl.GL11

/**
 * Created by kojin15.
 */


@SideOnly(Side.CLIENT)
class RenderWrenchSelectionBox {

    @SubscribeEvent
    fun onRenderSelectionBox(event: RenderWorldLastEvent) {
        val player = Minecraft.getMinecraft().thePlayer
        val world = Minecraft.getMinecraft().theWorld
        val MOP = Minecraft.getMinecraft().objectMouseOver
        val block = world.getBlock(MOP.blockX, MOP.blockY, MOP.blockZ)

        if (player != null) {

            val itemStack = player.currentEquippedItem
            // ItemBlockを持っていて、ブロックにポインタが行っている時
            if (itemStack != null && itemStack.item is ItemWrench && block is IDirectionWrenchAction) {

                val side = MOP.sideHit
                val direction = ForgeDirection.VALID_DIRECTIONS[side]
                val chunkPosition = ChunkPosition(MOP.blockX + direction.offsetX, MOP.blockY + direction.offsetY, MOP.blockZ + direction.offsetZ)
                drawSelectionBox(player, chunkPosition, event.partialTicks, side)
            }
        }
    }

    private fun drawSelectionBox(player: EntityPlayer, chunkPosition: ChunkPosition?, partialTicks: Float, side: Int) {
        GL11.glEnable(GL11.GL_BLEND)
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
        // 線の色 (R, G, B, Alpha)
        GL11.glColor4f(0.5568f, 0.0f, 0.8f, 0.8f)
        //　線の太さ
        GL11.glLineWidth(3.0f)
        GL11.glDisable(GL11.GL_TEXTURE_2D)
        GL11.glDepthMask(false)
        // 線の幅
        val baseBoxlineWidth = 0.002f
        val baseLineWidth = 0.002f

        if (chunkPosition != null) {
            val var8 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks
            val var10 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks
            val var12 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks
            val x = chunkPosition.chunkPosX
            val y = chunkPosition.chunkPosY
            val z = chunkPosition.chunkPosZ
            val ExtensionLineBoard: AxisAlignedBB
            val ExtensionLine: AxisAlignedBB
            val ExtensionLine2: AxisAlignedBB
            when (side) {
                0 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x.toDouble(), y + 1.0, z.toDouble(), x + 1.0, y + 1.0, z + 1.0)
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25, y + 1.0, z.toDouble(), x + 0.75, y + 1.0, z + 1.0)
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x.toDouble(), y + 1.0, z + 0.25, x + 1.0, y + 1.0, z + 0.75)
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
                1 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z.toDouble(), x + 1.0, y.toDouble(), z + 1.0)
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z + 0.25, x + 1.0, y.toDouble(), z + 0.75)
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x + 0.25, y.toDouble(), z.toDouble(), x + 0.75, y.toDouble(), z + 1.0)
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
                2 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z + 1.0, x + 1.0, y + 1.0, z + 1.0)
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25, y.toDouble(), z + 1.0, x + 0.75, y + 1.0, z + 1.0)
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x.toDouble(), y + 0.25, z + 1.0, x + 1.0, y + 0.75, z + 1.0)
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
                3 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z.toDouble(), x + 1.0, y + 1.0, z.toDouble())
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25, y.toDouble(), z.toDouble(), x + 0.75, y + 1.0, z.toDouble())
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x.toDouble(), y + 0.25, z.toDouble(), x + 1.0, y + 0.75, z.toDouble())
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
                4 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x + 1.0, y.toDouble(), z.toDouble(), x + 1.0, y + 1.0, z + 1.0)
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 1.0, y.toDouble(), z + 0.25, x + 1.0, y + 1.0, z + 0.75)
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x + 1.0, y + 0.25, z.toDouble(), x + 1.0, y + 0.75, z + 1.0)
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
                5 -> {
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z.toDouble(), x.toDouble(), y + 1.0, z + 1.0)
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x.toDouble(), y.toDouble(), z + 0.25, x.toDouble(), y + 1.0, z + 0.75)
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x.toDouble(), y + 0.25, z.toDouble(), x.toDouble(), y + 0.75, z + 1.0)
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble(), baseBoxlineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth.toDouble(), baseLineWidth.toDouble(), baseLineWidth.toDouble()).getOffsetBoundingBox(-var8, -var10, -var12))
                }
            }

            GL11.glDepthMask(true)
            GL11.glEnable(GL11.GL_TEXTURE_2D)
            GL11.glDisable(GL11.GL_BLEND)
        }
    }


    private fun drawOutlinedBoundingBox(par1AxisAlignedBB: AxisAlignedBB) {
        val var2 = Tessellator.instance
        var2.startDrawing(3)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ)
        var2.draw()
        var2.startDrawing(3)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ)
        var2.draw()
        var2.startDrawing(1)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ)
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ)
        var2.draw()
    }
}

