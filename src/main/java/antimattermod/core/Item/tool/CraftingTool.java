package antimattermod.core.Item.tool;

import antimattermod.core.Util.AMMToolMaterial;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Raiti-chan on 2016/10/14.
 * クラフティングで耐久値が減るツールのベース
 * @author Raiti-chan
 */
@SuppressWarnings("WeakerAccess")
public abstract class CraftingTool extends AMMToolNotToolClass {
	
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List informationList, boolean advanced){
		informationList.add(getMaxDamage()-getDamage(itemStack)+"/"+getMaxDamage());
	}

	/*@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}*/
}
