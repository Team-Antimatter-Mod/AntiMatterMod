/*
 * 
 */
package antimattaermod.core.client;

import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Energy.Transfer.TileEntityCable;
import antimattaermod.core.Render.CableRender;
import antimattaermod.core.Render.ItemRenderCable;
import antimattaermod.core.Render.OverlayBlockRender;
import antimattaermod.core.common.AntiMatterModCoreProxy;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/** <h1>ClientAntiMatterModCoreProxy</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class ClientAntiMatterModCoreProxy extends AntiMatterModCoreProxy {
	
	@Override
	public int getOverlayRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderer() {
		RenderingRegistry.registerBlockHandler(new OverlayBlockRender());
	}

	public void registerRenderThings(){
		TileEntitySpecialRenderer renderer = new CableRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class,renderer);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AntiMatterModRegistry.cable),new ItemRenderCable(renderer,new TileEntityCable()));
	}

	public void registerTileEntitySpecialRender(){

	}
	
}
