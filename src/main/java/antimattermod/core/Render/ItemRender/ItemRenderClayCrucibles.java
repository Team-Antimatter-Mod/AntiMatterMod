package antimattermod.core.Render.ItemRender;

import antimattermod.core.Model.ClayCrucibleModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by sora_suke on 2016/10/26.
 */
@SideOnly(Side.CLIENT)
public class ItemRenderClayCrucibles implements IItemRenderer{

    private final ResourceLocation resource = new ResourceLocation("antimattermodcore:textures/models/claycrucible.png");
    private ClayCrucibleModel model;

    public ItemRenderClayCrucibles(){
        model = new ClayCrucibleModel();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        switch(type)
        {
            case INVENTORY:
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case FIRST_PERSON_MAP:
                return true;
            default:
                return false;
        }
    }
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return false;
    }
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch(type)
        {
            case INVENTORY:
            case ENTITY:
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case FIRST_PERSON_MAP:
            {
                GL11.glPushMatrix();

                float scala = 2.5F;

                GL11.glScalef(scala, scala, scala);

                GL11.glRotatef(0F, 1F, 0.0F, 0.0F);
                GL11.glRotatef(0F, 0.0F, -0F, 0.0F);
                GL11.glRotatef(0F, 0.0F, 0.0F, -0F);

                GL11.glTranslatef(-0F, -0F, -0F);

                Minecraft.getMinecraft().renderEngine.bindTexture(resource);

                model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);

                GL11.glPopMatrix();
            }
            default:
                break;
        }
    }
}
