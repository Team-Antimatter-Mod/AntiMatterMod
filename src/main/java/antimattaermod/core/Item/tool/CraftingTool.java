package antimattaermod.core.Item.tool;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Raiti on 2016/10/14.
 */
public class CraftingTool extends Item {
	
	
	protected CraftingTool (ToolMaterial material){
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.setFull3D();
		this.setContainerItem(this);
	}
	
	protected CraftingTool (int maxUses){
		this.maxStackSize = 1;
		this.setMaxDamage(maxUses);
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.setFull3D();
		this.setContainerItem(this);
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_) {
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		itemStack.setItemDamage(itemStack.getItemDamage()+1);
		return itemStack;
	}
	
}
