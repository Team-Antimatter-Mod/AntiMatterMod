/*
 * 
 */
package antimattermod.core.client;

import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Block.TileEntity.TileEntityClayCrucible;
import antimattermod.core.Block.TileEntity.TileEntitySatStove;
import antimattermod.core.Mob.EntityDeveloperBoss;
import antimattermod.core.Mob.model.ModelDeveloperBoss;
import antimattermod.core.Mob.render.RenderDeveloperBoss;
import antimattermod.core.Render.*;
import antimattermod.core.Render.ItemRender.ItemRenderClayCrucibles;
import antimattermod.core.common.AntiMatterModCoreProxy;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

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

		//レンチ使用時の補助線
		MinecraftForge.EVENT_BUS.register(new RenderWrenchSelectionBox());

		RenderingRegistry.registerEntityRenderingHandler(EntityDeveloperBoss.class, new RenderDeveloperBoss());

		//MinecraftForgeClient.registerItemRenderer(AntiMatterModRegistry.hammer_01, new ToolDamageRender());

	}
	
	@Override
	public void registerTileEntitySpecialRender(){

	}
	
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
}
