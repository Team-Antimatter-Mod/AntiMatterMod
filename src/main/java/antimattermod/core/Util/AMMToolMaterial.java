package antimattermod.core.Util;

import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Raiti-chan on 2016/10/24.
 * AMMのツールマテリアルのEnum
 *
 * @author Raiti-chan
 */
public enum AMMToolMaterial {
	Raitium(3,200,6.0F,3.0F,14,new ItemStack(AntiMatterModRegistry.ingot_01,1,1)),
	;
	

	private ToolMaterial toolMaterial;
	
	AMMToolMaterial(int harvestLevel, int maxUse, float efficiency, float damage, int enchantAbility) {
		this.toolMaterial = EnumHelper.addToolMaterial(this.toString(),harvestLevel,maxUse,efficiency,damage,enchantAbility);
	}
	
	AMMToolMaterial(int harvestLevel, int maxUse, float efficiency, float damage, int enchantAbility, ItemStack repairItem) {
		this.toolMaterial = EnumHelper.addToolMaterial(this.toString(),harvestLevel,maxUse,efficiency,damage,enchantAbility);
		this.toolMaterial.setRepairItem(repairItem);
	}
	
	public ToolMaterial getToolMaterial() {
		return toolMaterial;
	}
}
