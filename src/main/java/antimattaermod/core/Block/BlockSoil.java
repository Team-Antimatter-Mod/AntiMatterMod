package antimattaermod.core.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


/**
 * Created by worldofthetakumi on 2016/10/14.
 */
public class BlockSoil extends Block {

    public BlockSoil(){
        super(Material.clay);
        setBlockName("soilBlock");
        setHardness(2.0f);
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setBlockTextureName(AntiMatterModCore.MOD_ID+":blocks/soilBlock");
    }


}
