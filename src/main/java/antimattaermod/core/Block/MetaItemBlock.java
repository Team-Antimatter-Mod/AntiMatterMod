/*
 * 
 */
package antimattaermod.core.Block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/** <h1>MetaItemBlock</h1>
 * <br>
 * @author Raiti
 * @version 1.0.0
 * 
 */
public class MetaItemBlock extends ItemBlock{
	
	public MetaItemBlock(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		
	}
	
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "_" + stack.getItemDamage();
		
	}
	
}
