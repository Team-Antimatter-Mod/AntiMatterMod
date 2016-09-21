package antimattaermod.core.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Entity.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by 西村　航 on 2016/09/21.
 */
public class Cable extends BlockContainer {

    public Cable(Material material){
        super(material);
        setBlockName("Cable");
        setHardness(2.0f);
        setResistance(0.0F);
        setCreativeTab(AntiMatterModRegistry.tabMachines);
        setBlockTextureName(AntiMatterModCore.MOD_ID+":"+"textures/blocks/cable");
    }

    public int getRenderType(){
        return -1;
    }

    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1,int var2){
        return new TileEntityCable();
    }
}
