/*
 * 
 */
package antimattaermod.core.Render;

import org.lwjgl.opengl.GL11;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.Block.Ores.CrystalOreBlock;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/** <h1>OreBlockRender</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class OreBlockRender implements ISimpleBlockRenderingHandler{
	
	public static final int RenderID = AntiMatterModCore.proxy.getNewRenderType();
	
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		
		if(!(block instanceof CrystalOreBlock)) return;
		CrystalOreBlock cblock = (CrystalOreBlock) block;
		Tessellator tessellator = Tessellator.instance;
		
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);
		
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0, 0, 0, cblock.getIcon(0, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0, 0, 0, cblock.getIcon(1, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0, 0, 0, cblock.getIcon(2, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0, 0, 0, cblock.getIcon(3, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0, 0, 0, cblock.getIcon(4, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0, 0, 0, cblock.getIcon(5, metadata));
		tessellator.draw();
		
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0, 0, 0, cblock.getOverlayIcon(0, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0, 0, 0, cblock.getOverlayIcon(1, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0, 0, 0, cblock.getOverlayIcon(2, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0, 0, 0, cblock.getOverlayIcon(3, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0, 0, 0, cblock.getOverlayIcon(4, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0, 0, 0, cblock.getOverlayIcon(5, metadata));
		tessellator.draw();
		
		
		
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
	}
	
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		
		renderer.setOverrideBlockTexture(block.getIcon(0,world.getBlockMetadata(x, y, z)));
		
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.setOverrideBlockTexture(((CrystalOreBlock)block).getOverlayIcon(0,world.getBlockMetadata(x, y, z)));
		
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);
		
		renderer.setOverrideBlockTexture(null);
		
		return true;
	}
	
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	
	@Override
	public int getRenderId() {
		return RenderID;
	}
}
