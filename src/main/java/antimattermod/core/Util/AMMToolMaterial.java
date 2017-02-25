package antimattermod.core.Util;

import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Created by Raiti-chan on 2016/10/24.
 * AMMのツールマテリアルのEnum
 *
 * @author Raiti-chan
 */
public enum AMMToolMaterial {
	@CraftingToolProperty(maxUse = 10, level = 0)IRON(ToolMaterial.IRON),
	DIAMOND(ToolMaterial.EMERALD),
	//Raitium(3, 250, 8.0F, 3.0F, 14, new ItemStack(AntiMatterModRegistry.ingot_01, 1, 1)),
	//Drantium(3, 250, 6.0F, 2.0F, 22, new ItemStack(AntiMatterModRegistry.ingot_01, 1, 2)),
	//Palazirite(3, 400, 6.0F, 2.0F, 14, new ItemStack(AntiMatterModRegistry.ingot_01, 1, 3)),
	
	;
	//==================================================================================================================
	
	AMMToolMaterial(int harvestLevel, int maxUse, float efficiency, float damage, int enchantAbility) {
		this.toolMaterial = EnumHelper.addToolMaterial(this.toString(), harvestLevel, maxUse, efficiency, damage, enchantAbility);
		this.annotationHandle();
	}
	
	AMMToolMaterial(ToolMaterial material) {
		this.toolMaterial = material;
		this.annotationHandle();
	}
	
	AMMToolMaterial(int harvestLevel, int maxUse, float efficiency, float damage, int enchantAbility, ItemStack repairItem) {
		this(harvestLevel, maxUse, efficiency, damage, enchantAbility);
		this.toolMaterial.setRepairItem(repairItem);
	}
	
	private ToolMaterial toolMaterial;
	
	public ToolMaterial getToolMaterial() {
		return toolMaterial;
	}
	
	private int craftingToolMaxUse;
	public int getCraftingToolMaxUse() {
		return craftingToolMaxUse;
	}
	
	private int craftingToolLevel = -1;
	public int getCraftingToolLevel() {
		return craftingToolLevel;
	}
	
	private void annotationHandle() {
		Class<? extends AMMToolMaterial> materialClass = this.getClass();
		try {
			Field field = materialClass.getField(this.name());
			
			//----------------------------------------------------------------------------------------------------------
			CraftingToolProperty cToolProperty = field.getAnnotation(CraftingToolProperty.class);
			if (cToolProperty != null) {
				this.craftingToolMaxUse = cToolProperty.maxUse();
				this.craftingToolLevel = cToolProperty.level();
			} else this.craftingToolMaxUse = toolMaterial.getMaxUses() / 10;
			//----------------------------------------------------------------------------------------------------------
			
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface CraftingToolProperty {
		int maxUse();
		
		int level();
	}
	
}
