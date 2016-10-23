package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/14.
 */
public class CraftingTool extends AMMTool {
	
	
	protected CraftingTool (@NotNull String name, @NotNull String textureName, ToolMaterial material){
		super(name,textureName);
		this.setMaxDamage(material.getMaxUses());
		this.setFull3D();
		this.setContainerItem(this);
	}
	
	protected CraftingTool (@NotNull String name,@NotNull String textureName, int maxUses){
		super(name,textureName);
		this.setMaxDamage(maxUses);
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
