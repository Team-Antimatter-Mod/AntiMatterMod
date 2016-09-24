package antimattaermod.core.common;

import antimattaermod.core.Energy.Transfer.BlockCable;
import antimattaermod.core.Render.OverlayBlockRender;

/** <h1>AntiMatterModCoreProxy</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class AntiMatterModCoreProxy {
	
	public int getOverlayRenderType() {
		return OverlayBlockRender.RenderID;
	}

	public int getCableRenderType(){return BlockCable.RENDER_ID;}

	public void registerRenderer(){}

	public void registerRenderThings(){

	}

	public void registerTileEntitySpecialRender(){

	}
}
