package antimattaermod.core.Render;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.Block.TileEntity.TileEntitySatStove;
import antimattaermod.core.Model.SatStoveModel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by worldofthetakumi on 2016/10/15.
 */
public class SatStoveSpecialRender extends TileEntitySpecialRenderer{

    private static final ResourceLocation texture = new ResourceLocation(AntiMatterModCore.MOD_ID +":"+"textures/models/satStove.png");

    private void setRotation(TileEntitySatStove tile, double x, double y, double z, float ff){
        SatStoveModel satStoveModel = new SatStoveModel();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
        GL11.glRotatef(180f,0f,0f,1f);

        this.bindTexture(texture);
    }

    @Override
    public void renderTileEntityAt(TileEntity tile,double x,double y,double z,float f){
        setRotation((TileEntitySatStove)tile,x,y,z,f);
    }


}
