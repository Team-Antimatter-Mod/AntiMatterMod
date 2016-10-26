package antimattermod.core.Item.tool;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import antimattermod.core.Util.AMMToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Raiti-chan on 2016/10/22.
 * AMMのツールベースクラス。基本このクラスを継承しましょう。
 *
 * @author Raiti-chan
 */
public class AMMTool extends ItemTool implements AMMToolBase{
	
	protected final AMMToolMaterial material;
	
	public AMMTool(@NotNull String name, @NotNull String textureName, AMMToolMaterial material, float toolDamage){
		super(toolDamage,material.getToolMaterial(),null);
		this.setCreativeTab(AntiMatterModRegistry.tabTools);
		this.material = material;
		setUnlocalizedName(name);
		setTextureName(textureName);
		this.maxStackSize = 1;
	}
	
	
	/**
	 * テクスチャ名を設定します
	 * @param name テクスチャ名
	 * @return 自身のItemインスタンス
	 */
	@Override
	public Item setTextureName(String name) {
		return super.setTextureName(AntiMatterModCore.MOD_ID+":tool/"+name);
	}
	
	
	@Override
	public float func_150893_a(ItemStack itemStack, Block block) {
		return 1.0F;
	}
	
}
