package antimattermod.core.Item.tool;

import antimattermod.core.Util.AMMToolMaterial;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti on 2016/10/14.
 */
public class CraftingTool extends AMMToolNotToolClass {
	
	protected CraftingTool(@NotNull String name, @NotNull String textureName, AMMToolMaterial material) {
		super(name, textureName, material);
		this.setMaxDamage(material.getCraftingToolMaxUse());
		this.setFull3D();
		this.setContainerItem(this);
	}
	
	public int getToolLevel() {
		return this.toolMaterial.getCraftingToolLevel();
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack p_77653_1_) {
		return super.getItemStackDisplayName(p_77653_1_) + ChatFormatting.GOLD + " [level:" + getToolLevel() + "]" + ChatFormatting.WHITE;
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack p_77630_1_) {
		return false;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		itemStack.setItemDamage(itemStack.getItemDamage() + 1);
		return itemStack;
	}
	
}
