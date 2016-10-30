package antimattermod.core.Render;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.ClayCrucible;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Model.ClayCrucibleModel;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by Raiti-chan on 2016/10/15.
 * 粘土るつぼのレンダ―
 * @author Raiti-chan
 */
public class ClayCrucibleSpecialRender extends TileEntitySpecialRenderer {
	
	private static final ResourceLocation texture = new ResourceLocation(AntiMatterModCore.MOD_ID +":"+"textures/models/claycrucible.png");
	
	
	private void setRotation(TileEntityClayCrucible tile, double x, double y, double z, float ff){
		ClayCrucibleModel model = new ClayCrucibleModel();
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
		GL11.glRotatef(180f,0f,0f,1f);
		
		this.bindTexture(texture);
		
		GL11.glPushMatrix();
		model.renderModel(0.0625f);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
		
		if(tile.getStack() == null) return;
		//==============================================================================================================
		//鉱石の表示
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.75F);
		GL11.glTranslatef((float)x, (float)y + 0.5F, (float)z);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
		
		IIcon iicon;
		switch (tile.getState()){
			case NONE:
			case HEATING:
			default:
				iicon = Blocks.iron_block.getIcon(0,0);
				break;
			case MELTED:
				iicon = ((ClayCrucible)AntiMatterModRegistry.clayCrucible).meltedIron;
				break;
			case SOLIDIFIED:
				iicon = Blocks.coal_block.getIcon(0,0);
				break;
		}
		float f14 = iicon.getMinU();
		float f15 = iicon.getMaxU();
		float f4 = iicon.getMinV();
		float f5 = iicon.getMaxV();
		
		this.bindTexture(TextureMap.locationBlocksTexture);
		
		double high = 0.5-(((double) tile.getStack().stackSize/(double) tile.getMaxOres())*12D/16D);//6で最大->2~12
		double d = 3D/16D;
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		tessellator.addVertexWithUV(0.0D+d, high, -1.0D+d, (double)f15, (double)f4);
		tessellator.addVertexWithUV(1.0D-d, high, -1.0D+d, (double)f14, (double)f4);
		tessellator.addVertexWithUV(1.0D-d, high, 0.0-d, (double)f14, (double)f5);
		tessellator.addVertexWithUV(0.0D+d, high, 0.0-d, (double)f15, (double)f5);
		tessellator.draw();
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		setRotation((TileEntityClayCrucible) tile,x,y,z,f);
		
	}
	
}
