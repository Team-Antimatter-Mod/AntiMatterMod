package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Util.AMMToolMaterial;
import net.minecraft.item.Item;

/**
 * Created by Raiti-chan on 2016/10/26.
 * AMMのItemToolを継承させないツールクラス。
 * 主にエンチャントの制御用
 *
 * @author Raiti-chan
 */
@SuppressWarnings("WeakerAccess")
public class AMMToolNotToolClass extends Item implements AMMItemBase {
	
	protected final AMMToolMaterial toolMaterial;
	
	protected AMMToolNotToolClass(String name, String textureName, AMMToolMaterial material) {
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.setUnlocalizedName(name);
		this.setTextureName(textureName);
		this.toolMaterial = material;
	}
	
	/**
	 * テクスチャ名を設定します
	 *
	 * @param name テクスチャ名
	 * @return 自身のItemインスタンス
	 */
	@Override
	public Item setTextureName(String name) {
		return super.setTextureName(AntiMatterModCore.MOD_ID + ":tool/" + name);
	}
	
	
}
