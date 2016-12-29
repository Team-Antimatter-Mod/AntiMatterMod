package antimattermod.core.common;

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

	public void registerClientInfo(){}

	public void registerRenderer(){}

	public void registerRenderThings(){}
	
	@SuppressWarnings("unused")
	public void registerTileEntitySpecialRender(){

	}
	
	@SuppressWarnings("unused")
	public World getClientWorld(){
		return null;
	}
}
