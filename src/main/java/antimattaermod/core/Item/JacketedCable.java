package antimattaermod.core.Item;

import antimattaermod.core.AntiMatterModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;


public class JacketedCable extends ItemBlock{

    public JacketedCable(Block block){
        super(block);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() ;

    }


}
