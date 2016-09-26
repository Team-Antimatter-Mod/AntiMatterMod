/*
 * 
 */
package antimattaermod.core.Render;

import antimattaermod.core.Block.OverlayBlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

import antimattaermod.core.AntiMatterModCore;
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
		tessellator.setNormal(0.0F, -1.0F, 0.0F);//不明
		renderer.renderFaceYNeg(block, 0, 0, 0, cblock.getIcon(0, metadata) != null ? cblock.getIcon(0,metadata): Blocks.stone.getIcon(0,0));//数値は描写座標。実際に変えてみたらわかる1
		tessellator.draw();//描写

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0, 0, 0, cblock.getIcon(1, metadata) != null ? cblock.getIcon(1,metadata): Blocks.stone.getIcon(0,0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0, 0, 0, cblock.getIcon(2, metadata) != null ? cblock.getIcon(2,metadata): Blocks.stone.getIcon(0,0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0, 0, 0, cblock.getIcon(3, metadata) != null ? cblock.getIcon(3,metadata): Blocks.stone.getIcon(0,0));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0, 0, 0, cblock.getIcon(4, metadata) != null ? cblock.getIcon(4,metadata): Blocks.stone.getIcon(0,0));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0, 0, 0, cblock.getIcon(5, metadata) != null ? cblock.getIcon(5,metadata): Blocks.stone.getIcon(0,0));
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
	
	/*
	 * ワールドでのレンダリング
	 */
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		
		if(block.getIcon(0,world.getBlockMetadata(x,y,z)) == null){
			IIcon icon;
			
			if(world.getBlock(x,y+1,z) != Blocks.air) {
				
			}else if(world.getBlock(x+1,y,z) != Blocks.air) {
				
			}else if(world.getBlock(x-1,y,z) != Blocks.air) {
				
			}else if(world.getBlock(x,y,z+1) != Blocks.air) {
				
			}else if(world.getBlock(x,y,z-1) != Blocks.air) {
				
			}else if(world.getBlock(x,y-1,z) != Blocks.air) {
				
			}
			//renderer.setOverrideBlockTexture(Blocks.stone.getIcon(0,0));
		}
		renderer.setOverrideBlockTexture(world.getBlock(x,y-1,x).getIcon(0,world.getBlockMetadata(x,y-1,z)));
		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);//レンダ―の開始位置と終了位置
		renderer.renderStandardBlock(block, x, y, z);//スタンダードな直方体を上の指定に合わせて描写

		renderer.setOverrideBlockTexture(((OverlayBlockBase)block).getOverlayIcon(0,world.getBlockMetadata(x, y, z)));//描写テクスチャを変更


		renderer.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderer.renderStandardBlock(block, x, y, z);

		renderer.setOverrideBlockTexture(null);//変更したら使い終わったらもとに戻そうね
		
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
