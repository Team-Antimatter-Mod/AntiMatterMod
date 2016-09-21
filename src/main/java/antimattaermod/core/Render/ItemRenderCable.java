package antimattaermod.core.Render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by 西村　航 on 2016/09/22.
 */
public class ItemRenderCable implements IItemRenderer {

    TileEntitySpecialRenderer renderer;
    private TileEntity entity;

    public ItemRenderCable(TileEntitySpecialRenderer renderer, TileEntity entity){
        this.entity = entity;
        this.renderer = renderer;
    }

    @Override
    public boolean handleRenderType(ItemStack stack,ItemRenderType type){
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type,ItemStack stack,ItemRendererHelper helper){
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type,ItemStack stack,Object... data){
        if (type == ItemRenderType.ENTITY)
            GL11.glTranslatef(-0.5f,0.0f,-0.5f);
        this.renderer.renderTileEntityAt(this.entity,0.0D,0.0D,0.0D,0.0f);
    }
}
