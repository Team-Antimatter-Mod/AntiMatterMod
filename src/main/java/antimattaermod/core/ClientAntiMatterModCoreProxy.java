/*
 * 
 */
package antimattaermod.core;

import antimattaermod.core.Render.OreBlockRender;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

/** <h1>ClientAntiMatterModCoreProxy</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class ClientAntiMatterModCoreProxy extends AntiMatterModCoreProxy{
	
	@Override
	public int getNewRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new OreBlockRender());
	}
	
}
