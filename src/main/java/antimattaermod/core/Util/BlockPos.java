package antimattaermod.core.Util;

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
}
