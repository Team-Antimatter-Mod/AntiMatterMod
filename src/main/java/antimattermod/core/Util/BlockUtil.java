package antimattermod.core.Util;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.Block.SimpleBlock;
import antimattermod.core.Block.SimpleMetaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by C6H2Cl2 on 2016/09/19.
 * 無機能なブロックを簡易追加する関数クラス
 */
public class BlockUtil {

    /**
     * 無機能なブロックを追加します
     * @param name ブロック名
     * @param textureName テクスチャ―名
     * @param material 材質
     * @param hardness 硬さ
     * @param resistance 耐爆値
     * @return Blockオブジェクト
     */
    public static Block CreateBlock(@NotNull String name, @Nullable String textureName, @NotNull Material material, float hardness, float resistance){
        Block block = new SimpleBlock(material);
        block.setHardness(hardness);
        block.setResistance(resistance);
        block.setBlockName(name);
        if(textureName == null || textureName.isEmpty()){
            textureName = name;
        }
        textureName = AntiMatterModCore.MOD_ID + textureName;
        block.setBlockTextureName(textureName);
        return block;
    }

    /**
     * 無機能なメタデータ持ちブロックを追加します。
     * メタ値による硬さ、耐爆値の変更はできません
     * @param name ブロック名
     * @param textureName テクスチャ―名
     * @param material 素材名
     * @param tab 追加タブ
     * @param maxMeta メタデータ最大値 1~16
     * @param hardness ブロックの硬さ
     * @param resistance 耐爆値
     * @return Blockオブジェクト
     */
    public static Block CreateBlock(@NotNull String name, @NotNull String textureName, @NotNull Material material, @Nullable CreativeTabs tab, int maxMeta, float hardness, float resistance){
        Block block = new SimpleMetaBlock(material,maxMeta);
        block.setHardness(hardness);
        block.setResistance(resistance);
        block.setBlockName(name);
        if(textureName == null || textureName.isEmpty()){
            textureName = name;
        }
        if(tab != null) block.setCreativeTab(tab);
        textureName = AntiMatterModCore.MOD_ID + ":" + textureName;
        block.setBlockTextureName(textureName);
        return block;
    }
}
