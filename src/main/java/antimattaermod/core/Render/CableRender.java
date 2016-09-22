package antimattaermod.core.Render;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.Model.CableModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CableRender extends TileEntitySpecialRenderer {

    private static final ResourceLocation texture = new ResourceLocation(AntiMatterModCore.MOD_ID+":"+"textures/models/Cable.png");

    public CableRender(){
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity,double x,double y,double z,float f){
        CableModel model = new CableModel();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f,(float) y+1.5f,(float) z + 0.5f);
        GL11.glRotatef(180,0f,0f,1f);

        this.bindTexture(texture);

        GL11.glPushMatrix();
        model.renderModel(0.0625f);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
