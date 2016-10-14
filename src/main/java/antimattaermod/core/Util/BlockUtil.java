package antimattaermod.core.Util;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.Block.SimpleBlock;
import antimattaermod.core.Block.SimpleMetaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by C6H2Cl2 on 2016/09/19.
 */
public class BlockUtil {
    
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
