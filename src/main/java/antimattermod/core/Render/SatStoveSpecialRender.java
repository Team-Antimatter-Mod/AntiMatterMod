package antimattermod.core.Render;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.Block.TileEntity.TileEntitySatStove;
import antimattermod.core.Model.SatStoveModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by worldofthetakumi on 2016/10/15.
 */
@SideOnly(Side.CLIENT)
public class SatStoveSpecialRender extends TileEntitySpecialRenderer{

    private static final ResourceLocation texture = new ResourceLocation(AntiMatterModCore.MOD_ID +":"+"textures/models/satStove.png");

    private void setRotation(TileEntitySatStove tile, double x, double y, double z, float ff){
        SatStoveModel satStoveModel = new SatStoveModel();

        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
        GL11.glRotatef(180f,0f,0f,1f);

        this.bindTexture(texture);

        GL11.glPushMatrix();
        satStoveModel.renderModel(0.625f);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile,double x,double y,double z,float f){
        setRotation((TileEntitySatStove)tile,x,y,z,f);
    }


}
