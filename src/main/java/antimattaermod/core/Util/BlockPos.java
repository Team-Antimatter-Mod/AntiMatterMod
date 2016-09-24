package antimattaermod.core.Util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author C6H2Cl2
 */
public class BlockPos {
    private int x,y,z;
    public BlockPos(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public BlockPos getUp(){
        return new BlockPos(x,y+1,z);
    }

    public BlockPos getDown(){
        return new BlockPos(x,y-1,z);
    }

    public BlockPos getNorth(){
        return new BlockPos(x,y,z-1);
    }

    public BlockPos getSouth(){
        return new BlockPos(x,y,z+1);
    }

    public BlockPos getEast(){
        return new BlockPos(x+1,y,z);
    }

    public BlockPos getWest(){
        return new BlockPos(x-1,y,z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Block getBlockFromPos(World world){
        return world.getBlock(x,y,z);
    }

    public TileEntity getTileEntityFromPos(IBlockAccess world){
        return world.getTileEntity(x,y,z);
    }

    public ForgeDirection getBlockDirection(BlockPos tilePos){
        if(getUp().equals(tilePos)){
            return ForgeDirection.UP;
        }else if (getDown().equals(tilePos)){
            return ForgeDirection.DOWN;
        }else if (getNorth().equals(tilePos)){
            return ForgeDirection.NORTH;
        }else if (getSouth().equals(tilePos)){
            return ForgeDirection.SOUTH;
        }else if (getEast().equals(tilePos)){
            return ForgeDirection.EAST;
        }else if (getWest().equals(tilePos)){
            return ForgeDirection.WEST;
        }else {
            return ForgeDirection.UNKNOWN;
        }
    }
}
