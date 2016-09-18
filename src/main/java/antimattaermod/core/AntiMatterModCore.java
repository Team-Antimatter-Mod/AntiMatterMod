package antimattaermod.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/** <h1>AntiMatterModCore</h1>
 * AntiMatterModCora MainClass<br>
 * 
 * @author Raiti
 * @version 1.0.0
 */
@Mod(modid = AntiMatterModCore.MOD_ID, name = AntiMatterModCore.MOD_NAME, version = AntiMatterModCore.MOD_VERSION)
public class AntiMatterModCore {
	
	/**
	 * ModID
	 */
	public static final String MOD_ID = "AntiMatterModCore";
	/**
	 * ModName
	 */
	public static final String MOD_NAME = "AntiMatterMod Core";
	/**
	 * ModVersion
	 */
	public static final String MOD_VERSION = "1.0.0";
	
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		// TODO: アイテム追加など
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO: GUIハンドラなどの設定
	}
	
	@Mod.EventHandler
	public void posinit(FMLPostInitializationEvent event) {
		// TODO: 知らん
	}
	
}
