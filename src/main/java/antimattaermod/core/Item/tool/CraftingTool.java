package antimattaermod.core.Item.tool;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/14.
 */
public class CraftingTool extends Item {
	
	
	protected CraftingTool (@NotNull String name, @NotNull String textureName, ToolMaterial material){
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.setFull3D();
		this.setContainerItem(this);
		this.setTextureName(AntiMatterModCore.MOD_ID+":tool/"+textureName);
		this.setUnlocalizedName(name);
	}
	
	protected CraftingTool (@NotNull String name,@NotNull String textureName, int maxUses){
		this.maxStackSize = 1;
		this.setMaxDamage(maxUses);
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.setFull3D();
		this.setContainerItem(this);
		this.setTextureName(AntiMatterModCore.MOD_ID+":tool/"+textureName);
		this.setUnlocalizedName(name);
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
