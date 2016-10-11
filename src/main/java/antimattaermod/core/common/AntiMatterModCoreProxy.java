package antimattaermod.core.common;

import antimattaermod.core.Energy.Transfer.BlockCable;
import antimattaermod.core.Render.OverlayBlockRender;
import net.minecraft.world.World;

/** <h1>AntiMatterModCoreProxy</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class AntiMatterModCoreProxy {
	
	public int getNewRenderType(){
		return -1;
	}
	
	public void registerRenderer(){}

	public void registerRenderThings(){}

	public void registerTileEntitySpecialRender(){

	}
	
	public World getClientWorld(){
		return null;
	}
}
