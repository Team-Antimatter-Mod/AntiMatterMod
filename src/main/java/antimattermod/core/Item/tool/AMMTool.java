package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti-chan on 2016/10/22.
 * AMMのツールベースクラス。基本このクラスを継承しましょう。
 *
 * @author Raiti-chan
 */
public class AMMTool extends Item {
	
	public AMMTool(@NotNull String name, @NotNull String textureName){
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		setUnlocalizedName(name);
		setTextureName(textureName);
		this.maxStackSize = 1;
	}
	
	@Override
	public Item setTextureName(String p_111206_1_) {
		return super.setTextureName(AntiMatterModCore.MOD_ID+":tool/"+p_111206_1_);
	}
}
