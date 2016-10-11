package antimattaermod.core.Energy.Machine;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class BlockAlloySmelter extends BlockContainer {
    private final int tier;
    public BlockAlloySmelter(int tier) {
        super(Material.rock);
        this.tier = tier;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileAlloySmelter(tier);
    }
}
