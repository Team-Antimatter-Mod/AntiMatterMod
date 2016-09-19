package antimattaermod.core.Energy.Generator.Block;

import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class BlockFurnaceGenerator extends BlockContainer {
    protected BlockFurnaceGenerator() {
        super(Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityFurnaceGenerator();
    }
}
