package antimattaermod.core.Block;

import antimattaermod.core.Render.OverlayBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

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
     * ベースのアイコンを返します
     * @param world ワールド
     *
     * @return ベースレイアイコン
     */
    public abstract IIcon getBaseIcon(IBlockAccess world, int x, int y, int z);
    
    /**
     * ベースアイコンを返します
     * @param meta メタデータ
     * @return ベースアイコン
     */
    public abstract IIcon getBaseIcon(int meta);


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
