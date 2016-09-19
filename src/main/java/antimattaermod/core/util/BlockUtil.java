package antimattaermod.core.util;

import antimattaermod.core.Block.SimpleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
        textureName = "antimattermod:" + textureName;
        block.setBlockTextureName(textureName);
        return block;
    }
}
