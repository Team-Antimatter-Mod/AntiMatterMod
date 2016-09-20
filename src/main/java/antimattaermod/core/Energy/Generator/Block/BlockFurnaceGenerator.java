package antimattaermod.core.Energy.Generator.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class BlockFurnaceGenerator extends BlockContainer {
    public BlockFurnaceGenerator() {
        super(Material.rock);
        //他modとの競合回避でAPつけた
        setBlockName("furnaceGeneratorAP");
        setBlockTextureName("antimattermod:furnaceGenerator");
        setHardness(50f);
        setResistance(50f);
        setHarvestLevel("pickaxe",3);
        setCreativeTab(AntiMatterModRegistry.tabMachines);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(player.getHeldItem() == null){
            return false;
        }
        int fuelVal = GameRegistry.getFuelValue(player.getHeldItem());
        if(fuelVal < 1600){
            return false;
        }
        fuelVal = ((TileEntityFurnaceGenerator)world.getTileEntity(x,y,z)).addFuel(fuelVal);
        if(fuelVal >= 1600){
            player.getHeldItem().stackSize = fuelVal / 1600;
        }else {
            player.getHeldItem().stackSize = 0;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityFurnaceGenerator();
    }
}
