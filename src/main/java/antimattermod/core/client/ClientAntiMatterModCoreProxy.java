/*
 * 
 */
package antimattermod.core.client;

import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Block.TileEntity.TileEntitySatStove;
import antimattermod.core.Energy.Transfer.TileEntityCable;
import antimattermod.core.Render.CableRender;
import antimattermod.core.Render.ClayCrucibleSpecialRender;
import antimattermod.core.Render.ItemRender.ItemRenderCable;
import antimattermod.core.Render.ItemRender.ItemRenderClayCrucibles;
import antimattermod.core.Render.OverlayBlockRender;
import antimattermod.core.Render.SatStoveSpecialRender;
import antimattermod.core.common.AntiMatterModCoreProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

/** <h1>ClientAntiMatterModCoreProxy</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
@SuppressWarnings("unused")
public class ClientAntiMatterModCoreProxy extends AntiMatterModCoreProxy {
	
	/**
	 * 空いてるレンダ―IDを取得
	 * @return 空いてるレンダ―ID
	 */
	@Override
	public int getNewRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void registerRenderThings(){
		
		//オーバーレイブロックレンダ―
		RenderingRegistry.registerBlockHandler(new OverlayBlockRender());
		//粘土るつぼレンダ―
		TileEntitySpecialRenderer clayCrucibleSpecialRender = new ClayCrucibleSpecialRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityClayCrucible.class,clayCrucibleSpecialRender);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AntiMatterModRegistry.clayCrucible), new ItemRenderClayCrucibles(clayCrucibleSpecialRender,new TileEntityClayCrucible()));

		//土かまどレンダー
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySatStove.class,new SatStoveSpecialRender());
		
		//ケーブルレンダ―
		TileEntitySpecialRenderer cableRender = new CableRender();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class,cableRender);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AntiMatterModRegistry.cable),new ItemRenderCable(cableRender,new TileEntityCable()));
	}
	
	@Override
	public void registerTileEntitySpecialRender(){

	}
	
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
