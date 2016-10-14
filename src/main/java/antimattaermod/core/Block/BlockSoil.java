package antimattaermod.core.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Block.Ores.AMMOreBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static net.minecraft.item.EnumAction.block;


/**
 * Created by worldofthetakumi on 2016/10/14.
 */
public class BlockSoil extends Block{

    public BlockSoil(){
        super(Material.clay);
        setBlockName("soilBlock");
        setHardness(2.0f);
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setBlockTextureName(AntiMatterModCore.MOD_ID+":blocks/soilBlock");
        setHarvestLevel("shovel",1);
        setStepSound(soundTypeGravel);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z,boolean willHarvest){
        if(player.getHeldItem() == new ItemStack(Items.stone_shovel)){
            return super.removedByPlayer(world,player,x,y,z,willHarvest);
        }
        world.setBlock(x,y,z,AntiMatterModRegistry.satStove);
        if(player.capabilities.isCreativeMode){
            return super.removedByPlayer(world,player,x,y,z,willHarvest);
        }

        return false;
    }

}
