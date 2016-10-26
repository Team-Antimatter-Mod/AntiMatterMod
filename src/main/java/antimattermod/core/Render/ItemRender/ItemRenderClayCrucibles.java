package antimattermod.core.Render.ItemRender;

import antimattermod.core.Model.ClayCrucibleModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Created by sora_suke on 2016/10/26.
 * Changed by Raiti-chan on 2016/10/26.
 * 粘土るつぼのインベントリレンダ―
 * @author sora_suke
 */
@SideOnly(Side.CLIENT)
public class ItemRenderClayCrucibles implements IItemRenderer {
	
	private TileEntitySpecialRenderer renderer;
	
	private TileEntity entity;
	
	public ItemRenderClayCrucibles(TileEntitySpecialRenderer renderer, TileEntity entity) {
		this.renderer = renderer;
		this.entity = entity;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
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
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (type == ItemRenderType.ENTITY)
			GL11.glTranslatef(-0.5f,0.0f,-0.5f);
		this.renderer.renderTileEntityAt(this.entity,0.0D,0.0D,0.0D,0.0f);
		
	}
}
