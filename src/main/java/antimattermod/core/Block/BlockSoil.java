package antimattermod.core.Block;

import antimattermod.core.AntiMatterModCore;
import antimattermod.core.AntiMatterModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;


/**
 * Created by worldofthetakumi on 2016/10/14.
 */
public class BlockSoil extends Block {

    public BlockSoil() {
        super(Material.clay);
        setBlockName("soilBlock");
        setHardness(2.0f);
        setCreativeTab(AntiMatterModRegistry.tabMaterials);
        setBlockTextureName(AntiMatterModCore.MOD_ID + ":blocks/soilBlock");
        setHarvestLevel("shovel", 1);
        setStepSound(soundTypeGravel);
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        //手持ちのアイテムを取得する、nullの場合はelseのほうに飛ぶ
        if (player.getHeldItem() != null && player.getHeldItem().getItem() == Items.stone_shovel){
            //ブロック配置
            world.setBlock(x,y,z,AntiMatterModRegistry.satStove);
            //粘土ドロップ
            player.dropItem(Items.clay_ball,1);
            return false;
        }else{
            //いつもの破壊
            return super.removedByPlayer(world,player,x,y,z,willHarvest);
        }
    }

}