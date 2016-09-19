/*
 * 
 */
package antimattaermod.core.Block;

import antimattaermod.core.Block.Ores.CrystalOreBlock;
import antimattaermod.core.Item.CoreItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

/** <h1>CoreBlocks</h1>
 * AntimatterModCoreのブロック追加部分<br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class CoreBlocks {
	private CoreBlocks() {}
	
	/**
	 * 鉱石ブロックタブ
	 */
	public static final CreativeTabs oreBlocks = new CreativeTabs("oreBlocks") {
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.diamond_ore);
		}
	};
		
	//==================================================================================================================
	
	public static Block crystalOreBlock_1 = new CrystalOreBlock(Material.rock, "crystalOreBlock_1", "antimattermodcore:crystaloreblock_1", CoreBlocks.oreBlocks, 2,
			new float[]{5.0F,5.0F}, new byte[]{3,3}, CoreItems.materials, new int[]{10,12}); 
	
	
	//==================================================================================================================
	/**
	 * <h1>addBlock</h1>
	 * GameRegistryへの登録<br>
	 */
	public static void addBlock() {
		GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_1");
	}
	
	
}
