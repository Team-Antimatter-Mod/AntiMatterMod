/*
 * 
 */
package antimattaermod.core.Render;

import antimattaermod.core.Block.OverlayBlockBase;
import net.minecraft.init.Blocks;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

/** <h1>OverlayBlockRender</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class OverlayBlockRender implements ISimpleBlockRenderingHandler{
	
	public static final int RenderID = 114514; //自身のレンダ―ID
	

	/*
	 * インベントリでのレンダ―処理
	 */
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

		if(!(block instanceof OverlayBlockBase)) return;
		OverlayBlockBase cblock = (OverlayBlockBase) block;
		Tessellator tessellator = Tessellator.instance;

		//不明
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);

		//描写開始位置を指定。
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		//レンダリング処理
		//石のテクスチャ―
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0, 0, 0, cblock.getBaseIcon(metadata));
		tessellator.draw();
		
		
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
		
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
	}
	
	/*
	 * ワールドでのレンダリング
	 */
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		
		renderer.setOverrideBlockTexture(((OverlayBlockBase)block).getBaseIcon(world,x,y,z));
		
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);//レンダ―の開始位置と終了位置
		renderer.renderStandardBlock(block, x, y, z);//スタンダードな直方体を上の指定に合わせて描写

		renderer.setOverrideBlockTexture(null);//変更したら使い終わったらもとに戻そうね


		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);
		
		return true;
	}
	
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;//ここがfalseだとインベントリのブロックは立体じゃなくなる(つまりアイコンが使える)
	}

	
	@Override
	public int getRenderId() {
		return RenderID;//自身のレンダ―IDを返す
	}
}
