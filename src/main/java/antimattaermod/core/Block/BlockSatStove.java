package antimattaermod.core.Block;

import antimattaermod.core.Block.TileEntity.TileEntitySatStove;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by worldofthetakumi on 2016/10/13.
 */
public class BlockSatStove extends BlockContainer {

    public BlockSatStove(){
        super(Material.rock);
        setBlockName("satStove");
        setHardness(2.0f);
        setResistance(0.0f);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType(){
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity  createNewTileEntity(World var1, int var2){
        return new TileEntitySatStove();
    }
}
