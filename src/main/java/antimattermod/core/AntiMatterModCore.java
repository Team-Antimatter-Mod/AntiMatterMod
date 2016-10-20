package antimattermod.core;

import antimattermod.core.command.Createsphere;
import antimattermod.core.command.ExclusiveDeleteBlock;
import antimattermod.core.common.AntiMatterModCoreProxy;
import antimattermod.core.crafting.OreDictionaryRegister;
import antimattermod.core.crafting.RecipeRegister;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * AntiMatterModCora MainClass
 *
 * @author Raiti, C6H2Cl2
 * @version 1.0.0
 */
@Mod(modid = AntiMatterModCore.MOD_ID,useMetadata = true,dependencies = "required-after:Forge@[10.13.4.1558,)")
public class AntiMatterModCore {

	public static final String MOD_ID = "AntiMatterModCore";
	public static final String MOD_NAME = "AntiMatterMod Core";
	public static final String MOD_VERSION = "1.0.0";

	@Mod.Metadata
	public static ModMetadata modMetadata;
	@SidedProxy(clientSide = "antimattaermod.core.client.ClientAntiMatterModCoreProxy", serverSide = "antimattaermod.core.common.AntiMatterModCoreProxy")
	public static AntiMatterModCoreProxy proxy;
	
	@Mod.EventHandler
	@SuppressWarnings("unused")
	public void preinit(FMLPreInitializationEvent event) {
		loadMeta(modMetadata);
        AntiMatterModRegistry.registerPreInit(event);
		OreDictionaryRegister.OreDictionaryRegisterPreInit(event);
		
	}
	
	@Mod.EventHandler
	@SuppressWarnings("unused")
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderer();
		AntiMatterModRegistry.registerInit(event);
		RecipeRegister.beforeRemoveRecipeinit(event);
		RecipeRegister.RecipeRegisterInit(event);
		RecipeRegister.afterRemoveRecipeinit(event);
	}
	
	@Mod.EventHandler
	@SuppressWarnings("unused")
	public void posinit(FMLPostInitializationEvent event) {
		AntiMatterModRegistry.registerPostInit(event);
	}
	
	@Mod.EventHandler
	@SuppressWarnings("unused")
	public void serverStarting(FMLServerStartingEvent event){
		event.registerServerCommand(new ExclusiveDeleteBlock());
		event.registerServerCommand(new Createsphere());
		
	}
	
	
	private void loadMeta(ModMetadata metadata){
		metadata.modId = MOD_ID;
		metadata.name = MOD_NAME;
		metadata.version = MOD_VERSION;
		metadata.authorList.add("C6H2Cl2");
		metadata.authorList.add("Raiti-Chan");
		metadata.authorList.add("Kojin15");
		metadata.authorList.add("Worldofthetakumi");
        metadata.authorList.add("Sora-Suke");
		metadata.description = "Make Anti-Matter in Minecraft!";
        metadata.autogenerated = false;
	}
}