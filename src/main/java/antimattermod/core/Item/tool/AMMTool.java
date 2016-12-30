package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Util.AMMToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Raiti-chan on 2016/10/22.
 * AMMのツールベースクラス。基本このクラスを継承しましょう。
 *
 * @author Raiti-chan
 */
@SuppressWarnings("WeakerAccess")
public class AMMTool extends ItemTool implements AMMItemBase {

	public static IIcon[] icons = new IIcon[9];
	
	protected final AMMToolMaterial material;
	
	protected AMMTool(@NotNull String name, @NotNull String textureName, AMMToolMaterial material, float toolDamage){
		super(toolDamage,material.getToolMaterial(),null);
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.material = material;
		this.setUnlocalizedName(name);
		this.setTextureName(textureName);
		this.maxStackSize = 1;
	}
	
	
	/**
	 * テクスチャ名を設定します
	 * @param name テクスチャ名
	 * @return 自身のItemインスタンス
	 */
	@Override
	public Item setTextureName(String name) {
		return super.setTextureName(AntiMatterModCore.MOD_ID+":item/tool/"+name);
	}
	
	
	@Override
	public float func_150893_a(ItemStack itemStack, Block block) {
		return 1.0F;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		for (int i = 0;i < icons.length; i++) {
			icons[i] = register.registerIcon(AntiMatterModCore.MOD_ID + ":damage/DURABILITY_BAR_" + i);
		}
	}

	/*@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return false;
	}*/
}
