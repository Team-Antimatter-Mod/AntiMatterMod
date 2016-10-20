package antimattermod.core.Item.ItemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by 西村　航 on 2016/09/21.
 */
public class CableItemBlock extends ItemBlock {

    public CableItemBlock(Block block){
        super(block);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName();

    }
}
