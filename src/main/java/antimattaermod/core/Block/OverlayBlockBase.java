package antimattaermod.core.Block;

import antimattaermod.core.Render.OverlayBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

/**
 * Created by Raiti on 2016/09/22.
 *
 * このクラスを継承したクラスはオーバーレイテクスチャが使えます。
 *
 */
public abstract class OverlayBlockBase extends Block{

    /**
     * Blockクラスのコンストラクタといっしょ
     * @param material ブロックのマテリアル
     */
    protected OverlayBlockBase(Material material){
        super(material);
    }

    /**
     * オーバーレイのアイコンを返します
     * @param par 方向
     * @param meta メタデータ
     * @return オーバーレイアイコン
     */
    public abstract IIcon getOverlayIcon(int par, int meta);


    /**
     * OverlayBlockレンダ―IDを返します。
     * 基本的にOverrideしないで。
     * @return レンダ―ID
     */
    @Override
    public int getRenderType() {
        return OverlayBlockRender.RenderID;
    }



}
