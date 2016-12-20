package antimattermod.core.Render;

import antimattermod.core.Block.IWrenchAction;
import antimattermod.core.Item.tool.ItemWrench;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

/**
 * Created by kojin15.
 */



@SideOnly(Side.CLIENT)
public class RenderWrenchSelectionBox {
    @SubscribeEvent
    public void onRenderSelectionBox(RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        World world = Minecraft.getMinecraft().theWorld;
        MovingObjectPosition MOP = Minecraft.getMinecraft().objectMouseOver;
        Block block = world.getBlock(MOP.blockX, MOP.blockY, MOP.blockZ);

        if ((player != null) && (world != null)) {

            ItemStack itemStack = player.getCurrentEquippedItem();
            // ItemBlockを持っていて、ブロックにポインタが行っている時
            if ((itemStack != null) && (itemStack.getItem() instanceof ItemWrench) && block instanceof IWrenchAction) {

                int side = MOP.sideHit;
                ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[side];
                ChunkPosition chunkPosition = new ChunkPosition(MOP.blockX + direction.offsetX, MOP.blockY + direction.offsetY, MOP.blockZ + direction.offsetZ);
                drawSelectionBox(player, chunkPosition, event.partialTicks, side);
            }
        }
    }

    private void drawSelectionBox(EntityPlayer player, ChunkPosition chunkPosition, float partialTicks, int side) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        // 線の色 (R, G, B, Alpha)
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.8F);
        //　線の太さ
        GL11.glLineWidth(3.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        // 線の幅
        float baseBoxlineWidth = 0.002F;
        float baseLineWidth = 0.002F;

        if (chunkPosition != null) {
            double var8 = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
            double var10 = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
            double var12 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
            int x = chunkPosition.chunkPosX;
            int y = chunkPosition.chunkPosY;
            int z = chunkPosition.chunkPosZ;
            AxisAlignedBB ExtensionLineBoard;
            AxisAlignedBB ExtensionLine;
            AxisAlignedBB ExtensionLine2;
            switch (side) {
                case 0:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x, y + 1.0D, z, x + 1.0D, y + 1.0D, z + 1.0D);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25D, y + 1.0, z, x + 0.75D, y + 1.0D, z + 1.0D);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x, y + 1.0D, z + 0.25D, x + 1.0D, y + 1.0D, z + 0.75D);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
                case 1:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y, z + 1.0D);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x, y, z + 0.25D, x + 1.0D, y, z + 0.75D);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x + 0.25D, y, z, x + 0.75D, y, z + 1.0D);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
                case 2:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x, y, z + 1.0D, x + 1.0D, y + 1.0D, z + 1.0D);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25D, y, z + 1.0D, x + 0.75D, y + 1.0D, z + 1.0D);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x, y + 0.25D, z + 1.0D, x + 1.0D, y + 0.75D, z + 1.0D);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
                case 3:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 0.25D, y, z, x + 0.75D, y + 1.0D, z);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x, y + 0.25D, z, x + 1.0D, y + 0.75D, z);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
                case 4:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x + 1.0D, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x + 1.0D, y, z + 0.25D, x + 1.0D, y + 1.0D, z + 0.75D);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x + 1.0D, y + 0.25D, z, x + 1.0D, y + 0.75D, z + 1.0D);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
                case 5:
                    ExtensionLineBoard = AxisAlignedBB.getBoundingBox(x, y, z, x, y + 1.0D, z + 1.0D);
                    ExtensionLine = AxisAlignedBB.getBoundingBox(x, y, z + 0.25D, x, y + 1.0D, z + 0.75D);
                    ExtensionLine2 = AxisAlignedBB.getBoundingBox(x, y + 0.25D, z, x, y + 0.75D, z + 1.0D);
                    this.drawOutlinedBoundingBox(ExtensionLineBoard.expand(baseBoxlineWidth, baseBoxlineWidth, baseBoxlineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    this.drawOutlinedBoundingBox(ExtensionLine2.expand(baseLineWidth, baseLineWidth, baseLineWidth).getOffsetBoundingBox(-var8, -var10, -var12));
                    break;
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }


    private void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB) {
        Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.draw();
    }
}

