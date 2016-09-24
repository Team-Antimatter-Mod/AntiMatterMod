package antimattaermod.core.Energy.Transfer;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Energy.IAPMachine;
import antimattaermod.core.Util.BlockPos;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCable extends BlockContainer {

    public static final int RENDER_ID = 334;
    public BlockCable(Material material){
        super(material);
        setBlockName("Cable");
        setHardness(2.0f);
        setResistance(0.0F);
        setCreativeTab(AntiMatterModRegistry.tabMachines);
        setBlockTextureName(AntiMatterModCore.MOD_ID+":"+"/blocks/cable");
        //setBlockBounds(0.4f,0f,0f,0.6f,0.1875f,1f);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        searchConnection(world, x, y, z);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        TileEntityCable tile = (TileEntityCable) world.getTileEntity(x,y,z);
        ForgeDirection direction;
        BlockPos tilePos = new BlockPos(tileX,tileY,tileZ);
        BlockPos pos = new BlockPos(x, y, z);
        direction = pos.getBlockDirection(tilePos);
        if(direction == ForgeDirection.UNKNOWN){
            return;
        }
        TileEntity tileEntity = tilePos.getTileEntityFromPos(world);
        tile.setConnection(direction,(tileEntity != null && tileEntity instanceof IAPMachine));
    }

    private void searchConnection(World world, int x, int y, int z){
        TileEntityCable tile = (TileEntityCable) world.getTileEntity(x,y,z);
        BlockPos pos = new BlockPos(x,y,z);
        tile.setConnection(ForgeDirection.UP,pos.getUp().getTileEntityFromPos(world) instanceof IAPMachine);
        tile.setConnection(ForgeDirection.DOWN,pos.getDown().getTileEntityFromPos(world) instanceof IAPMachine);
        tile.setConnection(ForgeDirection.NORTH,pos.getNorth().getTileEntityFromPos(world) instanceof IAPMachine);
        tile.setConnection(ForgeDirection.SOUTH,pos.getSouth().getTileEntityFromPos(world) instanceof IAPMachine);
        tile.setConnection(ForgeDirection.EAST,pos.getEast().getTileEntityFromPos(world) instanceof IAPMachine);
        tile.setConnection(ForgeDirection.WEST,pos.getWest().getTileEntityFromPos(world) instanceof IAPMachine);
    }

    //たぶん自作のRender使うときはこれ
    public int getRenderType(){
        return RENDER_ID;
    }

    //透明化
    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }

    //Entityの作成
    @Override
    public TileEntity createNewTileEntity(World var1,int var2){
        return new TileEntityCable();
    }
}
